package window.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import field.Field;
import main.Const;
import window.panels.ship.Ship;

public class MainPanel extends JPanel {

	private ArrayList<Point> points;

	private MouseHandler handler;
	
	private ArrayList<Ship> ships;
	private Field field;
	
	private JLabel lblRightPlayer;
	private JLabel lblRightStatus;
	private JLabel lblLeftStatus;
	private JLabel lblLeftPlayer;
	
	private JPanel pnl4decker;
	private JPanel pnl3decker;
	private JPanel pnl2decker;
	private JPanel pnl1decker;
	
	private JLabel lbl4Count;
	private JLabel lbl3Count;
	private JLabel lbl2Count;
	private JLabel lbl1Count;

	public MainPanel() {
		setSize(Const.MainFrameWidth, Const.MainFrameHeight);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		
		setBackground(Const.FrameBackgroudColor);
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
		pnlLeftStatus.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 255)));
		pnlLeftStatus.setOpaque(false);
		pnlLeft.add(pnlLeftStatus, BorderLayout.NORTH);
		pnlLeftStatus.setLayout(new BoxLayout(pnlLeftStatus, BoxLayout.X_AXIS));
		
		lblLeftPlayer = new JLabel("Player1");
		lblLeftPlayer.setForeground(Const.LabelColor);
		lblLeftPlayer.setFont(Const.LabelFont);
		pnlLeftStatus.add(lblLeftPlayer);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		pnlLeftStatus.add(horizontalGlue);
		
		lblLeftStatus = new JLabel("");
		lblLeftStatus.setForeground(Const.LabelColor);
		lblLeftStatus.setFont(Const.LabelFont);
		pnlLeftStatus.add(lblLeftStatus);
		
		JPanel pnlLeftField = new JPanel();
		pnlLeftField.setOpaque(false);
		pnlLeft.add(pnlLeftField, BorderLayout.CENTER);
		
		JPanel pnlRight = new JPanel();
		pnlBoth.add(pnlRight);
		pnlRight.setBorder(new MatteBorder(2, 5, 2, 2, new Color(65, 105, 225)));
		pnlRight.setOpaque(false);
		pnlRight.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRightStatus = new JPanel();
		pnlRightStatus.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 255)));
		pnlRightStatus.setOpaque(false);
		pnlRight.add(pnlRightStatus, BorderLayout.NORTH);
		pnlRightStatus.setLayout(new BoxLayout(pnlRightStatus, BoxLayout.X_AXIS));
		
		lblRightStatus = new JLabel("");
		lblRightStatus.setForeground(Const.LabelColor);
		lblRightStatus.setFont(Const.LabelFont);
		pnlRightStatus.add(lblRightStatus);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		pnlRightStatus.add(horizontalGlue_1);
		
		lblRightPlayer = new JLabel("Player2");
		lblRightPlayer.setForeground(Const.LabelColor);
		lblRightPlayer.setFont(Const.LabelFont);
		pnlRightStatus.add(lblRightPlayer);
		
		JPanel pnlShips = new JPanel();
		pnlShips.setOpaque(false);
		pnlRight.add(pnlShips, BorderLayout.CENTER);
		pnlShips.setLayout(new BoxLayout(pnlShips, BoxLayout.Y_AXIS));
		
		JPanel pnlShipsList = new JPanel();
		pnlShipsList.setOpaque(false);
		pnlShips.add(pnlShipsList);
		pnlShipsList.setLayout(new GridLayout(2, 2, 0, 0));
		
		pnl4decker = new JPanel();
		pnl4decker.setOpaque(false);
		pnlShipsList.add(pnl4decker);
		pnl4decker.setLayout(new BorderLayout(0, 0));
		
		lbl4Count = new JLabel("0");
		lbl4Count.setFocusable(false);
		lbl4Count.setBorder(new MatteBorder(0, 0, 1, 1, (Color) new Color(0, 0, 255)));
		lbl4Count.setForeground(new Color(220, 20, 60));
		lbl4Count.setFont(Const.LabelFont);
		lbl4Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl4decker.add(lbl4Count, BorderLayout.CENTER);
		
		pnl3decker = new JPanel();
		pnl3decker.setOpaque(false);
		pnlShipsList.add(pnl3decker);
		pnl3decker.setLayout(new BorderLayout(0, 0));
		
		lbl3Count = new JLabel("0");
		lbl3Count.setFocusable(false);
		lbl3Count.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 255)));
		lbl3Count.setForeground(new Color(220, 20, 60));
		lbl3Count.setFont(Const.LabelFont);
		lbl3Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl3decker.add(lbl3Count, BorderLayout.CENTER);
		
		pnl2decker = new JPanel();
		pnl2decker.setOpaque(false);
		pnlShipsList.add(pnl2decker);
		pnl2decker.setLayout(new BorderLayout(0, 0));
		
		lbl2Count = new JLabel("0");
		lbl2Count.setFocusable(false);
		lbl2Count.setBorder(new MatteBorder(0, 0, 1, 1, (Color) new Color(0, 0, 255)));
		lbl2Count.setForeground(new Color(220, 20, 60));
		lbl2Count.setFont(Const.LabelFont);
		lbl2Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl2decker.add(lbl2Count, BorderLayout.CENTER);
		
		pnl1decker = new JPanel();
		pnl1decker.setOpaque(false);
		pnlShipsList.add(pnl1decker);
		pnl1decker.setLayout(new BorderLayout(0, 0));
		
		lbl1Count = new JLabel("0");
		lbl1Count.setFocusable(false);
		lbl1Count.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 255)));
		lbl1Count.setForeground(new Color(220, 20, 60));
		lbl1Count.setFont(Const.LabelFont);
		lbl1Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl1decker.add(lbl1Count, BorderLayout.CENTER);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
		pnlShips.add(pnlButtons);
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		
		JButton btnReady = new JButton("Ready!");
		btnReady.setFocusable(false);
		btnReady.setBackground(Const.ButtonBackground);
		btnReady.setFont(Const.LabelFont);
		btnReady.setForeground(Const.LabelColor);
		pnlButtons.add(btnReady);
		
		JPanel pnlMessage = new JPanel();
		pnlMessage.setBackground(Const.MessageBackground);
		add(pnlMessage, BorderLayout.SOUTH);
		pnlMessage.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMessage = new JLabel("Move ships from right side on field.");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Const.LabelColor);
		lblMessage.setFont(Const.MessageFont);
		pnlMessage.add(lblMessage, BorderLayout.CENTER);
		
		ships = new ArrayList<Ship>(Const.ShipCount);
		for(int i = 0; i < Const.ShipCount; ++i) {
			Ship ship = new Ship(Const.ShipSizes[i], Const.ShipInitialCoords[Const.ShipSizes[i] - 1], true);
			ships.add(i, ship);
			
			JLabel counter = null;
			switch (Const.ShipSizes[i]) {
			case 4: {
				counter = lbl4Count;
				break;
			}
			case 3: {
				counter = lbl3Count;
				break;
			}
			case 2: {
				counter = lbl2Count;
				break;
			}
			case 1: {
				counter = lbl1Count;
				break;
			}
			}
			counter.setText(String.valueOf((Integer.parseInt(counter.getText()) + 1)));
		}

		points = new ArrayList<Point>();
		
		handler = new MouseHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		pnlLeftField.setLayout(new BorderLayout(0, 0));
		
		field = new Field();
		field.setOpaque(false);
		field.addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseMoved(MouseEvent e) {
				handler.fieldXOnScreen = e.getXOnScreen() - e.getX();
				handler.fieldYOnScreen = e.getYOnScreen() - e.getY();
				System.out.println(handler.fieldXOnScreen + " " + handler.fieldYOnScreen);
			}
		});
		pnlLeftField.add(field);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Ship ship : ships) {
			ship.draw(g);
		}		
		
		g.setColor(new Color(255, 255, 255));
		for(Point p : points) {
			g.drawRect(p.x, p.y, 2, 2);
		}
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		
		private Ship curShip;
		
		public int fieldXOnScreen = 0;
		public int fieldYOnScreen = 0;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Ship newShip = find(e.getPoint());
			if(newShip != null) {
				ships.set(ships.indexOf(newShip), newShip.oppositeOrientation());
			}
			repaint();
			System.out.println("mouseClicked");
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if(curShip != null) {
				// TODO move ship only if left mouse button is down
				if(true) {
					curShip.moveLocation(e.getPoint());
					repaint();
				}
			}
//			System.out.println("mouseDragged");
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Ship ship = find(e.getPoint());
			if(ship != null) {
				curShip = ship;
				decLabel(ship.getSize());
			}
			System.out.println("mousePressed");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(curShip != null) {
				int xPos = e.getXOnScreen() - fieldXOnScreen;
				int yPos = e.getYOnScreen() - fieldYOnScreen;
				int returnCode = field.addShip(curShip, xPos, yPos);
				if(returnCode == 0) {
					ships.remove(curShip);
				} else if(returnCode == 1) {
					// Error: ship is not fit to the field
					curShip.moveLocation(Const.ShipInitialCoords[curShip.getSize() - 1]);
					incLabel(curShip.getSize());
					System.err.println("Error: ship is not fit to the field");
				} else if(returnCode == 2) {
					// Error: ship is contacting other ships
					System.err.println("Error: ship is contacting other ships");
					curShip.moveLocation(Const.ShipInitialCoords[curShip.getSize() - 1]);
					incLabel(curShip.getSize());
				}
				curShip = null;
				repaint();
			}
			System.out.println("mouseReleased");
		}
		
		private void incLabel(int size) {
			JLabel lbl = null;
			switch (size) {
			case 1: {
				lbl = lbl1Count;
				break;
			}
			case 2: {
				lbl = lbl2Count;
				break;
			}
			case 3: {
				lbl = lbl3Count;
				break;
			}
			case 4: {
				lbl = lbl4Count;
				break;
			}
			}
			
			int value = Integer.parseInt(lbl.getText()) + 1;
			String strValue = String.valueOf(value);
			lbl.setText(strValue);
		}
		
		private void decLabel(int size) {
			JLabel lbl = null;
			switch (size) {
			case 1: {
				lbl = lbl1Count;
				break;
			}
			case 2: {
				lbl = lbl2Count;
				break;
			}
			case 3: {
				lbl = lbl3Count;
				break;
			}
			case 4: {
				lbl = lbl4Count;
				break;
			}
			}
			
			int value = Integer.parseInt(lbl.getText()) - 1;
			String strValue = String.valueOf(value);
			lbl.setText(strValue);
		}
		
		private Ship find(Point p) {
			for(Ship s : ships) {
				if(s.contains(p)) {
					return s;
				}
			}
			
			return null;
		}		
	}
}
