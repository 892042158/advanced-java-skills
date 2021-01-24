package top.xmindguoguo.spring.beans.model;

import lombok.Data;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 22:32
 * @Version: v1.0
 */
@Data
public class Person {
    private Long id;
    private String name;
    private Cat cat;

}
