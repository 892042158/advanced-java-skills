package top.xmindguoguo.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 合并两个excel 工具类 Poi版本
 * 
 * @ClassName ExcelUtils
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年7月16日 上午11:40:45
 */
@Slf4j
public class ExcelUtils {
    private static final int ROW_MAX = 65536; // 最大值65536 一行为标题行，一行为截取的list
    private static final int COL_MAX = 16384; // 最大列 16384
    private static final int SHEET_MAX = 256; // 2003版本 sheet的最大值
    // 自定义值
    private static final int DATA_ROW_MAX = 65000 + 1; // 限制大数据 每多少数据一个sheet
    private static final int DEFAULT_APPEND_NUMBER = 1000; // 默认 每1000条追加一次excel
    private static final String DEFAULT_SHEET_NAME = "sheet1";

    private ExcelUtils() { // 不允许实例化

    }

    /**
     * 导出excel文件(单Sheet，包括数据量超出excel最大行数时自动添加到下一sheet)
     * 
     * @param file
     * @param sheetName
     *            sheet名称
     * @param titleList
     *            表头名列表
     * @param keyList
     *            结果集中各Map对应的key值
     * @param resultList
     *            结果集
     */
    public static String exportExcel(String filePath, String sheetName, List<String> titleList, List<Map<String, Object>> resultList,
            List<String> keyList) {
        if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(sheetName) && CollectionUtils.isNotEmpty(titleList)
                && CollectionUtils.isNotEmpty(resultList) && CollectionUtils.isNotEmpty(keyList)) {

        }

        return filePath;
    }

//    public static void main(String[] args) {
//        // 追加excel 测试
//        List<Map<String, Object>> resultList = new ArrayList<>();
//        Map<String, Object> map = null;
//        for (int i = 0; i < 65536 - 1003; i++) {
//            map = new HashMap<>();
//            map.put("id", i);
//            resultList.add(map);
//        }
//        List<String> keyList = new LinkedList<>();
//        keyList.add("id");
//        String filePath = "F:\\2AsaveFile\\catalogConfig.xls";
//        ExcelUtils.appendExcel(filePath, resultList, keyList);
//        System.out.println("追加结束");
//        System.exit(0);
//    }

    /**
     * @Title appendExcel 追加数据到 excel 假设1000条的map
     * @author 于国帅
     * @date 2018年7月16日 上午11:47:58
     * @param filePath
     *            要追加的excel 的文件名称
     * @param keyList
     *            结果集中各Map对应的key值
     * @param resultList
     *            结果集
     * @throws IOException
     */
    public static int appendExcel(String filePath, List<Map<String, Object>> resultList, List<String> keyList) {
//        return appendExcel(filePath, resultList, keyList, DEFAULT_APPEND_NUMBER);
        return appendExcel(filePath, resultList, keyList, resultList.size()); // 先不分页 暂时先用全部数据追加，但是可以数据多了到下一个sheet
    }

    /**
     * @Title appendExcel 追加数据到 excel 假设1000条的map
     * @author 于国帅
     * @date 2018年7月16日 上午11:47:58
     * @param filePath
     *            要追加的excel 的文件名称
     * @param keyList
     *            结果集中各Map对应的key值
     * @param resultList
     *            结果集
     * @throws IOException
     */
    public static int appendExcel(String filePath, List<Map<String, Object>> resultList, List<String> keyList, int appendPageSize) {
        if (StringUtils.isNotBlank(filePath) && CollectionUtils.isNotEmpty(resultList) && CollectionUtils.isNotEmpty(keyList)) {
//            if (appendPageSize <= DEFAULT_APPEND_NUMBER) {
//                appendPageSize = DEFAULT_APPEND_NUMBER; // 至少1000条分页一次  未完成，意义不大
//            }
            // 对数据进行分页，然后递归调用追加方法
//            if()   
            // 传入模板的文件名称
            try {
                FileInputStream is = new FileInputStream(filePath);
                Workbook wb = getWorkbook(is);
                // 递归判断是否需要 新增sheet ，然后增加数据
                Sheet sheet = getAppendSheet(resultList, wb, appendPageSize);
                int len = keyList.size();
                String key = null;
                Cell cell = null;
                Row row = null;
                for (Map<String, Object> map : resultList) {
                    // sheet1.getLastRowNum() 获得当前空行的上一行 （空行是不存在数据且数据不为""的）
                    row = sheet.createRow(sheet.getLastRowNum() + 1);
                    for (int i = 0; i < len; i++) {
                        key = keyList.get(i);
                        cell = row.createCell(i);
                        setCellValue(cell, map.get(key)); // 设置列的值
                    }

                }
                FileOutputStream os = new FileOutputStream(filePath);
                wb.write(os);
                is.close();
                os.close();
            } catch (Exception e) {
                log.error("appendExcel错误", e);
            }
        }
        return 0; // 返回当前数据的条数 未完成 意义不大
    }

    /**
     * 获得可以追加数据的sheet
     * 
     * @Title getAppendSheet
     * @author 于国帅
     * @date 2018年7月16日 下午5:29:23
     * @param resultList
     * @param wb
     * @param appendPageSize
     * @param sheet1
     *            void
     * @return
     */
    private static Sheet getAppendSheet(List<Map<String, Object>> resultList, Workbook wb, int appendPageSize) {
        if (appendPageSize >= DATA_ROW_MAX) {
            log.error(appendPageSize + "大于最大行数" + DATA_ROW_MAX);
            return null;
        }
        Sheet sheet = null;
        int currentSheetNum = 0; // 从第一个sheet 开始获取
        for (;;) {
            sheet = wb.getSheetAt(currentSheetNum);
            if (sheet == null) {
                sheet = wb.createSheet("sheet" + currentSheetNum);
                return sheet;
            } else {
//                log.error(sheet.getLastRowNum() + "");
                if (DATA_ROW_MAX - appendPageSize - sheet.getLastRowNum() > 0) {
                    // 能否追加数据 限制的数据最大数 - 每次追加的map - 上一行有数据的行数
                    return sheet;
                }
                currentSheetNum++; // 否则增加 继续获取sheet
                if (currentSheetNum > SHEET_MAX) {
                    log.error(currentSheetNum + "已经超过最大行数" + SHEET_MAX);
                }
            }
        }
    }

    /**
     * 设置当前列的值 ，object 转变成 对应的excel 的值类型 //暂时没有处理图片的数据
     * 
     * @Title setCellValue
     * @author 于国帅
     * @date 2018年7月16日 下午4:33:41
     * @param cell
     * @param object
     *            void
     */
    private static void setCellValue(Cell cell, Object value) {
        if (value != null) {
            if (value instanceof String) {
                cell.setCellValue(value.toString());
            } else if (value instanceof Double) {
                cell.setCellValue(Double.parseDouble(value.toString()));
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue(Boolean.parseBoolean(value.toString()));
            } else { // 其他样式统一当做 文本处理 double 也当做文本处理
                cell.setCellValue(value.toString());
            }
        } else {
            cell.setCellValue("");
        }
    }

    /**
     * 获得poi 的指定版本的 流 Workbook
     * 
     * @Title getWorkbook
     * @author 于国帅
     * @date 2018年7月16日 上午11:55:38
     * @param is
     *            文件流 FileInputStream is = new FileInputStream(filePath);
     * @return
     * @throws IOException
     *             Workbook
     */
    private static Workbook getWorkbook(InputStream is) throws IOException {
        Workbook wb = null;
        if (!is.markSupported()) { // 测试该输入流是否支持mark()和reset()方法。
            is = new PushbackInputStream(is, 8); // 采用汇回退流
        }
        if (POIFSFileSystem.hasPOIFSHeader(is)) { // Excel2003及以下版本
            wb = (Workbook) new HSSFWorkbook(is);
        } else if (POIXMLDocument.hasOOXMLHeader(is)) { // Excel2007及以上版本
            wb = new XSSFWorkbook(is);
        } else {
            throw new IllegalArgumentException("你的Excel版本目前poi无法解析！");
        }
        return wb;
    }
}
