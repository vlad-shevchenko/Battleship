package window.panels;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import main.Const;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class MainPanel extends JPanel {

	private JLabel lblRightPlayer;
	private JLabel lblRightStatus;
	private JLabel lblLeftStatus;
	private JLabel lblLeftPlayer;

	public MainPanel() {
		setSize(Const.MainFrameWidth, Const.MainFrameHeight);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		
		setBackground(new Color(127, 255, 212));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBoth = new JPanel();
		pnlBoth.setOpaque(false);
		add(pnlBoth);
		pnlBoth.setLayout(new BoxLayout(pnlBoth, BoxLayout.X_AXIS));
		
		JPanel pnlLeft = new JPanel();
		pnlBoth.add(pnlLeft);
		pnlLeft.setBorder(new MatteBorder(2, 2, 2, 5, new Color(65, 105, 225)));
		pnlLeft.setOpaque(false);
		pnlLeft.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlLeftStatus = new JPanel();
		pnlLeftStatus.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 255)));
		pnlLeftStatus.setOpaque(false);
		pnlLeft.add(pnlLeftStatus, BorderLayout.NORTH);
		pnlLeftStatus.setLayout(new BoxLayout(pnlLeftStatus, BoxLayout.X_AXIS));
		
		lblLeftPlayer = new JLabel("Player1");
		lblLeftPlayer.setForeground(new Color(0, 0, 205));
		lblLeftPlayer.setFont(new Font("Cambria", Font.PLAIN, 14));
		pnlLeftStatus.add(lblLeftPlayer);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		pnlLeftStatus.add(horizontalGlue);
		
		lblLeftStatus = new JLabel("");
		lblLeftStatus.setForeground(new Color(0, 0, 205));
		lblLeftStatus.setFont(new Font("Cambria", Font.PLAIN, 14));
		pnlLeftStatus.add(lblLeftStatus);
		
		JPanel pnlLeftField = new JPanel();
		pnlLeftField.setOpaque(false);
		pnlLeft.add(pnlLeftField, BorderLayout.CENTER);
		
		JPanel pnlRight = new JPanel();
		pnlBoth.add(pnlRight);
		pnlRight.setBorder(new MatteBorder(2, 5, 2, 2, (Color) new Color(65, 105, 225)));
		pnlRight.setOpaque(false);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRightStatus = new JPanel();
		pnlRightStatus.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 255)));
		pnlRightStatus.setOpaque(false);
		pnlRight.add(pnlRightStatus, BorderLayout.NORTH);
		pnlRightStatus.setLayout(new BoxLayout(pnlRightStatus, BoxLayout.X_AXIS));
		
		lblRightStatus = new JLabel("");
		lblRightStatus.setForeground(new Color(0, 0, 205));
		lblRightStatus.setFont(new Font("Cambria", Font.PLAIN, 14));
		pnlRightStatus.add(lblRightStatus);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		pnlRightStatus.add(horizontalGlue_1);
		
		lblRightPlayer = new JLabel("Player2");
		lblRightPlayer.setForeground(new Color(0, 0, 205));
		lblRightPlayer.setFont(new Font("Cambria", Font.PLAIN, 14));
		pnlRightStatus.add(lblRightPlayer);
		
		JPanel pnlMessage = new JPanel();
		pnlMessage.setBackground(new Color(95, 158, 160));
		add(pnlMessage, BorderLayout.SOUTH);
		pnlMessage.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMessage = new JLabel("Move ships from right side on field.");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(0, 0, 205));
		lblMessage.setFont(new Font("Cambria", Font.ITALIC, 12));
		pnlMessage.add(lblMessage, BorderLayout.CENTER);
		
	}
}
