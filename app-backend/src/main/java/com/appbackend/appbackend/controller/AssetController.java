package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.business.AssetService;
import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequestMapping("api/v1/assets")
public class AssetController {

    @Autowired
    protected AssetService assetService;

    /**
     *
     * @param username / else all users is assumed
     * @return List<Asset> belonging to username
     */
    @GetMapping
    public List<Asset> getFilteredAssetByUsername(@RequestParam(value="username", required = false) String username){
        if (username == null){
            return StreamSupport.stream(assetService.readAll().spliterator(), false).collect(Collectors.toList());
        } else {
            return StreamSupport.stream(assetService.findAssetByUsername(username).spliterator(), false).collect(Collectors.toList());
        }
    }

    @DeleteMapping
    public ResponseEntity<Collection<Asset>> deleteByAccount(@RequestParam(value="username", required = false) String username){
        Collection<Asset> deletedAssets = assetService.deleteAssetByUsername(username);
        return new ResponseEntity<>(deletedAssets, HttpStatus.ACCEPTED);
    }

    // create asset REST API
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset){
//        System.out.println(asset.getAssetId());
        return assetService.create(asset);
    }

    //get asset by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id){
        Asset asset = assetService.readById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(asset);
    }

    //update asset REST API
    @PutMapping("{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset assetDetails){
        try{
            Asset assetToUpdate = assetService.updateByID(assetDetails, id);
            return ResponseEntity.ok(assetToUpdate);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete asset REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable Long id){
        assetService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    };
}
