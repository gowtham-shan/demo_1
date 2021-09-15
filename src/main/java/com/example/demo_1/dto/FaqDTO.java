package com.example.demo_1.dto;

import com.example.demo_1.model.Faq;
import com.example.demo_1.model.Partner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqDTO {
    private Faq faq;
    private Partner partner;
}
