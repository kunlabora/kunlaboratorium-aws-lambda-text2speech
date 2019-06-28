package be.kunlabora.kunlaboratorium.aws_text_to_speech;

public class S3ObjectKeyUtils {

    static String toMp3Key(String textKey) {
        int pathSeparatorPosition = textKey.lastIndexOf("/");
        int extensionPosition = textKey.lastIndexOf(".txt");
        return "speech/" + textKey.substring(pathSeparatorPosition+1, extensionPosition) + ".mp3";
    }
}
