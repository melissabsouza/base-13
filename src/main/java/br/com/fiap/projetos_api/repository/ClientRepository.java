package br.com.fiap.projetos_api.repository;

import br.com.fiap.projetos_api.model.Client;
import br.com.fiap.projetos_api.model.InsuranceContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
