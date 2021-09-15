package com.example.demo_1.controller;

import com.example.demo_1.model.Partner;
import com.example.demo_1.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public ResponseEntity<List<Partner>> getAllPartners(@RequestParam(defaultValue = "0") Integer offset,
                                                        @RequestParam(defaultValue = "10") Integer limit,
                                                        @RequestParam(defaultValue = "id") String sortBy){
        return ResponseEntity.ok(partnerService.getAllPartners(offset, limit, sortBy));
    }

    @PostMapping
    public ResponseEntity<Partner> addPartner(@Valid @RequestBody Partner partner){
        var createdPartner = partnerService.addPartner(partner);
        return ResponseEntity.ok(createdPartner);
    }

    @PutMapping
    public ResponseEntity<Partner> updatePartner(@Valid @RequestBody Partner partner){
        return ResponseEntity.ok(partnerService.updatePartner(partner));
    }

    @DeleteMapping("/{partnerId}")
    public ResponseEntity<?> deletePartner(@PathVariable Long partnerId) {
        return partnerService.deletePartner(partnerId);
    }
}
