package window;

import java.awt.Graphics;

import javax.swing.JFrame;

import window.panels.FieldPanel;
import main.Const;

public class MainFrame extends JFrame {
	private FieldPanel field;
	
	public MainFrame() {
		field = new FieldPanel();
		setContentPane(field);
		
		setLocation(100, 100);
		setSize(Const.MainFrameWidth, Const.MainFrameHeight);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		
	}

}
