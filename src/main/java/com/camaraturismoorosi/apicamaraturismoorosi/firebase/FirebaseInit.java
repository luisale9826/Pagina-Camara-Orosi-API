package com.camaraturismoorosi.apicamaraturismoorosi.firebase;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "firebase")
@Configuration
public class FirebaseInit {

    private String credentials;
    private String bucket;

    public FirebaseInit() {
    }

    public String getCredentials() {
        return credentials;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

}
