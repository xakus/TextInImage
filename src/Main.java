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


import com.aspose.ocr.IImageStream;
import com.aspose.ocr.ImageStream;
import com.aspose.ocr.LanguageContainer;
import com.aspose.ocr.OcrEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Main {
    JLabel jLabel = new JLabel();
    JLabel jLabel2 = new JLabel();
    Frame frame = new Frame();
    BufferedImage bufferedImage;
    public Main() {



        frame.add(jLabel);
        frame.add(jLabel2);
        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.visible();



        //Set the Image property by loading the image from file path location
        // ocrEngine.setImage(ImageStream.fromFile("C:\\Users\\xakus\\Desktop\\hqdefault.jpg"));
        Thread thread=new Thread(new Runnable() {
            OcrEngine ocrEngine = new OcrEngine();
            @Override
            public void run() {
                while(true){
                    bufferedImage = grabScreen(frame.getLocationX()+4, frame.getLocationY()+32, 200, 50);
                    ImageIcon  imageIcon = new ImageIcon( bufferedImage);
                    ocrEngine.setImage((IImageStream) imageIcon.getImage());

                    System.out.println(ocrEngine.getText());
                    jLabel.setIcon(imageIcon);
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
