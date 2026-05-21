package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper,
                             EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void addCredential(Credential credential) {
        String key = encryptionService.generateKey();
        credential.setKey(key);
        credential.setPassword(
                encryptionService.encryptValue(credential.getPassword(), key));

        credentialMapper.insert(credential);
    }

    public List<Credential> getCredentials(int userId) {
        return credentialMapper.getCredentials(userId);
    }
}