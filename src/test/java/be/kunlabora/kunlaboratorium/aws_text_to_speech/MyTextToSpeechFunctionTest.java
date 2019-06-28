package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyTextToSpeechFunctionTest {

    @Mock
    private PollyFacade pollyFacadeMock;
    @Mock
    private S3Facade s3FacadeMock;
    @Mock
    private InputStream audioStreamMock;

    @InjectMocks
    private MyTextToSpeechFunction myTextToSpeechFunction;

    @Test
    public void handleRequest() throws IOException {
        String text = "Hallo Kunlaboratorium";
        when(s3FacadeMock.read("eu-west-3", "example-bucket", "test/key.txt"))
            .thenReturn(text);
        when(pollyFacadeMock.toMp3(text)).thenReturn(audioStreamMock);

        String result = myTextToSpeechFunction.handleRequest(loadS3Event(), null);

        assertEquals("OK", result);
        verify(s3FacadeMock).write("eu-west-3", "example-bucket", "speech/key.mp3", audioStreamMock);
    }

    private S3Event loadS3Event() throws IOException {
        return new ObjectMapper().readValue(
            resourceToString("/S3-event.json", defaultCharset()),
            S3Event.class
        );
    }

}