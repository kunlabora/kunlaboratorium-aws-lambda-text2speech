package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;

class S3Facade {

    String read(String awsRegion, String s3BucketName, String s3ObjectKey) {
        S3ObjectInputStream objectContent = getAmazonS3Client(awsRegion)
            .getObject(s3BucketName, s3ObjectKey)
            .getObjectContent();

        try {
            return IOUtils.toString(objectContent);
        } catch (IOException e) {
            throw new RuntimeException("Could not read S3 object content!", e);
        }
    }

    void write(String awsRegion, String s3BucketName, String s3ObjectKey, InputStream inputStream) {
        getAmazonS3Client(awsRegion)
            .putObject(s3BucketName, s3ObjectKey, inputStream, null);
    }

    private AmazonS3 getAmazonS3Client(String awsRegion) {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(awsRegion)
            .build();
    }

}