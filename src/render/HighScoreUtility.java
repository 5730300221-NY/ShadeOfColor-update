package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HighScoreUtility {

	public static class HighScoreRecord implements Comparable<HighScoreRecord> {
		private String name = "";
		private int score = 0;

		public HighScoreRecord(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public HighScoreRecord(String record) throws ScoreParsingException {
			int index = record.indexOf(":");
			if (index < 0)
				throw new ScoreParsingException(1);
			for (int i = index + 1; i < record.length(); i++) {
				if ("0123456789".indexOf(record.substring(i, i + 1)) < 0)
					throw new ScoreParsingException(0);
			}
			this.name = record.substring(0, index);
			this.score = Integer.parseInt(record.substring(index + 1));

		}

		public String getRecord() {
			return name.trim() + ":" + score;
		}

		public String getName() {
			return name;
		}

		public String getScore() {
			String score = "" + this.score;
			int a = score.length() % 3;
			if (a == 0)
				a = 3;
			String score2 = score.substring(0, a) + ",";
			for (int i = a; i < score.length(); i += 3) {
				if (i + 3 == score.length())
					score2 += score.substring(i, i + 3);
				else
					score2 += score.substring(i, i + 3) + ",";
			}
			return score2;
		}

		private static String[] defaultRecord() {
			return new String[] { "A:8000000", "B:7500000", "C:7000000",
					"D:6500000", "E:6000000" };
		}

		@Override
		public int compareTo(HighScoreRecord o) {
			if (this.score > o.score)
				return -1;
			else if (this.score < o.score)
				return 1;
			return 0;
		}
	}

	private static HighScoreRecord[] highScoreRecord = null;

	private static String readFileName = "highscore";

	/*
	 * Display player's score and record if the player rank is 10 or higher.
	 */
	public static void recordHighScore(int score) {
		if (!loadHighScore() || highScoreRecord == null) {
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int index = highScoreRecord.length;
		for (int i = 0; i < highScoreRecord.length; i++) {
			if (score > highScoreRecord[i].score) {
				index = i;
				break;
			}
		}
		if (index >= highScoreRecord.length) {
			String message = "Game over\n" + "Your score is " + score;
			JOptionPane.showMessageDialog(null, message, "Game over",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			for (int i = highScoreRecord.length - 1; i >= index + 1; i--) {
				highScoreRecord[i] = highScoreRecord[i - 1];
			}
			String message = "Congratulation, you are ranked " + (index + 1)
					+ "\nPlease enter your name";
			String in = JOptionPane.showInputDialog(null, message,
					"High score", JOptionPane.INFORMATION_MESSAGE);
			highScoreRecord[index] = new HighScoreRecord(in, score);
			try {

				BufferedWriter out = new BufferedWriter(new FileWriter(
						"highscore"));

				String write = "";
				for (int i = 0; i < highScoreRecord.length; i++) {
					write += highScoreRecord[i].getRecord() + "\n";
				}
				out.write(getXORed(write));
				out.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
						"Error saving high score record", "Error",
						JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}

	public static void displayTop5() {
		if (!loadHighScore() || highScoreRecord == null) {
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private static boolean loadHighScore() {
		File f = new File(readFileName);
		// If no high score, create default
		if (!f.exists()) {
			if (!createDefaultScoreFile())
				return false;
		}
		// Read high score -- if fail: delete the file, create default one and
		// read it again
		if (!readAndParseScoreFile(f)) {
			f.delete();
			if (!createDefaultScoreFile())
				return false;
			return readAndParseScoreFile(f);
		}
		return true;

	}

	private static boolean readAndParseScoreFile(File f) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			highScoreRecord = new HighScoreRecord[5];
			String str = "";
			int c;
			while ((c = in.read()) != -1) {
				str += (char) c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for (int i = 0; i < highScoreRecord.length; i++) {
				try {
					highScoreRecord[i] = new HighScoreRecord(records[i]);
				} catch (ScoreParsingException e) {
					System.err.println("Error parsing line " + (i + 1) + ", "
							+ e.getMessage());
					highScoreRecord[i] = new HighScoreRecord("ERROR_RECORD", 0);
				}
			}
			Arrays.sort(highScoreRecord);
			return true;
		} catch (Exception e) {
			highScoreRecord = null;
		}
		return false;
	}

	private static boolean createDefaultScoreFile() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			String str = "";
			for (String s : HighScoreRecord.defaultRecord()) {
				str += s + "\n";
			}
			str = str.trim();
			out.write(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreRecord = null;
			return false;
		}
	}

	private static final byte[] key = "RmAAq2b5d8fjgu9dhher".getBytes();

	// This method does both encryption and decryption
	private static String getXORed(String in) {
		byte[] inData = in.getBytes();
		// Fill code
		int count = 0;
		for (int i = 0; i < inData.length; i++) {
			inData[i] = (byte) (inData[i] ^ key[count]);
			count++;
			if (count == key.length)
				count = 0;

		}
		return new String(inData);
	}

	public static void setReadFileName(String name) {
		readFileName = name;
	}

	public static HighScoreRecord[] getHighScoreRecord() {
		return highScoreRecord;
	}

}