package com.iwne.dorm.info.mangement.controller;

import com.iwne.dorm.info.mangement.service.ValidateCodeCreateService;
import com.iwne.dorm.info.mangement.validate.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("code/img")
    public String createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);

        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        //response.setDateHeader("Expires", 0);

//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
//        encoder.encode(imageCode.getImage());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imageCode.getImage(), "JPEG", baos);//图片格式

        byte[] bytes = baos.toByteArray();


        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    private ImageCode createImageCode(HttpServletRequest request) {
        int width = 98;
        int height = 38;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.ITALIC,40));
        g.setColor(getRandColor(160,200));
        for (int i = 0;i < 155; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        String sRand = "";
        for(int i = 0;i < 4; i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand,20 * i + 6,34);
        }
        g.dispose();
        return new ImageCode(image,sRand,60);
    }

    /**
     * 生成随机背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255){
            fc = 255;
        }
        if(bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
}
