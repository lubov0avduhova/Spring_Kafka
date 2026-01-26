package org.example.contentloaderadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.poi.ss.usermodel.*;
import org.example.contentloaderadapter.dto.EstateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelParsingService {
    private final KafkaTemplate<String, EstateDto> kafkaTemplate;
    private final DataFormatter formatter = new DataFormatter();

    @Value("${app.kafka.topic.estate-loader}")
    private String topicName;

    public void parseAndSend(MultipartFile file, String source) {
        System.out.println("Продюсер начал обработку");
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                EstateDto dto = mapRowToDto(row, source);

                var future = kafkaTemplate.send(topicName, dto.cadastr(), dto);

                future.whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.warn("Failed to send estate: {} {}", dto.cadastr(), ex);
                    }
                });

                Map<MetricName, ? extends Metric> metrics = kafkaTemplate.getProducerFactory()
                        .createProducer()
                        .metrics();

                metrics.forEach((name, metric) -> {
                    if ("batch-size-avg".equals(name.name())) {
                        System.out.println("Средний размер батча: " + metric.metricValue());
                    }
                    if ("records-per-batch-avg".equals(name.name())) {
                        System.out.println("Среднее кол-во записей в батче: " + metric.metricValue());
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении Excel файла: " + e.getMessage());
        }
    }

    private EstateDto mapRowToDto(Row row, String source) {
        String cadastr = formatter.formatCellValue(row.getCell(0));
        String type = formatter.formatCellValue(row.getCell(1));

        String squareStr = formatter.formatCellValue(row.getCell(2)).replace(",", ".");
        Double square = Double.valueOf(squareStr);

        String priceStr = formatter.formatCellValue(row.getCell(3)).replace(",", ".");
        BigDecimal price = new BigDecimal(priceStr);

        return new EstateDto(cadastr, type, square, price, source);
    }
}
