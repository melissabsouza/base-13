package br.com.fiap.projetos_api.service.mapper;

import br.com.fiap.projetos_api.dto.InsuranceContractDTO;
import br.com.fiap.projetos_api.model.InsuranceContract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsuranceContractMapper {
    InsuranceContractDTO toDTO(InsuranceContract contract);

    InsuranceContract toEntity(InsuranceContractDTO contractDTO);

}
