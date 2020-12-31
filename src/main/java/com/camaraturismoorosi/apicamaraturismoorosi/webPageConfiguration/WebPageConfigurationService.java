package com.camaraturismoorosi.apicamaraturismoorosi.webPageConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.files.FilesService;
import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Board;
import com.google.cloud.firestore.DocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class WebPageConfigurationService {
    private FilesService filesService;
    private final FirebaseService fbService;

    @Autowired
    public WebPageConfigurationService(FilesService filesService, FirebaseService fbService) {
        this.filesService = filesService;
        this.fbService = fbService;
    }

    public void updateImage(String path, MultipartFile image) throws Exception {
        Map<String, Object> updatedFields = new HashMap<String, Object>();
        final String COLLECTION_NAME = "images";
        String filePath = filesService.updateFile(COLLECTION_NAME, image, path);
        updatedFields.put("url", filePath);
        fbService.updateObject(path, COLLECTION_NAME, updatedFields);
    }

    public void editBoard(Board board) {
        final String COLLECTION = "board";
        Map<String, Object> currentBoard = new HashMap<>();
        currentBoard.put("board", board);
        fbService.updateObject(COLLECTION, COLLECTION, currentBoard);
    }

    public void editText(String position, String text) throws Exception {
        final String COLLECTION = "labels";
        if (position.trim().equals("") || text.trim().equals("")) {
            throw new Exception("La position o el texto no se han especificado");
        }
        Map<String, Object> updatedFields = new HashMap<String, Object>();
        updatedFields.put("text", text);
        fbService.updateObject(position, COLLECTION, updatedFields);
    }

    public void editBenefits(List<String> benefits) throws Exception {
        final String COLLECTION = "benefits";
        if (benefits.isEmpty()) {
            throw new Exception("Debe por lo menos haber un beneficio");
        }
        Map<String, Object> updatedFields = new HashMap<String, Object>();
        benefits = benefits.stream().filter(benefit -> !benefit.trim().equals("")).collect(Collectors.toList());
        updatedFields.put("benefits", benefits);
        fbService.updateObject(COLLECTION, COLLECTION, updatedFields);
    }

    public String getImage(String path) throws Exception {
        final String COLLECTION = "images";
        DocumentSnapshot document = fbService.getObjectById(COLLECTION, path);
        String url = document.get("url").toString();
        return url;
    }

    public String getText(String text) throws Exception {
        final String COLLECTION = "labels";
        DocumentSnapshot document = fbService.getObjectById(COLLECTION, text);
        String documentText = document.get("text").toString();
        return documentText;
    }
}
