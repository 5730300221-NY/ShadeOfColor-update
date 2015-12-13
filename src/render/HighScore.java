package render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighScore extends JPanel {
	public BufferedImage bg;

	public HighScore() {

		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(160,0,0,0));
		this.setBackground(Color.WHITE);

		JLabel title = new JLabel(String.format("%19s","rank") + String.format("%15s", "name")
				+ String.format("%19s", "score"), SwingConstants.CENTER);
		title.setFont(new Font("Cooper Black", Font.BOLD, 50));
		title.setForeground(Color.yellow);

		this.add(title);
;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			ClassLoader cloader = RenderableHolder.class.getClassLoader();
			bg = ImageIO.read(cloader.getResource("image/bghigh.jpg"));
		} catch (IOException ex) {
			System.out.println("can't load");
		}

		g.drawImage(bg, 0, 0, null);
		g.setFont(Resource.titleFont);
		g.setColor(new Color(255,200,255));
		int y = 260;
		int xName;
		int xScore;
		String name = "";
		String score = "";

		for (int i = 0; i < 5; i++) {
			g.drawString("" + (i + 1), 320, y);
			name = HighScoreUtility.getHighScoreRecord()[i].getName();
			xName = (int) g.getFontMetrics(Resource.titleFont)
					.getStringBounds(name, g).getWidth();
			g.drawString(name, 670 - xName, y);
			score = "" + HighScoreUtility.getHighScoreRecord()[i].getScore();
			xScore = (int) g.getFontMetrics(Resource.titleFont)
					.getStringBounds(score, g).getWidth();
			g.drawString(score, 1070 - xScore, y);
			y += 65;

		}

	}

}
