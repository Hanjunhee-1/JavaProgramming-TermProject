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
	private int score;
	private Color color;
	
	public PlayerComponent(Color color) {
		score = 0;
		
		this.setPreferredSize(new Dimension(200, 800));
		setColor(color);
		this.setBackground(color);
		this.setFont(new Font("Ink Free", Font.BOLD, 20));
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int score() {
		return this.score();
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
