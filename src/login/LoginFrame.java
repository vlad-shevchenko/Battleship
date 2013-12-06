package login;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.DefaultDesktopManager;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Const;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.StringTokenizer;

import javax.swing.JFormattedTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;

import window.MainFrame;

public class LoginFrame extends JFrame implements ActionListener {
	private JTextField fldName;
	private JFormattedTextField fldIp;
	private JButton btnServer;
	private JButton btnConnect;
	
	public LoginFrame() {		
		getContentPane().setBackground(Const.FrameBackgroudColor);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(5);
		getContentPane().add(verticalStrut);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setOpaque(false);
		getContentPane().add(pnlFields);
		pnlFields.setLayout(new BoxLayout(pnlFields, BoxLayout.Y_AXIS));
		
		JPanel pnlName = new JPanel();
		pnlName.setOpaque(false);
		pnlFields.add(pnlName);
		pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.X_AXIS));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		pnlName.add(horizontalStrut_3);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Const.LabelColor);
		lblName.setFont(Const.LabelFont);
		pnlName.add(lblName);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		pnlName.add(horizontalGlue_1);
		
		fldName = new JTextField(15);
		fldName.setForeground(Const.LabelColor);
		fldName.setFont(Const.LabelFont);
		fldName.setHorizontalAlignment(SwingConstants.CENTER);
		fldName.setMaximumSize(new Dimension(fldName.getColumns() * 5, 30));
		pnlName.add(fldName);
		fldName.setColumns(10);
		fldName.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				btnServer.setEnabled(!fldName.getText().isEmpty());
				btnConnect.setEnabled(!fldName.getText().isEmpty()
						&& !fldIp.getText().isEmpty());
			}
		});
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(5);
		pnlName.add(horizontalStrut_4);
		
		JPanel pnlIp = new JPanel();
		pnlIp.setOpaque(false);
		pnlFields.add(pnlIp);
		pnlIp.setLayout(new BoxLayout(pnlIp, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(5);
		pnlIp.add(horizontalStrut_2);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setForeground(Const.LabelColor);
		lblIp.setFont(Const.LabelFont);
		pnlIp.add(lblIp);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		pnlIp.add(horizontalGlue_2);
		
		try {
			MaskFormatter formatter = new MaskFormatter("###.###.###.###");
			formatter.setPlaceholderCharacter('#');
			fldIp = new JFormattedTextField(formatter);
			fldIp.setValue("127.000.000.001");
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		fldIp.setColumns(10);
		fldIp.setForeground(Const.LabelColor);
		fldIp.setFont(Const.LabelFont);
		fldIp.setHorizontalAlignment(SwingConstants.CENTER);
		fldIp.setFocusLostBehavior(JFormattedTextField.COMMIT);
		fldIp.setMaximumSize(new Dimension(fldIp.getColumns() * 5, 30));
		fldIp.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				StringTokenizer tokenizer = new StringTokenizer(fldIp.getText(), ".");
				for (int i = 0; i < 4; i++) {
					int b = 0;
					if(tokenizer.hasMoreTokens()) {
						try {
							b = Integer.parseInt(tokenizer.nextToken());
						} catch (Exception ex) {
							btnConnect.setEnabled(false);
							return;
						}
					}
					
					if (b < 0 || b >= 256) { 
						btnConnect.setEnabled(false);
						return;
					}
				}
				
				btnServer.setEnabled(!fldName.getText().isEmpty());
				btnConnect.setEnabled(!fldName.getText().isEmpty()
						&& !fldIp.getText().isEmpty());
			}
		});
		pnlIp.add(fldIp);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(5);
		pnlIp.add(horizontalStrut_5);
		
		Component verticalGlue = Box.createVerticalGlue();
		getContentPane().add(verticalGlue);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
		getContentPane().add(pnlButtons);
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		pnlButtons.add(horizontalStrut);
		
		btnServer = new JButton("Create server");
		btnServer.setActionCommand(Const.ServerButtonCommand);
		btnServer.addActionListener(this);
		btnServer.setToolTipText("<html><h4>Type name to enable this button</h4></html>");
		btnServer.setBackground(Const.ButtonBackground);
		btnServer.setEnabled(false);
		btnServer.setForeground(Const.LabelColor);
		btnServer.setFont(Const.LabelFont);
		pnlButtons.add(btnServer);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		pnlButtons.add(horizontalGlue);
		
		btnConnect = new JButton("Connect");
		btnConnect.setActionCommand(Const.ConnectButtonCommand);
		btnConnect.addActionListener(this);
		btnConnect.setToolTipText("<html><h4>Type name and IP to enable this button</h4></html>");
		btnConnect.setBackground(Const.ButtonBackground);
		btnConnect.setEnabled(false);
		btnConnect.setForeground(Const.LabelColor);
		btnConnect.setFont(Const.LabelFont);
		pnlButtons.add(btnConnect);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		pnlButtons.add(horizontalStrut_1);
		
		setLocation(200, 200);
		setMinimumSize(new Dimension(Const.LoginFrameMinWidth, Const.LoginFrameHeight));
		setSize(new Dimension(Const.LoginFrameWidth, Const.LoginFrameHeight));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command.equals(Const.ServerButtonCommand)) {
			try {
				ServerSocket server = new ServerSocket(Const.DefaultPort);
				
				// TODO: Waiting for connection
				
				Socket socket = server.accept();
				
				// TODO: Remove waiting message
				
				new MainFrame(fldName.getText(), socket);
				this.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(command.equals(Const.ConnectButtonCommand)) {
			try {
				Socket socket = new Socket(fldIp.getText(), Const.DefaultPort);
				new MainFrame(fldName.getText(), socket);
				this.setVisible(false);
			} catch (UnknownHostException e1) {
				JOptionPane.showMessageDialog(this, Const.UnknownHostErrorMsg, 
						"Unknown host", JOptionPane.ERROR_MESSAGE);
				System.err.println("UnknownHostException");
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, Const.ConnectionErrorMsg, 
						"Input\\output error!", JOptionPane.ERROR_MESSAGE);
				System.err.println("IOException");
				e1.printStackTrace();
			}
		}
	}
}
