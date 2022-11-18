package com.appbackend.appbackend.controller.model.converter;

import com.appbackend.appbackend.controller.model.AssetDto;
import com.appbackend.appbackend.model.Asset;

import java.util.function.Function;

public class AssetToDtoConverter implements Function<Asset, AssetDto> {
    @Override
    public AssetDto apply(Asset asset) {
        return new AssetDto(asset.getAssetId(), asset.getAccount().getId(),asset.getCryptoId(), asset.getCryptoName(), asset.getQuantity());
    }
}
