package window.panels.field;

import java.awt.Graphics;
import javax.swing.JPanel;
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
		
		setBackground(Const.FieldBackground);
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
					g.setColor(Const.FieldCellBackground);
					g.fillRect(x, y, Const.CellSize, Const.CellSize);
					break;
				}
				case Sinked: {
					g.setColor(Const.FieldShipColor);
					g.fillRect(x, y, Const.CellSize, Const.CellSize);
					g.setColor(Const.FieldSinkedShipCrossColor);
					g.drawLine(x, y, x + cellSize, y + cellSize);
					g.drawLine(x + cellSize, y, x, y + cellSize);
					break;
				}
				}
			}
		}
	}
}
