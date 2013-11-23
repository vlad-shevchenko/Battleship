package main;

import java.awt.Color;
import java.awt.Font;

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
	
	// Place for 2 fields and some additional
	public static final int MainFrameWidth = (CellSize + 1) * FieldWidth * 2 + 14;
	public static final int MainFrameHeight = (CellSize + 1) * FieldHeight + 45;
	
	// Fonts and colors
	public static final Color ButtonBackground = new Color(0, 250, 154);
	public static final Color LabelColor = new Color(0, 0, 205);
	public static final Font LabelFont = new Font("Cambria", Font.PLAIN, 14);
	public static final Color FrameBackgroudColor = new Color(127, 255, 212);
	public static final Font MessageFont = new Font("Cambria", Font.ITALIC, 12);
	public static final Color MessageBackground = new Color(95, 158, 160);
		
}
