package com.dmssolutions.fileservice.provider;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FSStorageProviderIml implements StorageProvider {
    private static final String METADATA_EXTENSION = ".metadata";
    private static final FileSystem fileSystem = FileSystems.getDefault();
    @Override
    public String storeFile(String fileName, byte[] fileData, byte[] fileMetadata) throws IOException {

        File catalog = new File(StorageProvider.UPLOAD);
        if (!catalog.exists()) {
            if (!catalog.mkdir()){
                throw new IOException("Can't create directory");
            }
        }
        String resultFileName = getResultFileName(fileName);
        Files.copy(new ByteArrayInputStream(fileData), Path.of(resultFileName));
        Files.copy(new ByteArrayInputStream(fileMetadata), Path.of(resultFileName + METADATA_EXTENSION));
        return fileName;
    }

    @Override
    public byte[] getFile(String fileName) throws IOException{
        String resultFileName = getResultFileName(fileName);
        return Files.readAllBytes(Path.of(resultFileName));
    }
    @Override
    public byte[] getFileMetaData(String fileName) throws IOException{
        String resultFileName = getResultFileName(fileName+METADATA_EXTENSION);
        return Files.readAllBytes(Path.of(resultFileName));
    }
    @Override
    public void deleteFile(String fileName) throws IOException {
        String resultFileName = getResultFileName(fileName);
        String resultMetadataFileName = getResultFileName(fileName+METADATA_EXTENSION);
        Files.delete(Path.of(resultFileName));
        Files.delete(Path.of(resultMetadataFileName));
    }

    private String getResultFileName(String fileName) {
        return StorageProvider.UPLOAD + fileSystem.getSeparator() + fileName;
    }
}
