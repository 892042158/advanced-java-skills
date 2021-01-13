package top.xmindguoguo.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * excel 读取数据 1.读取指定行（readTitle） 2.读取指定行后的所有数据集 readList
 * 
 * @ClassName ImportExecl
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年4月8日 下午2:40:07
 *
 */
@Slf4j
public class ImportExecl {
    /** 总行数 */
    private int totalRows = 0;
    /** 总列数 */
    private int totalCells = 0;
    /** 错误信息 */
    private String errorInfo;

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorInfo = "文件名不是excel格式";
            log.error(errorInfo);
            return false;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("文件不存在");
            return false;
        }
        return true;
    }

    /**
     * @描述：是否是2003的excel，返回true是2003
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @描述：是否是2007的excel，返回true是2007
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public List<List<String>> readList(String filePath) {
        return readList(filePath, 0);
    }

    public List<List<String>> readList(String filePath, Integer rowNum) {
        InputStream is = null;
        /** 验证文件是否合法 */
        if (!validateExcel(filePath)) {
            return null;
        }
        /** 判断文件的类型，是2003还是2007 */
        boolean isExcel2003 = true;
        if (isExcel2007(filePath)) {
            isExcel2003 = false;
        }
        try {
            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            is = new FileInputStream(file);
            return readList(is, isExcel2003, rowNum);
        } catch (Exception ex) {
            log.error("读取Excel文件出错!", ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    log.error("读取Excel文件时 流关闭异常!", e);
                }
            }
        }
        return null;
    }

    public List<List<String>> readList(InputStream inputStream, boolean isExcel2003) {
        return readList(inputStream, isExcel2003, 0);
    }

    public List<List<String>> readList(InputStream inputStream, boolean isExcel2003, Integer rowNum) {
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            return readList(wb, rowNum);
        } catch (Exception e) {
            log.error("读取Excel文件出错!", e);
        }
        return null;
    }

    public List<Map<String, Object>> readMapList(InputStream inputStream, boolean isExcel2003) {
        List<Map<String, Object>> dataLst = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = readMapList(wb);
        } catch (Exception e) {
            log.error("读取Excel文件出错!", e);
        }
        return dataLst;
    }

    private List<List<String>> readList(Workbook wb, Integer rowNum) {
        rowNum = getConformRowNum(rowNum);
        List<List<String>> dataLst = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        return readSheet(sheet, dataLst, rowNum);
    }

    /**
     * 
     * @描述：读取数据
     */
    private List<Map<String, Object>> readMapList(Workbook wb) {
        List<Map<String, Object>> dataLst = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        dataLst = readSheetMapList(sheet, dataLst);
        return dataLst;
    }

    /**
     * 读取所有的sheet,可以扩展
     * 
     * @Title readAll
     * @author 于国帅
     * @date 2018年4月3日 下午4:49:14
     * @param wb
     * @return List<List<String>>
     */
    @Deprecated
    private List<List<String>> readAll(Workbook wb) {
        List<List<String>> dataLst = new ArrayList<>();
        int len = wb.getNumberOfSheets();
        for (int i = 0; i < len; i++) {
            Sheet sheet = wb.getSheetAt(i);
            dataLst = readSheet(sheet, dataLst);
        }
        return dataLst;
    }

    private List<List<String>> readSheet(Sheet sheet, List<List<String>> dataLst) {
        return readSheet(sheet, dataLst, 0);
    }

    private List<List<String>> readSheet(Sheet sheet, List<List<String>> dataLst, Integer rowNum) {
        /** 得到Excel的行数 */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(rowNum) != null) {
            this.totalCells = sheet.getRow(rowNum).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行 */
        rowNum += 1; // 从标题行后面读取数据
        for (int r = rowNum; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = getCellValue(cell);
                rowLst.add(cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
        return dataLst;
    }

    private List<Map<String, Object>> readSheetMapList(Sheet sheet, List<Map<String, Object>> dataLst) {
        /** 得到Excel的行数 */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行 */
        Row row2 = sheet.getRow(0); // 读取第一行缓存为map
        List<String> rowLst = new LinkedList<>();
        /** 循环Excel的列 */
        for (int c = 0; c < this.getTotalCells(); c++) {
            Cell cell = row2.getCell(c);
            String cellValue = getCellValue(cell);
            rowLst.add(cellValue);
        }
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Map<String, Object> rowMap = new HashMap<>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = getCellValue(cell);
                rowMap.put(rowLst.get(c), cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowMap);
        }
        return dataLst;
    }

    /**
     * @Title 目前所有类型都当做String处理
     * @author 于国帅
     * @date 2018年3月22日 上午9:52:53
     * @param cell
     * @return String
     */
    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    cellValue = cell.getNumericCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }

    /**
     * 读取excel文件的标题行，返回标题的名称 类型统一当做String 处理 中文名称转换为拼音 --需要自己转
     * 
     * @Title readTitle
     * @author 于国帅
     * @date 2018年3月28日 下午2:08:00
     * @param filePath
     * @param rowNum
     * @return Map<String,Object>
     */
    public Map<String, Object> readTitle(String filePath, Integer rowNum) {
        InputStream is = null;
        /** 验证文件是否合法 */
        if (!validateExcel(filePath)) {
            log.error("文件不合法");
            return null;
        }
        /** 判断文件的类型，是2003还是2007 */
        boolean isExcel2003 = true;
        if (isExcel2007(filePath)) {
            isExcel2003 = false;
        }
        try {
            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            is = new FileInputStream(file);
            return readTitle(is, isExcel2003, rowNum);
        } catch (Exception ex) {
            log.error("读取Excel文件出错!", ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    log.error("读取Excel文件时 流关闭异常!", e);
                }
            }
        }
        return null;
    }

    private Map<String, Object> readTitle(InputStream inputStream, boolean isExcel2003, Integer rowNum) {
        rowNum = getConformRowNum(rowNum);
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = wb.getSheetAt(0);
            /** 得到Excel的列数 */
            if (sheet.getRow(rowNum) != null) {
                this.totalCells = sheet.getRow(rowNum).getPhysicalNumberOfCells();
            }
            Row row = sheet.getRow(rowNum);
            Map<String, Object> map = new LinkedHashMap<>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = getCellValue(cell);
                map.put(cellValue, String.class);
            }
            return map;
        } catch (Exception e) {
            log.error("读取Excel文件出错!", e);
            return null;
        }
    }

    /**
     * 获得符合规则的rowNum
     * 
     * @Title getConformRowNum
     * @author 于国帅
     * @date 2018年3月29日 下午1:59:27
     * @param rowNum
     * @return Integer
     */
    private Integer getConformRowNum(Integer rowNum) {
        if (rowNum == null) {
            rowNum = 0;
        } else if (rowNum > 65535) {
            rowNum = 65535;
        } else {
            rowNum = (rowNum - 1) < 0 ? 0 : rowNum - 1;
        }
        return rowNum;
    }

    public static void main(String[] args) throws Exception {
        ImportExecl poi = new ImportExecl();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        Map<String, Object> list = poi.readTitle("F:\\common-utils\\2018-03-27\\1522118305360@dashuju.xls", 0);
        System.out.println("长度" + list.size());
        if (list != null) {
            for (String string : list.keySet()) {
                System.out.println(string);
            }
        }
        System.exit(0);
    }
}
