package window.panels;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import main.Const;
import window.MainFrame.Connection;
import window.panels.field.EnemyField;
import window.panels.field.PlayerField;
import window.panels.ship.Ship;

public class MainPanel extends JPanel {

	private MouseHandler handler;
	private Connection connect;
	
	private ArrayList<Ship> ships;
	private PlayerField playerField;
	private EnemyField enemyField;
	
	private JLabel lblRightPlayer;
	private JLabel lblRightStatus;
	private JLabel lblLeftStatus;
	private JLabel lblLeftPlayer;

	private JPanel pnlShips;
	private JPanel pnlShipsList;
	private JPanel pnlButtons;
	
	private JPanel pnl4decker;
	private JPanel pnl3decker;
	private JPanel pnl2decker;
	private JPanel pnl1decker;
	
	private JLabel lbl4Count;
	private JLabel lbl3Count;
	private JLabel lbl2Count;
	private JLabel lbl1Count;
	
	private JButton btnReady;
	private Component horizontalGlue_2;
	private Component horizontalGlue_3;
	private Component horizontalGlue_4;
	private Component horizontalGlue_5;

	public MainPanel(String userName) {
		setSize(Const.MainFrameWidth, Const.MainFrameHeight);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		
		setBackground(Const.FrameBackgroudColor);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBoth = new JPanel();
		pnlBoth.setOpaque(false);
		add(pnlBoth);
		pnlBoth.setLayout(new GridLayout(0, 2, 0, 0));
		
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
		
		lblLeftPlayer = new JLabel(userName);
		lblLeftPlayer.setForeground(Const.LabelColor);
		lblLeftPlayer.setFont(Const.LabelFont);
		pnlLeftStatus.add(lblLeftPlayer);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		pnlLeftStatus.add(horizontalGlue_1);
		
		horizontalGlue_4 = Box.createHorizontalGlue();
		pnlLeftStatus.add(horizontalGlue_4);
		
		lblLeftStatus = new JLabel("");
		lblLeftStatus.setFont(Const.LabelFont);
		pnlLeftStatus.add(lblLeftStatus);
		
		horizontalGlue_2 = Box.createHorizontalGlue();
		pnlLeftStatus.add(horizontalGlue_2);
		
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
		
		horizontalGlue_3 = Box.createHorizontalGlue();
		pnlRightStatus.add(horizontalGlue_3);
		
		lblRightStatus = new JLabel("");
		lblRightStatus.setFont(Const.LabelFont);
		pnlRightStatus.add(lblRightStatus);
		
		horizontalGlue_5 = Box.createHorizontalGlue();
		pnlRightStatus.add(horizontalGlue_5);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		pnlRightStatus.add(horizontalGlue);
		
		lblRightPlayer = new JLabel("Player2");
		lblRightPlayer.setForeground(Const.LabelColor);
		lblRightPlayer.setFont(Const.LabelFont);
		pnlRightStatus.add(lblRightPlayer);
		
		pnlShips = new JPanel();
		pnlShips.setOpaque(false);
		pnlRight.add(pnlShips, BorderLayout.CENTER);
		pnlShips.setLayout(new BoxLayout(pnlShips, BoxLayout.Y_AXIS));
		
		pnlShipsList = new JPanel();
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
		lbl4Count.setForeground(Const.FrameBackgroudColor);
		lbl4Count.setFont(Const.LabelFont);
		lbl4Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl4decker.add(lbl4Count, BorderLayout.CENTER);
		
		pnl3decker = new JPanel();
		pnl3decker.setOpaque(false);
		pnlShipsList.add(pnl3decker);
		pnl3decker.setLayout(new BorderLayout(0, 0));
		
		lbl3Count = new JLabel("0");
		lbl3Count.setFocusable(false);
		lbl3Count.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 255)));
		lbl3Count.setForeground(Const.FrameBackgroudColor);
		lbl3Count.setFont(Const.LabelFont);
		lbl3Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl3decker.add(lbl3Count, BorderLayout.CENTER);
		
		pnl2decker = new JPanel();
		pnl2decker.setOpaque(false);
		pnlShipsList.add(pnl2decker);
		pnl2decker.setLayout(new BorderLayout(0, 0));
		
		lbl2Count = new JLabel("0");
		lbl2Count.setFocusable(false);
		lbl2Count.setBorder(new MatteBorder(0, 0, 1, 1, new Color(0, 0, 255)));
		lbl2Count.setForeground(Const.FrameBackgroudColor);
		lbl2Count.setFont(Const.LabelFont);
		lbl2Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl2decker.add(lbl2Count, BorderLayout.CENTER);
		
		pnl1decker = new JPanel();
		pnl1decker.setOpaque(false);
		pnlShipsList.add(pnl1decker);
		pnl1decker.setLayout(new BorderLayout(0, 0));
		
		lbl1Count = new JLabel("0");
		lbl1Count.setFocusable(false);
		lbl1Count.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 255)));
		lbl1Count.setForeground(Const.FrameBackgroudColor);
		lbl1Count.setFont(Const.LabelFont);
		lbl1Count.setHorizontalAlignment(SwingConstants.CENTER);
		pnl1decker.add(lbl1Count, BorderLayout.CENTER);
		
		pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
		pnlShips.add(pnlButtons);
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		
		btnReady = new JButton("Ready!");
		btnReady.setFocusable(false);
		btnReady.setEnabled(false);
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
			Ship ship = new Ship(Const.ShipSizes[i], new Point(0, 0), true);
			ship.moveCenter(Const.ShipInitialCoords[Const.ShipSizes[i] - 1]);
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
		
		handler = new MouseHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		pnlLeftField.setLayout(new BorderLayout(0, 0));
		
		playerField = new PlayerField();
		playerField.setOpaque(false);
		playerField.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				handler.fieldXOnScreen = e.getXOnScreen() - e.getX();
				handler.fieldYOnScreen = e.getYOnScreen() - e.getY();
			}
		});
		pnlLeftField.add(playerField);
		
		try {
			Robot robot = new Robot();
			robot.mouseMove(200, 200);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		
		updateLeftStatus(PlayerStatus.Places_ships);
		updateRightStatus(PlayerStatus.Connecting);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Ship ship : ships) {
			ship.draw(g);
		}
	}
	
	public void updateName(String name) {
		lblRightPlayer.setText(name);
	}
	
	public void enemyCheck(Point p, boolean shot) {
		if(shot) {
			enemyField.setSinked(p.x, p.y);
		} else {
			enemyField.setChecked(p.x, p.y);
		}
	}
	
	public void updateLeftStatus(PlayerStatus status) {
		switch (status) {
		case Connecting:
			lblLeftStatus.setForeground(Const.ConnectingStatusColor);
			lblLeftStatus.setText("Connecting...");
			break;
		case Places_ships:
			lblLeftStatus.setForeground(Const.PlacesShipsStatusColor);
			lblLeftStatus.setText("Places ships");
			break;
		case Leave:
			lblLeftStatus.setForeground(Const.LeaveStatusColor);
			lblLeftStatus.setText("Leave :(");
			break;
		case Ready:
			lblLeftStatus.setForeground(Const.ReadyStatusColor);
			lblLeftStatus.setText("Ready!");
			break;
		case Shot:
			lblLeftStatus.setForeground(Const.ShotStatusColor);
			lblLeftStatus.setText("Shot");
			break;
		case Miss:
			lblLeftStatus.setForeground(Const.MissStatusColor);
			lblLeftStatus.setText("Miss");
			break;
		case Turn:
			lblLeftStatus.setForeground(Const.ReadyStatusColor);
			lblLeftStatus.setText("Turn");
			break;
		case Wait:
			lblLeftStatus.setForeground(Const.ConnectingStatusColor);
			lblLeftStatus.setText("Wait");
			break;
		case Win:
			lblLeftStatus.setForeground(Const.ShotStatusColor);
			lblLeftStatus.setText("Win");
			break;
		case Lose:
			lblLeftStatus.setForeground(Const.MissStatusColor);
			lblLeftStatus.setText("Lose");
			break;
		}
	}
	
	public void updateRightStatus(PlayerStatus status) {
		switch (status) {
		case Connecting:
			lblRightStatus.setForeground(Const.ConnectingStatusColor);
			lblRightStatus.setText("Connecting...");
			break;
		case Places_ships:
			lblRightStatus.setForeground(Const.PlacesShipsStatusColor);
			lblRightStatus.setText("Places ships");
			break;
		case Leave:
			lblRightStatus.setForeground(Const.LeaveStatusColor);
			lblRightStatus.setText( "Leave :(" );
			break;
		case Ready:
			lblRightStatus.setForeground(Const.ReadyStatusColor);
			lblRightStatus.setText("Ready!");
			break;
		case Shot:
			lblRightStatus.setForeground(Const.ShotStatusColor);
			lblRightStatus.setText("Shot");
			break;
		case Miss:
			lblRightStatus.setForeground(Const.MissStatusColor);
			lblRightStatus.setText("Miss");
			break;
		case Turn:
			lblRightStatus.setForeground(Const.ReadyStatusColor);
			lblRightStatus.setText("Turn");
			break;
		case Wait:
			lblRightStatus.setForeground(Const.ConnectingStatusColor);
			lblRightStatus.setText("Wait");
			break;
		case Win:
			lblRightStatus.setForeground(Const.ShotStatusColor);
			lblRightStatus.setText("Win");
			break;
		case Lose:
			lblRightStatus.setForeground(Const.MissStatusColor);
			lblRightStatus.setText("Lose");
			break;
		}
	}
	
	public String getLeftName() {
		return lblLeftPlayer.getText();
	}
	
	public String getRightName() {
		return lblRightPlayer.getText();
	}
	
	public void setLeftName(String name) {
		lblLeftPlayer.setText(name);
	}
	
	public void setRightName(String name) {
		lblRightPlayer.setText(name);
	}
	
	/**
	 * Replaces panels for ships with EnemyField object. Invoked when all ships
	 * are placed and player click btnReady
	 */
	public void addEnemyField() {
		enemyField = new EnemyField();
		pnlShips.remove(pnlShipsList);
		pnlShips.remove(pnlButtons);
		pnlShips.add(enemyField);
		repaint();
	}

	/**
	 * @param connect
	 *            - the connect to set
	 */
	public void setConnection(Connection connect) {
		this.connect = connect;
	}
	
	public int getShipsCount() {
		return playerField.getShipsCount();
	}
	
	public void setReadyActionListener(ActionListener l) {
		btnReady.addActionListener(l);
	}
	
	public void setEnemyFieldMouseListener(MouseListener l) {
		enemyField.addMouseListener(l);
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {
		
		private Ship curShip;
		
		public int fieldXOnScreen = 0;
		public int fieldYOnScreen = 0;
		
		public void mouseClicked(MouseEvent e) {
			if((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
				ArrayList<Ship> shipArr = findAll(e.getPoint());
				for(Ship s : shipArr) {
					ships.set(ships.indexOf(s), s.oppositeOrientation());
				}
				repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if(curShip != null) {
				if((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					curShip.moveLocation(e.getPoint());
					repaint();
				}
			}
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
				Ship ship = find(e.getPoint());
				if(ship != null) {
					curShip = ship;
					decLabel(ship.getSize());
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			if(curShip != null) {
				int xPos = e.getXOnScreen() - fieldXOnScreen;
				int yPos = e.getYOnScreen() - fieldYOnScreen;
				int returnCode = playerField.addShip(curShip, xPos, yPos);
				if(returnCode == 0) {
					ships.remove(curShip);
					if(ships.size() == 0) {
						btnReady.setEnabled(true);
					}
				} else if(returnCode == 1) {
					System.err.println("Error: ship is not fit to the field");
					curShip.moveCenter(Const.ShipInitialCoords[curShip.getSize() - 1]);
					incLabel(curShip.getSize());
				} else if(returnCode == 2) {
					System.err.println("Error: ship is contacting other ships");
					curShip.moveCenter(Const.ShipInitialCoords[curShip.getSize() - 1]);
					incLabel(curShip.getSize());
				}
				curShip = null;
				repaint();
			}
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
		
		private ArrayList<Ship> findAll(Point p) {
			ArrayList<Ship> result = new ArrayList<Ship>(0);
			for(Ship s : ships) {
				if(s.contains(p)) {
					result.add(s);
				}
			}
			
			return result;
		}
	}

	public boolean fire(Point p) {
		boolean result = playerField.fire(p.x, p.y);
		repaint();
		return result;
	}
}
