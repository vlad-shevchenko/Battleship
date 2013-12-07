package window.panels.field;

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
	
	public void setSinked(int x, int y) {
		if(cells[x][y] == CellState.Empty) {
			checkedCells.remove(new Point(x, y));
			cells[x][y] = CellState.Sinked;
		}
		
		repaint();
	}
	
	public void setChecked(int x, int y) {
		if (cells[x][y] == CellState.Empty) {
			checkedCells.add(new Point(x, y));
		}

		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(Point p : checkedCells) {
			int x = p.x * (Const.CellSize + 1);
			int y = p.y * (Const.CellSize + 1);
			
			g.setColor(Const.FieldCellPointColor);
			g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
		}
	}
}
