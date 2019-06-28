package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

import java.io.InputStream;

import static be.kunlabora.kunlaboratorium.aws_text_to_speech.S3ObjectKeyUtils.toMp3Key;

public class MyTextToSpeechFunction implements RequestHandler<S3Event, String> {

    private final S3Facade s3Facade;
    private final PollyFacade pollyFacade;

    public MyTextToSpeechFunction() {
        this.s3Facade = new S3Facade();
        this.pollyFacade = new PollyFacade();
    }

    MyTextToSpeechFunction(S3Facade s3Facade, PollyFacade pollyFacade) {
        this.s3Facade = s3Facade;
        this.pollyFacade = pollyFacade;
    }

    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        s3Event.getRecords().forEach(this::handleRecord);
        return "OK";
    }

    private void handleRecord(S3EventNotification.S3EventNotificationRecord record) {
        String s3BucketName = record.getS3().getBucket().getName();
        String s3ObjectKey = record.getS3().getObject().getKey();
        String awsRegion = record.getAwsRegion();

        String s3ObjectContentAsText = s3Facade.read(awsRegion, s3BucketName, s3ObjectKey);
        InputStream audioStream = pollyFacade.toMp3(s3ObjectContentAsText);
        s3Facade.write(awsRegion, s3BucketName, toMp3Key(s3ObjectKey) , audioStream);
    }

}
