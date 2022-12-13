package com.appbackend.appbackend;

import com.appbackend.appbackend.model.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.*;

import static org.junit.Assert.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class AccountsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        RequestBuilder createAccountRequest = MockMvcRequestBuilders.post("/api/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user1\", \"notes\": \"user1 notes\",\"accountBalance\": \"10.0\"}");
        MvcResult createAccountResult = mockMvc.perform(createAccountRequest).andReturn();
        String createAccountResponse = createAccountResult.getResponse().getContentAsString();

    }

    @Test
    public void createAccount() throws Exception {
        RequestBuilder getAccountsRequest = MockMvcRequestBuilders.get("/api/v1/accounts");
        MvcResult getAccountsResult = mockMvc.perform(getAccountsRequest).andReturn();

        String getAccountsResponse = getAccountsResult.getResponse().getContentAsString();

        // Create a way to convert string json into an ArrayList<HashMap<>> object to compare
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<HashMap<String,Object>>>() {}.getType();
        ArrayList<Map> itemList = gson.fromJson(getAccountsResponse, listType);

        // Create the intended output of the request
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("username","user1");
        map1.put("notes","user1 notes");
        map1.put("accountBalance", 10.0);
        ArrayList<Map> intendedList = new ArrayList<>();
        intendedList.add(map1);

        assertEquals(intendedList, itemList);
    }

    @Test
    public void updateAccount() throws Exception {

        RequestBuilder putAccountRequest = MockMvcRequestBuilders.put("/api/v1/accounts/user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"notes\": \"updated user1 notes\",\"accountBalance\": \"15.0\"}");

        MvcResult putAccountResult = mockMvc.perform(putAccountRequest).andReturn();
        RequestBuilder getAccountRequest = MockMvcRequestBuilders.get("/api/v1/accounts/user1");

        MvcResult getAccountResult = mockMvc.perform(getAccountRequest).andReturn();
        String getAccountResponse = getAccountResult.getResponse().getContentAsString();


        // Create a way to convert string json into an ArrayList<HashMap<>> object to compare
        Gson gson = new Gson();
        Type listType = new TypeToken<HashMap<String,Object>>() {}.getType();
        Map responseMap = gson.fromJson(getAccountResponse, listType);

        // Create the intended output of the request
        HashMap<String, Object> intendedMap = new HashMap<>();
        intendedMap.put("username","user1");
        intendedMap.put("notes","updated user1 notes");
        intendedMap.put("accountBalance", 15.0);
//        ArrayList<Map> intendedList = new ArrayList<>();
//        intendedList.add(map1);

        assertEquals(intendedMap, responseMap);
    }

    @Test
    public void deleteAccount() throws Exception {

        RequestBuilder delAccountRequest = MockMvcRequestBuilders.delete("/api/v1/accounts/user1");

        MvcResult delAccountResult = mockMvc.perform(delAccountRequest).andReturn();
        RequestBuilder getAccountRequest = MockMvcRequestBuilders.get("/api/v1/accounts/user1");

        MvcResult getAccountResult = mockMvc.perform(getAccountRequest).andReturn();
        String getAccountResponse = getAccountResult.getResponse().getContentAsString();

        // Create a way to convert string json into an ArrayList<HashMap<>> object to compare
        Gson gson = new Gson();
        Type listType = new TypeToken<HashMap<String,Object>>() {}.getType();
        Map responseMap = gson.fromJson(getAccountResponse, listType);

        assertEquals(null, responseMap);
    }

}
