package com.example.demo_1.controller;

import com.example.demo_1.dto.FaqDTO;
import com.example.demo_1.exceptions.ResourceNotFoundException;
import com.example.demo_1.model.Faq;
import com.example.demo_1.repository.FaqRepository;
import com.example.demo_1.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @GetMapping
    public ResponseEntity<List<Faq>> getFaqByPartnerId(@RequestParam(name = "partnerId") Long partnerId){
        return ResponseEntity.ok(faqRepository.findFaqByPartnerId(partnerId));
    }

    @PostMapping
    public ResponseEntity<Faq> addFaq(@Valid @RequestBody FaqDTO faqDTO){
        return partnerRepository.findById(faqDTO.getPartner().getId()).map(partner -> {
            Faq newFaq = faqDTO.getFaq();
            newFaq.setPartner(partner);
            return ResponseEntity.ok(faqRepository.save(newFaq));
        }).orElseThrow(() -> new ResourceNotFoundException("Partner  " + faqDTO.getPartner().getId() + " not found"));
    }

    @PutMapping
    public ResponseEntity<Faq> updateFaq(@Valid @RequestBody FaqDTO faqDTO){
        if(!partnerRepository.existsById(faqDTO.getPartner().getId())){
            throw new ResourceNotFoundException("Partner with Id [" + faqDTO.getPartner().getId() + "] not found");
        }
        return faqRepository.findById(faqDTO.getFaq().getId()).map(faq -> {
            faq.setTitle(faqDTO.getFaq().getTitle());
            faq.setDescription(faqDTO.getFaq().getDescription());
//            faq.setPartner(faqDTO.getPartner());
            return ResponseEntity.ok(faqRepository.save(faq));
        }).orElseThrow(()-> new ResourceNotFoundException("Partner with Id [" + faqDTO.getFaq().getId() + "] not found"));
    }

    @DeleteMapping("/{faqId}")
    public ResponseEntity<?> deleteFaq(@PathVariable(value = "faqId") Long faqId){
        return faqRepository.findById(faqId).map(faq -> {
            faqRepository.delete(faq);
            return ResponseEntity.ok("Record Deleted Successfully");
        }).orElseThrow(() -> new ResourceNotFoundException("Faq not found with id " + faqId));

    }
}
