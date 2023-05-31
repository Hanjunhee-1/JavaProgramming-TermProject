package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardComponent extends JPanel{
	private ImageIcon frontImage;
	private ImageIcon backImage;
	private JLabel cardLabel;
	private boolean isFaceUp;
	private boolean isMatched;
	private String cardName;
	
	public CardComponent(ImageIcon frontImage, ImageIcon backImage, String fileName) {
		this.frontImage = frontImage;
		this.backImage = backImage;
		isFaceUp = false;
		isMatched = false;
		cardName = fileName;
		
		cardLabel = new JLabel();
		cardLabel.setOpaque(false);
		this.setOpaque(false);
		setBackImage();
		setPreferredSize(new Dimension(backImage.getIconWidth(), backImage.getIconHeight()));
		
		this.add(cardLabel);
	}
	
	// isFaceUp 필드 getter 
	public boolean isFaceUp() {
		return isFaceUp;
	}

	// isFaceUp 필드 setter
	public void setFaceUp(boolean isFaceUp) {
		this.isFaceUp = isFaceUp;
	}
	
	// isMatched 필드 getter 
	public boolean isMatched() {
		return isMatched;
	}

	// isMatched 필드 setter
	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}
	
	// cardName 필드 getter
	public String getCardName() {
		return cardName;
	}
	
	// cardName 필드 setter
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	// 카드의 이미지를 앞면으로 설정
	public void setFrontImage() {
		cardLabel.setIcon(this.frontImage);
	}
	
	public ImageIcon getFrontImage() {
		return this.frontImage;
	}
	
	// 카드의 이미지를 뒷면으로 설정
	public void setBackImage() {
		cardLabel.setIcon(this.backImage);
	}
	
	public ImageIcon getBackImage() {
		return this.backImage;
	}
}
