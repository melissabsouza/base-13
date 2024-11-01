package br.com.fiap.projetos_api;

import br.com.fiap.projetos_api.model.Coverage;
import br.com.fiap.projetos_api.model.InsuranceContract;
import br.com.fiap.projetos_api.repository.InsuranceContractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DataJpaTest
public class ApiTest {

    public static final String CARRO_PLACA_XXX_9999 = "Carro placa XXX-9999";
    @Autowired
    private InsuranceContractRepository insuranceContractRepository;

    @Test
    void whenfindByEndCoverageTimeStampBefore_thenReturnInsuranceContracts(){

        InsuranceContract insuranceContract = new InsuranceContract();
        insuranceContract.setInsuranceObject(CARRO_PLACA_XXX_9999);
        insuranceContract.setSignTimeStamp(ZonedDateTime.now());
        insuranceContract.setEndCoverageTimeStamp(ZonedDateTime.now().plusYears(1));
        Coverage coverage = new Coverage();
        coverage.setAmount(new BigDecimal(123000));
        coverage.setDescription("Cobertura Terceiros");
        coverage.setInsuranceContract(insuranceContract);
        insuranceContract.getCoverageList().add(coverage);

        InsuranceContract savedInsuredContract = insuranceContractRepository.save(insuranceContract);

        assertThat(insuranceContract.getCoverageList()).hasSize(1);
        assertThat(insuranceContract.getInsuranceObject()).isEqualTo(CARRO_PLACA_XXX_9999);

    }


}
