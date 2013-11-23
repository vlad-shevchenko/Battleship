package window.ships;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Ship implements Shape {

	public Ship() {
		
	}
	
	public boolean contains(Point2D p) {
		return false;
			
	}

	public boolean contains(Rectangle2D r) {
		return false;
			
	}

	public boolean contains(double x, double y) {
		return false;
			
	}

	public boolean contains(double x, double y, double w, double h) {
		return false;
			
	}

	public Rectangle getBounds() {
		return null;
			
	}

	public Rectangle2D getBounds2D() {
		return null;
			
	}

	public PathIterator getPathIterator(AffineTransform at) {
		return null;
			
	}

	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return null;
			
	}

	public boolean intersects(Rectangle2D r) {
		return false;
			
	}

	public boolean intersects(double x, double y, double w, double h) {
		return false;
			
	}

}
