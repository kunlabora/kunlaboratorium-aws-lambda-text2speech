package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import org.junit.Test;

import static org.junit.Assert.*;

public class S3ObjectKeyUtilsTest {

    @Test
    public void toMp3Key() {
        String mp3Key = new S3ObjectKeyUtils().toMp3Key("text/bla.txt");

        assertEquals("speech/bla.mp3", mp3Key);
    }

}