package br.com.fiap.projetos_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoverageDTO {
    private long id;
    private String description;
    private BigDecimal amount;
}
