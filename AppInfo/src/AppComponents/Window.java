package AppComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Window extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = -6304786995095278440L;
	
	public Vector<ActionListener> actionListeners = new Vector<>();
	
	private ImagePanel imagePanel = new ImagePanel();
	private JComboBox<String> boxImages;
	private JDesktopPane desktopPane = new JDesktopPane();
	
	public JComboBox<String> selectionList = new JComboBox<String>();
	
	public ArrayList<DefaultMutableTreeNode> tabNodes = new ArrayList<>();
	public String [] tabString = {"Pages", "index.html", "Styles", "style.css", "Scripts", "script.js",  "Images", "image01", "image02", "ImagesAutres" ,"image03", "image04"};

	public JSplitPane firstSplitPane;
	public JSplitPane secondSplitPane;
	public JSplitPane thirdSplitPane;
	public JSplitPane fourthSplitPane;
	
	public JScrollPane menuLeft;
	public JScrollPane menuBottom;
	public JScrollPane menuRight;
	public JScrollPane menuProp;
	
    public JScrollPane panelContainer = new JScrollPane();
	
	public JTabbedPane tab = new JTabbedPane();
	public JTabbedPane tab2 = new JTabbedPane();
	
	public JEditorPane helpPane = new JEditorPane();
	public JEditorPane toolBox = new JEditorPane();
	
    public JTextArea textArea = new JTextArea("Zone HTML");
    
    public JInternalFrame containerCenter = new JInternalFrame(null, false, false, false, false);
    
    public JTree tree;

	
	public Window() {
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize);
		setResizable(true);
		setUndecorated(false);
		setLayout(new BorderLayout());
		*/
	}
	
	//   -----   Top level Window   -----
	public Window(boolean isFullWindow, int w, int h, Color backColor, boolean isDecorated, String closeOperation, String Layout, int rows, int cols){

		//   -----   Apply Look and Feel   -----
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {
			System.out.println("Unsupported Look and Feel");
			e.printStackTrace();
		}
		
		//   -----   Sets the defaultCloseOperation   -----	
		switch (closeOperation) {
			case "DISPOSE_ON_CLOSE", "dispose_on_close", "dispose" -> setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			case "DO_NOTHING_ON_CLOSE", "do_nothing_on_close", "nothing" -> setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			case "EXIT_ON_CLOSE", "exit_on_close", "exit" -> setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			case "HIDE_ON_CLOSE", "hide_on_close", "hide" -> setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			default -> setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		try {
			File f = new File("images");
			String[] images;
			images = f.list();
			
		
			
			//   -----   Defines the contentPane   -----
			JPanel contentPane = (JPanel) this.getContentPane();
			//   -----   Either screen size or defined size.   -----
			if (isFullWindow) { setSize(Variables.screenSize); } else { setSize(new Dimension(w,h)); }
			//   -----   Sets the background color   -----
			contentPane.setBackground(backColor);
			setResizable(true);
			setLocationRelativeTo(null);
			//   -----   Sets if the window has a title bar with close button and such   -----
			setUndecorated(  isDecorated );
			//   -----   Sets the window's layout (with rows and cols if GridLayout)   -----
			switch(Layout) {
				case "BorderLayout" -> setLayout(new BorderLayout()) ;
				case "FlowLayout" -> setLayout(new FlowLayout());
				case "GridLayout" -> setLayout(new GridLayout(rows, cols));
			}
			//   -----   Menu Bar   -----
			setJMenuBar(createMenuBar());
			
			// -------- Center screen component -------		
			
			 tree = Menu.createLeftTreeMenu();

			  Menu menu = new Menu();
			  menu.createRootNode();
			
			menuLeft = new JScrollPane(tree);
			menuRight = new JScrollPane(Menu.createRightMenu());
			boxImages = new JComboBox<>(images);
			boxImages.addActionListener(this::refreshContainer);
			
			contentPane.add(boxImages, BorderLayout.NORTH);
			
			String nomImage = (String)boxImages.getSelectedItem();
			BufferedImage bi = ImageIO.read(new File("images/" + nomImage));
			imagePanel.setBi(bi);
			menuBottom = new JScrollPane(Menu.createBottomMenu());
			containerCenter = new JInternalFrame(null, false, false, false, false);          // ---   JInternalFrame   ---
			containerCenter.setSize(400, 300);

			BasicInternalFrameUI ui = (BasicInternalFrameUI)containerCenter.getUI();
			ui.setNorthPane(null);
			
			containerCenter.setLocation(0, 0);
			containerCenter.setResizable(false);
			containerCenter.setVisible(true);
			containerCenter.add((JPanel)imagePanel);
			desktopPane.add(containerCenter);

	        panelContainer = new JScrollPane(desktopPane);
			
			tab.setPreferredSize( new Dimension( 260, 250 ) );
			tab2.setPreferredSize(new Dimension(250, 250));
			tab.setTabPlacement( JTabbedPane.TOP );
			tab2.setTabPlacement(JTabbedPane.BOTTOM);
			
	        helpPane.setEditable( false );
	        helpPane.setPage( "file:///C:/Users/ndurand/eclipse-workspace/AppInfo/src/AppComponents/Docs/index.html" );
	        
	        Dimension textDimension = new Dimension( panelContainer.getWidth(), panelContainer.getHeight());
	        textArea.setSize(textDimension);
	        textArea.setVisible(true);
	        
	        tab.addTab( "Help", new JScrollPane( helpPane ) );
	        tab.addTab("Container", panelContainer);
	        tab.addTab("Text", textArea);
			tab.setVisible(true);
			
			menuProp = new JScrollPane(Menu.createPropMenu("Properties", 20, 2, 3, 3, Variables.colorMenuProp));
			
			tab2.addTab("Properties", menuProp);
			tab2.addTab("ToolBox", toolBox);
			
			//   -----   Menu de droite   -----
			menuRight.setMinimumSize(Variables.minimumTailleMenuRight);
			menuRight.setMaximumSize(Variables.maximumTailleMenuRight);
			menuRight.setPreferredSize(Variables.useTailleMenuRight);
			
			//   -----   Menu du bas   -----
			menuBottom.setMinimumSize(Variables.minimumTailleMenuBottom);
			menuBottom.setMaximumSize(Variables.maximumTailleMenuBottom);
			menuBottom.setPreferredSize(Variables.useTailleMenuBottom);
			
			//   -----   Container central   -----
			panelContainer.setMinimumSize(Variables.minimumTailleContainerCenter);
			panelContainer.setMaximumSize(Variables.maximumTailleContainerCenter);
			panelContainer.setPreferredSize(Variables.useTailleContainerCenter);
			
			//   -----   Menu de gauche   -----
			menuProp.setMinimumSize(Variables.minimumTailleMenuProp);
			menuProp.setMaximumSize(Variables.maximumTailleMenuProp);
			menuProp.setPreferredSize(Variables.useTailleMenuProp);
			
			firstSplitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, tab, menuRight);
			firstSplitPane.setResizeWeight(0.95);
			firstSplitPane.setOneTouchExpandable(false);
			
			secondSplitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT, firstSplitPane, menuBottom);
			secondSplitPane.setResizeWeight(0.95);
			secondSplitPane.setOneTouchExpandable(false);
			
			fourthSplitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT, menuLeft, tab2);
			fourthSplitPane.setOneTouchExpandable(false);

			thirdSplitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, fourthSplitPane, secondSplitPane);
			thirdSplitPane.setResizeWeight(0.05);
			thirdSplitPane.setOneTouchExpandable(false);

			contentPane.add(thirdSplitPane, BorderLayout.CENTER);
			
			firstSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, 
					new PropertyChangeListener() {
					@Override
			        public void propertyChange(PropertyChangeEvent evt) {
						Dimension panelCenterDimension = new Dimension( panelContainer.getWidth(), panelContainer.getHeight());
						dimensionChangeListener(containerCenter, panelCenterDimension);
					}
			});
			secondSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, 
					new PropertyChangeListener() {
					@Override
			        public void propertyChange(PropertyChangeEvent evt) {
						Dimension panelCenterDimension = new Dimension( panelContainer.getWidth(), panelContainer.getHeight());
						dimensionChangeListener(containerCenter, panelCenterDimension);
					}
			});
			thirdSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, 
					new PropertyChangeListener() {
					@Override
			        public void propertyChange(PropertyChangeEvent evt) {
						Dimension panelCenterDimension = new Dimension( panelContainer.getWidth(), panelContainer.getHeight());
						dimensionChangeListener(containerCenter, panelCenterDimension);		
					}
			});
			fourthSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, 
					new PropertyChangeListener() {
					@Override
			        public void propertyChange(PropertyChangeEvent evt) {
						Dimension panelCenterDimension = new Dimension( panelContainer.getWidth(), panelContainer.getHeight());
						dimensionChangeListener(containerCenter, panelCenterDimension);				
					}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//   -----   Change panelCenterDimension listener   -----
	public void dimensionChangeListener(JInternalFrame container, Dimension dimension) {
		container.setSize(dimension);
		container.setLocation(0,0);
	}

	//   -----   Sets a "Click" listener on a label   -----
	public void setLabelClickListener(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				labelClickEvent(e, label);
				System.out.println(label.getMouseListeners());
				System.out.println(label.getAncestorListeners());
//				System.out.println(label.getListeners());
			};
		});
	}
	
	//   -----   Menu bar creation   -----
	public JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnuFile = new JMenu("Fichier");
		menuBar.add(mnuFile);
		JMenuItem mnuNew = new JMenuItem("Nouveau fichier");
		mnuNew.addActionListener(this::mnuNewListener);
		mnuFile.add(mnuNew);
		mnuFile.addSeparator();
		JMenuItem mnuOpen = new JMenuItem("Ouvrir...");
		mnuFile.add(mnuOpen);
		mnuOpen.addActionListener(this::mnuOpenListener);
		mnuFile.addSeparator();
		JMenuItem mnuExit = new JMenuItem("Quitter");
		mnuFile.add(mnuExit);
		mnuExit.addActionListener(this::mnuExitListener);
		
		JMenu mnuEdit = new JMenu("Edition");
		menuBar.add(mnuEdit);
		JMenuItem mnuModify = new JMenuItem("Modifier");
		mnuEdit.add(mnuModify);
		mnuModify.addActionListener(this::mnuModifyListener);
		
		JMenuItem mnuMove = new JMenuItem("Déplacer");
		mnuEdit.add(mnuMove);
		
		JMenu mnuAffichage = new JMenu("Affichage");
		menuBar.add(mnuAffichage);
		
		JMenu mnuHelp = new JMenu("Aide");
		menuBar.add(mnuHelp);
		
		return menuBar;
	}

	public ArrayList<String> getLeavesNameFromBranch(ArrayList<String> list){
		ArrayList<String> result = new ArrayList<>();
		if (list != null) {
			for (String value : list) {
				result.add(value);
			}
		}
		return result;
	}
	
	public int nbrLeavesFromBranch(DefaultMutableTreeNode node) {
		return node.getChildCount();
	}
	
	//   -----   Lists the different listeners of the menuBar's components   -----

	public void mnuNewListener( ActionEvent e) {
		
		ArrayList<String> selection = new ArrayList<String>();
		for (String value : Variables.listFilesInTree) {
			selection.add(value);
		}
		if (selection != null) {
			for (String value : selection) {
				selectionList.addItem(value);
			}
			
			Variables.response = JOptionPane.showInputDialog(null, selectionList, "Select the folder and enter a valid file name", JOptionPane.QUESTION_MESSAGE);
	        String nomItem = selectionList.getSelectedItem().toString();
	        System.out.print(nomItem);
	        Variables.emplacement = nomItem;
	        
	        System.out.println("emplacement : " + Variables.emplacement + " nom du fichier : " + Variables.response);
			String chemin = ("C:\\Users\\ndurand\\eclipse-workspace\\AppInfo\\UserFiles\\" + Variables.emplacement + "\\" + Variables.response);
			
			File f = new File(chemin);
			try {
				f.createNewFile();
				System.out.println("The file : " + Variables.response + " has been created under the folder : " + Variables.emplacement);
			} catch (IOException e1) {
				System.out.println("Une erreur est survenu lors de la création du fichier.");
				e1.printStackTrace();
				System.exit(0);
			};
	
			tree = Menu.createLeftTreeMenu();
			menuLeft = new JScrollPane(tree);
			fourthSplitPane.setLeftComponent(menuLeft);

		}
	}
	
	public void getUserItem(ItemEvent e) {

//            if(e.getStateChange() == ItemEvent.SELECTED) {
//              String location = selectionList.getSelectedItem().toString();
//                System.out.print(location); 
//                Variables.emplacement = location;
//            }
        }
	
	public void mnuOpenListener( ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Open an existing document");
	}
	public void mnuExitListener( ActionEvent e) {
		int reponse = JOptionPane.showConfirmDialog(this, "Quitter ?","Quitter l'application",2);
		if (reponse == 0) {System.exit(0);}
	}
	public void mnuModifyListener(ActionEvent e) {
		
	}
	
	public void refreshContainer(ActionEvent e) {
		String nomImage = (String)boxImages.getSelectedItem();
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("images/" + nomImage));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		imagePanel.setBi(bufferedImage);
		imagePanel.repaint();
	}
	
	//   -----   
	public void labelClickEvent(MouseEvent e, JLabel label) {
		JOptionPane.showMessageDialog(this, "La Zone Centrale à bien été cliquée");
		System.out.println(e.getSource());
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
}

