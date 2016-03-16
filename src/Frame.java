import javax.swing.*;

/**
 * Created by xakus on 10.03.2016.
 */
public class Frame extends JFrame {
    public Frame() {
        this.setSize(200,50);
        this.setResizable(false);
        this.setLocation(0,0);


    }
    public int getLocationX(){
        return (int) this.getLocation().getX();
    }
    public int getLocationY(){
        return (int) this.getLocation().getY();
    }
    public void visible(){
        this.setVisible(true);
    }
}
