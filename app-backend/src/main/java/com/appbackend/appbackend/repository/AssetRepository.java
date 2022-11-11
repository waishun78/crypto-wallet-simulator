package com.appbackend.appbackend.repository;


import com.appbackend.appbackend.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    // all crud database methods
}
