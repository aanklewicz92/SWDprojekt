package logic;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface AlgorithmListener extends ActionListener {
	public void algorithmFinished(ArrayList<Integer> list);
}
