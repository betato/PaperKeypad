import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
    public static void main(String[] args){
        //title of the frame
        JFrame tableThing = new JFrame("KeyPad");
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit();
            }
        });
        // sets the frame size
        frame.setSize(670, 510);
        //gets a panel
        JPanel thePanel = (JPanel) frame.getContentPane();
        thePanel.setLayout(new FlowLayout());
        // makes a button
        pane.add(new JButton(("Test")));
        //makes it possible to see the frame
        frame.setVisible(true);
    }
}