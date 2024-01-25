package Application;

import AppComponents.Menu;
import AppComponents.Variables;
import AppComponents.Window;


public class StartApplication {

	Menu menu =  new Menu();

	
	public static void main(String[] args) {

		Window firstWindow = new Window(true, 0, 0, Variables.bgColorPrimaryWindow, false, "exit", "BorderLayout", 0, 0 );
		firstWindow.setVisible(true);


		
//		Window secondWindow = new Window(Variables.bgColorSecondaryWindow, Variables.screenQuarterDimension);
//		secondWindow.setVisible(true);
		

	}

}
