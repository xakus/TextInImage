import com.aspose.ocr.ImageStream;
import com.aspose.ocr.OcrEngine;

/**
 * Created by xakus on 10.03.2016.
 * lib взял на сайтн http://www.aspose.com/
 */
public class Main {
 public  static  void main(String[] args) {
     //Initialize an instance of OcrEngine
     OcrEngine ocrEngine = new OcrEngine();

//Set the Image property by loading the image from file path location
     ocrEngine.setImage(ImageStream.fromFile("C:\\Users\\xakus\\Desktop\\hqdefault.jpg"));

//Process the image
     if (ocrEngine.process()) {
         //Display the recognized text
         System.out.println(ocrEngine.getText());
     }
 }
}
