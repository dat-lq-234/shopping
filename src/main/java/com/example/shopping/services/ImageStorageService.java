package com.example.shopping.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements StorageService {
    private final Path storageFolder = Path.of("upload");

    public ImageStorageService() {
        try {
            Files.createDirectories(storageFolder);
        } catch (Exception exception) {
            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Empty file");
            }
            if (!isImageFile(file)) {
                throw new RuntimeException("Not a image");
            }
            float size = (float) file.getSize() / 1_000_000;
            if (size > 5.0f) {
                throw new RuntimeException("Size too big");
            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFilename = UUID.randomUUID().toString().replace("-", "");
            generatedFilename = generatedFilename + "." + fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(Path.of(generatedFilename)).normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("Outside parent folder");
            }
            try (InputStream inputStream = file.getInputStream()) {
                // Copy file vao folder
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFilename;
        } catch (Exception exception) {
            throw new RuntimeException("Cannot initialize storage", exception);

        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFileContent(String fileName) {
        return new byte[0];
    }

    @Override
    public void deleteAllFiles() {

    }
}
