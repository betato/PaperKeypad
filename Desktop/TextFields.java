
import java.awt.event.*;
import javax.swing.*;

class TextFields extends JFrame implements ActionListener{
    static JTextField test;//t
    static JButton theOK;//b
    static JLabel label;//l
    static JFrame frame;//f

    public TextFields(){

    }

    public static void test(){
        frame = new JFrame("Input");
        label = new JLabel("Empty.");
        theOK = new JButton("OK");

        TextFields text = new TextFields();

        theOK.addActionListener(text);

        test = new JTextField("Enter", 5);

        JPanel pane = new JPanel();

        pane.add(test);
        pane.add(theOK);
        pane.add(label);

        frame.add(pane);

        frame.setSize(250,250);
        frame.show();

    }

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();

        if(s.equals("OK")){
            label.setText(test.getText());
            test.setText("Great.");
        }
        else{
            System.exit(0);
        }
    }

}
