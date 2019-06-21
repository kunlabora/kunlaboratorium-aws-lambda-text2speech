package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;

class S3Facade {

    private final AmazonS3 amazonS3 = AmazonS3ClientBuilder
        .standard()
        .withRegion("eu-west-3")
        .build();

    String read(String bucket, String filename) {
        S3Object object = amazonS3.getObject(bucket, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toString(objectContent);
        } catch (IOException e) {
            throw new RuntimeException("Could not read S3 object content!", e);
        }
    }

    void write(String bucket, String filename, InputStream inputStream) {
        amazonS3.putObject(bucket, filename, inputStream, null);
    }

}
