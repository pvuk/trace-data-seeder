package com.nl.trace.dataseeder.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nl.trace.dataseeder.constants.Constants;
import com.nl.trace.dataseeder.service.ExcelToCsvService;

@RestController
@RequestMapping("/convert")
public class ExcelController {
	
	private final Logger LOG = LoggerFactory.getLogger(ExcelController.class);
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
    
    /**
     * Below Script should run to prepare all filepaths from Folder. Execute it in Powershell
     * ----
     * Get-ChildItem -Path "D:\data-file-base\read-files\india-ifsc-codes-2-1510j" -File | Select-Object -ExpandProperty FullName | Out-File D:\data-file-base\read-files\india-ifsc-codes-2-1510j\filelist.csv -Encoding ascii
     * ----
     * 
     * @author Venkata.Pulipati
     * @since Friday 06-March-2026 18:22:17
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload-multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @GetMapping(value = "/upload-multiple")
	public ResponseEntity<Map<String, Object>> uploadMultiple(@RequestParam(name = "file") MultipartFile[] file,
			@RequestParam(value = "path") String path) throws IOException {


//        @RequestPart("files") List<MultipartFile> files
        long totalSize = 0L;
        Path parentPath = Paths.get(path).getParent();
        for (MultipartFile f : file) {
            if (!f.isEmpty()) {
                Path target = Path.of(parentPath.toString() + Constants.FILE.UPLOADS).resolve(f.getOriginalFilename());
                LOG.info("Reading FileName: {}, Path: {}", f.getOriginalFilename(), parentPath.toString());
                
                //Files.createDirectories(target.getParent());
                try (var in = f.getInputStream()) {
                    //Files.copy(in, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
                totalSize += f.getSize();
            }
        }
        return ResponseEntity.ok(Map.of("count", file.length, "totalSize", totalSize));
//        return ResponseEntity.ok(Map.of("Success", "Test File Upload Response"));
    }
    
    @GetMapping("/testAPI")
    public ResponseEntity<String> testAPI() {
    	return ResponseEntity.ok("Hello Trace-Data-Seeder !");
    }
}
