package top.xmindguoguo.skills.demo.mybatis.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.skills.SkillsCoreApplicationTest;
import top.xmindguoguo.skills.demo.mybatis.model.TSystemUserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @ClassName: TSystemUserMapperTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 0:30
 * @Version: v1.0
 */
public class TSystemUserMapperTest extends SkillsCoreApplicationTest {
    @Autowired
    TSystemUserMapper mapper;

    @Test
    public void insert() {
        TSystemUserModel model = new TSystemUserModel();
//        model.setId(CmUtil.getPkId());
        model.setName("测试mybatis的xml");
        model.setAccount("test" + System.currentTimeMillis());
        model.setPassword("123456");
        Integer number = mapper.insert(model);
        print(number);
        Assert.assertTrue(number > 0);
    }

    @Test
    public void insertBatch() {
        List<TSystemUserModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TSystemUserModel model = new TSystemUserModel();
//            model.setId(CmUtil.getPkId());
            model.setName("测试mybatis的xml");
            model.setAccount("test" + System.currentTimeMillis() + "___" + i);
            model.setPassword("123456");
            list.add(model);
        }

        boolean number = mapper.insertBatch(list);
        print(number);
        Assert.assertTrue(number);
    }

    @Test
    public void updateById() {
        TSystemUserModel model = new TSystemUserModel();
        model.setId(7L);
        model.setName("测试mybatis的xml");
        model.setAccount("test" + System.currentTimeMillis());
        model.setPassword("123456");
        Integer number = mapper.updateById(model);
        print(number);
        Assert.assertTrue(number > 0);
    }

    @Test
    public void updateBatchById() {
        List<Long> ids = new ArrayList<>();
        Collections.addAll(ids, 1607014661270L, 1607014661269L, 1607014661268L);
        List<TSystemUserModel> list = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            TSystemUserModel model = new TSystemUserModel();
            model.setId(ids.get(i));
            model.setName("修改了");
            model.setAccount("test" + System.currentTimeMillis() + "___" + i);
            model.setPassword("123456");
            list.add(model);
        }

        Integer number = mapper.updateBatchById(list);
        print(number);
        Assert.assertTrue(number > 0);
    }

    @Test
    public void deleteById() {

        Integer number = mapper.deleteById(1607014661270L);
        print(number);
        Assert.assertTrue(number > 0);
    }

    @Test
    public void deleteBatchIds() {
        List<Long> ids = new ArrayList<>();
        Collections.addAll(ids, 1607014661270L, 1607014661269L, 1607014661268L);


        Integer number = mapper.deleteBatchIds(ids);
        print(number);
        Assert.assertTrue(number > 0);
    }

    @Test
    public void selectById() {

        TSystemUserModel model = mapper.selectById(1L);
        print(model);
        Assert.assertTrue(model != null);
    }

    @Test
    public void selectBatchIds() {
        List<Long> ids = new ArrayList<>();
        Collections.addAll(ids, 1546331276337L, 1546432980758L, 1607014661268L);


        List<TSystemUserModel> modelList = mapper.selectBatchIds(ids);
        print(modelList);
        Assert.assertTrue(modelList.size() > 0);
    }


}