package org.springframework.util;

import lombok.Data;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ObjectUtilsTest {
    /**
     * Return a hex String form of an object's identity hash code.
     *
     * @param obj the object
     * @return the object's identity code in hex notation
     */
    @Test
    public void getIdentityHexString() {
        System.err.println(ObjectUtils.getIdentityHexString(new TestModel()));
        System.err.println(System.identityHashCode(new TestModel()));
        System.err.println(new TestModel());

    }


}

@Data
class TestModel extends BaseModel {
    private String name;
    //    @NoExport
    private Integer age;
    private Date createTime;
    private List<TestModel> testList;

}

@Data
class BaseModel {
    private Long id;
}
