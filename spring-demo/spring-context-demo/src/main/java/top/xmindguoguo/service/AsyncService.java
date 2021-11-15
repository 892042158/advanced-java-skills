package top.xmindguoguo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/11/15 22:13
 * @Version: v1.0
 */
@Service
public class AsyncService {
    @Async
    public Future<AsyncResult<Boolean>> asyncResult() throws InterruptedException {
        System.err.println(Thread.currentThread().getName());
        Thread.sleep(3*1000);

        return new AsyncResult(true);
    }
    @Async
    public Future<AsyncResult<Boolean>> thisAsyncResult() throws InterruptedException {
        return this.asyncResult();
    }

}
