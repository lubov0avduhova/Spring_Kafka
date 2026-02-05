package org.example.contentloaderadapter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.contentloaderadapter.service.ExcelParsingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MultipartFileController {
    private final ExcelParsingService excelService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("source") String source) {
        log.info("Продюсер получил данные");

        excelService.parseAndSend(file, source);
        return ResponseEntity.ok("Файл из источника " + source + " принят в обработку");
    }
}
