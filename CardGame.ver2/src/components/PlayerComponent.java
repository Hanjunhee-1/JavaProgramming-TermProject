package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * 
 * 각 플레이어의 점수를 기록하고 그에 관련한 method 를 모아놓은 클래스입니다.
 *
 */
public class PlayerComponent extends JLabel {
	private String playerName;
	private String currentStatus;
	private int score;
	private Color color;
	
	public PlayerComponent(Color color, String playerName) {
		score = 0;
		
		this.setOpaque(true);
		setPlayerName(playerName);
		setCurrentStatus();
		this.setBackground(color);
		this.setText(currentStatus);
		this.setFont(new Font("Ink Free", Font.BOLD, 20));
	}
	
	public String playerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setCurrentStatus() {
		this.currentStatus = new String(playerName() + "\'s score: " + Integer.toString(score()));
	}
	
	public void changeText() {
		this.setText(currentStatus);
	}
	
	public int score() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
