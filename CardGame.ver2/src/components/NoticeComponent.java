package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class NoticeComponent extends JLabel {
	public NoticeComponent(Color c) {
		this.setOpaque(false);
		
		this.setText("");
		this.setBackground(c);
		this.setFont(new Font("Ink Free", Font.BOLD, 40));
	}
	
	public void setNotice(String notice) {
		this.setText(notice);
	}
}
