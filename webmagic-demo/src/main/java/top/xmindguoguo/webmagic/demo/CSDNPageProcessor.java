package top.xmindguoguo.webmagic.demo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: GithubRepoPageProcessor
 * @author: 于国帅
 * @Description:
 * @Date: 2020/11/23 18:54
 * @Version: v1.0
 */
public class CSDNPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {

        String pageLevel = page.getRequest().getExtra("pageLevel") == null ? "" : String.valueOf(page.getRequest().getExtra("pageLevel"));
        if (StringUtils.isBlank(pageLevel)) {
            pageLevel = "list";
        }
        switch (pageLevel) {
            case "list":
                listProcess(page);
                break;
            case "details":
                detailsProcess(page);
                break;
        }
        //结束了的话 输出数据

    }

    /**
     * 抓取详情
     *
     * @param page
     */
    private void detailsProcess(Page page) {
        Html html = page.getHtml();
        //抓取文章标题
        String title = html.$("#articleContentId").xpath("//h1/text()").get();
        page.putField("title", title);

        //抓取文章内容
//        String content  =  html.$(" #article_content").xpath("//allText()").get();

        //抓取文章时间


        BlogModel model = new BlogModel();
        model.setName(String.valueOf(page.getRequest().getExtra("name")));
//        model.setContent(content);
        model.setTitle(title);
        System.err.println(JSON.toJSONString(model));

    }

    /**
     * 抓取list
     *
     * @param page
     */
    private void listProcess(Page page) {
        Html html = page.getHtml();
        //抓取名称 #uid > span
        String name = html.$("#uid > span ").xpath("//span/text()").get();
        System.err.println(name);

        //码龄6年 #asideProfile > div.profile-intro.d-flex > div.user-info.d-flex.flex-column.profile-intro-name-box > div.profile-intro-name-boxFooter > span.personal-home-page.personal-home-years
        String maLing = html.$("#asideProfile > div.profile-intro.d-flex > div.user-info.d-flex.flex-column.profile-intro-name-box > div.profile-intro-name-boxFooter > span.personal-home-page.personal-home-years").xpath("//span/text()").get();
        System.err.println(maLing);

        //热门文章#asideHotArticle > h3
        String hotPage = html.$("#asideHotArticle > h3 ").xpath("//h3/text()").get();
        System.err.println(hotPage);

        //TA的主页
        String zhuYe = html.$("#asideProfile > div.profile-intro-name-boxOpration > div:nth-child(1) > a ").xpath("//a/text()").get();
        System.err.println(zhuYe);

        String nextUrl = html.$(".article-item-box h4 a").xpath("//a/@href").get();
        Request request = new Request(nextUrl);

        request.putExtra("pageLevel", "details");
        request.putExtra("name", name);
//test

        //抓取列表的url  .article-item-box h4 a
//        List<String> hrefList = html.$(".article-item-box h4 a").xpath("//a/@href").all();
//
//        Request request = new Request(hrefList.get(0));
//        request.putExtra("pageLevel", "details");
//        request.putExtra("name", name);
//
//        page.addTargetRequest(request);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CSDNPageProcessor()).addUrl("https://blog.csdn.net/mengxiangxingdong").thread(5).run();
    }


}

@Data
class BlogModel implements Serializable {
    private static final long serialVersionUID = 4427199980616355080L;

    /**
     * 主键id
     */
    private Long id;


    /**
     * 头像
     */
    private String name;

    /**
     * 账号
     */

    private String title;

    /**
     *
     */

    private String content;

    /**
     *
     */
    private Date time;

}