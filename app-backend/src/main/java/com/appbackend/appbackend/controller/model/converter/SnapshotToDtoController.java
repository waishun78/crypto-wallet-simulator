package com.appbackend.appbackend.controller.model.converter;

import com.appbackend.appbackend.controller.model.SnapshotDto;
import com.appbackend.appbackend.model.Snapshot;

import java.util.function.Function;

public class SnapshotToDtoController implements Function<Snapshot, SnapshotDto> {
    @Override
    public SnapshotDto apply(Snapshot snapshot) {
        return new SnapshotDto(snapshot.getSnapshotId(), snapshot.getAccount().getId(), snapshot.getAssetValue(), snapshot.getAccountBalance(), snapshot.getSnapshotTime());
    }
}
