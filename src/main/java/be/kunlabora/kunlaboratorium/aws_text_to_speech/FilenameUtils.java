package be.kunlabora.kunlaboratorium.aws_text_to_speech;

public class FilenameUtils {

    static String getMp3Key(String textKey) {
        int pathSeparatorPosition = textKey.lastIndexOf("/");
        int extensionPosition = textKey.lastIndexOf(".txt");
        return "speech" + textKey.substring(pathSeparatorPosition, extensionPosition) + ".mp3";
    }
}
