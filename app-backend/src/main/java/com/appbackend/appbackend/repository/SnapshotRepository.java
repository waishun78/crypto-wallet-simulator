package com.appbackend.appbackend.repository;


import com.appbackend.appbackend.model.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {
    // all crud database methods
}
