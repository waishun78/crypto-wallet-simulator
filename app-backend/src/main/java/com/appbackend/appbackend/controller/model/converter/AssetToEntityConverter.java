//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.AssetDto;
//import com.appbackend.appbackend.model.Account;
//import com.appbackend.appbackend.model.Asset;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Function;
//
//@Component
//public class AssetToEntityConverter implements Function<AssetDto, Asset> {
//    @Override
//    public Asset apply(AssetDto assetDto) {
//        Asset asset = new Asset();
//        asset.setAssetId(assetDto.getAssetId());
//        asset.setAccount(/** TODO: Get account reference here **/);
//        asset.setCryptoName(asset.getCryptoName());
//        asset.setCryptoId(asset.getCryptoId());
//        asset.setQuantity(asset.getQuantity());
//        return asset;
//    }
//}
