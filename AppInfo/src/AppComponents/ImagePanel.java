package AppComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage bi;
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(bi, 0, 0, getWidth(), getHeight(), null);
	}
	public BufferedImage getBi() {
		return bi;
	}
	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}
	
	
}
