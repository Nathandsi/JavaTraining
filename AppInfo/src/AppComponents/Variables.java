package AppComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.lang.System.Logger.Level;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class Variables {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //  Get screen size as Dimension object.
	public static int screenWidth = (int)screenSize.getWidth();
	public static int screenHeight = (int)screenSize.getHeight();
	public static int screenWidthQuarter = screenWidth / 4;
	public static int screenHeightQuarter = screenHeight / 4;
	public static int screenHeightHalf = screenHeight / 2;
	public static int screenWidthHalf = screenWidth / 2;
	public static Dimension screenQuarterDimension = new Dimension(screenWidthQuarter, screenHeightQuarter);
	public static Dimension screenHalfDimension = new Dimension(screenWidthHalf, screenHeightHalf);
	
	// couleur de fond de la fenêtre principale
	public static Color bgColorPrimaryWindow = new Color( 18,32,56);
	public static Color bgColorSecondaryWindow = new Color( 58,132,243);
	
	public static Color colorMenuLeft = new Color(50,50,50);
	public static Color colorMenuProp = new Color(70,70,70);
	public static Color colorMenuRight = new Color(34,185,48);
	public static Color colorMenuTop = new Color(164,45,88);
	public static Color colorMenuBottom = new Color(28,45,198);
	public static Color colorMenuCenter = new Color(82,5,148);
	public static Color colorLabelProp = new Color(130, 130, 130);
	public static Color colorTextProp = new Color(150, 150, 150);
	
	public static Dimension tailleLabel = new Dimension(800,100);
	public static Dimension tailleLabelProp = new Dimension(120, 30);
	public static Dimension tailleBouton = new Dimension(50,100);
	public static Dimension tailleTextProp = new Dimension(120, 30);
	
	public static final Dimension useTailleMenuRight = new Dimension(50, 50);
	public static final Dimension minimumTailleMenuRight = new Dimension(0, 0);
	public static final Dimension maximumTailleMenuRight = new Dimension(300, 600);
	
	public static final Dimension useTailleMenuProp = new Dimension(50, 0);
	public static final Dimension minimumTailleMenuProp = new Dimension(0, 0);
	public static final Dimension maximumTailleMenuProp = new Dimension(300, 500);
	
	public static final Dimension useTailleMenuBottom = new Dimension(50, 50);
	public static final Dimension minimumTailleMenuBottom = new Dimension(0, 0);
	public static final Dimension maximumTailleMenuBottom = new Dimension(200, 200);
	
	public static final Dimension useTailleContainerCenter = new Dimension(500, 600);
	public static final Dimension minimumTailleContainerCenter = new Dimension(100, 100);
	public static final Dimension maximumTailleContainerCenter = new Dimension(2000, 2000);
	
	public static boolean isNodeLeaf = false;
	
	//   -----   temporary lists that will be added to final list   -----
	public static ArrayList<String> tempNameNodeList = new ArrayList<String>();
	public static ArrayList<String> tempPathNodeList = new ArrayList<String>();
	public static ArrayList<File> tempFileNodeList = new ArrayList<File>();
	public static ArrayList<CustomNode> tempCustomNodeList = new ArrayList<CustomNode>();
	//   -----   lists that lists the elements following the FileTreeModel, each list element represent a level of tree model.   -----
	public static ArrayList<ArrayList<String>> nameNodeList = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> pathNodeList = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<File>> fileTreeList = new ArrayList<ArrayList<File>>();
	public static ArrayList<ArrayList<CustomNode>> customNodeTree = new ArrayList<ArrayList<CustomNode>>();
	
	//   -----   Représente le nombre de fichier (ou dossier) contenu dans chaque dossier (branch)   -----
	
	// public static ArrayList<Integer> level = new ArrayList<Integer>();
	
	public static File rootDirectory;
	
	//   -----   Représente le niveau de branch (la branch actuelle)   -----
	public static int treeLevel;
	//   -----   Représente le nombre de dossier dans une branch   -----
	public static int nbrDirInTempBranch = 0;
	//   -----   Représente le nombre de dossier qu'il reste à gérer dans une branch   -----
	public static int nbrDirLeft = 0;
	//   -----   Représente le nombre d'éléments dans le dossier actuellement process   -----
	public static int nbrElementInDir = 0;
	
	//   -----   Tableau d'entiers qui représente le nombre de fichiers par branch   -----
	public static ArrayList<Integer> listNbrFile = new ArrayList<Integer>();

	//   -----   Tableau de tableau de File qui représente le squelette de l'arborescence   -----
	public static ArrayList<File[]> treeSqueletton = new ArrayList<File[]>();
	
	//  -----   Tableau de String qui représente la liste des noms des dossiers de l'arbre.   -----
	public static ArrayList<String> listDirName = new ArrayList<String>();
	//   -----   Tableau de String qui représente la liste des noms des éléments (fichiers et dossiers) de l'arbre.   -----
	public static ArrayList<String> listFilesInTree = new ArrayList<String>();
	
	//   -----   Tableau temporaire représentant la liste des éléments, de type dossier, du dossier dans lequel on se trouve   -----
	public static ArrayList<File> tempListDirectory = new ArrayList<File>();
	//   -----   Tableau temporaire représentant la liste des éléments, de type fichier, du dossier dans lequel on se trouve.   -----
	public static ArrayList<File> tempListFiles = new ArrayList<File>();
	//  -----   Tableau temporaire représentant la liste des éléments du dossier dans lequel on se trouve.   -----
	public static ArrayList<File> tempListElement = new ArrayList<File>();
	
	
	
	//   -----   Node racine de l'arbre   -----
	public static DefaultMutableTreeNode rootDir;
	
	public static DefaultMutableTreeNode nodeDir;
	
	//public static nextDir
	
	public static String emplacement;
	public static String response;
	
	public static int nbrDir = 0;
	
	public static ArrayList<String> listChild = new ArrayList<String>();
	
	public static String pathRootDir = "C:\\Users\\ndurand\\eclipse-workspace\\AppInfo\\UserFiles";
	
	public static void addElementToListTemp(ArrayList<String> listName, ArrayList<String> listPath, ArrayList<File> listFile, ArrayList<CustomNode> listCustomNode) {
		int i = 0;
		for(i=0; i < listName.size(); i++) {
			tempNameNodeList.add(listName.get(i));
			tempPathNodeList.add(listPath.get(i));
			tempFileNodeList.add(listFile.get(i));
			tempCustomNodeList.add(listCustomNode.get(i));
		}	
	}
	
	public static void addListsTempToLists() {
		nameNodeList.add(tempNameNodeList);
		pathNodeList.add(tempPathNodeList);
		fileTreeList.add(tempFileNodeList);
		customNodeTree.add(tempCustomNodeList);
	}
	
}
