package top.xmindguoguo.skills.demo.jackson.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateJsonDeserializer
 * @author: 于国帅
 * @Description: 自定义json序列化的方式
 * @Date: 2020/12/20 20:30
 * @Version: v1.0
 */
@Slf4j
public class DateJsonDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            if (p != null && StringUtils.isNotEmpty(p.getText())) {
                return format.parse(p.getText());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info("text:{} , errmesg:{}", p.getText(), e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
