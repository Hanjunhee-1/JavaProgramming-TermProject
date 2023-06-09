package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerComponent extends JLabel {
	private Timer timer;
	private int seconds = 0;
	private Runnable timeExpiredCallback;

	public TimerComponent() {
		this.setFont(new Font("Ink Free", Font.BOLD, 40));
		this.setText(Integer.toString(seconds));
		
		timer = new Timer(1000, e -> {
			this.seconds++;
			
			if (seconds > 6) {
				this.setForeground(Color.red);
			}
			if (seconds % 10 == 0) {
				stopTimer();
				timeExpired();
			}
			
			this.setText(Integer.toString(seconds));
		});
	}
	
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public void setTimeExpiredCallback(Runnable callback) {
		this.timeExpiredCallback = callback;
	}
	
	public void timeExpired() {
		if (timeExpiredCallback != null) {
			timeExpiredCallback.run();
		}
	}
	
	public void startTimer() {
		this.setFont(new Font("Ink Free", Font.BOLD, 40));
		this.setForeground(Color.black);
		setSeconds(0);
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void restartTimer() {
		stopTimer();
		startTimer();
	}
	
	public boolean isTimerRunning() {
		return timer.isRunning();
	}
}
