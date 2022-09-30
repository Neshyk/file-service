package com.dmssolutions.fileservice.controller;

import com.dmssolutions.fileservice.dto.FileIDDto;
import com.dmssolutions.fileservice.dto.MetadataDto;
import com.dmssolutions.fileservice.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/files")
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> storeFile(@RequestParam("metadata") String metadata,
                                       @RequestParam("file")MultipartFile file) throws IOException {

        String uuid = fileService.storeFile(file, metadata);
        if(uuid == null) {
            return ResponseEntity.badRequest().build();
        }
        FileIDDto fileIDDto = new FileIDDto();
        fileIDDto.setFileId(uuid);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(uuid)
                .toUri();

        return ResponseEntity.created(location).body(fileIDDto);


    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<byte[]> getFile(@PathVariable String uuid) throws IOException {
        byte[] file = fileService.getFile(uuid);
        return ResponseEntity.ok().body(file);

    }

    @GetMapping(value = "/{uuid}/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<MetadataDto> getMetadata(@PathVariable String uuid) throws IOException {
        MetadataDto metadata = fileService.getFileMetadata(uuid);
        if (metadata == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(metadata);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(@PathVariable String uuid) throws IOException {
        fileService.deleteFile(uuid);
    }
}
