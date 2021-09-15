package com.example.demo_1.model;

import com.example.demo_1.constants.TenureType;
import com.example.demo_1.constants.TenureTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "partner_tenure")
@Getter
@Setter
public class PartnerTenure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int minTenure;
    private int maxTenure;
    private float interestRate;
    private float excessInterestRate;

    @Convert(converter = TenureTypeConverter.class)
    private TenureType tenureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    @JsonIgnore
    private Partner partner;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PartnerTenure)) return false;
        return id == ((PartnerTenure) obj).getId();
    }
}

