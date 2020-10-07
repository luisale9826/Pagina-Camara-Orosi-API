package com.camaraturismoorosi.apicamaraturismoorosi.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class FirebaseConfig {

    @PostConstruct
    public void firebaseInitializer() {

        try {
            FileInputStream serviceAccount = new FileInputStream(
                    "credentials.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Connected!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Firestore getFirebaseDatabase() {
        return FirestoreClient.getFirestore();
    }

}
