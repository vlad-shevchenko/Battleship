package window;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import window.panels.MainPanel;
import window.panels.PlayerStatus;
import window.panels.field.CellState;
import main.Const;

public class MainFrame extends JFrame {
	private Connection connect;
	private MainPanel panel;
	
	public MainFrame(String userName, Socket socket) throws UnknownHostException, IOException {
		connect = new Connection(socket);
		
		panel = new MainPanel(userName);
		setContentPane(panel);
		
		setLocation(100, 100);
		setSize(panel.getSize());
		setResizable(false);
		
		setTitle("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		connect.start();
	}

	public void updateStatus(PlayerStatus status) {
		
	}
	
	/**
	 * Set name of second player. <br>
	 * Such method for first user is not necessary, cause it's known before
	 * creating panel and can be transmitted to the constructor.
	 * 
	 * @param name
	 *            - name of second player
	 */
	public void setSecondUserName(String name) {
		
	}

	public boolean fire(Point p) {
		return false;
			
	}

	public boolean isFinish() {
		return false;
			
	}
	
	private class Connection extends Thread {
		
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		
		public Connection(Socket socket) throws UnknownHostException, IOException {
			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		
		@Override
		public void run() {
			try {
				panel.updateRightStatus(PlayerStatus.Places_ships);
				out.write(panel.getLeftName());
				String rightName = in.readLine();
				panel.setRightName(rightName);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		void setReady() {
			out.print(true);
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
}
