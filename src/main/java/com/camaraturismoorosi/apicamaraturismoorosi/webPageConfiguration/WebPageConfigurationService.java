package com.camaraturismoorosi.apicamaraturismoorosi.webPageConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.camaraturismoorosi.apicamaraturismoorosi.files.FilesService;
import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Board;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Director;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Value;
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
        currentBoard.put("president", board.getPresident());
        currentBoard.put("vicepresident", board.getVicepresident());
        currentBoard.put("secretary", board.getSecretary());
        currentBoard.put("treasurer", board.getTreasurer());
        currentBoard.put("vocal", board.getVocal());
        currentBoard.put("fiscal", board.getFiscal());
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

    @SuppressWarnings("unchecked")
    public Board getBoard() throws InterruptedException, ExecutionException {
        final String COLLECTION = "board";
        DocumentSnapshot document = fbService.getObjectById(COLLECTION, COLLECTION);
        Board board = new Board();
        HashMap<String, String> map = (HashMap<String, String>) document.get("president");
        Director member = new Director(map.get("name").toString(), map.get("major").toString());
        board.setPresident(member);
        map = (HashMap<String, String>) document.get("vicepresident");
        member = new Director(map.get("name").toString(), map.get("major").toString());
        board.setVicepresident(member);
        map = (HashMap<String, String>) document.get("treasurer");
        member = new Director(map.get("name").toString(), map.get("major").toString());
        board.setTreasurer(member);
        map = (HashMap<String, String>) document.get("secretary");
        member = new Director(map.get("name").toString(), map.get("major").toString());
        board.setSecretary(member);
        map = (HashMap<String, String>) document.get("vocal");
        member = new Director(map.get("name").toString(), map.get("major").toString());
        board.setVocal(member);
        map = (HashMap<String, String>) document.get("fiscal");
        member = new Director(map.get("name").toString(), map.get("major").toString());
        board.setFiscal(member);
        return board;
    }

    @SuppressWarnings("unchecked")
    public List<String> getBenefits() throws InterruptedException, ExecutionException {
        final String COLLECTION = "benefits";
        DocumentSnapshot document = fbService.getObjectById(COLLECTION, COLLECTION);
        List<String> documentText = (List<String>) document.get("benefits");
        return documentText;
    }

	public void editValues(@Valid List<Value> value) {
        final String COLLECTION = "values";
        Map<String, Object> values = new HashMap<>();
        values.put("values", value);
        fbService.updateObject(COLLECTION, COLLECTION, values);
	}

    @SuppressWarnings("unchecked")
	public List<Value> getValues() throws InterruptedException, ExecutionException {
        final String COLLECTION = "values";
        DocumentSnapshot document = fbService.getObjectById(COLLECTION, COLLECTION);
        List<Value> values = (ArrayList<Value>) document.get("values");
		return values;
	}
}
