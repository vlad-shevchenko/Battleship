package window.panels.ship;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import main.Const;

public class Ship {

	private ArrayList<Rectangle2D> rects;
	private boolean orientation;
	private int size;
	
	
	public Ship(int size, Point location, boolean orientation) {
		this.size = size;
		this.orientation = orientation;
		
		rects = new ArrayList<Rectangle2D>(size);
		
		for(int i = 0; i < size; ++i) {
			Rectangle2D rect = null;
			if(orientation) {
				rect = new Rectangle2D.Double(
						location.x + i * Const.CellSize + 1, location.y, 
						Const.CellSize, Const.CellSize);
			} else {
				rect = new Rectangle2D.Double(
						location.x + i * Const.CellSize + 1, location.y, 
						Const.CellSize, Const.CellSize);
			}
			rects.add(rect);
		}
	}
	
	/**
	 * Return coords of left-upper corner of the ship
	 * 
	 * @return point location of left-upper corner of the ship
	 */
	public Point getLocation() {
		return new Point((int) rects.get(0).getX(), (int) rects.get(0).getY());
	}
	
	/**
	 * Returns coords of the center of the ship
	 * 
	 * @return point coords of the center of the ship
	 */
	public Point getCenter() {
		if(size % 2 == 1) {
			int x = (int) rects.get(size / 2).getCenterX();
			int y = (int) rects.get(size / 2).getCenterY();
			return new Point(x, y);
		} else {
			int x = (int) rects.get(size / 2 - 1).getMaxX() + 1;
			int y = (int) rects.get(size / 2 - 1).getCenterY();
			return new Point(x, y);
		}
	}
	
	/**
	 * Move the ship to new location
	 * 
	 * @param newLoc new coords of left-upper corner of the ship
	 */
	public void moveLocation(Point newLoc) {
		Point curLoc = getLocation();
		Point diff = new Point(newLoc.x - curLoc.x, newLoc.y - curLoc.y);
		
		for(Rectangle2D rect : rects) {
			rect.setFrame(rect.getX() + diff.x, rect.getY() + diff.y, 
					Const.CellSize, Const.CellSize);
		}
	}
	
	/**
	 * @param newCenter new coords of the center of the ship
	 */
	public void moveCenter(Point newCenter) {
		Point curLoc = getLocation();
		Point curCenter = getCenter();
		
		Point diff = new Point(curLoc.x - curCenter.x, curLoc.y - curCenter.y);
		
		Point newLoc = new Point(newCenter.x - diff.x, newCenter.y - diff.y);
		
		moveLocation(newLoc);
	}
}
