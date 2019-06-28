package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.VoiceId;

import java.io.InputStream;

import static com.amazonaws.services.polly.AmazonPollyClientBuilder.defaultClient;

class PollyFacade {

    InputStream toMp3(String text) {
        SynthesizeSpeechRequest synthesizeSpeechRequest = new SynthesizeSpeechRequest()
            .withText(text)
            .withVoiceId(VoiceId.Lotte)
            .withOutputFormat(OutputFormat.Mp3);

        return defaultClient().synthesizeSpeech(synthesizeSpeechRequest).getAudioStream();
    }

}
