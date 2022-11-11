package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping
    public List<Asset> getAllAssets(){
        return assetRepository.findAll();
    }

    // create asset REST API
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset){
        return assetRepository.save(asset);
    }

    //get asset by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id){
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Asset %d does not exist","id")));
        return ResponseEntity.ok(asset);
    }

    //update asset REST API
    @PutMapping("{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset assetDetails){
        Asset assetToUpdate = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Asset %d does not exist","id")));
        assetToUpdate.setQuantity(124.4);
        assetToUpdate.setCryptoId(Long.valueOf(214335));
        assetToUpdate.setAccount(assetDetails.getAccount());

        assetRepository.save(assetDetails);

        return ResponseEntity.ok(assetToUpdate);
    }

    // delete asset REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable Long id){
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %d does not exist","id")));

        assetRepository.delete(asset);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
