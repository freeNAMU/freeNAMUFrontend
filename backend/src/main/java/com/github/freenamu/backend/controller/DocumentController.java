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

    @GetMapping("/document/{documentName}/latest")
    public ResponseEntity<Content> getLatestDocument(@PathVariable String documentName) {
        Content latestDocument = documentService.getLatestDocument(documentName);
        if (latestDocument == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(latestDocument, HttpStatus.OK);
        }
    }

    @GetMapping("/document/{documentName}/{revisionIndex}")
    public ResponseEntity<Content> getDocument(@PathVariable String documentName, @PathVariable int revisionIndex) {
        Content document = documentService.getDocumentByRevisionIndex(documentName, revisionIndex);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(document, HttpStatus.OK);
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
