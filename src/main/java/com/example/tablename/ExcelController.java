package com.example.tablename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ExcelController {
//import header columns into excel===============================//
    @Autowired
    private ExcelService excelService;

    @GetMapping("/export-headers")
    public void exportHeaders(@RequestParam String tableName, HttpServletResponse response) throws IOException {
        excelService.generateExcelWithHeaders(tableName, response);
        response.flushBuffer();
    }
    
}