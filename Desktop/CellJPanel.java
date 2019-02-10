import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.*;
//import java.awt.event;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.io.*;
import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CellJPanel extends JPanel{
    public void paint(Graphics g){
        Image img = createImageWithText();
        g.drawImage(img, 0, 0, Color.WHITE, this);//, WHITE);
    }
 
    private Image createImageWithText(){
        BufferedImage bufferedImage  = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();

        UserInput array = new UserInput();
        array.arrayMod();

        int x = 200, y = 100, x2 = 222, y2 = 130;
        g.setColor(Color.BLACK);
        //drawRect(int x, int y, int width, int height)
        //drawString (String s, int x, int y)

        for(int i = 0; i < array.getArray().length; i++){
            for(int j = 0; j < array.getArray()[i].length; j++){
                g.drawRect(x,y,50,50);
                //g.drawRect(x,y,10,10);
                g.drawString(array.getArray()[i][j], x2, y2);
                x += 50;
                x2 += 50;
            }

            x = 200;
            x2 = 222;
            y += 50;
            y2 += 50;
        }

        return bufferedImage;
    }

    public static void main (String[] args){
        JFrame frame = new JFrame();
        JButton print = new JButton("Print");
        frame.getContentPane().add(new CellJPanel());
        JPanel panel = new JPanel();
        Image image = new Image();
        image = createImageWithText();
/*
        panel.add(print, BorderLayout.LINE_END);
        panel.setPreferredSize(new Dimension(50, 50));
        
        frame.getContentPane().add(panel);
*/
        File outputFile = new File("image.jpg");
        ImageIO.write(image, "jpg", outputFile);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}