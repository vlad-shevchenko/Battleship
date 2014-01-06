package window;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Const;
import window.panels.MainPanel;
import window.panels.PlayerStatus;

public class MainFrame extends JFrame {
	private Connection connect;
	private MainPanel panel;
	
	public MainFrame(String userName, Socket socket) throws UnknownHostException, IOException {
		connect = new Connection(socket);
		
		panel = new MainPanel(userName);
		panel.setReadyActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					connect.setReady();
					panel.addEnemyField();
					panel.setEnemyFieldMouseListener(new MouseHandler());
				}
			});
		
		setContentPane(panel);
		
		setLocation(100, 100);
		setSize(panel.getSize());
		setResizable(false);
		
		setTitle("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		connect.start();
	}
	
	public class Connection extends Thread {
		
		private Socket socket;
		private DataInputStream in;
		private DataOutputStream out;
		
		private Point target;
		
		private boolean playerIsReady = false;
		private boolean enemyIsReady = false;
		private boolean firstFire = false;
		private boolean win = false;
		
		Connection(Socket socket) throws UnknownHostException, IOException {
			this.socket = socket;
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		}
		
		@Override
		public void run() {
			try {
				panel.updateRightStatus(PlayerStatus.Places_ships);
				out.writeUTF(panel.getLeftName());
				String rightName = in.readUTF();
				panel.setRightName(rightName);
				
				enemyIsReady = in.readBoolean();
				panel.updateRightStatus(PlayerStatus.Ready);
				
				try {
					while(!(playerIsReady && enemyIsReady)) {
						sleep(200);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// if currentTurn == true, then there is player's turn now, else
				// enemy's one
				boolean currentTurn = firstFire;
				
				if(currentTurn) {
					panel.updateLeftStatus(PlayerStatus.Turn);
					panel.updateRightStatus(PlayerStatus.Wait);
				} else {
					panel.updateLeftStatus(PlayerStatus.Wait);
					panel.updateRightStatus(PlayerStatus.Turn);
				}
				
				while(true) {
					if(currentTurn) {
						try {
							while(target == null) {
								sleep(100);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						byte[] targetBytes = {(byte) target.x, (byte) target.y};
						out.write(targetBytes);
						
						if(targetBytes[0] < 0 && targetBytes[1] < 0) {
							panel.updateLeftStatus(PlayerStatus.Lose);
							panel.updateRightStatus(PlayerStatus.Win);
							win = false;
							break;
						}
						boolean shot = in.readBoolean();
						panel.enemyCheck(target, shot);
						if (shot) {
							panel.updateLeftStatus(PlayerStatus.Shot);
						} else {
							panel.updateLeftStatus(PlayerStatus.Miss);
						}
						
						currentTurn = shot;
						target = null;
					} else {
						byte[] targetBytes = new byte[2];
						in.read(targetBytes);
						
						if(targetBytes[0] < 0 && targetBytes[1] < 0) {
							panel.updateLeftStatus(PlayerStatus.Win);
							panel.updateRightStatus(PlayerStatus.Lose);
							win = true;
							break;
						}
						
						boolean shot = panel.fire(new Point((int) targetBytes[0], (int) targetBytes[1]));
						out.writeBoolean(shot);
						if(shot) {
							panel.updateRightStatus(PlayerStatus.Shot);
						} else {
							panel.updateRightStatus(PlayerStatus.Miss);
						}
						
						currentTurn = !shot;
						
						if(panel.getShipsCount() == 0) {
							target = new Point(-1, -1);
						}
					}
				}
				
				JOptionPane.showMessageDialog(MainFrame.this,
						win ? "Game over. You've won!"
								: "Game over. You'he lose!", "End",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				System.err.println("Connection has been lost.");
				panel.updateRightStatus(PlayerStatus.Leave);
				e.printStackTrace();
			}			
		}
		
		public void setReady() {
			playerIsReady = true;
			try {
				out.writeBoolean(true);
			} catch (IOException e) {
				// Ready-signal has not been send
				e.printStackTrace();
			}
			panel.updateLeftStatus(PlayerStatus.Ready);
			if(!enemyIsReady) firstFire = true;
		}
		
		public void setTarget(Point p) {
			target = p;
			System.out.println("Target: " + p);
		}
		
		@Override
		protected void finalize() {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class MouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX() / (Const.CellSize + 1);
			int y = e.getY() / (Const.CellSize + 1);
			
			connect.setTarget(new Point(x, y));
		}		
	}
}
