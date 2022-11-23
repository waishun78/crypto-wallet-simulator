package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    /**
     *
     * @param username / else all users is assumed
     * @return List<Asset> belonging to username
     */
    @GetMapping
    public List<Asset> getFilteredAssetByUsername(@RequestParam(value="username", required = false) String username){

        List<Asset> fullList = assetRepository.findAll();
        if (username == null){
            return fullList;
        }
        List<Asset> filteredList = new ArrayList<>();
        for (Asset a : fullList){
            if (a.getAccount().getUsername().equalsIgnoreCase(username)){
                filteredList.add(a);
            }
        }
        return filteredList;
    }

    @DeleteMapping
    public ResponseEntity<Collection<Asset>> deleteByAccount(@RequestParam(value="username", required = false) String username){

        List<Asset> fullList = assetRepository.findAll();
        if (username == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        List<Asset> filteredList = new ArrayList<>();
        for (Asset a : fullList){
            if (a.getAccount().getUsername().equalsIgnoreCase(username)){
                assetRepository.delete(a);
            }
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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
        if (assetDetails.getQuantity() != null) {
            assetToUpdate.setQuantity(assetDetails.getQuantity());
        }
        if (assetDetails.getCryptoId() != null){
            assetToUpdate.setCryptoId(assetDetails.getCryptoId());
        }
        if (assetDetails.getCryptoName() != null){
            assetToUpdate.setCryptoName(assetDetails.getCryptoName());
        }
        if (assetDetails.getAccount() != null){
            assetToUpdate.setAccount(assetDetails.getAccount());
        }

        assetRepository.save(assetDetails);

        return ResponseEntity.ok(assetToUpdate);
    }

    // delete asset REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable Long id){
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %d does not exist","id")));

        assetRepository.delete(asset);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    };
}
