package br.com.fiap.projetos_api.service.mapper;

import br.com.fiap.projetos_api.dto.ClientDTO;
import br.com.fiap.projetos_api.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO clientDTO);

    ClientDTO toDTO(Client client);

}
