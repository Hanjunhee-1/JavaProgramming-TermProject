package components;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
*/

public class CardComponent extends JPanel {
	private boolean isFaceUp;
	private boolean isMatched;
	private ImageIcon frontImage;
	private ImageIcon backImage;
	private JLabel cardLabel;
	
	public CardComponent(ImageIcon frontImage, ImageIcon backImage) {
		this.frontImage = frontImage;
		this.backImage = backImage;
		isFaceUp = false;
		isMatched = false;
		
		// 카드 이미지를 표시할 레이블 생성
		cardLabel = new JLabel(backImage);
		setPreferredSize(new Dimension(backImage.getIconWidth(), backImage.getIconHeight()));
		
		
		// 카드를 클릭할 때 이벤트 처리
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// 클릭을 했다면 카드 뒤집기 동작 수행
				flipCard();
			}
		});
		
		add(cardLabel);
	}
	
	// 카드 뒤집기 동작
	public void flipCard() {
		if (!isMatched) {
			isFaceUp = !isFaceUp;
			
			if (isFaceUp) {
				cardLabel.setIcon(frontImage);
			} else {
				cardLabel.setIcon(backImage);
			}
		}
	}
	
	// 카드의 상태 반환
	public boolean isFaceUp() {
		return isFaceUp;
	}
	
	// 카드가 짝이 맞춰졌는지 여부 반환
	public boolean isMatched() {
		return isMatched;
	}
	
	// 카드 짝 맞추기
	public void setMatched(boolean matched) {
		isMatched = matched;
	}
}













