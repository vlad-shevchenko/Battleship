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
	
// Field properties
	public static final int CellSize = 20;
	public static final int FieldWidth = 10;
	public static final int FieldHeight = 10;

// Windows properties
	public static final int LoginFrameMinWidth = 250;
	public static final int LoginFrameMinHeight = 100;
	public static final int LoginFrameWidth = 300;
	public static final int LoginFrameHeight = 150;
	
// Place for 2 fields and some additional for borders, status and message bars
	public static final int MainFrameWidth = (CellSize + 1) * FieldWidth * 2 + 50;
	public static final int MainFrameHeight = (CellSize + 1) * FieldHeight + 65;
	
// Fonts and colors
	public static final Color ButtonBackground = new Color(0, 250, 154);
	public static final Color LabelColor = new Color(0, 0, 205);
	public static final Font LabelFont = new Font("Cambria", Font.PLAIN, 14);
	public static final Color FrameBackgroudColor = new Color(127, 255, 212);
	public static final Font MessageFont = new Font("Cambria", Font.ITALIC, 12);
	public static final Color MessageBackground = new Color(95, 158, 160);
	
// Game properties
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
		new Point(387, 129),  
		new Point(262, 129),
		new Point(372, 50),
		new Point(239, 50) 
		};
		
}
