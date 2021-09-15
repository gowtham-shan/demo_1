package com.example.demo_1.controller;

import com.example.demo_1.exceptions.ResourceNotFoundException;
import com.example.demo_1.model.PartnerTenure;
import com.example.demo_1.repository.PartnerRepository;
import com.example.demo_1.repository.PartnerTenureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tenures")
public class PartnerTenureController {

    @Autowired
    private PartnerTenureRepository partnerTenureRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @GetMapping
    public ResponseEntity<List<PartnerTenure>> getFaqByPartnerId(@RequestParam(name = "partnerId") Long partnerId){
        return ResponseEntity.ok(partnerTenureRepository.findPartnerTenureByPartnerId(partnerId));
    }


    @PostMapping
    public void addTenure(@Valid @RequestBody PartnerTenure partnerTenure){
        partnerTenureRepository.save(partnerTenure);
    }
    @PutMapping
    public ResponseEntity<PartnerTenure> updateTenure(@Valid @RequestBody PartnerTenure partnerTenure){
        return partnerTenureRepository.findById(partnerTenure.getId()).map(tenure -> {
            tenure.setMinTenure(partnerTenure.getMinTenure());
            tenure.setMaxTenure(partnerTenure.getMaxTenure());
            tenure.setInterestRate(partnerTenure.getInterestRate());
            tenure.setExcessInterestRate(partnerTenure.getExcessInterestRate());
            tenure.setTenureType(partnerTenure.getTenureType());
            return ResponseEntity.ok(partnerTenureRepository.save(tenure));
        }).orElseThrow(() -> new ResourceNotFoundException(" Tenure not found with id ["+ partnerTenure.getId() + "]"));
    }

    @DeleteMapping("/{tenureId}")
    public ResponseEntity<?> deleteTenure(@PathVariable(value = "tenureId") Long tenureId){
        if (!partnerTenureRepository.existsById(tenureId)){
            throw new ResourceNotFoundException("Tenure not found with id ["+ tenureId + "]");
        }
        partnerTenureRepository.deleteById(tenureId);
        return ResponseEntity.ok("Tenure with Id ["+ tenureId + "] deleted successfully");
    }
}
