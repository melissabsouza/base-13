package br.com.fiap.projetos_api.model;

import br.com.fiap.projetos_api.model.enums.ClaimStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Data
@ToString
@Entity
@Table(name = "tb_claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(name="claim_date", insertable = false, updatable = false)
    private ZonedDateTime claimDate;

    @Column(name = "claim_text", length = 100, nullable = false)
    private String claimText;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private ClaimStatus status;

    @ManyToOne
    @JoinColumn(name = "insurance_contract_claim_id", nullable = false)
    private InsuranceContract insuranceContract;

}
