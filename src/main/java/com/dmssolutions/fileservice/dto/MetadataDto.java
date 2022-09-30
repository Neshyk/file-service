package com.dmssolutions.fileservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Validated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MetadataDto {
    String fileName;
    BigDecimal size;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_сreated")
    LocalDateTime dateCreated;

    public MetadataDto() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
