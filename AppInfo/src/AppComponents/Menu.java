package AppComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

public class Menu {
	
	// variable static qui représente le numéro des dossiers, dans l'ordre.
	public int i = 0;
	// variable static qui représente le numéro des fichiers, dans l'ordre.
	public int j = 0;
	// variable static qui représente le numéro des éléments, dans l'ordre.
	public int k = 0;
	
	public File source = new File(Variables.pathRootDir);
	
	public CustomDirectory customDir = new CustomDirectory(source);
	
	public ArrayList<ArrayList<File>> bigList = customDir.listDirectory(source);
	
	
	// tableau qui va récupérer le résultat de listDirectory, dans le but d'en faire un stream pour l'organiser.
	public static ArrayList<File> listFilesToStream = new ArrayList<File>();
	
	public int nbrChildFromModel;
	
	
	// Constructeur
	public Menu() {
		
	
	}
	
	public static JButton createButton(String nameButton, Dimension size, String Vpos, String Hpos) {
		JButton bouton = new JButton(nameButton);
		bouton.setPreferredSize(size);
		switch (Hpos) {
		case "LEFT":
			bouton.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case "RIGHT":
			bouton.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		case "CENTER":
			bouton.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		default:
			break;
		}
		switch (Vpos) {
		case "TOP":
			bouton.setVerticalAlignment(SwingConstants.TOP);
			break;
		case "BOTTOM":
			bouton.setVerticalAlignment(SwingConstants.BOTTOM);
			break;
		case "CENTER":
			bouton.setVerticalAlignment(SwingConstants.CENTER);
			break;
		default:
			break;
		}
		
		return bouton;
	}
	
	public static JLabel createLabel(String nameLabel, Dimension size) {
		nameLabel = "<html><b>Zone<br/>Centrale</b></html>";
		JLabel label = new JLabel(nameLabel);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setPreferredSize(size);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		return label;
	}

	public static JLabel createLabel(String textLabel) {
		JLabel label = new JLabel(textLabel);
		label.setPreferredSize(Variables.tailleLabel);
		label.setSize(Variables.tailleLabel);
		label.setBackground(Variables.colorLabelProp);
		//System.out.println(label.getBackground());
		//System.out.println(label.getSize());
		label.setPreferredSize(Variables.tailleLabelProp);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		return label;
	}
	
	public static JTextArea createTextArea(String title) {
		JTextArea text = new JTextArea(title);
		text.setPreferredSize(Variables.tailleTextProp);
		text.setBackground(Variables.colorTextProp);
		text.setPreferredSize(Variables.tailleTextProp);
		text.setSize(Variables.tailleTextProp);
		//System.out.println(text.getSize());
		//System.out.println(text.getBackground());
		return text;
	}
	
	public static JTextArea createTextArea(String title, Color couleur) {
		JTextArea text = new JTextArea(title);
		text.setPreferredSize(Variables.tailleTextProp);
		text.setBackground(Variables.colorLabelProp);
		text.setPreferredSize(Variables.tailleTextProp);
		text.setSize(Variables.tailleTextProp);
		//System.out.println(text.getSize());
		//System.out.println(text.getBackground());
		return text;
	}
	
	public static JPanel createPropMenu(String title, int rows, int cols, int hgap, int vgap, Color couleur) {			// Menu LEFT
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());
		JLabel titleProp = new JLabel("<html><b> " + title + "</b></html>");
		titleProp.setSize(Variables.tailleLabel);
		titleProp.setHorizontalAlignment(SwingConstants.CENTER);
		containerPanel.add(titleProp, BorderLayout.NORTH);
		
		JPanel gridPanelMenu = new JPanel();

		gridPanelMenu.setLayout(new GridLayout(rows, cols, hgap, vgap));
		gridPanelMenu.setBackground(couleur);
		gridPanelMenu.setAlignmentY(SwingConstants.CENTER);
		gridPanelMenu.setAlignmentX(SwingConstants.CENTER);
		int i = 1;
		for (i=1; i<rows; i++) {
			gridPanelMenu.add(createTextArea("Propriété n° " + i, Variables.colorLabelProp));
			gridPanelMenu.add(createTextArea("Valeur n° " + i));
		}

		containerPanel.add(gridPanelMenu, BorderLayout.CENTER);
		
		return containerPanel;
	}
	
//******************************************************************************************************************
	public void listDir(File source) {
		
		
		
	//  Instanciation de la liste des éléments du dossier source dans les variables
/*
		File[] listFiles = source.listFiles();
		
		for (File element : listFiles) {
			
			if (element.isDirectory()) {
				Variables.tempListDirectory.add(Menu.i, element);   // ajout dossier temporaire
				Variables.tempListElement.add(Menu.k, element);   // ajout élément temporaire
				Menu.listFilesToStream.add(element);
				Menu.i = Menu.i + 1;
				Menu.k = Menu.k + 1;
				System.out.println("Nom du dossier : " + element.getName() + " : est le dossier numéro " + Menu.i);
				listDirectory(element);
			} else {
				Variables.tempListFiles.add(Menu.j, element);   // ajout fichier temporaire
				Variables.tempListElement.add(Menu.k, element);   // ajout élément temporaire
				Menu.listFilesToStream.add(element);
				Menu.j = Menu.j + 1;
				Menu.k = Menu.k + 1;
				System.out.println("Nom du fichier : " + element.getName() + " : est le fichier numéro " + Menu.j);
			}
			
		}
		int compteur = 0;
		for (File element : Variables.tempListElement) {
			String typeElement = (element.isDirectory()) ? "dossier : " : "fichier : ";
			compteur += 1;
			System.out.println("Type d'élément : " + typeElement + "  -  Nom  :  " + element.getName() + " : est le " + typeElement + " numéro " + compteur);
		}  
		*/
		
		
		
	}
	
//*******************************************************************************************************************
	public void createRootNode() throws IOException {	
		//   -----   1ère instanciation des variables pour création de l'arbre.   -----
		//   -----   Chemin dossier racine   -----
		String rootPath = Variables.pathRootDir;
		Variables.treeLevel = 0;
		//   -----   Création d'un dossier à partir du chemin du dossier racine   -----
		File rootDir = new File(rootPath);
		Variables.rootDirectory = rootDir;
	
		listDir(rootDir);
		
		File fichierSource = new File(Variables.pathRootDir);
		
		Path chemin = fichierSource.toPath();
		ArrayList<File> resultSet = new ArrayList<>();
		ArrayList<String> resultSetName = new ArrayList<>();
		
		 List<Path> result = new ArrayList<>();
		 try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){
			 for (Path entry : stream) {
				 result.add(entry);
				 resultSet.add(new File(entry.toString()));
			 }
		 } catch (DirectoryIteratorException ex) {
			 throw ex.getCause();
		 }
		 
	//	 System.out.println(result.toString());
	//	 System.out.println(resultSet.toString());
		 
		 for (File element : resultSet) {
			 System.out.println(element.getName());
			 resultSetName.add(element.getName());
		 }
		 
		 

		 
		 
		 resultSetName.stream().forEach(System.out::println);
		// resultSetName.stream().sorted().forEach(System.out::println);
		 
		 
		// result = (Menu.listFilesToStream).directoryStream().filter(f -> f.getName() == File.getName()).sorted().forEach(System.out::println);
		

		
		
		
		//   -----   Création d'un TreeModel à partir de ce dossier   -----
		FileTreeModel model = new FileTreeModel(rootDir);
		//   -----   On enregistre le nom du dossier dans un String   -----
		String nameRootNode = model.toString();
		//   -----   Création d'une DefaultMutableTreeNode portant le nom du dossier   -----
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(nameRootNode);
		//   -----   Instanciation de la node racine dans les Variables statiques  -----
		Variables.rootDir = rootNode;
		//   -----   Instanciation du dossier racine (1er dossier) de la liste qui contiendra l'intégralité des fichiers et dossiers   -----
		Variables.listDirName.add(rootNode.toString());
		//   -----   On récupère le nombre d'éléments situés dans la dossier racine   -----
		Variables.nbrElementInDir = (int)rootDir.length();
		//   -----   On récupère le nombre de dossiers et on la met dans les Variables (nbrDirInTempBranch)   -----
		Variables.nbrDirInTempBranch = nbrDir(rootDir);
		//   -----   On compte le nombre de dossiers qu'il reste à process dans le dossier racine   -----
		Variables.nbrDirLeft = nbrDir(rootDir);
		//   -----   Stockage du contenu du dossier racine dans les Variables.   -----
	//	contentDirToVariables(rootDir);
	//	checkDir(getFirstDir(getFirstDir(rootDir)));
	//	checkDir(getNextDir(getFirstDir(getFirstDir(rootDir))));
		
	}
	
	public static JTree createLeftTreeMenu() {
	//	createRootNode();
		int nbrRootElement = Variables.nbrDirInTempBranch;
		
		File subDir = getFirstDir(Variables.rootDirectory);
		Variables.nbrDirInTempBranch = (int) subDir.length();
		
		
		

		
//			//		Variables.nodeDir = tempRootNode;
//			int i = 0;
//			int j = 0;
//			int tempTreeLevel = Variables.treeLevel;
//			DefaultMutableTreeNode tempNode;
//			CustomNode customNode;
//			CustomNode rootCustomNode = new CustomNode(nameRootNode, rootPath);
//			//   -----   tree creation from root folder   -----	
//			customNode = rootCustomNode;
//			//   -----   JTree creation with root node  -----
//			//createSubDir(rootNode, Variables.nameNodeList);		
//			loopDir(rootDir);
//			JTree tree = new JTree(rootNode);
//			System.out.println("rootNode : " + rootNode);
//			System.out.println("customNode : " + customNode);
//			System.out.println("model : " + model);
//			System.out.println("rootNode userObject : " + rootNode.getUserObject());
//			System.out.println("nombre de dossiers : " + nbrDir(rootNode));
//			tree.setShowsRootHandles(true);
		//return tree;
		return null;
		}
	
	public static JTree updateLeftTreeMenu() {
//		//   -----   Root of the Tree   -----
//		DefaultMutableTreeNode treeName = CustomTree.createSingleNode(nameTree);
//		//   -----   The changing node that is used to define if the node needs to be a branch or a leave   -----
//		DefaultMutableTreeNode switchNode = new DefaultMutableTreeNode();
//		DefaultMutableTreeNode previousFolderNode;
//		previousFolderNode = treeName;
//		DefaultMutableTreeNode previousLeafNode = treeName;
//		DefaultMutableTreeNode previousNode = treeName;
//		//   -----   Sub elements following the root   -----
//		ArrayList<DefaultMutableTreeNode> collection = CustomTree.createMultipleNodes(tabString);
//		for (DefaultMutableTreeNode node : collection) {
//			switchNode = node;
//			if (switchNode.getAllowsChildren() == true){
//				if(previousFolderNode.getLevel() == 0) {
//					previousFolderNode.add(switchNode);
//					previousFolderNode = switchNode;
//					previousNode = switchNode;
//				} else if (previousFolderNode.getLevel() > 0 && switchNode.getAllowsChildren() == true) {
//					if (previousNode.isLeaf() == true) {
//						previousLeafNode = previousNode;
//						while(previousLeafNode.getPreviousLeaf() != null) {
//						previousLeafNode = previousLeafNode.getPreviousLeaf();
//						}
//						previousFolderNode = previousLeafNode.getPreviousNode();
//						previousFolderNode.getPreviousNode().add(switchNode);
//					} else {
//						previousNode.getPreviousNode().add(switchNode);
//						previousFolderNode = previousNode;
//					}
//					previousFolderNode.add(switchNode);
//					previousFolderNode = switchNode;
//				}
//
//			} else {
//				previousFolderNode.add(switchNode);
//			}
//			
//			System.out.println(previousFolderNode.getLevel());
			
//			if (Character.isUpperCase(value) && switchNode.getLevel() == 0) {
//				treeName.add(node);
//				switchNode = node;
//				System.out.println("1er cas  :  " + switchNode.getPreviousNode());
//				//treeName.add(switchNode);
////			} else if (Character.isUpperCase(value) && switchNode.getAllowsChildren() == false) {
////				System.out.println("2eme cas  :  " + switchNode.getPreviousNode());
////				switchNode = switchNode.getPreviousNode();
////				switchNode.add(node);
//			} else {
//				System.out.println("3eme cas  :  " + switchNode.getPreviousNode());
//				switchNode.getPreviousNode().add(node);
//			}
//		}
		//   -----   JTree creation   -----
//		JTree tree = new JTree(treeName);
//		return tree;
		
		File dir = new File("C:\\Users\\ndurand\\eclipse-workspace\\AppInfo\\UserFiles");
		TreeModel model = new FileTreeModel(dir);
		
		JTree tree = new JTree(model);
		
		return tree;
		
	}
	
	//   -----   On récupère la liste des éléments du dossier et on l'envoi dans Variables (squelette de l'arbre).   -----
	public static void contentDirToVariables(File actualBranch) {
		Variables.treeSqueletton.add(actualBranch.listFiles());
	}
	
	//   -----   Loop the actual branch to process each Dir   -----
	public static void checkDir(File actualBranch) {
		//   -----   Loop the actualBranch to process each Dir   -----
		for (File value : actualBranch.listFiles()) {
			if (value.isDirectory()) {
				processDir(value);
			}
		}
	}
	
	public static void processDir(File actualDir) {
		contentDirToVariables(actualDir);
	// 	Variables.nbrDirLeft = Variables.nbrDirLeft - 1;
	}
	
	public void fillDirOfBranch(File root) {
		
	}

//		
//		for (File value : Variables.tempListFiles) {
//			//   -----   on l'envoi dans les Variables.   -----
//		//	Variables.listFilesInTree.add();
//			
//			//   -----   Création d'une CustomNode temporaire   -----
//			CustomNode tempNode = new CustomNode(value.getName(),value.getPath(), value.isDirectory());
//			//   -----   Ajout de la CustomNode temporaire dans la liste de CustomNode temporaire dans Variables.   -----
//			Variables.tempCustomNodeList.add(tempNode);
//			
//			//   -----   On vérifie si c'est un dossier ou non   -----
//			if (countChild(value) != 0) {
//				//   -----   On recupère uniquement les dossiers et on envoi dans Variables.   -----
//				Variables.listDirName.add(value.getName());
//			}
//		}
		
//		//   -----   On fait un TreeModel temporaire à partir du fichier en paramètre   -----
//		FileTreeModel tempModel = new FileTreeModel(root);
//		//   -----   On récupère le nombre de fichiers à l'interieur   -----
//		int nbrChildFromModel = tempModel.getChildCount(root);
//		//   -----   On ajoute une entrée au tableau d'entiers qui représente le nombre de FICHIERS par branch   -----
//		Variables.listNbrFile.add(nbrChildFromModel);
//		//   -----   On ajoute une entrée au tableau de String qui représente la liste des DOSSIERS de l'arbre.   -----
//		Variables.listDirName.add(root.getName());

	
	
	public ArrayList<String> createStringTree() {
		int i = 0;
		ArrayList<String> listNode = new ArrayList<String>();
		for (i = 0; i < Variables.treeLevel; i++) {
			int j = 0;
			for (j = 0; j == Variables.treeLevel; j++) {
//				listNode.add(Variables.listFileName.get(i));
			}
		}
		return listNode;
	}
	
	public static int countChild(File Dir) {
		return Dir.list().length;
	}
	
	public static DefaultMutableTreeNode createSubDir(DefaultMutableTreeNode rootSubDirNode, ArrayList<String> list) {
		for (String value : list) {
			rootSubDirNode.add(new DefaultMutableTreeNode(value));
		}
		return rootSubDirNode;
	}
	
	//   -----   Renvoi le premier dossier qui se trouve à l'intérieur du dossier en paramètres   -----
	public static File getFirstDir(File file) {
		int nbrFiles = file.list().length;
		File[] files = new File[nbrFiles];
		files = file.listFiles();
		for (File value : files) {
			if(value.isDirectory()) {
				return value;
			}
		}
		return null;
	}
	
	public static File getNextDir(File file) {
		boolean asReached = false;
		File parentFile;
		File[] listFile;
		parentFile = file.getParentFile();
		listFile = parentFile.listFiles();
		for (File value : listFile) {
			if (!value.isDirectory()) {  
				//  --- Do nothing ---
			} else if (value.isDirectory() && asReached == false) {
			//   -----   On tombe sur un dossier, on regarde si c'est le dossier en question   -----
				if (value == file) {
					asReached = true;   //   -----   On a bien atteint notre dossier   -----
				}
			} else if (value.isDirectory() && asReached == true && value != file) {
				//   -----   On a dépassé notre dossier et on tombe sur le dossier suivant   -----
				return value;
			} 
		}
		return null;
	}
	
	public File[] getListDir(File file) {
		int i = 0;
		int nbrFiles = file.list().length;
		File[] listFiles = file.listFiles();
		File[] tabFile = new File[nbrFiles];
		for (i = 0; i < nbrFiles; i++) {
			if(listFiles[i].isDirectory()) {
				tabFile[i] = listFiles[i];
			} else {
				tabFile[i] = null;
			}
		}
		return tabFile;
	}
	
	public File[] getListFile( File file) {
		int i = 0;
		int nbrFiles = file.list().length;
		File[] listFiles = file.listFiles();
		File[] tabFile = new File[nbrFiles];
		for (i = 0; i < file.list().length; i++) {
			tabFile[i] = listFiles[i];
		}
		return tabFile;
	}

	public static int nbrDir(File root) {
		int nbrDir = 0;
		File[] tabFiles = root.listFiles();
		for (File file : tabFiles) {
			if (file.isDirectory()) {
				nbrDir = nbrDir + 1;		
			}
		}
		return nbrDir;	
	}
	
	public static int nbrLeaves(File root) {
		int nbrLeaves = 0;
		File[] tabFiles = root.listFiles();
		for (File file : tabFiles) {
			if (!file.isDirectory()) {
				nbrLeaves = nbrLeaves + 1;			
			}
		}
		return nbrLeaves;	
	}
	
	public static int nbrDir(DefaultMutableTreeNode dirNode) {
		int nbrDir = 0;
		int i = 0;
		for (i=0; i < dirNode.getChildCount(); i++) {
			if (!dirNode.getChildAt(i).isLeaf()) {
				nbrDir = nbrDir + 1;			
			}
		}
		return nbrDir;	
	}
	
	public static void createLeftTree(DefaultMutableTreeNode top) {
		    DefaultMutableTreeNode category = null;
		    DefaultMutableTreeNode book = null;
		    DefaultMutableTreeNode other = null;
		    
		    category = new DefaultMutableTreeNode("First folder element");
		    top.add(category);
		    //
		    book = new DefaultMutableTreeNode("First file element in First folder");
		    category.add(book);
		    //
		    book = new DefaultMutableTreeNode("Second file element in First folder");
		    category.add(book);
		    //
		    book = new DefaultMutableTreeNode("Third file element in First folder");
		    category.add(book);
		    //
		    category = new DefaultMutableTreeNode("Second folder element");
		    top.add(category);
		    //
		    book = new DefaultMutableTreeNode("First file element in Second folder");
		    category.add(book);
		    //
		    book = new DefaultMutableTreeNode("Second file element in Second folder");
		    category.add(book);
		    //
		    book = new DefaultMutableTreeNode("Third file element in Second folder");
		    category.add(book);
		    //
		    category = new DefaultMutableTreeNode("Third folder element");
		    top.add(category);
		    //
		    book = new DefaultMutableTreeNode("First file element in Third folder");
		    category.add(book);
		    //
		    book = new DefaultMutableTreeNode("Second file element in Third folder");
		    category.add(book);
		    //
		    book = new DefaultMutableTreeNode("Third file element in Third folder");
		    category.add(book);
		    //
		    other = new DefaultMutableTreeNode("Other element");
		    book.add(other);
		}

	public static JPanel createRightMenu() {			// Menu RIGHT

//		JPanel gridPanelMenuRight = createGridPanel(8,1,10,10,Variables.colorMenuRight);
//		gridPanelMenuRight.setBackground(Variables.colorMenuRight);
//		gridPanelMenuRight.add(createLabel("<html>Ceci est<br/> le Menu de droite</html>", Variables.tailleLabel));
//		
//		JButton bouton001 = createButton("Menu001", Variables.tailleBouton, "CENTER", "CENTER");
//		bouton001.setIcon( new ImageIcon("images/imgTest.png"));
//		bouton001 = Evenement.boutonEvenement(bouton001);
//		gridPanelMenuRight.add(bouton001);
//		
//		gridPanelMenuRight.add(createButton("Menu 002", Variables.tailleBouton, "CENTER", "CENTER"));
//		gridPanelMenuRight.add(createButton("Menu 003", Variables.tailleBouton, "CENTER", "CENTER"));
//		gridPanelMenuRight.add(createButton("Menu 004", Variables.tailleBouton, "CENTER", "CENTER"));
//		gridPanelMenuRight.add(createButton("Menu 005", Variables.tailleBouton, "CENTER", "CENTER"));
//		gridPanelMenuRight.add(createButton("Menu 006", Variables.tailleBouton, "CENTER", "CENTER"));
//		gridPanelMenuRight.add(createButton("Menu 007", Variables.tailleBouton, "CENTER", "CENTER"));
//		
//		return gridPanelMenuRight;
		

		
		return null;
	}

	public static JPanel createBottomMenu() {		// Menu BOTTOM
		JPanel bottomMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//bottomMenu.setSize(0, 100);
		bottomMenu.add(createLabel("<html><b>Console :  </b></html>"));

		return bottomMenu;
	}
}











