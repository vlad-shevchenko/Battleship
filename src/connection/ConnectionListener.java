package connection;

import java.awt.Point;
import window.PlayerStatus;
import field.CellState;

public interface ConnectionListener {

	/**
	 * Update status of second player:
	 * <ul>
	 * <li>Connecting...</li>
	 * <li>Places ships</li>
	 * <li>Leave</li>
	 * <li>Ready</li>
	 * </ul>
	 * 
	 * @param status
	 *            - new status of second player
	 */
	public void updateStatus(PlayerStatus status);
	
	
	/**
	 * Set name of second player
	 * 
	 * @param name - 
	 *            name of second player
	 */
	public void updateName(String name);
	
	
	/**
	 * Fire into cell of field in cords, specified by parameter p
	 * 
	 * @param p
	 *            - cords of cell for fire
	 * @return new <b>state</b> of targeted cell
	 */
	public CellState fire(Point p);
	
	
	/**
	 * Flag of end of game. Returns true if all ships of player are sinked and
	 * he have lost
	 * 
	 * @return finishStatus - is player lose
	 */
	public boolean isFinish();
}
