package window.panels.field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import main.Const;

public class EnemyField extends AbstractField {
	
	private ArrayList<Point> checkedCells;
	
	public EnemyField() {
		super();
		
		checkedCells = new ArrayList<Point>(0);
	}
	
	public void setChecked(int x, int y) {
		if (cells[x][y] == CellState.Empty) {
			checkedCells.add(new Point(x, y));
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Point p : checkedCells) {
			int x = p.x * (Const.CellSize + 1);
			int y = p.y * (Const.CellSize + 1);
			
			g.setColor(new Color(0, 0, 0));
			g.fillOval(x, y, cellSize, cellSize);
		}
	}
}
