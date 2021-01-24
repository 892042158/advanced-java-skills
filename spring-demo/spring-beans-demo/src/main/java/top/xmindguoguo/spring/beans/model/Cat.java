package top.xmindguoguo.spring.beans.model;

import lombok.Data;

import java.util.UUID;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 22:10
 * @Version: v1.0
 */
@Data
public class Cat extends Animal {
    private UUID uuid;

}
