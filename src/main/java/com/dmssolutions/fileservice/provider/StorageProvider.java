package com.dmssolutions.fileservice.provider;

import java.io.IOException;

public interface StorageProvider {
    String UPLOAD = "upload";
    String storeFile(String fileName, byte[] fileData, byte[] fileMetadata) throws IOException;
    byte[] getFile(String fileName) throws IOException;
    byte[] getFileMetaData(String fileName) throws IOException;
    void deleteFile(String fileName) throws IOException;
}
