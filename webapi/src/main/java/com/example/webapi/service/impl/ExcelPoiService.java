package com.example.webapi.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExcelPoiService {
    public static final String exportEmployees = "exportEmployees";


    public String[] getExportExcelTitles(String type) {
        return switch (type) {
            case exportEmployees -> new String[]{"序号", "职工姓名", "性别", "年龄", "部门名称", "学历"};
            default -> new String[0];
        };
    }

    public void writeExcel(String filePath, String sheetName, String[] titles, List<String[]> contents, Map<Integer, Integer> columnWidthMap) {
        writeExcel(new File(filePath), sheetName, titles, contents, columnWidthMap);
    }

    public void writeExcel(File file, String sheetName, String[] titles, List<String[]> contents, Map<Integer, Integer> columnWidthMap){
        Workbook wb = null;
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            wb = new XSSFWorkbook();
            Sheet st = wb.createSheet(sheetName);

            //标题列写入
            //对于 excel 第一行的标题，设置样式为字体加粗，居中显示
            CellStyle cstitle = wb.createCellStyle();
            cstitle.setAlignment(HorizontalAlignment.CENTER);
            Font font = wb.createFont();
            font.setBold(true);
            cstitle.setFont(font);
            Row rowtitle = st.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                Cell celltitle = rowtitle.createCell(i);
                celltitle.setCellValue(titles[i]);
                celltitle.setCellStyle(cstitle);
            }

            //数据列写入
            CellStyle csfield = wb.createCellStyle();
            csfield.setAlignment(HorizontalAlignment.CENTER);

            int rowIndex = 1;
            for(String[] content : contents){
                Row rowContent = st.createRow(rowIndex);
                for(int j = 0; j < content.length; j++){
                    Cell cellContent = rowContent.createCell(j);
                    cellContent.setCellValue(content[j]);
                    cellContent.setCellStyle(csfield);
                }
                rowIndex++;
            }

            //设置列宽
            for (int i = 0; i < titles.length; i++) {
                st.setColumnWidth(i, 5000);		//设置初始列宽
                if(columnWidthMap != null && columnWidthMap.containsKey(i)){
                    st.setColumnWidth(i, columnWidthMap.get(i));
                }
            }
            wb.write(os);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
