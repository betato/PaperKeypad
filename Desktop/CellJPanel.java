import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CellJPanel extends JPanel{
    public void paint(Graphics g){
        Image img = createImageWithText();
        g.drawImage(img, 5, 5, Color.WHITE, this);//, WHITE);
    }
 
    private Image createImageWithText(){
        BufferedImage bufferedImage  = new BufferedImage(1000, 560, BufferedImage.TYPE_INT_ARGB);
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
        frame.getContentPane().add(new CellJPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}