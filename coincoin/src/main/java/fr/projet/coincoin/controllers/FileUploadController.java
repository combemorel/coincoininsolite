package fr.projet.coincoin.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileUploadController {


    @PostMapping(
            value = "uploadimg",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String handleFileUpload(@RequestBody MultipartFile file) throws IOException {
        File convertFile = new File("./src/main/resources/images/" + file.getOriginalFilename());
        convertFile.createNewFile();

        try (FileOutputStream out = new FileOutputStream(convertFile))
        {
            out.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        return convertFile.getPath();
    }
}