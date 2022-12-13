package com.appbackend.appbackend.business;

import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.repository.AssetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// Unit Test for Asset Service
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetServiceTest {

    @Autowired
    AssetService assetService;

    @MockBean
    AssetRepository assetRepository;

    @Test
    public void testUpdateByID(){
        Long assetId = new Long(10);
        Asset assetPosted = new Asset();
        assetPosted.setAssetId(assetId);
        assetPosted.setCryptoId("bitcoin");

        Account account1 = new Account();
        account1.setUsername("test1");
        account1.setAccountBalance(10.0);
        account1.setNotes("test1 notes");

        Asset assetInRepo = new Asset();
        assetInRepo.setAssetId(assetId);
        assetInRepo.setCryptoId("dogecoin");
        assetInRepo.setAccount(account1);
        assetInRepo.setQuantity(10.55);
        assetInRepo.setCryptoName("bitcoin");

        Asset assetUpdated = new Asset();
        assetUpdated.setAssetId(assetId);
        assetUpdated.setCryptoId("bitcoin");
        assetUpdated.setAccount(account1);
        assetUpdated.setQuantity(10.55);
        assetUpdated.setCryptoName("bitcoin");

        // In service.update()
        Mockito.when(assetRepository.existsById(assetId)).thenReturn(true);
        Mockito.when(assetRepository.save(assetInRepo)).thenReturn(assetInRepo);
        Mockito.when(assetRepository.findById(assetId)).thenReturn(Optional.of(assetInRepo));
        Mockito.when(assetService.update(assetInRepo)).thenReturn(assetUpdated);

        assertThat(assetService.updateByID(assetPosted,assetId).isEqualTo(assetInRepo));

    }

    @Test
    public void testFindAssetByUsername(){
        Account account1 = new Account();
        account1.setUsername("test1");
        Account account2 = new Account();
        account2.setUsername("test2");
        Account account3 = new Account();
        account3.setUsername("test3");

        Long assetId1 = new Long(10);
        Asset asset1 = new Asset();
        asset1.setAssetId(assetId1);
        asset1.setAccount(account1);
        Long assetId2 = new Long(10);
        Asset asset2 = new Asset();
        asset2.setAssetId(assetId2);
        asset2.setAccount(account2);
        Long assetId3 = new Long(10);
        Asset asset3 = new Asset();
        asset3.setAssetId(assetId3);
        asset3.setAccount(account3);


        ArrayList<Asset> fullList = new ArrayList<>(
                Arrays.asList(asset1,asset2,asset3)
        );

        Mockito.when(assetRepository.findAll()).thenReturn(fullList);
        assertThat(assetService.findAssetByUsername("test2").containsAll(new ArrayList<>(Arrays.asList(account2))));

    }
}
