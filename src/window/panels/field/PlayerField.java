package window.panels.field;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import main.Const;
import window.panels.ship.Ship;

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
			g.setColor(Const.FieldFilledCellColor);
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
		
		if(!isNoShipsAround(cells, shipCells)) {
			return 2; // Error code: ship too close to other ships
		}
		
		for(int i = 0; i < shipCells.length; ++i) {
			cells[shipCells[i].x][shipCells[i].y] = CellState.Filled;
			filledCells.add(new Point(shipCells[i].x, shipCells[i].y));
			System.out.println(filledCells.size());
		}
		return 0;		
	}
	
	public void fillRandom(ArrayList<Ship> ships) {
		CellState[][] bufferCells = cells.clone();
		ArrayList<Ship> bufferShips = (ArrayList<Ship>)ships.clone();

		Random rand = new Random();
		int count = 0;
		// In loop work with copies of data to be able to back to the initial
		// condition in the case of endless loop 
		while(bufferShips.size() > 0) {
			count++;
			
			int x = rand.nextInt(Const.FieldWidth);
			int y = rand.nextInt(Const.FieldHeight);
			
			int resultCode = directAddShip(bufferCells, bufferShips.get(bufferShips.size() - 1), x, y);
			if(resultCode == 0) {
				bufferShips.remove(bufferShips.size() - 1);
			}

			// If there is more then 200 iterations, it can be eternal loop
			// So back to the initial conditions and start place ships again
			if(count > 200) {
				bufferCells = cells.clone();
				bufferShips = (ArrayList<Ship>)ships.clone();
				count = 0;
			}
		}
		
		// Commit changes from buffers to containers
		for(int i = 0; i < cells.length; ++i) {
			for(int j = 0; j < cells[0].length; ++j) {
				cells[i][j] = bufferCells[i][j];
			}
		}
		ships.removeAll(ships);
	}
	
	/**
	 * @param ship - ship to be added
	 * @param x - number of column for left-upper corner of ship
	 * @param y - number of row for left-upper corner of ship
	 * @return code:
	 * <ul>
	 * <li> 0 - there are no errors, the ship added
	 * <li> 1 - error code: ship is not in the field
	 * <li> 2 - error code: ship to close to other ships
	 * </ul>
	 */
	private int directAddShip(CellState[][] cells, Ship ship, int x, int y) {
		Point[] shipCells = new Point[ship.getSize()];
		for(int i = 0; i < shipCells.length; ++i) {
			Point newCell = null;
			if(ship.getOrientation()) {
				newCell = new Point(x + i, y);
			} else {
				newCell = new Point(x, y + i);
			}
			shipCells[i] = newCell;
		}
		
		if(!isShipInField(shipCells)) {
			return 1; // Error code: ship is not in the field
		}
		
		if(!isNoShipsAround(cells, shipCells)) {
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
	private boolean isNoShipsAround(CellState[][] cells, Point[] shipCells) {
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
