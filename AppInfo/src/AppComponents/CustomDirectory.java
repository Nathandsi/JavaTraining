package AppComponents;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;


public class CustomDirectory {

	private ArrayList<ArrayList<File>> bigOrderedList = new ArrayList<ArrayList<File>>();
	
	private static String nameDirectory;   // nom du dossier
	private static Path pathDirectory;      // chemin du dossier
	private ArrayList<File> listDirectory = new ArrayList<File>();   // listing de l'ensemble des dossiers
	private ArrayList<File> partListDirectory = new ArrayList<File>();   // listing du dossier dans lequel on se trouve
	
	private int i = 0;   // numéro du dossier
	private int j = 0;   // numéro du fichier
	private int k = 0;  // numéro de l'élément (fichier ou dossier)
	private int l = 0;  // index de l'ArrayList contenu dans le big ArrayList final
	
	//Constructeur d'un custom directory qui prend un File en paramètre
	public CustomDirectory(File directory) {
		
		// Instanciation depuis le File en paramètre
		nameDirectory = directory.getName();
		pathDirectory = directory.toPath();
		

	}
	
	// Méthode qui liste l'ensemble du contenu du dossier source
	public ArrayList<ArrayList<File>> listDirectory(File dir){
		
		partListDirectory = convertTableFileToArrayListFile(dir);
		bigOrderedList.add(partListDirectory);
		//  On boucle sur chaque élément et si on tombe sur un dossier, on relance la même méthode avec ce dossier en paramètre.
		for (File element : partListDirectory) {
			// Si on tombe sur un dossier, on récupère le contenu et on l'envoi dans la liste finale.
			if (element.isDirectory()) {
				partListDirectory = convertTableFileToArrayListFile(element);
				bigOrderedList.add(partListDirectory);
				//  On relance la même méthode avec le sous dossier.
				listDirectory(element);
			} 
		}
		return bigOrderedList;
	}
	
	// Méthode de convertion d'un tableau de File en ArrayList de File : on renvoi un ArrayList de File créé à partir d'un dossier source
	private ArrayList<File> convertTableFileToArrayListFile(File source){
		// Récupération, dans un tableau de File, de la liste des éléments contenu dans la source
		File[] listElement = source.listFiles();
		// Création d'un ArrayList de File qui va permettre de stocker les éléments du tableau de File dans un ArrayList
		ArrayList<File> partList = new ArrayList<File>();
		//  On parcours le tableau de File et on ajoute chaque élément dans l'ArrayList de File
		for (File element : listElement) { partList.add(element); }
		// Enfin, on renvoi un ArrayList de File qui contient l'ensemble des éléments qui ont été listé depuis le dossier source
		return partList;
	}
	
}
