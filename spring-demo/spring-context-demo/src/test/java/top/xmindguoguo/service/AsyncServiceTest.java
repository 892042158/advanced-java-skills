package top.xmindguoguo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.SpringWebMainTest;
import top.xmindguoguo.ext.ApplicationContextHelper;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/11/15 22:15
 * @Version: v1.0
 */
class AsyncServiceTest extends SpringWebMainTest {
//    @Autowired
//    private ApplicationContextHelper applicationContextHelper;
    @Autowired
    private  AsyncService asyncService;


    @Test
    void asyncResult() throws InterruptedException {
        System.err.println("tasyncService:"+asyncService.getClass().getName());
        for(int i=0;i<10;i++){
            asyncService.asyncResult();
        }
    }
    @Test
    void thisAsyncResult() throws InterruptedException {
        System.err.println("thisAsyncResult:"+asyncService.getClass().getName());
        for(int i=0;i<10;i++){
            asyncService.thisAsyncResult();
        }
    }
//    @Test
//    void thisAsyncResultApplicationContext() throws InterruptedException {
//        for(int i=0;i<10;i++){
//            AsyncService asyncServiceProxy = (AsyncService) applicationContextHelper.getApplicationContext().getBean("asyncService");
//            asyncServiceProxy.thisAsyncResult();
//        }
//    }
//    @Test
//    void test() throws InterruptedException {
//        for(int i=0;i<10;i++){
//            asyncService.test();
//        }
//    }

}