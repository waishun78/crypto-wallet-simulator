 package com.appbackend.appbackend.business;

 import org.springframework.stereotype.Service;

 import com.appbackend.appbackend.repository.AssetRepository;
 import com.appbackend.appbackend.model.Asset;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Optional;


 /**
  * Business logic operations related to Asset domain type.
  */
 @Service
 public class AssetService extends AbstractCrudService<Asset, Long> {
     public AssetService(AssetRepository assetRepository) {
         super(assetRepository);
     }
     @Transactional
     public Collection<Asset> findAssetByUsername(String username) {
         Collection<Asset> fullList = repository.findAll();
         Collection<Asset> filteredList = new ArrayList<>();
         for (Asset a : fullList){
             if (a.getAccount().getUsername().equalsIgnoreCase(username)){
                 filteredList.add(a);
             }
         }
         return filteredList;
     }
     @Transactional
     public Asset updateByID(Asset assetDetails, Long id){
         Optional<Asset> optionalAsset = repository.findById(id);
         Asset assetToUpdate = optionalAsset.orElseThrow();
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
         this.update(assetToUpdate);
         return assetToUpdate;
     }
     @Transactional
     public Collection<Asset> deleteAssetByUsername(String username) {
         Collection<Asset> fullList = repository.findAll();
         Collection<Asset> removedList = new ArrayList<>();
         for (Asset a : fullList){
             if (a.getAccount().getUsername().equalsIgnoreCase(username)){
                 repository.delete(a);
                 removedList.add(a);
             }
         }
         return removedList;
     }
 }

