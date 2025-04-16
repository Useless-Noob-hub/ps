
import service.PhoneImageGenerate;

import java.io.IOException;


public class ImageTextReplacer {

    private static final PhoneImageGenerate pig= new PhoneImageGenerate();
    public static void main(String[] args) {
        try {
            pig.generateImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}