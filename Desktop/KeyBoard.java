
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.event.ActionListener.*;

public class KeyBoard{
    public static void main(String[] args){
        //title of the frame
        JFrame tableThing = new JFrame("KeyPad");
        tableThing.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        // sets the frame size
        tableThing.setSize(670, 510);
        //gets a panel
        JPanel thePanel = (JPanel) tableThing.getContentPane();
        thePanel.setLayout(new FlowLayout());
        // makes a button
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the amount of buttons you wish to have");
        int input1 = input.nextInt();
        JButton[] temp = new JButton[input1];
        for(int i = 0; i < input1; i++){
            temp[i] = (new JButton("But"+(i+1)));
            thePanel.add(temp[i]);
            temp[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    TextFields.test();
                }
            });
        }



        //makes it possible to see the frame
        tableThing.setVisible(true);
    }

}


