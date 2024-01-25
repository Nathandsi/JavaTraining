package AppComponents;

import java.io.File;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

public class CustomTree {
	
	DefaultMutableTreeNode node;
	ArrayList<DefaultMutableTreeNode> listNodes = new ArrayList<DefaultMutableTreeNode>();
	File[] files;
	
	File file;

	public CustomTree(File root) {
		this.file = root;
	}
	
	public File[] listFile(File rootFile) {
		if (rootFile.listFiles() != null) {
			files = rootFile.listFiles();
			return files;
		} else {
			return null;
		}

	}
	
	public void listFileToNode(File[] files){
		for (File file : files) {
			node = new DefaultMutableTreeNode(file.getName());
			listNodes.add(node);
		}
	}
	
	
	public DefaultMutableTreeNode createSingleNode(String nodeName) {
		char value = nodeName.charAt(0);
		if (Character.isUpperCase(value)) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName, true);
			Variables.isNodeLeaf = false;
			return node;
		} else {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName, false);
			Variables.isNodeLeaf = true;
			return node;
		}
		

	}
	
	public ArrayList<DefaultMutableTreeNode> createMultipleNodes(String[] tabString) {
		ArrayList<DefaultMutableTreeNode> collection = new ArrayList<DefaultMutableTreeNode>();
		if (tabString.length != 0) {
			for (String value : tabString) {
				DefaultMutableTreeNode treeNode = createSingleNode(value);
				collection.add(treeNode);
			}
		}
		return collection;
	}
	
	
	
}
