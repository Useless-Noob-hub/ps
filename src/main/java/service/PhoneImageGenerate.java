package service;

import pojo.CallLog;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static constant.Constants.*;


public class PhoneImageGenerate {


    public  void generateImages() throws IOException {
        // 初始化
        new File(OUTPUT_DIR).mkdirs(); // 直接使用 OUTPUT_DIR
        Random random = new Random();
        ArrayList<CallLog> dataList = new ArrayList<>();


        // 读取模板图片
        BufferedImage template = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_PATH)); // 直接使用 TEMPLATE_PATH



        InputStream is = this.getClass().getClassLoader().getResourceAsStream(PHONE_FILE_PATH);

        List<String> phones = new BufferedReader(new InputStreamReader(is))
                .lines()
                .filter(line -> !line.trim().isEmpty())
                .toList();



        LocalTime startTime = START_TIME; // 直接使用 START_TIME

        //声明数据
        int interval, duration;
        // 生成数据
        for (String phone : phones) {
            interval = random.nextInt(MAX_INTERVAL - MIN_INTERVAL + 1) + MIN_INTERVAL; // 使用 MIN_INTERVAL 和 MAX_INTERVAL
            startTime = startTime.plusSeconds(interval);
            duration = random.nextInt(40) + 20;// 持续时间
            dataList.add(new CallLog(phone, startTime, duration));
        }

        // 生成图片
        for (int i = 0; i < dataList.size(); i++) {
            try {
                // 创建图片副本
                BufferedImage image = new BufferedImage(
                        template.getWidth(), template.getHeight(), template.getType());
                Graphics2D graphics = image.createGraphics();
                graphics.drawImage(template, 0, 0, null);

                // 绘制内容
                drawCenteredString(graphics, dataList.get(i).getPhone(),
                        PHONE_X, PHONE_Y, 100, new Font("PingFang SC", Font.TRUETYPE_FONT, 40), Color.WHITE);
                drawCenteredString(graphics, dataList.get(i).getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                        CALL_TIME_X, CALL_TIME_Y, 100, new Font("Microsoft YaHei", Font.PLAIN, 35), Color.BLACK);
                drawCenteredString(graphics, dataList.get(i).getDuration() + "秒钟",
                        DURATION_X, DURATION_Y, 200, new Font("Microsoft YaHei", Font.PLAIN, 35), Color.decode("#8A8A8E"));

                // 保存图片
                String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                ImageIO.write(image, "png", new File(OUTPUT_DIR + "/微信图片_" + date + "1702" + i + ".png"));
                System.out.println((i+1)+"、图片生成成功: " + (OUTPUT_DIR + "/微信图片_" + date + "1702" + i + ".png"));
            } catch (IOException e) {
                System.err.println("图片生成失败: " + e.getMessage());
            }
            //关闭资源


        }
    }

    private static void drawCenteredString(Graphics2D g, String text, int x, int y, int width, Font font, Color color) {
        g.setFont(font);
        g.setColor(color);
        FontRenderContext frc = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, frc);
        double xMargin = (width - bounds.getWidth()) / 2;
        g.drawString(text, (float) (x + xMargin), (float) (y + bounds.getHeight() - bounds.getY()));
    }
}
