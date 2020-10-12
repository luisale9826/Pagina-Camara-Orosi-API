package com.camaraturismoorosi.apicamaraturismoorosi.firebase;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseService {

    private final FirebaseConfig fbConfig;

    @Autowired
    public FirebaseService(FirebaseConfig fbConfig) {
        this.fbConfig = fbConfig;
    }

    public void insertObject(String collection, Map<String, Object> item) {
        Firestore fbInstance = fbConfig.getFirestoreInstance();
        fbInstance.collection(collection).add(item);
    }

    public void updateObject(String objectId, String collection, Object updatedObject) {
        Firestore fbInstance = fbConfig.getFirestoreInstance();
        fbInstance.collection(collection).document(objectId).set(updatedObject);
    }

    public void deleteObject(String objectId, String collection) {
        Firestore fbInstance = fbConfig.getFirestoreInstance();
        fbInstance.collection(collection).document(objectId).delete();
    }

    public List<QueryDocumentSnapshot> getObjects(String collection) {
        Firestore fbInstance = fbConfig.getFirestoreInstance();
        try {
            return fbInstance.collection(collection).get().get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveImage(String path, MultipartFile image) {
        Bucket bucket = fbConfig.getStorageInstance();
        Blob blob;
        try {
            blob = bucket.create(path + "/" + image.getOriginalFilename(), image.getInputStream(),
                    image.getContentType());
            return blob.getMediaLink();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
