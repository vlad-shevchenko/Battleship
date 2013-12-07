package window.panels.field;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import window.panels.ship.Ship;
import main.Const;

public class PlayerField extends AbstractField {

	private ArrayList<Point> filledCells;
	
	public PlayerField() {
		super();
		
		filledCells = new ArrayList<Point>(0);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Point p : filledCells) {
			int x = p.x * (Const.CellSize + 1);
			int y = p.y * (Const.CellSize + 1);
			g.setColor(Const.FilledCellColor);
			g.fillRect(x, y, Const.CellSize, Const.CellSize);
		}
	}
	
	public boolean fire(int x, int y) {
		if(cells[x][y] == CellState.Filled) {
			filledCells.remove(new Point(x, y));
			cells[x][y] = CellState.Sinked;

			repaint();
			return true;
		}
		
		return false;
	}
	
	public int getShipsCount() {
		System.out.println(filledCells.size());
		return filledCells.size();
	}
	
	public int addShip(Ship ship, int x, int y) {		
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
			filledCells.add(new Point(shipCells[i].x, shipCells[i].y));
			System.out.println(filledCells.size());
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
			
			// return false if current cell or any neighbor already is not empty 
			if(cells[x][y] != CellState.Empty) return false;
			if(x + 1 < width && cells[x + 1][y] != CellState.Empty) return false; 
			if(x - 1 > 0 && cells[x - 1][y] != CellState.Empty) return false; 
			if(y + 1 < height && cells[x][y + 1] != CellState.Empty) return false; 
			if(y - 1 > 0 && cells[x][y - 1] != CellState.Empty) return false;
			if(y - 1 > 0 && x - 1 > 0 && cells[x - 1][y - 1] != CellState.Empty) return false;
			if(y - 1 > 0 && x + 1 < width && cells[x + 1][y - 1] != CellState.Empty) return false;
			if(y + 1 < height && x - 1 > 0 && cells[x - 1][y + 1] != CellState.Empty) return false;
			if(y + 1 < height && x + 1 < width && cells[x + 1][y + 1] != CellState.Empty) return false;
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
}
