package com.github.freenamu.backend.service;

import com.github.freenamu.backend.entity.Content;
import com.github.freenamu.backend.entity.Document;
import com.github.freenamu.backend.repository.DocumentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DocumentServiceTest {
    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void postDocumentOnce() {
        // Given
        String documentName = "test name";
        String documentBody = "test body";
        String contributor = "127.0.0.1";
        int expectedSize = 1;

        // When
        documentService.postDocument(documentName, documentBody, contributor);

        // Then
        Optional<Document> optionalDocument = documentRepository.findById(documentName);
        assertTrue(optionalDocument.isPresent());
        Document document = optionalDocument.get();
        assertEquals(documentName, document.getDocumentName());
        List<Content> revisions = document.getRevisions();
        assertEquals(expectedSize, revisions.size());
        Content content = revisions.get(0);
        assertEquals(documentBody, content.getContentBody());
        assertEquals(contributor, content.getContributor());
    }

    @Test
    public void postDocumentMany() {
        // Given
        String documentName = "test name";
        String contributor = "127.0.0.1";
        int expectedSize = 100;

        // When
        for (int i = 0; i < expectedSize; i++) {
            String documentBody = String.valueOf(i);
            documentService.postDocument(documentName, documentBody, contributor);
        }

        // Then
        Optional<Document> optionalDocument = documentRepository.findById(documentName);
        assertTrue(optionalDocument.isPresent());
        Document document = optionalDocument.get();
        assertEquals(documentName, document.getDocumentName());
        List<Content> revisions = document.getRevisions();
        assertEquals(expectedSize, revisions.size());
        for (int i = 0; i < expectedSize; i++) {
            Content content = revisions.get(i);
            assertEquals(String.valueOf(i), content.getContentBody());
        }
    }

    @Test
    public void postDocumentWithLongBody() {
        // Given
        String documentName = "test name";
        StringBuilder documentBodyBuilder = new StringBuilder("l");
        documentBodyBuilder.append("o".repeat(1000000));
        documentBodyBuilder.append("ng");
        String documentBody = documentBodyBuilder.toString();
        String contributor = "127.0.0.1";
        int expectedSize = 1;

        // When
        documentService.postDocument(documentName, documentBody, contributor);

        // Then
        Optional<Document> optionalDocument = documentRepository.findById(documentName);
        assertTrue(optionalDocument.isPresent());
        Document document = optionalDocument.get();
        assertEquals(documentName, document.getDocumentName());
        List<Content> revisions = document.getRevisions();
        assertEquals(expectedSize, revisions.size());
        Content content = revisions.get(0);
        assertEquals(documentBodyBuilder.toString(), content.getContentBody());
        assertEquals(contributor, content.getContributor());
    }

    @Test
    public void getLatestRawDocument() {
        // Given
        String documentName = "test name";
        String contributor = "127.0.0.1";
        int size = 100;
        for (int i = 1; i <= size; i++) {
            String documentBody = String.valueOf(i);
            documentService.postDocument(documentName, documentBody, contributor);
        }

        // When
        Content actual = documentService.getLatestRawDocument(documentName);

        // Then
        String expected = String.valueOf(size);
        assertEquals(expected, actual.getContentBody());
    }

    @Test
    public void returnNullIfDocumentIsNotExistWhenGetLatestRawDocument() {
        // Given
        String documentName = "test name";

        // When
        Content actual = documentService.getLatestRawDocument(documentName);

        // Then
        assertNull(actual);
    }
}
