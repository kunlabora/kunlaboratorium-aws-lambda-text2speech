package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

import java.io.InputStream;

public class MyTextToSpeechFunction implements RequestHandler<S3Event, String> {

    private final S3Facade s3Facade;
    private final PollyFacade pollyFacade;

    public MyTextToSpeechFunction() {
        this.s3Facade = new S3Facade();
        this.pollyFacade = new PollyFacade();
    }

    public MyTextToSpeechFunction(S3Facade s3Facade, PollyFacade pollyFacade) {
        this.s3Facade = s3Facade;
        this.pollyFacade = pollyFacade;
    }

    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        s3Event.getRecords().forEach(this::handleRecord);
        return "OK";
    }

    private void handleRecord(S3EventNotification.S3EventNotificationRecord record) {
        String bucket = record.getS3().getBucket().getName();
        String filename = record.getS3().getObject().getKey();

        String text = s3Facade.read(bucket, filename);
        InputStream audioStream = pollyFacade.call(text);
        s3Facade.write(bucket, FilenameUtils.getMp3Key(filename) , audioStream);
    }

}
