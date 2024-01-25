package AppComponents;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class SingletonCollection {
	private Collection<File> fileCollection;
	private static SingletonCollection instance;
	
	private SingletonCollection() {
		fileCollection = new ArrayList<File>();
	}
	
	public static SingletonCollection getInstance() {
		if (instance == null) {
			instance = new SingletonCollection();
		}
		return instance;
	}
	
	public void addFile(File f) {
		fileCollection.add(f);
	}
	
	public Collection<File> getFiles(){
		return fileCollection;
	}
}
