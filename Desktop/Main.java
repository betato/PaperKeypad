import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
    public static main(String[] args){
        JFrame tableThing = new JFrame("KeyPad");
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit();
            }
        });

        frame.setSize(670, 510);

    }
}