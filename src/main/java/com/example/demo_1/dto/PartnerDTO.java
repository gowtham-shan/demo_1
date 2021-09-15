package com.example.demo_1.dto;

import com.example.demo_1.model.Partner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PartnerDTO {

    private List<Partner> partners;

    public PartnerDTO(){

    }

    public PartnerDTO(List<Partner> partners) {
        this.partners = partners;
    }

}
