package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.model.Snapshot;
import com.appbackend.appbackend.repository.AccountRepository;
import com.appbackend.appbackend.repository.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/snapshots")
public class SnapshotController {

    @Autowired
    private SnapshotRepository snapshotRepository;

    @GetMapping
    public List<Snapshot> getFilteredSnapshotsByUsername(@RequestParam(value="username", required = false) String username){
        List<Snapshot> fullList = snapshotRepository.findAll();
        if (username == null){
            return fullList;
        }
        List<Snapshot> filteredList = new ArrayList<>();
        for (Snapshot s : fullList){
            if (s.getAccount().getUsername().equalsIgnoreCase(username)){
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    // create snapshot REST API
    @PostMapping
    public Snapshot createSnapshot(@RequestBody Snapshot snapshot){
        return snapshotRepository.save(snapshot);
    }

    //get snapshot by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Snapshot> getSnapshotById(@PathVariable Long id){
        Snapshot snapshot = snapshotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Snapshot %s does not exist","id")));
        return ResponseEntity.ok(snapshot);
    }

    //update snapshot REST API
    @PutMapping("{id}")
    public ResponseEntity<Snapshot> updateSnapshot(@PathVariable Long id, @RequestBody Snapshot snapshotDetails){
        Snapshot snapshotToUpdate = snapshotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %d does not exist","id")));

        if (snapshotDetails.getSnapshotTime() != null){
            snapshotToUpdate.setSnapshotTime(snapshotDetails.getSnapshotTime());
        }
        if (snapshotDetails.getAccountBalance() != null){
            snapshotToUpdate.setAccountBalance(snapshotDetails.getAccountBalance());
        }
        if (snapshotDetails.getAssetValue()!= null){
            snapshotToUpdate.setAssetValue(snapshotDetails.getAssetValue());
        }
        snapshotRepository.save(snapshotToUpdate);

        return ResponseEntity.ok(snapshotToUpdate);
    }

    // delete snapshot REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Snapshot> deleteSnapshot(@PathVariable Long id){
        Snapshot snapshot = snapshotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %d does not exist","id")));

        snapshotRepository.delete(snapshot);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
