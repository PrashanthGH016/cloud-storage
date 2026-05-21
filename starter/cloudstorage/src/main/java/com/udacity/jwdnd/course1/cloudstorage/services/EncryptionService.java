package com.udacity.jwdnd.course1.cloudstorage.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;

@Service
public class EncryptionService {

    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    // ✅ Generate AES key (16 bytes = 128-bit)
    public String generateKey() {
        byte[] key = new byte[16];
        new java.security.SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    // ✅ Encrypt
    public String encryptValue(String data, String key) {

        byte[] encryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            SecretKey secretKey =
                    new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | UnsupportedEncodingException |
                 IllegalBlockSizeException | BadPaddingException e) {

            logger.error("Error encrypting data: " + e.getMessage());
        }

        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    // ✅ Decrypt
    public String decryptValue(String data, String key) {

        byte[] decryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            SecretKey secretKey =
                    new SecretKeySpec(Base64.getDecoder().decode(key), "AES");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            decryptedValue =
                    cipher.doFinal(Base64.getDecoder().decode(data));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {

            logger.error("Error decrypting data: " + e.getMessage());
        }

        return new String(decryptedValue);
    }
}
