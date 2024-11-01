package br.com.fiap.projetos_api.service;

import br.com.fiap.projetos_api.dto.ClientDTO;
import br.com.fiap.projetos_api.repository.ClientRepository;
import br.com.fiap.projetos_api.service.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Optional<ClientDTO> findById(long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO);
    }
}
