package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TokenStoreService {
    private Map<String, String> tokenStore = new HashMap<>();


    public Set<String> getEmails(){
        return tokenStore.keySet();
    }public Map<String,String> getTokens(){
        return tokenStore;
    }
    public void storeToken(String email, String token){
        tokenStore.put(email,token);
    }
    public String getToken(String email){
        return tokenStore.get(email);
    }
    public void removeToken(String email){
        tokenStore.remove(email);
    }
    public boolean isTokenValid(String email,String token){
        return token.equals(tokenStore.get(email));
    }

}
