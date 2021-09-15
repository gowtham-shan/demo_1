package com.example.demo_1.repository;

import com.example.demo_1.model.PartnerTenure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerTenureRepository extends JpaRepository<PartnerTenure, Long> {

    List<PartnerTenure> findPartnerTenureByPartnerId(Long partnerId);
}
