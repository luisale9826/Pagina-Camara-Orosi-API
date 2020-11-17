package com.camaraturismoorosi.apicamaraturismoorosi.firebase;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
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

    public String insertObject(String collection, Map<String, Object> item) throws Exception {
        Firestore fbInstance = fbConfig.getFirestoreInstance();
        ApiFuture<DocumentReference> document = fbInstance.collection(collection).add(item);
        return document.get().getId();
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

    public String saveImageCompany(String path, MultipartFile image, String companyId) throws Exception {
        if (image.getContentType().equals("image/png") || image.getContentType().equals("image/jpg") || image.getContentType().equals("image/jpeg")) {
            Bucket bucket = fbConfig.getStorageInstance();
            Blob blob = bucket.create(String.format("%s/%s", path, companyId), image.getInputStream(),
                        image.getContentType());
                return blob.getMediaLink();
        } else {
            throw new Exception("El formato del archivo no es el indicado");
        }

    }

    public String saveImage(String path, MultipartFile image) throws Exception {
        if (image.getContentType().equals("image/png") || image.getContentType().equals("image/jpg") || image.getContentType().equals("image/jpeg")) {
            Bucket bucket = fbConfig.getStorageInstance();
            Blob blob = bucket.create(String.format("%s/%s", path), image.getInputStream(),
                        image.getContentType());
                return blob.getMediaLink();
        } else {
            throw new Exception("El formato del archivo no es el indicado");
        }

    }

    public void updateObjectSpecificFields(String collection, String objectId, Map<String,Object> updatedFields) {
        DocumentReference documentRef = fbConfig.getFirestoreInstance().collection(collection).document(objectId);
        documentRef.get();
        documentRef.update(updatedFields);
    }

    public void deleteImage(String path, String companyId) {
        Bucket bucket = fbConfig.getStorageInstance();
        String file= String.format("%s/%s", path, companyId);
        Blob blob = bucket.get(file);
        if (blob != null) {
            blob.delete();
        }
    }

}
