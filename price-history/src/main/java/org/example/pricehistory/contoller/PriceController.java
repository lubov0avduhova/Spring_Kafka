package org.example.pricehistory.contoller;

import lombok.RequiredArgsConstructor;
import org.example.pricehistory.dto.PriceHistoryDto;
import org.example.pricehistory.service.PriceHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {
    private final PriceHistoryService priceHistoryService;

    @GetMapping
    public List<PriceHistoryDto> getAllPrices(){
        return priceHistoryService.getAllPrices();
    }
}
