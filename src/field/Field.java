package field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import window.panels.ship.Ship;
import main.Const;

public class Field extends JPanel {

	private ArrayList<Point> points;
	
	private CellState[][] cells;
	
	private int width;
	private int height;
	private int cellSize;
	
	public Field() {
		width = Const.FieldWidth;
		height= Const.FieldHeight;
		cellSize = Const.CellSize;
		
		cells = new CellState[width][height];
		
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				cells[i][j] = CellState.Empty;
			}
		}
		
		setBackground(new Color(0, 0, 255));
		setSize(width * (cellSize + 1), height * (cellSize + 1));
		setMinimumSize(getSize());
		setMaximumSize(getSize());

		points = new ArrayList<Point>();
	}
	
	public int addShip(Ship ship, int x, int y) {
		points.add(new Point(x, y));
		
		Point newCords = getGridCoords(ship, x, y);
		
		Point[] shipCells = new Point[ship.getSize()];
		for(int i = 0; i < shipCells.length; ++i) {
			Point newCell;
			if(ship.getOrientation()) {
				newCell = new Point(newCords.x + i, newCords.y);
			} else {
				newCell = new Point(newCords.x, newCords.y + i);
			}
			shipCells[i] = newCell;
		}
		
		if(!isShipInField(shipCells)) {
			return 1; // Error code: ship is not in the field
		}
		
		if(!isNoShipsAround(shipCells)) {
			return 2; // Error code: ship too close to other ships
		}
		
		for(int i = 0; i < shipCells.length; ++i) {
			cells[shipCells[i].x][shipCells[i].y] = CellState.Filled;
		}
		return 0;		
	}
	
	private boolean isShipInField(Point[] shipCells) {
		for(int i = 0; i < shipCells.length; ++i) {
			if(shipCells[i].x < 0) return false;
			if(shipCells[i].y < 0) return false;
			if(shipCells[i].x >= width) return false;
			if(shipCells[i].y >= width) return false;
		}
		
		return true;
	}
	
	/**
	 * @param shipCells array with coords of all cells of the ship
	 * @return is no ships too close to shipCells
	 */
	private boolean isNoShipsAround(Point[] shipCells) {
		for(int i = 0; i < shipCells.length; ++i) {
			int x = shipCells[i].x;
			int y = shipCells[i].y;
			
			if(cells[x][y] != CellState.Empty) return false;
			if(x + 1 < width && cells[x + 1][y] != CellState.Empty) return false; 
			if(x - 1 > 0 && cells[x - 1][y] != CellState.Empty) return false; 
			if(y + 1 < height && cells[x][y + 1] != CellState.Empty) return false; 
			if(y - 1 > 0 && cells[x][y - 1] != CellState.Empty) return false; 
		}
		
		return true;
	}

	/**
	 * @param ship
	 * @return 
	 */
	private Point getGridCoords(Ship ship, int x, int y) {
		int newX;
		int newY;
		int cellSize = Const.CellSize;
		
		if(x % cellSize < cellSize / 2) {
			newX = (int) (x / cellSize);
		} else {
			newX = (int) (x / cellSize) + 1;
		}
		
		if(y % cellSize < cellSize / 2) {
			newY = (int) (y / cellSize);
		} else {
			newY = (int) (y / cellSize) + 1;
		}
		
		return new Point(newX, newY);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i = 0; i < Const.FieldWidth; ++i) {
			for(int j = 0; j < Const.FieldHeight; ++j) {
				int x = i * (Const.CellSize + 1);
				int y = j * (Const.CellSize + 1);
				
				switch (cells[i][j]) {
				case Empty: {
					g2.setColor(new Color(95, 150, 150));
					g2.fillRect(x, y, Const.CellSize, Const.CellSize);
					break;
				}
				case Filled: {
					g2.setColor(new Color(195, 150, 150));
					g2.fillRect(x, y, Const.CellSize, Const.CellSize);
					break;
				}
				case Sinked: {
					// TODO: Draw cross as a symbol that ship is sinked
					break;
				}
				case Checked: {
					// TODO: Draw dot in the center of cell
					break;
				}
				}
			}
		}
		
		g2.setColor(new Color(0, 0, 0));
		for(Point p : points) {
			g2.drawRect(p.x, p.y, 3, 3);
		}
	}
}
