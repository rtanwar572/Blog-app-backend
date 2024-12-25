package com.rohit.blog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.UUID;

@Service
public class FileService {

    public String uploadImg(String path, MultipartFile file) throws IOException {
        //converting "Images" Into Path Object
        Path root = Paths.get(path);
        // check if not  Exits then create Directory with name "Images/"
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        // Creates a unique file name by generating a random UUID (to avoid overwriting files with the same name).
        String originalFileName=file.getOriginalFilename();
//        assert originalFileName != null;

        if (originalFileName == null || !originalFileName.matches(".*\\.(jpeg|jpg|png|mp4)$")){
                        throw new InputMismatchException("Invalid file format. Only .jpeg, .jpg, .png, .mp4 are allowed.");

        }
        String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + fileType;
        //Creating a full path with Path Object
        Path filePath = root.resolve(filename);
        //Reads the uploaded file's content as a stream (file.getInputStream()).
        //Copies this content into the filePath on the server.
        Files.copy(file.getInputStream(), filePath);

        return filename;

    }
    //serve image/file
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+ File.separator +fileName;
        InputStream inputStream;
        inputStream = new FileInputStream(fullPath);
        return  inputStream;

    }

}
