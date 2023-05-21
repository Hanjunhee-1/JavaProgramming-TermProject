package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import components.CardComponent;
import components.TimerComponent;

public class GUIController {
	private JFrame frame = new JFrame();
	private JPanel cardPanel = new JPanel();
	private JPanel timerPanel = new JPanel();
	private ImageController imageController = new ImageController();
	private ArrayList<CardComponent> cards = new ArrayList<>();
	private static final String BACK_IMAGE = "resources\\img\\card_backImage.png";
	private static final int width = 60;
	private static final int height = 90;
	
	private CardComponent clickedCard1 = null;
	private CardComponent clickedCard2 = null;
	private Timer timer;
	static final Color c = new Color(0x80CBC4);
	
	public GUIController() throws IOException {
		frame.setTitle("Card Game");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(c);
		
		// 카드들을 세로 5칸, 가로 4칸이 되도록 설정
		cardPanel.setLayout(new GridLayout(5, 4));
		cardPanel.setOpaque(false);
		
        timer = new Timer(400, e -> checkPair()); // Timer 생성 및 ActionListener 설정
        timer.setRepeats(false); // 한 번만 실행되도록 설정
		
		// 카드 뒷면 이미지 생성 및 크기 조정
		Image back = ImageIO.read(new File(BACK_IMAGE));
		back = back.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon backImage = new ImageIcon(back);
		
		for (int j=0; j<imageController.filePaths.size(); j++) {
			if (imageController.filePaths.get(j).equals(BACK_IMAGE)) {
				continue;
			}
				
			// 카드 앞면 이미지 생성 및 크기 조정
			Image front = ImageIO.read(new File(imageController.filePaths.get(j)));
			front = front.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon frontImage = new ImageIcon(front);
				
			// cards 에 저장
		    cards.add(new CardComponent(frontImage, backImage, imageController.filePaths.get(j)));
		    cards.add(new CardComponent(frontImage, backImage, imageController.filePaths.get(j)));
		}
		
		// 섞어주기
		Collections.shuffle(cards);
		
		for (int j=0; j<cards.size(); j++) {
			CardComponent card = cards.get(j);
			card.addMouseListener(new CardClickListener());
			cardPanel.add(card);
		}
		
		
		// TimerComponent 더해주기
		timerPanel.setOpaque(false);
//		timerPanel.setLayout(new BorderLayout());
		TimerComponent timerComponent = new TimerComponent();
		timerPanel.add(timerComponent);		
		
		frame.add(timerPanel, BorderLayout.NORTH);
		frame.add(cardPanel, BorderLayout.CENTER);
		timerComponent.startTimer();
		
		frame.pack();
		frame.setSize(600, 800);
		frame.setVisible(true);
	}
	
	public void checkPair() {
		if (clickedCard1.getCardName().equals(clickedCard2.getCardName())) {
			clickedCard1.setMatched(true);
			clickedCard2.setMatched(true);
		} else {
			clickedCard1.setFaceUp(false);
			clickedCard1.setBackImage();
			clickedCard2.setFaceUp(false);
			clickedCard2.setBackImage();
		}
		
		clickedCard1 = null;
		clickedCard2 = null;
	}
	
	class CardClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        
        	CardComponent c = (CardComponent) e.getSource();
        	
        	if (!c.isFaceUp() && !c.isMatched()) {
            	if (clickedCard1 == null) {
            		clickedCard1 = c;
            		clickedCard1.setFaceUp(true);
            		clickedCard1.setFrontImage();
            	} else {
            		clickedCard2 = c;
            		clickedCard2.setFaceUp(true);
            		clickedCard2.setFrontImage();
            		
            		timer.restart();
            	}
        	}
        	
        }

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
