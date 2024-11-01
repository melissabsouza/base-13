package br.com.fiap.projetos_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "tb_insurance_contract")
public class InsuranceContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "insurance_object", length = 100, nullable = false)
    private String insuranceObject;

    @Column(name="sign_timestamp")
    private ZonedDateTime signTimeStamp;

    @Column(name="end_coverage_timestamp")
    private ZonedDateTime endCoverageTimeStamp;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="insurance_contract_coverage_id")
    private List<Coverage> coverageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="insurance_contract_claim_id")
    private List<Claim> claimList = new ArrayList<>();

    @ManyToOne
    private Client client;

}
