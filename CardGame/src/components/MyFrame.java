package components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ImageController;

public class MyFrame extends JFrame{
	ArrayList<CardComponent> cards = new ArrayList<>();
	ImageController i = new ImageController();
	
	static final String BACK_IMAGE = "resources\\img\\card_backImage.png";
	static final int width = 60;
	static final int height = 100;
	
	public MyFrame() throws IOException {
		setTitle("Card Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		// 카드들을 5x4 의 레이아웃을 보여주기 위한 cardPanel 생성
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new GridLayout(5,4));
		
		// cardComponent 를 활용하여 카드들 생성
		for (int j=0; j<i.filePaths.size(); j++) {
			if (i.filePaths.get(j).equals(BACK_IMAGE)) {
				continue;
			}
			else {
				Image front = ImageIO.read(new File(i.filePaths.get(j)));
				front = front.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon frontImage = new ImageIcon(front);
				
				Image back = ImageIO.read(new File(BACK_IMAGE));
				back = back.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon backImage = new ImageIcon(back);
				
				CardComponent c1 = new CardComponent(frontImage, backImage);
				CardComponent c2 = new CardComponent(frontImage, backImage);
				cards.add(c1);
				cards.add(c2);
			}
		}
		
		Collections.shuffle(cards);
		
		for (int j=0; j<cards.size(); j++) {
			cardPanel.add(cards.get(j));
		}
		
		
		
		// Timer 를 생성
		JPanel timerPanel = new JPanel();
		TimerComponent timer = new TimerComponent();
		timerPanel.add(timer);
		
		// Timer 시작
		timer.startTimer();
		
		
		// 프레임에 컴포넌트들 추가.
		// timer 가 프레임의 상단에 가운데 정렬하여 보여지고
		// 20장의 카드들이 프레임의 아래에 보여짐.
		add(timerPanel, BorderLayout.NORTH);
		add(cardPanel, BorderLayout.SOUTH);
		
		pack();
		setSize(550, 700);
		setVisible(true);
	}
}
