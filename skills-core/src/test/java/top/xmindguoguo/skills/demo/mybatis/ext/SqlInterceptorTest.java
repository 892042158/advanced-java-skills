package top.xmindguoguo.skills.demo.mybatis.ext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.skills.ApplicationSuperTest;
import top.xmindguoguo.skills.demo.mybatis.mapper.TSystemUserMapper;
import top.xmindguoguo.skills.demo.mybatis.model.TSystemUserModel;

import java.util.List;

/**
 * @ClassName: SqlInterceptorTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 1:22
 * @Version: v1.0
 */
public class SqlInterceptorTest extends ApplicationSuperTest {
    @Autowired
    TSystemUserMapper mapper;

    @Test
    public void intercept() {
        List<TSystemUserModel> list = mapper.selectList();
        print(list.size());
        print(list);
    }
}