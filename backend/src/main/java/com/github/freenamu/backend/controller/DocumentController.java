package com.github.freenamu.backend.controller;

import com.github.freenamu.backend.entity.Content;
import com.github.freenamu.backend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/document/{documentName}/latest/raw")
    public ResponseEntity<Content> getLatestRawDocument(@PathVariable String documentName) {
        Content latestRawDocument = documentService.getLatestRawDocument(documentName);
        if (latestRawDocument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(latestRawDocument, HttpStatus.OK);
        }
    }

    @GetMapping("/document/{documentName}/revisions")
    public ResponseEntity<List<Content>> getRevisionsOfDocument(@PathVariable String documentName) {
        List<Content> revisionsOfDocument = documentService.getRevisionsOfDocument(documentName);
        if (revisionsOfDocument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(revisionsOfDocument, HttpStatus.OK);
        }
    }

    @PostMapping("/document/{documentName}")
    public ResponseEntity<Void> PostDocument(@PathVariable String documentName, @RequestParam String contentBody, HttpServletRequest request) {
        documentService.postDocument(documentName, contentBody, request.getRemoteAddr());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
