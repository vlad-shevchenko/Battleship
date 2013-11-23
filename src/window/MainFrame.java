package window;

import java.awt.Graphics;

import javax.swing.JFrame;

import window.panels.MainPanel;
import main.Const;

public class MainFrame extends JFrame {
	private MainPanel field;
	
	public MainFrame() {
		field = new MainPanel();
		setContentPane(field);
		
		setLocation(100, 100);
		setSize(field.getSize());
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}

}
