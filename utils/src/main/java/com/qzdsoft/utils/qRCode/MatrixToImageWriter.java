package com.qzdsoft.utils.qRCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;
/**
 * 
 * @author pc-20170422
 *
 */
public class MatrixToImageWriter {
	
	 private static final int BLACK = 0xFF000000;
	 private static final int WHITE = 0xFFFFFFFF;

	 private MatrixToImageWriter() {}


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }


    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }


    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }
    
    
    /**
     * 给二维码图片添加Logo
     * 
     * @param qrPic
     * @param logoPic
     */
    public static void addLogo_QRCode(File qrPic, File logoPic, LogoConfig logoConfig)
    {
        try
        {
            if (!qrPic.isFile() || !logoPic.isFile())
            {
                System.out.print("file not find !");
                System.exit(0);
            }

            /**
             * 读取二维码图片，并构建绘图对象
             */
            BufferedImage image = ImageIO.read(qrPic);
            Graphics2D g = image.createGraphics();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(logoPic);

            int widthLogo = image.getWidth()/logoConfig.getLogoPart(); 
        //    int    heightLogo = image.getHeight()/logoConfig.getLogoPart();
            int    heightLogo = image.getWidth()/logoConfig.getLogoPart(); //保持二维码是正方形的

            // 计算图片放置位置
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2 ;        


            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
            g.setStroke(new BasicStroke(logoConfig.getBorder()));
            g.setColor(logoConfig.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);

            g.dispose();

            ImageIO.write(image, "jpeg", new File("D:/newPic.jpg"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     * @为图片添加文字
     * @param pressText 文字
     * @param newImg    带文字的图片
     * @param targetImg 需要添加文字的图片
     * @param fontStyle 
     * @param color
     * @param fontSize
     * @param width
     * @param heigh
     */
    public static void pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color, int fontSize, int width, int height) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width-(fontSize*pressText.length()*2/3))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height-(height-width)/2;        
        
        try {
            File file = new File(newImg);
            Image src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
            out.close();
            System.out.println("image press success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * 
     * @param pressText 文字
     * @param image1 放文字的二维码
     * @param newImg 二维码的存放路径和文件名
     * @param fontStyle
     * @param color
     * @param fontSize
     * @param width 二维码的宽度
     * @param height 二维码的高度
     */
    public static void pressText(String pressText, BufferedImage image1,String newImg, int fontStyle, Color color, int fontSize, int width, int height) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width-(fontSize*pressText.length()*2/3))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height-(height-width)/2;        
        
        try {
//	            File file = new File(newImg);
//	            Image src = ImageIO.read(file);
            Image src = image1;
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
            out.close();
            System.out.println("image press success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
public static void pressText(String pressText, BufferedImage image1,OutputStream os,String format, int fontStyle, Color color, int fontSize, int width, int height) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (width-(fontSize*pressText.length()*3/4))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height-(height-width)/2;        
        
        try {
//	            File file = new File(newImg);
//	            Image src = ImageIO.read(file);
            Image src = image1;
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();
            
            if (!ImageIO.write(image, format, os)) {
                throw new IOException("Could not write an image of format " + format);
            }
            
//            FileOutputStream out = new FileOutputStream(newImg);
//            ImageIO.write(image, "JPEG", out);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
           
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
