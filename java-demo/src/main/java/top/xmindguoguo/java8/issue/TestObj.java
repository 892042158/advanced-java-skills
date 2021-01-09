package top.xmindguoguo.java8.issue;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class TestObj {
    private Long id;
    private String name;
    private String title;
    // 扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
    private String ext1 = "";
    // 扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
    private String ext2 = "";
    // 支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
    private String payType = "00";
    private String merchantSignMsgVal = "";

    public String appendParam(String returns, String paramId, String paramValue) {
        if (returns != "") {
//            if (!paramValue.equals("")) {
            String empty = "";
            System.err.println(System.identityHashCode(empty));
            System.err.println(System.identityHashCode(paramValue));
            if (paramValue != empty) {
                returns += "&" + paramId + "=" + paramValue;
            }
        } else {
            if (paramValue != "") {
                returns = paramId + "=" + paramValue;
            }
        }
        return returns;
    }

    public String getSignMsgVal() {
        merchantSignMsgVal = "";
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "id", id + "");
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "name", name);
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "title", title);
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "payType", payType);
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext1", ext1);
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext2", ext2);
        return merchantSignMsgVal;
    }

    public static void main(String[] args) {
        TestObj obj1 = new TestObj();
        obj1.setId(1L);
        obj1.setName("kevin");
        obj1.setTitle("title多方式");
        obj1.setPayType("10");
//        obj1.setExt1("");
//        obj1.setExt2("");

//        String signMsg = obj1.getSignMsgVal();
//        System.err.println("分割线====obj1.getSignMsgVal()");
        String text = JSONObject.toJSONString(obj1);
        System.err.println("分割线====JSONObject.toJSONString(obj1)");

        TestObj obj2 = JSONObject.parseObject(text, TestObj.class);
        System.err.println("分割线====JSONObject.parseObject(text, TestObj.class)");

        String signMsg2 = obj2.getSignMsgVal();
        System.err.println("分割线==== obj2.getSignMsgVal()");

//        
//        System.out.println("signMsg==signMsg2 : " + signMsg.equals(signMsg2));
//        System.out.println("signMsg  : " + signMsg);
//        System.out.println("signMsg2 : " + signMsg2);

    }

}
