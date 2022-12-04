package com.appbackend.appbackend.controller.model_archived.converter;

import com.appbackend.appbackend.controller.model_archived.SnapshotDto;
import com.appbackend.appbackend.model.Snapshot;

import java.util.function.Function;

public class SnapshotToDtoController implements Function<Snapshot, SnapshotDto> {
    @Override
    public SnapshotDto apply(Snapshot snapshot) {
        return new SnapshotDto(snapshot.getSnapshotId(), snapshot.getAccount().getId(), snapshot.getAssetValue(), snapshot.getAccountBalance(), snapshot.getSnapshotTime());
    }
}
