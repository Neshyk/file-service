package com.dmssolutions.fileservice.service;

import com.dmssolutions.fileservice.dto.MetadataDto;
import com.dmssolutions.fileservice.provider.StorageProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    StorageProvider storageProvider;

    ObjectMapper objectMapper;

    @Autowired
    public FileService(StorageProvider storageProvider, ObjectMapper objectMapper) {
        this.storageProvider = storageProvider;
        this.objectMapper = objectMapper;
    }
    public String storeFile(MultipartFile file, String metadata) throws IOException {
        if(metadata == null) {
            return null;
        }
        String fileName = UUID.randomUUID().toString();
        MetadataDto metadataDto = objectMapper.readValue(metadata, MetadataDto.class);
        byte[] fileMetadata = objectMapper.writeValueAsBytes(metadataDto);
        return storageProvider.storeFile(fileName, file.getBytes(), fileMetadata);

    }

    public byte[] getFile(String fileName) throws IOException{
        return storageProvider.getFile(fileName);
    }

    public MetadataDto getFileMetadata(String fileName) throws IOException{
        String metadata = new String(storageProvider.getFileMetaData(fileName));
        return objectMapper.readValue(metadata, MetadataDto.class);
    }

    public void deleteFile(String uuid) throws IOException{
        storageProvider.deleteFile(uuid);
    }
}


