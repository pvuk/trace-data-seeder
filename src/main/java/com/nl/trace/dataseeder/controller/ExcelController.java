package com.nl.trace.dataseeder.controller;
import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nl.trace.dataseeder.service.ExcelToCsvService;

@RestController
@RequestMapping("/convert")
public class ExcelController {

    private final ExcelToCsvService excelToCsvService;
    
    public ExcelController(ExcelToCsvService excelToCsvService) {
    	this.excelToCsvService = excelToCsvService;
	}

    @PostMapping("/xls-to-csv")
    public String convertXlsToCsv(@RequestParam("file") MultipartFile file) throws IOException {
        File tempXls = File.createTempFile("input", ".xls");
        file.transferTo(tempXls);

        File tempCsv = File.createTempFile("output", ".csv");
        excelToCsvService.convertXlsToCsv(tempXls, tempCsv);

        return "CSV file created at: " + tempCsv.getAbsolutePath();
    }
}
