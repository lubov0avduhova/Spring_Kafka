package org.example.pricehistory.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history", indexes = {
        @Index(name = "idx_cadastr_source", columnList = "cadastr, source")
})
@Data
@NoArgsConstructor
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_hist_seq")
    @SequenceGenerator(name = "price_hist_seq", allocationSize = 1)
    private Long id;

    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;
    private String source;
    private LocalDateTime createdAt;

    public PriceHistory(String cadastr, String type, Double square, BigDecimal price, String source, LocalDateTime createdAt) {
        this.cadastr = cadastr;
        this.type = type;
        this.square = square;
        this.price = price;
        this.source = source;
        this.createdAt = createdAt;
    }
}