package AppComponents;

import java.io.File;
import java.util.ArrayList;

public class TabDirectory {

	private ArrayList<File> Files = new ArrayList<File>();
	
	private int nbrFiles;
	
	
	public ArrayList<File> tabDirectory(File rootDirectory) {
		
		this.nbrFiles = rootDirectory.listFiles().length;
		
		this.DirLoop(rootDirectory);
		
		return Files;
		
	}
	
	private void DirLoop(File file) {
		
	}
	
}
