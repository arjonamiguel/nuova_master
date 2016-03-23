package com.nuova.model;

// Generated Mar 22, 2016 12:01:41 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrdenDocument generated by hbm2java
 */
@Entity
@Table(name = "orden_document"
        , catalog = "nuova")
public class OrdenDocument implements java.io.Serializable {

    private Integer documentId;
    private String type;
    private String fileName;
    private String fileType;
    private byte[] content;
    private Integer ordenId;

    public OrdenDocument() {
    }

    public OrdenDocument(String type) {
        this.type = type;
    }

    public OrdenDocument(String type, String fileName, String fileType, byte[] content, Integer ordenId) {
        this.type = type;
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
        this.ordenId = ordenId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id", unique = true, nullable = false)
    public Integer getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    @Column(name = "type", nullable = false, length = 256)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "fileName", length = 512)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "fileType", length = 256)
    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Column(name = "content")
    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Column(name = "orden_id")
    public Integer getOrdenId() {
        return this.ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

}
