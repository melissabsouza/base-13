package br.com.fiap.projetos_api.service;

import br.com.fiap.projetos_api.dto.InsuranceContractDTO;
import br.com.fiap.projetos_api.model.InsuranceContract;
import br.com.fiap.projetos_api.repository.InsuranceContractRepository;
import br.com.fiap.projetos_api.service.mapper.InsuranceContractMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class InsuranceContractService {

    private final InsuranceContractRepository insuranceContractRepository;

    private final InsuranceContractMapper insuranceContractMapper;

    // Save an insurance contract
    public InsuranceContractDTO saveInsuranceContract(InsuranceContractDTO contractDTO) {
        InsuranceContract contract = insuranceContractMapper.toEntity(contractDTO);
        InsuranceContract savedContract = insuranceContractRepository.save(contract);
        return insuranceContractMapper.toDTO(savedContract);
    }

    // Find by ID
    public Optional<InsuranceContractDTO> getInsuranceContractById(long id) {
        return insuranceContractRepository.findById(id)
                .map(insuranceContractMapper::toDTO);
    }

    // Find expired contracts
    public List<InsuranceContractDTO> getExpiredContracts() {
    	//insuranceContractRepository
        //        .findByEndCoverageTimeStampBefore(ZonedDateTime.now())
        //return insuranceContractRepository.findExpiredContracts()
        return insuranceContractRepository.findByEndCoverageTimeStampBefore(ZonedDateTime.now())
                .stream()
                .map(insuranceContractMapper::toDTO)
                .toList();
    }

    // Delete a contract by ID
    public void deleteInsuranceContractById(long id) {
        insuranceContractRepository.deleteById(id);
    }

    // List all insurance contracts
    public List<InsuranceContractDTO> getAllInsuranceContracts() {
        return insuranceContractRepository.findAll()
                .stream()
                .map(insuranceContractMapper::toDTO)
                .toList();
    }

}
