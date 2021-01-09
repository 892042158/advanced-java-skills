package top.xmindguoguo.java8.jdbc.ext;

import lombok.Data;

/**
 * @ClassName: DataSource
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/9 0:33
 * @Version: v1.0
 */
@Data
public class DataSource {
    private String url;
    private String driver;
    private String user;
    private String password;
}
