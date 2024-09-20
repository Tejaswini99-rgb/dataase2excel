package com.example.columnnames;



import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {
//============================download file with data==========================//
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=courses_dtls.xls";

		response.setHeader(headerKey, headerValue);
		
		reportService.generateExcel(response);
		
		response.flushBuffer();
	}
//=====================export data into database================================//
  @PostMapping("/upload-list")
  public ResponseEntity<?> uploadCustomersData(@RequestParam("file")MultipartFile file){
      this.reportService.saveCourseToDatabase(file);
      return ResponseEntity
              .ok(Map.of("Message" , "Course list data uploaded and saved to database successfully"));
  }
  @GetMapping("/course")
  public ResponseEntity<List<Course>> getCourse(){
  	 List<Course> course = reportService.getCourse();
  	    return new ResponseEntity<>(course, HttpStatus.FOUND);
  }

}