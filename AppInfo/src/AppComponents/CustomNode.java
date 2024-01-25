package AppComponents;


public class CustomNode {
	private String nodeName;
	private String nodeUrl;
	private boolean isDir;
	
	public CustomNode(String nom, String url, boolean boolDir) {
		this.setName(nom);
		this.setUrl(url);
		this.setIsDir(boolDir);
		customNodeToArray(this);
	}
	
	//  Object nodeInfo = currentNode.getUserObject();
	
	public void customNodeToArray(CustomNode node) {
		Variables.tempNameNodeList.add(this.getName());
		Variables.tempPathNodeList.add(this.getUrl());
		
		Variables.tempCustomNodeList.add(this);
	}
	
	public boolean getIsDir() {
		return this.isDir;
	}
	
	public void setIsDir(boolean boolDir) {
		this.isDir = boolDir;
	}
	
	public String getName() {
		return this.nodeName;
	}
	
	public void setName(String name) {
		this.nodeName = name;
	}
	
	public String getUrl() {
		return this.nodeUrl;
	}
	
	public void setUrl(String url) {
		this.nodeUrl = url;
	}
	
	public String toString() {
		return this.nodeName;
	}
	
}
