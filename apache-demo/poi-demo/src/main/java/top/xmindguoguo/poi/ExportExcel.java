package top.xmindguoguo.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import top.xmindguoguo.common.utils.file.FileUtil;
import top.xmindguoguo.poi.model.YtExcelModel;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.增加大数据处理，生成多个文件
 *
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类 注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx() byte[]表jpg格式的图片数据
 * @author gaol
 * @version v1.0
 * @NoExport存在这个注解的字段不导出 <p>
 */
@Slf4j
public class ExportExcel<T> {

    private HSSFWorkbook workbook;
    private static final String DEFAULT_SHEET_NAME = "第一页";
    private static final String PATTERN = "yyyy-MM-dd";
    private static final int ROW_MAX = 65534; // 最大值65536 一行为标题行，一行为截取的list
    private static final int SHEET_MAX = 256; // 2003版本 sheet的最大值

    public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel(DEFAULT_SHEET_NAME, null, dataset, out, PATTERN);
    }

    public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out) {
        exportExcel(DEFAULT_SHEET_NAME, headers, dataset, out, PATTERN);
    }

    public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        exportExcel(DEFAULT_SHEET_NAME, headers, dataset, out, pattern);
    }

    /**
     * 对大数据进行处理超过65536的自己进行生成多个文件处理处理 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title   表格sheet标题名
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings({"deprecation"})
    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        workbook = new HSSFWorkbook();
        // 生成一个表格
        if (StringUtils.isBlank(title)) {
            title = DEFAULT_SHEET_NAME;
        }
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 设置标题
        HSSFCellStyle style = getTitleStyle();
        // 设置数据样式
        HSSFCellStyle dataStyle = getDataStyle();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
//        setMemo(patriarch);
        // 产生表格标题行
        if (ArrayUtils.isNotEmpty(headers)) {
            setTitle(headers, sheet, style);
        }
        // 遍历集合数据，产生数据行
        if (CollectionUtils.isNotEmpty(dataset)) {
            setDataLines(dataset, pattern, sheet, dataStyle, patriarch);
        }
        try {
            workbook.write(out); // 需要补充如果路径文件不存在的生成方式
        } catch (Exception e) {
            log.error("exportExcel", e);
        }

    }

    private void setDataLines(Collection<T> dataset, String pattern, HSSFSheet sheet, HSSFCellStyle dataStyle, HSSFPatriarch patriarch) {
        if (StringUtils.isBlank(pattern)) {
            pattern = this.PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        HSSFRow row;
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index); // 添加蓝色
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            Field[] fields = getAllFields(t);
//            short col = 0; // 这个对空数据处理的行进行重定向前移
            for (short i = 0; i < fields.length; i++) {
                // 排除存在 NoExport 注解的字段不导出
                Field field = fields[i];
                if (field.getAnnotation(NoExport.class) != null) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), t.getClass());
                    Method getMethod = pd.getReadMethod(); // 获得get方法
                    Object value = getMethod.invoke(t);
                    if (value != null) { // 对字段不存在值得进行处理
//                        setCellValue(sheet, dataStyle, patriarch, sdf, row, font3, index, col, value);
                        setCellValue(sheet, dataStyle, patriarch, sdf, row, font3, index, i, value);
//                        col++;
                    }
                } catch (Exception e) {
                    log.error("setDataLines", e);
                }
            }

        }
    }

    /**
     * @param sheet
     * @param dataStyle
     * @param patriarch
     * @param sdf
     * @param row
     * @param font3
     * @param index
     * @param i
     * @param value
     * @throws NumberFormatException void
     * @Title setCellValue
     * @author 于国帅
     * @date 2018年4月4日 上午9:58:18
     */
    private void setCellValue(HSSFSheet sheet, HSSFCellStyle dataStyle, HSSFPatriarch patriarch, SimpleDateFormat sdf, HSSFRow row,
                              HSSFFont font3, int index, short i, Object value) throws NumberFormatException {
        HSSFCell cell = row.createCell(i);
        cell.setCellStyle(dataStyle);
        String textValue = null; // 判断值的类型后进行强制类型转换
        if (value instanceof Date) {
            Date date = (Date) value;
            textValue = sdf.format(date);
        } else if (value instanceof byte[]) {
            // 有图片时，设置行高为60px;
            row.setHeightInPoints(60);
            // 设置图片所在列宽度为80px,注意这里单位的一个换算
            sheet.setColumnWidth(i, (short) (35.7 * 80));
            byte[] bsValue = (byte[]) value;
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6, index);
            anchor.setAnchorType(2);
            patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
        } else {
            // 其它数据类型都当作字符串简单处理
            textValue = (value == null) ? "" : value.toString();
        }
        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
        if (textValue != null) {
            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher = p.matcher(textValue);
            if (matcher.matches()) {
                // 是数字当作double处理
                cell.setCellValue(Double.parseDouble(textValue));
            } else {
                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                richString.applyFont(font3);
                cell.setCellValue(richString);
            }
        }
    }

    private Field[] getAllFields(Object t) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> tempClass = t.getClass();
        while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
        }
        return fieldList.toArray(new Field[fieldList.size()]);
    }

    /**
     * @param headers
     * @param sheet
     * @param style   void
     * @Title setTitle
     * @author 于国帅
     * @date 2018年3月12日 下午4:07:02
     */
    private void setTitle(String[] headers, HSSFSheet sheet, HSSFCellStyle style) {
        if (ArrayUtils.isEmpty(headers)) { // 如果不设置这个，则默认取实体上的注解的，这么做是为了实体类属性太多的时候的烦恼（可以设置分组实体的，暂时不写那么麻烦了）
            headers = getHeaderArray();
        }
        HSSFRow row = sheet.createRow(0);
        for (short i = 0, len = (short) headers.length; i < len; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
    }

    private String[] getHeaderArray() {

        return new String[0];
    }

    /**
     * 设置注释 代码仅供参考
     *
     * @param patriarch void
     * @Title setMemo
     * @author 于国帅
     * @date 2018年3月12日 下午4:03:54
     */
    private void setMemo(HSSFPatriarch patriarch) {
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("帆帆");
    }

    /**
     * @return HSSFCellStyle
     * @Title 设置标题样式
     * @author 于国帅
     * @date 2018年3月12日 下午4:02:26
     */
    private HSSFCellStyle getTitleStyle() {
        HSSFCellStyle style = getCellStyle(workbook);
        // 生成一个字体
        HSSFFont font = getFont(workbook);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * @return HSSFCellStyle
     * @Title getDataStyle
     * @author 于国帅
     * @date 2018年3月12日 下午4:01:36
     */
    private HSSFCellStyle getDataStyle() {
        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont dataFont = workbook.createFont();
        dataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 加粗
        dataStyle.setFont(dataFont);
        return dataStyle;
    }

    /**
     * @return HSSFFont
     * @Title getFont
     * @author 于国帅
     * @date 2018年3月12日 下午3:56:27
     */
    private HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }

    /**
     * @return HSSFCellStyle
     * @Title getCellStyle
     * @author 于国帅
     * @date 2018年3月12日 下午3:55:56
     */
    private HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }

    /**
     * 对大数据进行了处理，但是会造成内存的大量使用，建议分多次数据库查询
     *
     * @param headers
     * @param dataset
     * @param fileName void
     * @Title 导出数据的文件
     * @author 于国帅
     * @date 2018年3月21日 下午2:34:00
     */
    public void exportExcelFile(String[] headers, Collection<T> dataset, String fileName) {
        List<T> data = new ArrayList<>(dataset); // 对大数据进行后处理
        List<T> dataColl = null;

        int len = dataset.size() / ROW_MAX + 1;
        for (int i = 0; i < len; i++) {
//            long maxMem = Runtime.getRuntime().maxMemory() / 1024 / 1024;
//            long freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024;
//            long usedMem = maxMem - freeMem;
//            System.out.println("maxMem" + maxMem);
//            System.out.println("freeMem" + freeMem);
//            System.out.println("usedMem" + usedMem);
            if (i == (len - 1)) {
                dataColl = data.subList(i * ROW_MAX, dataset.size());
            } else {
                dataColl = data.subList(i * ROW_MAX, ROW_MAX * (i + 1));
            }
            this.exportExcelFile(headers, dataColl, fileName, len);
        }
    }

    private String exportExcelFile(String[] headers, Collection<T> dataset, String fileName, int len) {
        for (int i = 0; i < len; i++) {
            String filePath = FileUtil.getDefaultSavePath(i + fileName); // 增加时间的文件夹
            OutputStream out = FileUtil.getFileOutputStream(filePath); // 需要自己检测文件是否存在并且生成
            this.exportExcel(headers, dataset, out);
            try {
                out.close();
            } catch (IOException e) {
                log.error("导出excel出现异常 关闭资源", e);
            }
        }
        return fileName;
    }

    public static void main(String[] args) {
        // 测试学生
        ExportExcel<YtExcelModel> ex = new ExportExcel<>();
        String[] headers = {"titleRow", "memo", "time", "state"};
        List<YtExcelModel> dataList = new ArrayList<>();
        YtExcelModel model = null;
        for (int i = 0; i < 10; i++) { // 一个excel的sheet最多数据是所有的65535，超出的新建一个excel
            model = new YtExcelModel();
//            String ss = "";
//            for (int j = 0; j < 400; j++) {
//                ss += "测试最大长度" + j;
//            }
            model.setMemo("ss");
            model.setCreatetime(new Date());
            model.setState(new Random().nextInt(10));
            dataList.add(model);
        }
        try {
//            String filePath = "";// E://a.xls
//            filePath = FileUtil.getDefaultSavePath("test.xls");
//            OutputStream out = FileUtil.getFileOutputStream(filePath); // 需要自己检测文件是否存在并且生成
//            ex.exportExcel(headers, dataList, out);
            ex.exportExcelFile(headers, dataList, "excel2007测试.xlsx");
//            out.close();
//            JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");
//            System.out.println("在" + filePath);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}