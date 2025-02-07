package com.akash.dhembare2000.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UtilsExcel {

    public static String FILE_NAME = "src/test/java/com/akash/dhembare2000/utils/TD.xlsx";

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        Workbook book = null;
        Sheet sheet = null;
        try {
            File filePath = new File(FILE_NAME);
            if (!filePath.exists()) {
                throw new RuntimeException("Excel file not found at: " + FILE_NAME);
            }
            file = new FileInputStream(FILE_NAME);
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);

            Object[][] data = new Object[sheet.getPhysicalNumberOfRows() - 1][sheet.getRow(0).getPhysicalNumberOfCells()];
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j) != null ? sheet.getRow(i).getCell(j).toString() : "";
                }
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        } finally {
            try {
                if (file != null) file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @DataProvider
    public Object[][] getData() {
        String sheetName = System.getProperty("sheetName", "Sheet1"); // Default to Sheet1 if not provided
        return getTestData(sheetName);
    }
}
