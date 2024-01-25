package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		String nomAction = e.getActionCommand();
		switch (nomAction) {
		case "closeWindow" :
			
			break;
		default :
			break;
		}
	}
	
	public Control() {
		
	}

}
