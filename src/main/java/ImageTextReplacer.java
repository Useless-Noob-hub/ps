
import lombok.extern.slf4j.Slf4j;
import service.PhoneImageGenerate;

import java.io.IOException;


//启动类
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