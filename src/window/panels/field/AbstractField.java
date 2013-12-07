package window.panels.field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import window.panels.ship.Ship;
import main.Const;

public abstract class AbstractField extends JPanel {
	
	protected CellState[][] cells;
	
	protected int width;
	protected int height;
	protected int cellSize;
	
	public AbstractField() {
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
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i = 0; i < Const.FieldWidth; ++i) {
			for(int j = 0; j < Const.FieldHeight; ++j) {
				int x = i * (Const.CellSize + 1);
				int y = j * (Const.CellSize + 1);
				
				switch (cells[i][j]) {
				case Empty: {
					g.setColor(new Color(95, 150, 150));
					g.fillRect(x, y, Const.CellSize, Const.CellSize);
					break;
				}
				case Sinked: {
					g.setColor(new Color(195, 150, 150));
					g.fillRect(x, y, Const.CellSize, Const.CellSize);
					g.setColor(new Color(200, 30, 80));
					g.drawLine(x, y, x + cellSize, y + cellSize);
					g.drawLine(x + cellSize, y, x, y + cellSize);
					break;
				}
				}
			}
		}
	}
}
