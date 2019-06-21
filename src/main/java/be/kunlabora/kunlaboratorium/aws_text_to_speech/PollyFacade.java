package be.kunlabora.kunlaboratorium.aws_text_to_speech;

import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.VoiceId;

import java.io.InputStream;

class PollyFacade {

    InputStream call(String text) {
        AmazonPolly amazonPolly = AmazonPollyClientBuilder.defaultClient();

        SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest()
            .withText(text)
            .withVoiceId(VoiceId.Lotte)
            .withOutputFormat(OutputFormat.Mp3);
        SynthesizeSpeechResult synthRes = amazonPolly.synthesizeSpeech(synthReq);

        return synthRes.getAudioStream();
    }

}
