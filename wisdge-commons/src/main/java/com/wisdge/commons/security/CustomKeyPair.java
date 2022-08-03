package com.wisdge.commons.security;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
@Slf4j
@ConfigurationProperties(prefix = "key-pair")
public class CustomKeyPair {
    private String file;
    private String alias;
    private String key;

    @Setter(AccessLevel.NONE)
    private KeyPair keyPair;

    public KeyPair getKeyPair() {
        if (keyPair == null) {
            log.debug("Create keyPair from classpath {}", file);
            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(file), key.toCharArray());
            keyPair = keyStoreKeyFactory.getKeyPair(alias, key.toCharArray());
            log.debug("Create keyPair succeed");
        }
        return keyPair;
    }

    public PublicKey getPublic() {
        return getKeyPair().getPublic();
    }

    public PrivateKey getPrivate() {
        return getKeyPair().getPrivate();
    }

    public String getPrivateString() {
        return Base64.getEncoder().encodeToString(getPrivate().getEncoded());
    }

    public String getPublicString() {
        return Base64.getEncoder().encodeToString(getPublic().getEncoded());
    }

    public static String getPrivate(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    public static String getPublic(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

}
