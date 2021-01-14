package top.xmindguoguo.common.utils.file;//package common.utils.file;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//
//import common.utils.ToolsUtil;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 安全字符核心工具类
// * 
// * @author Administrator
// * 
// */
//@Slf4j
//public class SystemSafeCharUtil {
//
//    // 目前不过滤-
//    private static final String SQL_D_STR = "'|and|exec|execute|insert|create|drop|table|from|grant|use|group_concat|column_name|"
//            + "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
//            + "chr|mid|master|truncate|char|declare|or|;|--|+|,|like|//|/|%|#";
//
//    private static String[] SQL_D_STR_A;
//
//    static {
//        SQL_D_STR_A = ToolsUtil.split(SQL_D_STR, "\\|");
//    }
//
//    /**
//     * 有规定的特殊字符吗？
//     * 
//     * @param input
//     * @return bool
//     */
//    public static boolean hasSpecialChars(String input) {
//        boolean flag = false;
//
//        if (null != input && !input.trim().equals("")) {// 空输入认为是特殊
//            char c;
//            for (int i = 0; i < input.length(); i++) {
//                c = input.charAt(i);
//                switch (c) {
//                    case '<':
//                        flag = true;
//                        break;
//                    case '>':
//                        flag = true;
//                        break;
//                    case '&':
//                        flag = true;
//                        break;
//                    case '"':
//                        flag = true;
//                        break;
//                    case '.':
//                        flag = true;
//                        break;
//                    case '\'':
//                        flag = true;
//                        break;
//                    case '#':
//                        flag = true;
//                        break;
//                    case '$':
//                        flag = true;
//                        break;
//                    case '!':
//                        flag = true;
//                        break;
//                    case '%':
//                        flag = true;
//                        break;
//                    case ',':
//                        flag = true;
//                        break;
//                    case '~':
//                        flag = true;
//                        break;
//                    case '^':
//                        flag = true;
//                        break;
//                    case '*':
//                        flag = true;
//                        break;
//                    case '(':
//                        flag = true;
//                        break;
//                    case ')':
//                        flag = true;
//                        break;
//                    case '=':
//                        flag = true;
//                        break;
//                    case '+':
//                        flag = true;
//                        break;
//                    case '|':
//                        flag = true;
//                        break;
//                    case '\\':
//                        flag = true;
//                        break;
//                    case ';':
//                        flag = true;
//                        break;
//                    case ':':
//                        flag = true;
//                        break;
//                    case ' ':
//                        flag = true;
//                        break;
//                    case '?':
//                        flag = true;
//                        break;
//                }
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 替换掉HTML标记
//     * 
//     * @param input
//     * @return
//     */
//    public static String filterHTML(String input) {
//        if (input == null) {
//            return null;
//        }
//
//        StringBuffer buf = new StringBuffer();
//        char c;
//        for (int i = 0; i < input.length(); i++) {
//            c = input.charAt(i);
//            switch (c) {
//                case '<':
//                    buf.append("&lt;");
//                    break;
//                case '>':
//                    buf.append("&gt;");
//                    break;
//                case '"':
//                    buf.append("&quot;");
//                    break;
//                case '\'':
//                    buf.append("&#39;");
//                    break;
//                case '&':
//                    buf.append("&amp;");
//                    break;
//                default:
//                    buf.append(c);
//            }
//        }
//        return buf.toString();
//    }
//
//    /**
//     * 将替换掉的HTML标记还原
//     * 
//     * @param input
//     * @return
//     */
//    public static String resumeHTML(String input) {
//
//        input = ToolsUtil.replaceString(input, "&lt;", "<", false, false);
//
//        input = ToolsUtil.replaceString(input, "&gt;", ">", false, false);
//
//        input = ToolsUtil.replaceString(input, "&quot;", "\"", false, false);
//
//        input = ToolsUtil.replaceString(input, "&#39;", "'", false, false);
//
//        input = ToolsUtil.replaceString(input, "&amp;", "&", false, false);
//
//        return input;
//    }
//
//    /**
//     * 替换掉HTML标记,转义为Html方式
//     * 
//     * @param input
//     * @return
//     */
//    public static String filterHTMLNotApos(String input) {
//        if (input == null) {
//            return null;
//        }
//
//        StringBuffer buf = new StringBuffer();
//        char c;
//        for (int i = 0; i < input.length(); i++) {
//            c = input.charAt(i);
//            switch (c) {
//                case '<':
//                    buf.append("&lt;");
//                    break;
//                case '>':
//                    buf.append("&gt;");
//                    break;
//                case '"':
//                    buf.append("&quot;");
//                    break;
//                // case '\'':
//                // buf.append( "&apos;" );
//                // break;
//
//                default:
//                    buf.append(c);
//            }
//        }
//        return buf.toString();
//    }
//
//    /**
//     * 转义JS符号
//     * 
//     * @param input
//     * @return
//     */
//    public static String encodeJS(String input) {
//        if (input == null) {
//            return null;
//        }
//
//        input = ToolsUtil.replaceString(input, "\\", "\\\\", false, false);
//        input = ToolsUtil.replaceString(input, "&#39;", "\\&#39;", false, false);
//        input = ToolsUtil.replaceString(input, "'", "\\'", false, false);
//        input = ToolsUtil.replaceString(input, "&quot;", "\\&quot;", false, false);
//        input = ToolsUtil.replaceString(input, "\"", "\\\"", false, false);
//
//        // input = replaceString( input, " ", "\n", false, false );
//        input = ToolsUtil.replaceString(input, "<", "&lt;", false, false);
//        input = ToolsUtil.replaceString(input, ">", "&gt;", false, false);
//        return input;
//    }
//
//    /**
//     * 转义JS符号
//     * 
//     * @param input
//     * @return
//     */
//    public static String encodeJSNotIncludeLtAndGt(String input) {
//        if (input == null) {
//            return null;
//        }
//
//        input = ToolsUtil.replaceString(input, "\\", "\\\\", false, false);
//        input = ToolsUtil.replaceString(input, "&#39;", "\\&#39;", false, false);
//        input = ToolsUtil.replaceString(input, "'", "\\'", false, false);
//        input = ToolsUtil.replaceString(input, "&quot;", "\\&quot;", false, false);
//        input = ToolsUtil.replaceString(input, "\"", "\\\"", false, false);
//        // input = replaceString( input, " ", "\n", false, false );
//
//        return input;
//    }
//
//    /**
//     * 去掉HTML标记
//     * 
//     * @param input
//     * @return
//     */
//    public static String removeHTMLTag(String input) {
//        if (ToolsUtil.isStringNull(input)) {
//            return null;
//        }
//        int tagStart;
//        int tagEnd;
//
//        StringBuffer buf = new StringBuffer(input);
//        while (true) {
//            tagStart = buf.indexOf("<");
//            tagEnd = buf.indexOf(">");
//            if (tagStart < tagEnd && tagStart >= 0 && tagEnd >= 0) {
//                buf.replace(tagStart, tagEnd + 1, "");
//            } else
//                break;
//
//        }
//
//        return buf.toString();
//    }
//
//    /**
//     * 转义正则特殊字符 （$()*+.[]?\^{},|）
//     * 
//     * @param keyword
//     * @return
//     */
//    public static String escapeExprSpecialWord(String keyword) {
//        if (ToolsUtil.isStringNotNull(keyword)) {
//            // String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]",
//            // "?", "^", "{", "}", "|" };
//
//            String[] fbsArr = { "?" };
//
//            for (String key : fbsArr) {
//                if (keyword.contains(key)) {
//                    keyword = keyword.replace(key, "\\" + key);
//                }
//            }
//        }
//        return keyword;
//    }
//
//    /**
//     * 判断是否有SQL非法语句片段
//     * 
//     * @param input
//     * @return
//     */
//    public static boolean hasSQLDChars(String input) {
//        if (input == null) {
//            return false;
//        }
//
//        String testStr = input.toLowerCase();
//
//        for (int i = 0; i < SQL_D_STR_A.length; i++) {
//            if (testStr.indexOf(SQL_D_STR_A[i]) != -1) {
//                log.info("[SystemSafeCharUtil] hasSQLDChars 包含非法字:" + SQL_D_STR_A[i]);
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * html安全处理
//     * 
//     * @param input
//     * @return
//     */
//    public static String encodeAllHTML(String input) {
//        String str = filterHTML(input);
//        return str;
//
//    }
//
//    /**
//     * 去掉字符串中的某个字符（unicode代码点形式），以新的字符替代
//     * 
//     * @param input
//     * @param code
//     * @param newStr
//     * @return
//     */
//    public static String delSpeChar(String input, int code, String newStr) {
//        int start = 0;
//        int res = input.indexOf(code);
//        String sub;
//        StringBuffer buf = new StringBuffer();
//
//        while (res != -1) {
//            sub = new String(input.substring(start, res));
//            buf.append(sub + newStr);
//            start = res + 1;
//
//            res = input.indexOf(code, start);
//
//        }
//        buf.append(input.substring(start));
//
//        return buf.toString();
//    }
//
//    public static String decodeFromWeb(String input) {
//        return decodeFromWeb(input, "utf-8");
//    }
//
//    public static String decodeFromWeb(String input, String enc) {
//        String target = "";
//
//        if (input == null) {
//            return "";
//        }
//
//        try {
//            target = URLDecoder.decode(input, enc);
//        } catch (Exception e) {
//            target = input;
//        }
//
//        target = decodeDangerChar(target);
//
//        return target;
//    }
//
//    // /*
//    // * 单引号不转义，直接转换
//    // */
//    // public static String decodeFromWebCodeMode( String input )
//    // {
//    // String target = "";
//    //
//    // try
//    // {
//    // target = URLDecoder.decode( input, "UTF-8" );
//    // }
//    // catch ( Exception e )
//    // {
//    //
//    // }
//    //
//    // target = decodeDangerChar ( target);
//    //
//    // return target;
//    // }
//
//    public static String encodeDangerChar(String input) {
//
//        if (ToolsUtil.isStringNull(input) || !haveDangerChar(input)) {
//            return input;
//        }
//
//        String target = input;
//
//        target = ToolsUtil.replaceString(target, "'", "**!1**", false, false);
//        target = ToolsUtil.replaceString(target, "(", "**!2**", false, false);
//        target = ToolsUtil.replaceString(target, ")", "**!3**", false, false);
//        target = ToolsUtil.replaceString(target, "..", "**!4**", false, false);
//
//        // target = StringUtil.replaceString( target, "\'","**!5**", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "\"", "**!6**", false, false);
//        // target = StringUtil.replaceString( target, "\\\"","**!7**", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "<", "**!8**", false, false);
//
//        target = ToolsUtil.replaceString(target, ">", "**!9**", false, false);
//        target = ToolsUtil.replaceString(target, "|", "**!10**", false, false);
//        target = ToolsUtil.replaceString(target, "\\", "**!11**", false, false);
//        target = ToolsUtil.replaceString(target, "+", "**!12**", false, false);
//
//        // target = StringUtil.replaceString( target,";", "**!13**", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "@", "**!14**", false, false);
//        target = ToolsUtil.replaceString(target, "$", "**!15**", false, false);
//        target = ToolsUtil.replaceString(target, ":", "**!16**", false, false);
//        // target = StringUtil.replaceString( target, "/","**!17**", false,
//        // false );
//        target = ToolsUtil.replaceString(target, " a", "**!18**", false, false);
//        target = ToolsUtil.replaceString(target, " A", "**!19**", false, false);
//        target = ToolsUtil.replaceString(target, "/**/", "**!20**", false, false);
//
//        return target;
//    }
//
//    public static String decodeDangerChar(String input) {
//
//        if (ToolsUtil.isStringNull(input) || !haveDangerChar(input)) {
//            return input;
//        }
//
//        String target = input;
//
//        target = ToolsUtil.replaceString(target, "**!1**", "'", false, false);
//        target = ToolsUtil.replaceString(target, "**!2**", "(", false, false);
//        target = ToolsUtil.replaceString(target, "**!3**", ")", false, false);
//        target = ToolsUtil.replaceString(target, "**!4**", "..", false, false);
//
//        // target = StringUtil.replaceString( target, "**!5**", "\'", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "**!6**", "\"", false, false);
//        // target = StringUtil.replaceString( target, "**!7**", "\\\"", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "**!8**", "<", false, false);
//
//        target = ToolsUtil.replaceString(target, "**!9**", ">", false, false);
//        target = ToolsUtil.replaceString(target, "**!10**", "|", false, false);
//        target = ToolsUtil.replaceString(target, "**!11**", "\\", false, false);
//        target = ToolsUtil.replaceString(target, "**!12**", "+", false, false);
//
//        // target = StringUtil.replaceString( target, "**!13**", ";", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "**!14**", "@", false, false);
//        target = ToolsUtil.replaceString(target, "**!15**", "$", false, false);
//        target = ToolsUtil.replaceString(target, "**!16**", ":", false, false);
//        // target = StringUtil.replaceString( target, "**!17**", "/", false,
//        // false );
//        target = ToolsUtil.replaceString(target, "**!18**", " a", false, false);
//        target = ToolsUtil.replaceString(target, "**!19**", " A", false, false);
//        target = ToolsUtil.replaceString(target, "**!20**", "/**/", false, false);
//
//        return target;
//    }
//
//    public static boolean haveDangerChar(String input) {
//        if (input.indexOf("**!") != -1) {
//            return true;
//        }
//
//        return false;
//    }
//
//    // public static String decodeDangerCharCodeMode ( String input )
//    // {
//    //
//    // if(StringUtil.isStringNull( input ))
//    // {
//    // return null;
//    // }
//    // String target = input;
//    //
//    // target = replaceString( target, "**1**", "'", false, false );
//    // target = replaceString( target, "**2**", "(", false, false );
//    // target = replaceString( target, "**3**", ")", false, false );
//    // target = replaceString( target, "**4**", "\\", false, false );
//    // target = replaceString( target, "**5**", "..", false, false );
//    //
//    //
//    // return target;
//    // }
//
//    public static String encode(String target) {
//        if (ToolsUtil.isStringNull(target)) {
//            return target;
//        }
//
//        target = ToolsUtil.replaceString(target, " ", "%20", false, false);
//
//        try {
//            return URLEncoder.encode(target, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//        }
//
//        return target;
//    }
//}
