package com.appbackend.appbackend.controller.model_archived.converter;

import com.appbackend.appbackend.controller.model_archived.AssetDto;
import com.appbackend.appbackend.model.Asset;

import java.util.function.Function;

public class AssetToDtoConverter implements Function<Asset, AssetDto> {
    @Override
    public AssetDto apply(Asset asset) {
        return new AssetDto(asset.getAssetId(), asset.getAccount().getId(),asset.getCryptoId(), asset.getCryptoName(), asset.getQuantity());
    }
}
