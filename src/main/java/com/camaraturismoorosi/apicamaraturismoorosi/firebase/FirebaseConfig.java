package com.camaraturismoorosi.apicamaraturismoorosi.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;

import org.springframework.stereotype.Service;

@Service
public class FirebaseConfig {

    private FirebaseApp firebaseApp;

    @PostConstruct
    private FirebaseApp firebaseInitializer() {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("sitio-web-camara-orosi-firebase-credentials.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setStorageBucket("sitio-web-camara-orosi.appspot.com")
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            firebaseApp = FirebaseApp.initializeApp(options);
            System.out.println("CONNECTED!!!");
            return firebaseApp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Firestore getFirestoreInstance() {
        return FirestoreClient.getFirestore(firebaseApp);
    }

    public Bucket getStorageInstance() {
        return StorageClient.getInstance().bucket();
    }

    @PreDestroy
    private void firebaseCloser() {
        firebaseApp.delete();
    } 

}
