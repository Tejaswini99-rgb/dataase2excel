package com.example.tablename;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.columnnames.Course;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {
//===============================import header columns into excel=================================//
    @Autowired
    private DataRepository dataRepository;

       

        public void generateExcelWithHeaders(String tableName, HttpServletResponse response) throws IOException {
            // Fetch column names
            List<String> columnNames = dataRepository.getColumnNames(tableName);

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(tableName);

            // Create header row
            HSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < columnNames.size(); i++) {
                headerRow.createCell(i).setCellValue(columnNames.get(i));
            }

            // Set response headers
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + tableName + ".xls");

            // Write workbook to the response output stream
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        }
        
        
        
        
}