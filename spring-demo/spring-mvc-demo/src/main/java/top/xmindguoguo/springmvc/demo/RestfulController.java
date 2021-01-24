package top.xmindguoguo.springmvc.demo;

import org.springframework.web.bind.annotation.*;
import top.xmindguoguo.springmvc.api.ApiResult;
import top.xmindguoguo.springmvc.demo.model.PersonModel;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 23:24
 * @Version: v1.0
 */
@RestController
@RequestMapping("/restful")
public class RestfulController {
    /**
     * 模拟数据库的增删改查 表  演示例子用
     */
    Map<Long, PersonModel> personMOdelTable = new HashMap<>();


    /**
     * 演示思路
     * get  post 等方法的请求
     * <p>
     * 然后增删改查
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ApiResult<PersonModel> get() {
        return ApiResult.sendMessage("基础的 get 请求");
    }

    @RequestMapping(value = "/getRequestParam", method = RequestMethod.GET)
    public ApiResult<PersonModel> getRequestParam(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return ApiResult.sendMessage(MessageFormat.format("你传递的参数为name:{0} age:{1}", name, age));
    }

    @RequestMapping(value = "/getPathVariable/{name}/{age}", method = RequestMethod.GET)
    public ApiResult<PersonModel> getPathVariable(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        return ApiResult.sendMessage(MessageFormat.format("你传递的参数为name:{0} age:{1}", name, age));
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ApiResult<PersonModel> post() {
        return ApiResult.sendMessage("基础的post请求");
    }

    @RequestMapping(value = "/postRequestBody", method = RequestMethod.POST)
    public ApiResult<PersonModel> postRequestBody(@RequestBody PersonModel personModel) {
        return ApiResult.sendOk("postRequestBody ", personModel);
    }

    @RequestMapping(value = "/postRequestParam", method = RequestMethod.POST)
    public ApiResult<PersonModel> post(PersonModel personModel) {
        return ApiResult.sendOk("postRequestParam ", personModel);
    }

    //=====演示一下增删改查=====================
    //init
    @RequestMapping(value = "/getInitTable", method = RequestMethod.GET)
    public ApiResult<PersonModel> getInitTable() {
        personMOdelTable.clear();
        personMOdelTable.put(1L, new PersonModel(1L, "任帆帆", 18, new Date()));
        personMOdelTable.put(2L, new PersonModel(2L, "任帆帆2", 20, new Date()));
        personMOdelTable.put(3L, new PersonModel(3L, "任帆帆3", 22, new Date()));
        return ApiResult.sendOk("初始化数据成功", personMOdelTable);
    }

    //add
    @RequestMapping(value = "/postAdd", method = RequestMethod.POST)
    public ApiResult<PersonModel> postAdd(@RequestBody PersonModel personModel) {
        personMOdelTable.put(personModel.getId(), personModel);
        return ApiResult.sendOk("添加成功!");
    }

    //del
    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public ApiResult<PersonModel> del(@PathVariable Long id) {
        personMOdelTable.remove(id);
        return ApiResult.sendOk("删除成功!");
    }

    //update
    @RequestMapping(value = "/postUpdate", method = RequestMethod.POST)
    public ApiResult<PersonModel> postUpdate(@RequestBody PersonModel personModel) {
        personMOdelTable.put(personModel.getId(), personModel);
        return ApiResult.sendOk("更新成功!");
    }

    //queryOne
    @RequestMapping(value = "/queryOne/{id}", method = RequestMethod.POST)
    public ApiResult<PersonModel> queryOne(@PathVariable Long id) {
        PersonModel personModel = personMOdelTable.get(id);
        return ApiResult.sendOk("queryOne  查询单条!", personModel);
    }

    //queryList
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public ApiResult<PersonModel> queryList(@RequestParam("name") String name) {

        List<PersonModel> list = personMOdelTable.values().stream().filter(personModel -> {
            return personModel.equals(name);
        }).collect(Collectors.toList());
        return ApiResult.sendOk("queryList 查询列表", list);
    }
}
