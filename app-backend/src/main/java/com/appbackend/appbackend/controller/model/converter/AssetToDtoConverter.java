//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.AssetDto;
//import com.appbackend.appbackend.model.Asset;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Function;
//
//@Component
//public class AssetToDtoConverter implements Function<Asset, AssetDto> {
//    @Override
//    public AssetDto apply(Asset asset) {
//        AssetDto assetdto = new AssetDto();
//        assetdto.setAssetId(asset.getAssetId());
//        assetdto.setAccount(asset.getAccount().getId());
//        assetdto.setCryptoName(asset.getCryptoName());
//        assetdto.setCryptoId(asset.getCryptoId());
//        assetdto.setQuantity(asset.getQuantity());
//        return assetdto;
//    }
//}
