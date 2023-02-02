package com.example.diploma.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class AWSService {

    private AmazonS3 s3client;

    private AWSCredentials credentials;

    public AWSService() {
        credentials = new BasicAWSCredentials(
                "a",
                "s"
        );
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

    }

    private File convertMultipartFileToFile(MultipartFile file){
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)){
            fileOutputStream.write(file.getBytes());
        }catch (IOException e){
            System.out.println("error");
        }
        return convertedFile;
    }

    public String putElement(MultipartFile multipartFile, Long courseId, String folder){
        File file = convertMultipartFileToFile(multipartFile);
        String extension = multipartFile.getOriginalFilename().split("\\.")[1];
        s3client.putObject( new PutObjectRequest("s1ma.diploma",folder+ courseId +"." + extension, file));
        String url =  s3client.getUrl("s1ma.diploma", "courseImg/"+ courseId +"." + extension).toString();
        file.delete();
        return url;
    }
//
//
//    public String getFileName() throws IOException {
//        S3Object object = s3client.getObject(new GetObjectRequest("s1ma.diploma", "courseImg/itsme.jpg"));
//        InputStream objectData = object.getObjectContent();
//// Process the objectData stream.
//        String dir = "C:\\Users\\S1ma\\IdeaProjects\\diploma\\target";
//        File targetFile = new File(dir+"\\test.jpg");
//
//        java.nio.file.Files.copy(
//                objectData,
//                targetFile.toPath(),
//                StandardCopyOption.REPLACE_EXISTING);
//        objectData.close();
//        return "";
//    }
}
