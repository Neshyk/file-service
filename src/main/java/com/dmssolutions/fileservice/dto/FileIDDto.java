package com.dmssolutions.fileservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileIDDto {
    @JsonProperty("file_id")
    String fileId;

    public FileIDDto() {
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
