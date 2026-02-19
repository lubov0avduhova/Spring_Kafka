package org.example.contentprocessor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "estate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estate_seq")
    @SequenceGenerator(name = "estate_seq", sequenceName = "estate_sequence", allocationSize = 50)
    private Long id;
    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;
    private String source;

}
