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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighScore extends JPanel {
	public BufferedImage bg;

	public HighScore() {

		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		JLabel label = new JLabel("High Score", SwingConstants.CENTER);
		label.setFont(new Font("Cooper Black", Font.BOLD, 50));
		label.setForeground(new Color(156,0,153));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 3));
		JLabel title = new JLabel("rank" + String.format("%18s", "name")
				+ String.format("%22s", "score"), SwingConstants.CENTER);
		title.setFont(Resource.titleFont);
		title.setForeground(new Color(156,0,153));
		panel.add(label);
		panel.add(title);

		panel.setOpaque(false);
		this.add(panel, BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			ClassLoader cloader = RenderableHolder.class.getClassLoader();
			bg = ImageIO.read(cloader.getResource("image/Bg16r.jpg"));
		} catch (IOException ex) {
			System.out.println("can't load");
		}

		g.drawImage(bg, 0, 0, null);
		g.setFont(Resource.titleFont);
		g.setColor(new Color(255,51,153));
		int y = 200;
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
			g.drawString(score, 1020 - xScore, y);
			y += 80;

		}

	}

}
