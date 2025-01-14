package com.example.columnnames;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
//=====================================export data  from excel to database====================================//
public class ExcelUploadService {
	public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }


    
	public static List<Course> getCustomersDataFromExcel(InputStream inputStream) {
	    List<Course> products = new ArrayList<>();
	    try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

	        System.out.println("Sheets available in the workbook:");
	        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	            System.out.println("Sheet " + i + ": " + workbook.getSheetName(i));
	        }

	        String sheetName = "course_dtls"; 
	        XSSFSheet sheet = workbook.getSheet(sheetName);

	        if (sheet == null) {
	            throw new RuntimeException("Sheet with name '" + sheetName + "' not found in the Excel file. Available sheets: " +
	                workbook.getNumberOfSheets());
	        }

	        int rowIndex = 0;
	        for (Row row : sheet) {
	            if (rowIndex == 0) {
	                rowIndex++;
	                
	                continue;
	            }
	            Iterator<Cell> cellIterator = row.iterator();
	            int cellIndex = 0;
	            Course product = new Course();

	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                try {
	                    switch (cellIndex) {
	                        case 0 -> product.setCid((int) cell.getNumericCellValue());
	                        case 1 -> product.setName(cell.getStringCellValue());
	                        case 2 -> product.setPrice(cell.getNumericCellValue());
	                        default -> {}
	                    }
	                } catch (Exception e) {
	                    throw new RuntimeException("Error processing cell at row " + rowIndex + " and column " + cellIndex + ": " + e.getMessage());
	                }
	                cellIndex++;
	            }
	            products.add(product);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error while processing Excel file: " + e.getMessage());
	    }
	    return products;
	}
}
