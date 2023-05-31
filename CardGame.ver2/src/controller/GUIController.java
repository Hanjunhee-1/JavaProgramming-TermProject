package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.border.LineBorder;

import components.CardComponent;
import components.NoticeComponent;
import components.PlayerComponent;
import components.TimerComponent;

public class GUIController {
	private JFrame frame = new JFrame();
	private JPanel cardPanel = new JPanel();
	private JPanel timerPanel = new JPanel();
	private JPanel noticePanel = new JPanel();
	private JPanel timerAndNoticePanel = new JPanel();
	private JPanel p1Panel = new JPanel();
	private JPanel p1MatchedCards = new JPanel();
	private JPanel p2Panel = new JPanel();
	private JPanel p2MatchedCards = new JPanel();
	private ImageController imageController = new ImageController();
	private GameController gameController = new GameController();
	private ArrayList<CardComponent> cards = new ArrayList<>();
	private static final String BACK_IMAGE = "resources\\img\\card_backImage.png";
	private static final int width = 60;
	private static final int height = 90;
	
	private boolean p1Turn = true;
	private PlayerComponent p1, p2;
	private CardComponent clickedCard1 = null;
	private CardComponent clickedCard2 = null;
	private TimerComponent timerComponent;
	private NoticeComponent noticeComponent;
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
		
        timer = new Timer(200, e -> checkPair()); // Timer 생성 및 ActionListener 설정
        timer.setRepeats(false); // 한 번만 실행되도록 설정
		
		// 카드 뒷면 이미지 생성 및 크기 조정
		Image back = ImageIO.read(new File(BACK_IMAGE));
		back = back.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon backImage = new ImageIcon(back);
		
		// frame 의 아이콘 설정
		frame.setIconImage(backImage.getImage());
		
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
		timerComponent = new TimerComponent();
		timerComponent.setTimeExpiredCallback(() -> {
		    if (clickedCard1 != null || clickedCard2 != null) {
		        // 클릭해서 뒤집은 카드를 다시 뒷면으로 돌리기
		        if (clickedCard1 != null) {
		            clickedCard1.setFaceUp(false);
		            clickedCard1.setBackImage();
		        }
		        if (clickedCard2 != null) {
		            clickedCard2.setFaceUp(false);
		            clickedCard2.setBackImage();
		        }
		    }
	        // 턴을 상대에게 넘기기
	        nextTurn();
		});
		timerPanel.add(timerComponent);
		
		noticePanel.setOpaque(false);
		noticeComponent = new NoticeComponent(new Color(0xc0ca33));
		noticeComponent.setNotice("Who\'s turn?");
		noticePanel.add(noticeComponent);
		
		p1 = new PlayerComponent(new Color(0xef5350), "P1");
		p1MatchedCards.setBackground(new Color(0xffb74d));
		p1MatchedCards.setBorder(new LineBorder(new Color(0xe65100), 2));
		p1MatchedCards.setLayout(new GridLayout(0, 2));
		p1Panel.setOpaque(false);
		p1Panel.setLayout(new BorderLayout());
		p1Panel.add(p1, BorderLayout.NORTH);
		p1Panel.add(p1MatchedCards, BorderLayout.CENTER);
		
		p2 = new PlayerComponent(new Color(0x1565c0), "P2");
		p2MatchedCards.setBackground(new Color(0x2196f3));
		p2MatchedCards.setBorder(new LineBorder(new Color(0x4a148c), 2));
		p2MatchedCards.setLayout(new GridLayout(0, 2));
		p2Panel.setOpaque(false);
		p2Panel.setLayout(new BorderLayout());
		p2Panel.add(p2, BorderLayout.NORTH);
		p2Panel.add(p2MatchedCards, BorderLayout.CENTER);
		
		if (p1Turn) {
			noticeComponent.setNotice(p1.playerName() + "\'s turn!");
		}
		
		timerAndNoticePanel.setOpaque(false);
		timerAndNoticePanel.setLayout(new BorderLayout());
		timerAndNoticePanel.add(timerPanel, BorderLayout.NORTH);
		timerAndNoticePanel.add(noticePanel, BorderLayout.CENTER);
		frame.add(timerAndNoticePanel, BorderLayout.NORTH);
		frame.add(p1Panel, BorderLayout.WEST);
		frame.add(p2Panel, BorderLayout.EAST);
		frame.add(cardPanel, BorderLayout.CENTER);
		timerComponent.startTimer();
		
		frame.pack();
		frame.setSize(900, 700);
		frame.setVisible(true);
	}
	
	public void checkPair() {		
		gameController.checkPair(clickedCard1, clickedCard2, p1Turn, p1, p2);
		
		if (clickedCard1.getCardName().equals(clickedCard2.getCardName())) {
			if (p1Turn) {
				setMatchedCards(p1, clickedCard1);
			} else {
				setMatchedCards(p2, clickedCard1);
			}
		}
		
		if (!checkAllFaceUp()) {
			nextTurn();
		} else {
			timerComponent.stopTimer();
			
			PlayerComponent winner = gameController.calculateWinner(p1, p2);
			
			checkWinner(winner);
		}
	}
	
	public void checkWinner(PlayerComponent winner) {
		if (winner == null) {
			noticeComponent.setNotice("DRAW");
		}
		
		if (winner.playerName().equals(p1.playerName())) {
			noticeComponent.setNotice(p1.playerName() + " is Winner!");
		} else {
			noticeComponent.setNotice(p2.playerName() + " is Winner!");
		}
	}
	
	public boolean checkAllFaceUp() {
		return gameController.checkAllFaceUp(cards);
	}
	
	public void nextTurn() {
		timerComponent.stopTimer();
		timerComponent.startTimer();
		
		clickedCard1 = null;
		clickedCard2 = null;
		
		p1Turn = p1Turn ? false : true;
		noticeComponent.setNotice(p1Turn ? p1.playerName() + "\'s turn!" : p2.playerName() + "\'s turn!");
	}
	
	public void setMatchedCards(PlayerComponent player, CardComponent matchedCard) {
		CardComponent card = new CardComponent(matchedCard.getFrontImage(), matchedCard.getBackImage(), matchedCard.getCardName());
		card.setFrontImage();
		
		if (player.playerName().equals("P1")) {
			p1MatchedCards.add(card);
		} else {
			p2MatchedCards.add(card);
		}
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
