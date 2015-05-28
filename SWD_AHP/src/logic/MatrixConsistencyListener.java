package logic;

import java.awt.event.ActionListener;

public interface MatrixConsistencyListener extends ActionListener{
	public void inconsistentMatrix(Integer number);
	public void matrixConsistency(Integer matrixNumber, Double consistency);
}
