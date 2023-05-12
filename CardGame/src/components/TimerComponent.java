package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * 시간을 보여주기 위한 클래스입니다. 
 */

public class TimerComponent extends JLabel{
	private Timer timer;
	private int seconds;
	
	public TimerComponent() {
		seconds = 0;
		this.setFont(new Font("Arial", Font.BOLD, 30));
		this.setForeground(Color.BLACK);
		setText(Integer.toString(seconds));
		
		// ActionListener 부분
		timer = new Timer(1000, e -> {
			seconds++;
			if (seconds > 7) {
				this.setForeground(Color.RED);
			}
			if (seconds % 10 == 0) {
				resetTimer();
			}
			setText(Integer.toString(seconds));
		});
	}
	
	public void startTimer() {
		this.setFont(new Font("Arial", Font.BOLD, 30));
		this.setForeground(Color.BLACK);
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void resetTimer() {
		stopTimer();
		seconds = 0;
		startTimer();
	}
}
