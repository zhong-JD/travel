package com.icis.controller;



import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/9/29 9:55
 */
@WebServlet("/checkCode")
public class checkCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.定义图片的宽和高
        int width = 100;
        int height = 30;
        //在内存钟创建一个对象
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.美化 图片
        //2.1 填充背景色
        Graphics g = img.getGraphics();
        //设置颜色
        g.setColor(Color.orange);
        //填充背景色
        g.fillRect(0,0,width,height);

        //设置颜色
        g.setColor(Color.blue);
        //画边框
        g.drawRect(0,0,width,height);

        String str = "ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int rNum = random.nextInt(str.length());
            //根据对应的字符
            char ch = str.charAt(rNum);
            //写入字符
            g.setColor(Color.DARK_GRAY);
            g.drawString(String.valueOf(ch),width/5*i,height/2);
            sb.append(ch);
        }
        request.getSession().setAttribute("vcode",sb.toString());
        //干扰线
        g.setColor(Color.CYAN);

        for (int i = 0; i < 7; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1,x2,y1,y2);

        }
        //向页面输入图片
        ImageIO.write(img,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
