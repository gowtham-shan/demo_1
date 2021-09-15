package com.example.demo_1.model;

import com.example.demo_1.constants.PartnerType;
import com.example.demo_1.constants.PartnerTypeConverter;
import com.example.demo_1.constants.PayOutType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "partner")
@Getter
@Setter
public class Partner extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = PartnerTypeConverter.class)
    private PartnerType type;
    private String logoUrl;
    private String about;
    private double minDepositAmount;
    private int lockInMonths;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "partner")
    private Set<Faq> faqSet =  new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "partner")
    private Set<PartnerTenure> tenures = new HashSet<>();

}
