package jdk.text;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * @ClassName: MessageFormatText
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/2 17:21
 * @Version: v1.0
 */
@Slf4j
public class MessageFormatTest {
    private static String messageFormat = " test1:{0} ,test2:{1},test3:{2}";
    private static String stringFormat = " test1:%s,test2:%s,test3:%s";
    private static String logFormat = " test1 {} ,test2{},test3{}";

    @Test
    public void format() {
        log.info(MessageFormat.format(messageFormat, "messageFormat1", "messageFormat2", "messageFormat3"));
        log.info(String.format(stringFormat, "messageFormat1", "messageFormat2", "messageFormat3"));
        log.info(logFormat, "messageFormat1", "messageFormat2", "messageFormat3");

    }


}
