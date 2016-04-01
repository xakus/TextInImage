//import com.aspose.ocr.ImageStream;
//import com.aspose.ocr.OcrEngine;
//
///**
// * Created by xakus on 10.03.2016.
// * lib взял на сайтн http://www.aspose.com/
// */
//public class Main {
// public  static  void main(String[] args) {
//     //Initialize an instance of OcrEngine
//     OcrEngine ocrEngine = new OcrEngine();
//
////Set the Image property by loading the image from file path location
//     ocrEngine.setImage(ImageStream.fromFile("C:\\Users\\xakus\\Desktop\\hqdefault.jpg"));
//
////Process the image
//     if (ocrEngine.process()) {
//         //Display the recognized text
//         System.out.println(ocrEngine.getText());
//     }
// }
//}


import com.aspose.barcode.internal.Exceptions.Exception;
import com.aspose.barcode.internal.as.bu;
import com.aspose.barcode.internal.ms.System.Drawing.*;
import com.aspose.imaging.imageoptions.TypeOfEntities;
import com.aspose.imaging.internal.bouncycastle.util.io.Streams;
import com.aspose.ocr.*;
import com.sun.corba.se.impl.orb.ORBConfiguratorImpl;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Main {
    JLabel jLabel = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField=new JTextField("0");
    Frame frame = new Frame();
    BufferedImage bufferedImage;
    public Main() {



        frame.add(jLabel);
        frame.add(jLabel2);
        frame.add(jTextField);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.visible();



        //Set the Image property by loading the image from file path location
        // ocrEngine.setImage(ImageStream.fromFile("C:\\Users\\xakus\\Desktop\\hqdefault.jpg"));
        Thread thread=new Thread(new Runnable() {
            OcrEngine ocrEngine = new OcrEngine();
            @Override
            public void run() {
                  int oldRGB=0;
                int   newRGB=0;
                while(true){
                    bufferedImage = grabScreen(frame.getLocationX()+4, frame.getLocationY()+43, 100, 60);

                    for (int i=0;i<bufferedImage.getHeight();i++) {
                        bufferedImage.setRGB(i, i, Color.black.getRGB());
                    }
                    ImageIcon  imageIcon = new ImageIcon( bufferedImage);
                    newRGB =bufferedImage.getRGB(bufferedImage.getWidth()-5,bufferedImage.getHeight()-5);
                    if(oldRGB!=newRGB) {
                        oldRGB = newRGB;
                        System.out.println("RGB=" + newRGB+"  x="+(bufferedImage.getWidth()-5)+" y="+(bufferedImage.getHeight()-5)+" color red="+Color.red.getRGB());
                    }


                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    try {
                        ImageIO.write((RenderedImage) imageIcon.getImage(), "bmp", os);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    InputStream is = new ByteArrayInputStream(os.toByteArray());
                    jLabel.setIcon(imageIcon);
                        ocrEngine.setProcessAllPages(true);
                        ocrEngine.setImage(ImageStream.fromStream(is,ImageStreamFormat.Bmp));

                    try {
                        if (ocrEngine.process()) {
                           // System.out.println(ocrEngine.getText());
                            jLabel2.setText( ocrEngine.getText().toString());
                        }
                    }catch (Exception ex){
                        System.out.println("Error");
                    }


                }
            }
        } );
      thread.start();
    }




    public static void main(String[] args) {
        try {
            //ImageIO.write(grabScreen(), "png", new File(getHomeDir(), "screen.png"));
            new Main();
        } catch (Exception e) {
            System.out.println("IO exception" + e);
        }
    }

    private static File getHomeDir() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getHomeDirectory();
    }

    private static BufferedImage grabScreen(int x1, int x2, int y1, int y2) {
        try {
            return new Robot().createScreenCapture(new Rectangle(x1, x2, y1, y2));
        } catch (SecurityException e) {
        } catch (AWTException e) {
        }
        return null;
    }

}
