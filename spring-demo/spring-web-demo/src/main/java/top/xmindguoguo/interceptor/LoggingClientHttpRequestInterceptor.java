package top.xmindguoguo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/11/27 22:14
 * @Version: v1.0
 */
@Slf4j
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        tranceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void tranceRequest(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
        log.debug("=========================== request begin ===========================");
        log.debug("uri : {}", request.getURI());
        log.debug("method : {}", request.getMethod());
        log.debug("headers : {}", request.getHeaders());
        log.debug("request body : {}", new String(body, "utf-8"));
        log.debug("============================ request end ============================");
    }

    private void traceResponse(ClientHttpResponse httpResponse) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.debug("============================ response begin ============================");
        log.debug("Status code  : {}", httpResponse.getStatusCode());
        log.debug("Status text  : {}", httpResponse.getStatusText());
        log.debug("Headers      : {}", httpResponse.getHeaders());
        log.debug("Response body: {}", inputStringBuilder.toString());
        log.debug("============================= response end =============================");
    }
}
