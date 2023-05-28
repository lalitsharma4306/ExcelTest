//package com.test.excel.service;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//@Component
//public class ExcelReader1 {
//    public static void readColumnFromExcel(String filePath, String columnName) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//
//        Workbook workbook = WorkbookFactory.create(fileInputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//
//        int columnIndex = -1;
//        Row headerRow = sheet.getRow(0);
//        for (Cell cell : headerRow) {
//            if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
//                columnIndex = cell.getColumnIndex();
//                break;
//            }
//        }
//
//        if (columnIndex == -1) {
//            System.out.println("Column not found");
//            workbook.close();
//            return;
//        }
//
//        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//            Row row = sheet.getRow(rowIndex);
//            Cell cell = row.getCell(columnIndex);
//
//            // Process the cell value (e.g., print it)
//            if (cell != null /*&& !cell.getStringCellValue().equals("NA")*/) {
//                System.out.println(cell.getStringCellValue());
//            }
//        }
//
//        workbook.close();
//    }
//}
