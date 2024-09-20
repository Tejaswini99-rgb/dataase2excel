package com.example.domain;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customers_Information.xlsx";
        response.setHeader(headerKey, headerValue);
        customerService.exportCustomerToExcel(response);

    }
//    @GetMapping("/export-database-metadata")
//    public ResponseEntity<Void> exportDatabaseMetadata(HttpServletResponse response) throws SQLException, IOException {
//        response.setContentType("application/octet-stream");
//		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=database_metadata.xlsx");
//		customerService.exportMetadataToExcel(response);
//		return ResponseEntity.ok().build();
//    }
}
