package br.com.fiap.projetos_api.dto;

import org.springframework.hateoas.Link;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceContractDTO extends RepresentationModel<InsuranceContractDTO> {
    private long id;
    @NotNull(message = "O Objeto do Contrato não pode ser nulo")
    private String insuranceObject;
    private ZonedDateTime signTimeStamp;
    private ZonedDateTime endCoverageTimeStamp;
    private List<CoverageDTO> coverageList;
    private List<ClaimDTO> claimList;
    private ClientDTO client;
}
