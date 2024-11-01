package br.com.fiap.projetos_api.controller;

import br.com.fiap.projetos_api.dto.ClientDTO;
import br.com.fiap.projetos_api.dto.InsuranceContractDTO;
import br.com.fiap.projetos_api.service.ClientService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// Controller Class
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(
            @PathVariable long id
    ) {
        Optional<ClientDTO> clientDTOOption = clientService.findById(id);

        return clientDTOOption.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

}
