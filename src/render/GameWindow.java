package render;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.GameTitle;

public class GameWindow extends JFrame {
	private JPanel currentFrame;

	public GameWindow(JPanel frame) {
		super("Shade Of Colorful");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.currentFrame = frame;
		getContentPane().add(currentFrame);
		pack();
		this.setVisible(true);
		this.currentFrame.requestFocus();
	}

	public  void switchScene(JPanel frame) {
		getContentPane().removeAll();
		this.currentFrame = frame;
		AudioUtility.stopSound("Intro");
		getContentPane().add(frame);
		getContentPane().validate();
		pack();
		currentFrame.requestFocus();
	}

	public JPanel getCurrentScene() {
		return currentFrame;
	}

	public void setCurrentFrame(JPanel currentFrame) {
		this.currentFrame = currentFrame;
	}

}
