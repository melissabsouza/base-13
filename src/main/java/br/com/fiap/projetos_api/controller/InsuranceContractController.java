package br.com.fiap.projetos_api.controller;

import br.com.fiap.projetos_api.dto.InsuranceContractDTO;
import br.com.fiap.projetos_api.service.InsuranceContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


// Controller Class
@RestController
@RequestMapping("/insurancecontracts")
@RequiredArgsConstructor
public class InsuranceContractController {

    private final InsuranceContractService insuranceContractService;

    // Create a new insurance contract
    @Operation(summary = "Cria novo contrato de seguro", description = "Retorno é um contrato com seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public EntityModel<InsuranceContractDTO> createInsuranceContract(@RequestBody InsuranceContractDTO contractDTO) {
        InsuranceContractDTO savedContract = insuranceContractService.saveInsuranceContract(contractDTO);

        EntityModel<InsuranceContractDTO> model = EntityModel.of(savedContract);

        Link linkToAll = WebMvcLinkBuilder.linkTo(methodOn(InsuranceContractController.class).getAllInsuranceContracts()).withRel("allContracts");
        model.add(linkToAll);

        Link linkSelf = WebMvcLinkBuilder.linkTo(methodOn(InsuranceContractController.class).getInsuranceContractById(savedContract.getId())).withSelfRel();
        model.add(linkSelf);

        return model;
    }

    // Get an insurance contract by ID
    @Operation(summary = "Busca de contratos por id", description = "Busca de contratos por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contrato localizado"),
            @ApiResponse(responseCode = "404", description = "Contrato não localizado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public EntityModel<InsuranceContractDTO> getInsuranceContractById(
            @Parameter(description = "ID do contrato de seguro a ser recuperado", required = true)
            @PathVariable long id
    ) {
        Optional<InsuranceContractDTO> contractOpt = insuranceContractService.getInsuranceContractById(id);

        if (contractOpt.isPresent()) {
            InsuranceContractDTO contractDTO = contractOpt.get();
            EntityModel<InsuranceContractDTO> model = EntityModel.of(contractDTO);
            Link link = WebMvcLinkBuilder.linkTo(methodOn(InsuranceContractController.class).getAllInsuranceContracts()).withRel("allContracts");
            Link linkSelf = WebMvcLinkBuilder.linkTo(methodOn(InsuranceContractController.class).getInsuranceContractById(id)).withSelfRel();
            model.add(link);
            model.add(linkSelf);
            return model;
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    // Get all insurance contracts
    @GetMapping
    public CollectionModel<InsuranceContractDTO> getAllInsuranceContracts() {
        List<InsuranceContractDTO> contracts = insuranceContractService.getAllInsuranceContracts();

        Link link = WebMvcLinkBuilder.linkTo(InsuranceContractController.class).withRel("allContracts");
        CollectionModel<InsuranceContractDTO> results = CollectionModel.of(contracts, link);

        return results;
    }

    // Get expired insurance contracts
    @GetMapping("/expired")
    public ResponseEntity<List<InsuranceContractDTO>> getExpiredContracts() {
        List<InsuranceContractDTO> expiredContracts = insuranceContractService.getExpiredContracts();
        return ResponseEntity.ok(expiredContracts);
    }

    // Delete an insurance contract by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceContractById(@PathVariable long id) {
        insuranceContractService.deleteInsuranceContractById(id);
        return ResponseEntity.noContent().build();
    }
}