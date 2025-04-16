package constant;

import java.time.LocalTime;

public class Constants {
    // 模板图片路径
    public static final String TEMPLATE_PATH = System.getProperty("user.dir") +"/src/main/resources/template.png";
    // 手机号文件路径
    public static final String PHONE_FILE_PATH =System.getProperty("user.dir") + "/src/main/resources/phone.txt";
    // 输出目录
    public static final String OUTPUT_DIR = "output";

    // 坐标常量
    public static final int PHONE_X = 322, PHONE_Y = 135 - 66;
    public static final int CALL_TIME_X = 40, CALL_TIME_Y = 510 - 66;
    public static final int DURATION_X = 106, DURATION_Y = 558 - 66;

    // 最初开始时间
    public static final LocalTime START_TIME = LocalTime.of(16, 0);

    // 间隔常量
    public static final int MIN_INTERVAL = 60;
    public static final int MAX_INTERVAL = 120;
}