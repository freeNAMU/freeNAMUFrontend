package com.github.freenamu.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Document {
    @Id
    private String documentName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Content> revisions = new ArrayList<>();

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public List<Content> getRevisions() {
        return revisions;
    }

    public void addContent(Content content) {
        revisions.add(content);
    }
}
