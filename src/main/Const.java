package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Collection;

import window.panels.ship.Ship;

/**
 * Class which encapsulates all constants of program
 *
 */
public class Const {
	
//	Error messages
	
	public static final String UnknownHostErrorMsg = 
			"Exception: UnknownHostException.\n"
			+ "Can not connect to this server. Check IP, please.";
	public static final String ConnectionErrorMsg = 
			"Exception: IOException.\n"
			+ "Error while connecting. Maybe it isn't Battleship server running\n"
			+ "at this address or you have problems with Internet connection.";
	
//	Network properties
	public static final int DefaultPort = 8181;
	
//	Field properties
	public static final int CellSize = 20;
	public static final int FieldWidth = 10;
	public static final int FieldHeight = 10;

//	Windows properties
	public static final int LoginFrameMinWidth = 250;
	public static final int LoginFrameMinHeight = 100;
	public static final int LoginFrameWidth = 300;
	public static final int LoginFrameHeight = 150;
	
//	Place for 2 fields and some additional for borders, status and message bars
	public static final int MainFrameWidth = (CellSize + 1) * FieldWidth * 2 + 20;
	public static final int MainFrameHeight = (CellSize + 1) * FieldHeight + 65;
	
//	Fonts and colors
	public static final Color ButtonBackground = new Color(0, 250, 154);
	public static final Color LabelColor = new Color(0, 0, 205);
	public static final Font LabelFont = new Font("Cambria", Font.PLAIN, 14);
	public static final Color FrameBackgroudColor = new Color(127, 255, 212);
	public static final Font MessageFont = new Font("Cambria", Font.ITALIC, 12);
	public static final Color MessageBackground = new Color(95, 158, 160);
	
	public static final Color ConnectingStatusColor = new Color(50, 80, 150);
	public static final Color PlacesShipsStatusColor = new Color(30, 30, 30);
	public static final Color LeaveStatusColor = new Color(0, 0, 0);
	public static final Color ReadyStatusColor = new Color(100, 180, 100);
	public static final Color ShotStatusColor = new Color(75, 160, 75);
	public static final Color MissStatusColor = new Color(160, 75, 75);
	
//	ActionCommand's
	public static final String ServerButtonCommand = "LoginFrame -> btnServer";
	public static final String ConnectButtonCommand = "LoginFrame -> btnConnect";
	
//	Game properties
	public static final int ShipCount = 10;
	
	/**
	 * Size of the array must be equal to ShipCount.
	 * Change array is not recommended, because UI was 
	 * designed specially for such ship-set. 
	 */
	public static final int[] ShipSizes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
	
	/**
	 * Yeah, I'm crazy code monkey :-(
	 */
	public static final Point[] ShipInitialCoords = {
		new Point(378, 153),  
		new Point(273, 153),
		new Point(378, 65),
		new Point(273, 65) 
		};
	public static final Color FilledCellColor = new Color(195, 150, 150);
		
}
