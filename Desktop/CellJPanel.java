import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CellJPanel extends JPanel{
    public void paint(Graphics g){
        Image img = createImageWithText();
        g.drawImage(img, 20, 20, this);
    }
 
    private Image createImageWithText(){
        BufferedImage bufferedImage  = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        UserInput array = new UserInput();
        array.arrayMod();

        int x = 20, y = 20;
        //drawRect(int x, int y, int width, int height)
        //drawString (String s, int x, int y)

        for(int i = 0; i < array.getArray().length; i++){
            for(int j = 0; j < array.getArray()[i].length; j++){
                //g.drawRect
                g.drawString(array.getArray()[i][j], x, y);
                x += 20;
            }
            x = 20;
            y += 20;
        }

        return bufferedImage;
    }

    public static void main (String[] args){
        JFrame frame = new JFrame();
        frame.getContentPane().add(new CellJPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}