package com.nuova.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class OrdenDocumentDTO {

    private Integer documentId;
    private String type;
    private String fileName;
    private String fileType;
    private Integer ordenId;
    private CommonsMultipartFile fileData;

    public OrdenDocumentDTO() {

    }

    public OrdenDocumentDTO(Integer documentId, String type, String fileName, String fileType, Integer ordenId,
            CommonsMultipartFile fileData) {
        super();
        this.documentId = documentId;
        this.type = type;
        this.fileName = fileName;
        this.fileType = fileType;
        this.ordenId = ordenId;
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

}
