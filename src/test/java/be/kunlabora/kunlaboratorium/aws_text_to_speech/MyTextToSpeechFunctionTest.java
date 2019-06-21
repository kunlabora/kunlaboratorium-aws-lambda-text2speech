package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyTextToSpeechFunctionTest {

    @Mock
    private PollyFacade pollyFacadeMock;
    @Mock
    private S3Facade s3FacadeMock;

    @InjectMocks
    private MyTextToSpeechFunction myTextToSpeechFunction;

    @Test
    public void handleRequest_callsPolly() throws IOException {
        String text = "Hallo dag Heidi";
        when(s3FacadeMock.read("example-bucket", "test/key.txt")).thenReturn(text);

        myTextToSpeechFunction.handleRequest(createS3Event(), null);

        verify(pollyFacadeMock).call(text);
    }

    private S3Event createS3Event() throws IOException {
        return new ObjectMapper().readValue(
            resourceToString("/S3-event.json", defaultCharset()),
            S3Event.class
        );
    }

}