package com.camaraturismoorosi.apicamaraturismoorosi.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class FirebaseConfig {

    private FirebaseApp firebaseApp;

    @PostConstruct
    private FirebaseApp firebaseInitializer() {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("credentials.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            firebaseApp = FirebaseApp.initializeApp(options);
            System.out.println("CONNECTED!!!");
            return firebaseApp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Firestore getInstance() {
        return FirestoreClient.getFirestore(firebaseApp);
    }

    @PreDestroy
    private void firebaseCloser() {
        firebaseApp.delete();
    } 

}
