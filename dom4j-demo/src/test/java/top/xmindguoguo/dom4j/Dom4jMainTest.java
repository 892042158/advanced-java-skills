package top.xmindguoguo.dom4j;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * @ClassName: Dom4jMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/3 11:30
 * @Version: v1.0
 * <p>
 * 1.简单的使用
 * 2.大文件的读取
 */
@Slf4j
public class Dom4jMainTest {
    private static final String XML_PATH = "dom4j/dom4j-demo.xml";
    URL url = Thread.currentThread().getContextClassLoader().getResource(XML_PATH);

    @Test
    public void test() throws IOException, URISyntaxException, DocumentException {
        log.info(url.getFile()); //打印出文件

    }

    @Test
    public void readXml() throws IOException, URISyntaxException, DocumentException {
        // 创建解析器
        SAXReader reader = new SAXReader();//这个是用来读取文件内容的
        Document doc = reader.read(url); //指定要读取的文件
        log.info(doc.asXML()); //打印出文件
    }

    @Test
    public void testDocument() throws IOException, URISyntaxException, DocumentException {
        // 创建解析器
        SAXReader reader = new SAXReader();//这个是用来读取文件内容的
        Document doc = reader.read(url); //指定要读取的文件
        //获取根元素
        Element root = doc.getRootElement();
        //获取根元素中所有student元素
        List<Element> stuEleList = root.elements("student");
        // 循环遍历所有学生元素
        for (Element stuEle : stuEleList) {
            //获取学生元素的number
            String number = stuEle.attributeValue("number");
            //获取学生元素名为name的子元素的文本内容
            String name = stuEle.elementText("name");
            //获取学生元素名为age的子元素的文本内容
            String age = stuEle.elementText("age");
            //获取学生元素名为sex的子元素的文本内容
            String sex = stuEle.elementText("sex");
            log.info(number + ", " + name + ", " + age + ", " + sex);
        }

    }

    @Test
    public void add2Xml() throws IOException, URISyntaxException, DocumentException {
        // 创建解析器
        SAXReader reader = new SAXReader();//这个是用来读取文件内容的
        Document doc = reader.read(url); //指定要读取的文件

        //获取root元素
        Element root = doc.getRootElement();

        Element stuEle = root.addElement("student"); //添加了student元素
        // 给stuEle添加属性，名为number，值为1003
        stuEle.addAttribute("number", "1003");
        // 分别为stuEle添加名为name、age、sex的子元素，并为子元素设置文本内容
        stuEle.addElement("name").setText("wangWu");
        stuEle.addElement("age").setText("18");
        stuEle.addElement("sex").setText("male");

        // 设置保存的格式化器  1. \t，使用什么来完成缩进 2. true, 是否要添加换行
        OutputFormat format = new OutputFormat("\t", true);
        format.setTrimText(true); //去掉空白
        // 在创建Writer时，指定格式化器
        //注意是target 下的文件夹去验证
        FileOutputStream fileOutputStream = new FileOutputStream(url.getPath());
        XMLWriter writer = new XMLWriter(fileOutputStream, format);
        writer.write(doc);

    }

    @Test
    public void update2Xml() throws IOException, URISyntaxException, DocumentException {
        // 创建解析器
        SAXReader reader = new SAXReader();//这个是用来读取文件内容的
        Document doc = reader.read(url); //指定要读取的文件


        //使用XPath找到符合条件的元素
        // 查询student元素，条件是number属性的值为1003
        Element stuEle = (Element) doc.selectSingleNode("//student[@number='ITCAST_1001']");
        //修改stuEle的age子元素内容为81
        stuEle.element("age").setText("81");
        //修改stuEle的sex子元素的内容为female
        stuEle.element("sex").setText("female");

    }

    //删除元素
    @Test
    public void delete2Xml() throws Exception {

        // 创建解析器
        SAXReader reader = new SAXReader();//这个是用来读取文件内容的
        Document doc = reader.read(url); //指定要读取的文件

        // 查找student元素，条件是name子元素的文本内容为wangWu
        Element stuEle = (Element) doc.selectSingleNode("//student[name='wangWu']");

        // 2. 获取父元素，使用父元素删除指定子元素
        stuEle.getParent().remove(stuEle);
    }


    /**
     * 读取大文件
     *
     * @throws IOException
     * @throws URISyntaxException
     * @throws DocumentException
     * @see https://blog.csdn.net/mark_lq/article/details/45040731
     */
    @Test
    public void readBigXml() throws IOException, URISyntaxException, DocumentException {
        //            一般网上的都是用一个实现 ElementHandler的类来做，然后把reader.setDefaultHandler(this)
        SAXReader reader = new SAXReader();//这个是用来读取文件内容的
        reader.setDefaultHandler(new ElementHandler() {
            public void onEnd(ElementPath ep) {
                Element e = ep.getCurrent(); // 获得当前节点
                System.out.println(e.getName());
                e.detach(); // 记得从内存中移去
            }

            public void onStart(ElementPath ep) {
                //todo 需要思考为什么 onStart需要释放内存   而onEnd也要释放内存
                //释放内存
                ep.getCurrent().detach();
            }
        });
        Document doc = reader.read(url); //指定要读取的文件
        log.info(doc.asXML()); //打印出文件

    }


}