package br.com.fiap.projetos_api.repository;

import br.com.fiap.projetos_api.model.InsuranceContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface InsuranceContractRepository extends JpaRepository<InsuranceContract, Long> {

    // List expired contracts
    // @Query("SELECT c FROM InsuranceContract c WHERE c.endCoverageTimeStamp < CURRENT_TIMESTAMP")
    // List<InsuranceContract> findExpiredContracts();

    // Find expired contracts using derived query
    List<InsuranceContract> findByEndCoverageTimeStampBefore(ZonedDateTime currentTime);

}
