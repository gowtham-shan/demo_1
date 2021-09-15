package com.example.demo_1.service;

import com.example.demo_1.exceptions.ResourceNotFoundException;
import com.example.demo_1.model.Faq;
import com.example.demo_1.model.Partner;
import com.example.demo_1.repository.FaqRepository;
import com.example.demo_1.repository.PartnerRepository;
import com.example.demo_1.repository.PartnerTenureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private PartnerTenureRepository partnerTenureRepository;

    public List<Partner> getAllPartners(Integer offset, Integer limit, String sortBy)
    {
        Pageable paging = PageRequest.of(offset, limit, Sort.by(sortBy));

        Page<Partner> pagedResult = partnerRepository.findAll(paging);

        return (pagedResult.hasContent()) ? pagedResult.getContent() : new ArrayList<>();
    }

    public Partner addPartner(Partner partner) {
        var createdPartner = partnerRepository.save(partner);
        var faqAddedSet = createdPartner.getFaqSet().stream().map(faq -> {
            faq.setPartner(createdPartner);
            return faq;
        }).collect(Collectors.toSet());
        faqRepository.saveAll(faqAddedSet);
        var tenureSet = createdPartner.getTenures().stream().map(partnerTenure -> {
            partnerTenure.setPartner(createdPartner);
            return partnerTenure;
        }).collect(Collectors.toSet());
        partnerTenureRepository.saveAll(tenureSet);
        return createdPartner;
    }

    public Partner updatePartner(Partner partner){
        return partnerRepository.findById(partner.getId()).map(partnerObj -> {
            partnerObj.setName(partner.getName());
            partnerObj.setType(partner.getType());
            partnerObj.setLogoUrl(partner.getLogoUrl());
            partnerObj.setAbout(partner.getAbout());
            partnerObj.setMinDepositAmount(partner.getMinDepositAmount());
            partnerObj.setLockInMonths(partner.getLockInMonths());
//            for (Faq faq:partner.getFaqSet()){
//                if(faq.getId()==null){
//                    // New Faq
//                    faq.setPartner(partnerObj);
//                    faqRepository.save(faq);
//                }else{
//
//                }
//            }
            return partnerRepository.save(partnerObj);
        }).orElseThrow(() -> new ResourceNotFoundException("Partner With Id " + partner.getId() + " not found"));
    }

    public ResponseEntity<?> deletePartner(Long partnerId){
        return partnerRepository.findById(partnerId).map(post -> {
            partnerRepository.delete(post);
            return ResponseEntity.ok("Deleted Successfully");
        }).orElseThrow(() -> new ResourceNotFoundException("Partner With Id " + partnerId + " not found"));
    }

    public void temp(){
        /*
            partner, investment, tenure, payout type, tenure type, payouttype, seniorCitizen
            P = investment
            N = (12/payouttype_value) * (tenure converted to year)
            r = interest rate from the tenure table
            t = tenure in years
            A = P (1+r/n) ^ (n * t)
         */
    }
}
