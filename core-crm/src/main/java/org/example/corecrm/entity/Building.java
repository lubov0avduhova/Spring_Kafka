package org.example.corecrm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;

    private String source;

    @Version
    private Long version;

    private String assignedManager;
}
