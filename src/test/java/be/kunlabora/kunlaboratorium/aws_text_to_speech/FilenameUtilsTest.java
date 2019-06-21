package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilenameUtilsTest {

    @Test
    public void getMp3Key() {
        String mp3Key = new FilenameUtils().getMp3Key("text/bla.txt");

        assertEquals("speech/bla.mp3", mp3Key);
    }

}