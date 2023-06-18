package com.prokopenko.diploma.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
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
                "AKIARLPKFFLF5UQG5GOJ",
                "YueSb8ji7AysGJXM9KlnYjjZzktCWQn7YEvlBQrB"
        );
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("error");
        }
        return convertedFile;
    }

    public String putElement(MultipartFile multipartFile, Long courseId, String folder) {
        File file = convertMultipartFileToFile(multipartFile);
        String extension = multipartFile.getOriginalFilename().split("\\.")[1];
        s3client.putObject(new PutObjectRequest("s1ma.diploma", folder + courseId + "." + extension, file));
        //String url = s3client.getUrl("s1ma.diploma", "courseImg/" + courseId + "." + extension).toString();
        String url = getPreSignedUrl("s1ma.diploma", "courseImg/" + courseId + "." + extension);
        file.delete();
        return url;
    }

    private String getPreSignedUrl(String bucketName, String objectKey) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                .withMethod(HttpMethod.GET); // Устанавливаем метод запроса (GET, PUT, DELETE и т.д.)
        // Устанавливаем время, на которое будет выдана ссылка (7 дней - максимальное количество дней)
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60*24*7;
        expiration.setTime(expTimeMillis);
        generatePresignedUrlRequest.setExpiration(expiration); // Генерируем pre-signed URL
        return s3client.generatePresignedUrl(generatePresignedUrlRequest).toString();
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
