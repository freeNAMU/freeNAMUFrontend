package com.github.freenamu.backend.service;

import com.github.freenamu.backend.entity.Content;
import com.github.freenamu.backend.entity.Document;
import com.github.freenamu.backend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public void postDocument(String documentName, String documentBody, String contributor) {
        Optional<Document> optionalDocument = documentRepository.findById(documentName);
        Document document;
        if (optionalDocument.isEmpty()) {
            document = new Document();
            document.setDocumentName(documentName);
        } else {
            document = optionalDocument.get();
        }
        Content content = new Content();
        content.setContentBody(documentBody);
        content.setContributor(contributor);
        document.addContent(content);
        documentRepository.save(document);
    }

    public Content getLatestRawDocument(String documentName) {
        Optional<Document> optionalDocument = documentRepository.findById(documentName);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            List<Content> revisions = document.getRevisions();
            return revisions.stream().max(Comparator.comparing(Content::getContentId)).get();
        } else {
            return null;
        }
    }
}
