package render;

import input.InputUtility;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import entity.GameLogic;
import entity.Sheep;

public class GameScreen extends JPanel {

	public GameScreen() {
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(1280, 720));
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					try {
						Thread.sleep(100);
						Sheep.setSheepIndex(0);
						Sheep.setColor(0);
					} catch (InterruptedException r) {
						// e.printStackTrace();
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					Button.update(6);
					InputUtility.keyRelease(e);
					InputUtility.Updatestate();
				}
				if (e.getKeyCode() == KeyEvent.VK_W) {
					Button.update(7);
					InputUtility.keyRelease(e);
					InputUtility.Updatestate();
				}
				if (e.getKeyCode() == KeyEvent.VK_E) {
					Button.update(8);
					InputUtility.keyRelease(e);
					InputUtility.Updatestate();
				}
				if (e.getKeyCode() == KeyEvent.VK_R) {
					Button.update(9);
					InputUtility.keyRelease(e);
					InputUtility.Updatestate();
				}
				if (e.getKeyCode() == KeyEvent.VK_O) {
					Button.update(10);
					InputUtility.keyRelease(e);
					InputUtility.Updatestate();
				}
				if (e.getKeyCode() == KeyEvent.VK_I) {
					Button.update(11);
					InputUtility.keyRelease(e);
					InputUtility.Updatestate();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					Sheep.setSheepIndex(1);
					GameLogic.KillWolf(Sheep.getSheepColor());
					AudioUtility.playSound("Attack");
				}
				else if (e.getKeyCode() == KeyEvent.VK_Q) {
					Button.update(0);
					InputUtility.keyPressed(e);
					InputUtility.Updatestate();
					Sheep.Update();
				}
				else if (e.getKeyCode() == KeyEvent.VK_W) {
					Button.update(1);
					InputUtility.keyPressed(e);
					InputUtility.Updatestate();
					Sheep.Update();
				}
				else if (e.getKeyCode() == KeyEvent.VK_E) {
					Button.update(2);
					InputUtility.keyPressed(e);
					InputUtility.Updatestate();
					Sheep.Update();
				}
				else if (e.getKeyCode() == KeyEvent.VK_R) {
					Button.update(3);
					InputUtility.keyPressed(e);
					InputUtility.Updatestate();
					Sheep.Update();
				}
				else if (e.getKeyCode() == KeyEvent.VK_O) {
					Button.update(4);
					InputUtility.keyPressed(e);
					InputUtility.Updatestate();
					Sheep.Update();
				}
				else if (e.getKeyCode() == KeyEvent.VK_I) {
					Button.update(5);
					InputUtility.keyPressed(e);
					InputUtility.Updatestate();
					Sheep.Update();
				}

			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (IRenderable en : RenderableHolder.getInstance().getRenderable()) {
			if (en.IsVisible()) {
				en.draw(g2d);
			}
		}
	}
}
