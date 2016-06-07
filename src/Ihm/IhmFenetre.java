package Ihm;

import javax.swing.*;

public class IhmFenetre extends JPanel {
    public IhmFenetre() {
	JFrame frame = new JFrame();
	frame.setTitle("Monopoly");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Interface inter = new Interface();

	frame.add(inter);
	frame.setSize(500, inter.ResponsiveHeight());

	frame.setVisible(true);
    }
    
}
