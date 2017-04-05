package com.seagen.ecc.common.report;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 
 * @类名: ReportTemplet
 * @说明: 报表模板
 * @author   xumiaoshun
 * @Date     2015年4月22日 上午11:53:26
 * 修改记录：
 * @see
 */
public abstract class ReportTemplet {

    private HSSFWorkbook wb = new HSSFWorkbook();

    public abstract HSSFSheet createSheet(String reportName, Object entity);

    public HSSFSheet createSheet(String reportName, Object entity, List<Map<String, Object>> list) {
        HSSFWorkbook wb = createTemplet(reportName, entity);
        HSSFSheet sheet = wb.getSheet(reportName);

        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Map<String, Object> m = list.get(i);

            Field[] fields = entity.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                String typeName = fields[j].getType().getTypeName();
                String name = fields[j].getName();
                if (typeName.equals("java.lang.String")) {
                    if (null == m.get(name)) {
                        row.createCell(j).setCellValue("");
                    } else {
                        row.createCell(j).setCellValue(m.get(name).toString());
                    }
                } else if (typeName.equals("java.lang.Integer")) {
                    if (null == m.get(name)) {
                        row.createCell(j).setCellValue("");
                    } else {
                        row.createCell(j).setCellValue((int) m.get(name));
                    }
                } else if (typeName.equals("java.lang.Long")) {
                    if (null == m.get(name)) {
                        row.createCell(j).setCellValue("");
                    } else {
                        row.createCell(j).setCellValue((int) m.get(name));
                    }
                } else if (typeName.equals("java.util.Date")) {
                    if (null == m.get(name)) {
                        row.createCell(j).setCellValue("");
                    } else {
                        row.createCell(j).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format((Date) m.get(name)));
                    }
                }

            }
        }
        return sheet;
    };

    public HSSFWorkbook createTemplet(String reportName, Object entity) {
        HSSFSheet sheet = wb.createSheet(reportName);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        Field[] fields = entity.getClass().getDeclaredFields();
        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("report.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fields.length; i++) {
            String value = p.getProperty(fields[i].getName());
            HSSFCell cell = row.createCell(i);
            if ("".equals(value) || null == value) {
                cell.setCellValue(fields[i].getName());
            } else {
                cell.setCellValue(value);
            }
            cell.setCellStyle(style);
        }
        return wb;
    }

    public void exportReport(String reportName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String fileName = new String(reportName.getBytes("utf-8"), "iso-8859-1");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    };

}
