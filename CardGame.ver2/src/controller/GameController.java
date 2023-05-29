package controller;

import components.CardComponent;
import components.PlayerComponent;

// GUIController 의 checkPair() 함수 GameController 로 가져오기

public class GameController {
	
	public void calculateScore(boolean isPair, PlayerComponent player) {
		if (isPair) {
			player.setScore(player.score() + 1);
		} else {
			player.setScore(player.score() > 0 ? player.score() - 1 : player.score());
		}
	}
	
	public void checkPair(CardComponent clickedCard1, CardComponent clickedCard2, boolean p1Turn, PlayerComponent p1, PlayerComponent p2) {
		if (clickedCard1.getCardName().equals(clickedCard2.getCardName())) {
			clickedCard1.setMatched(true);
			clickedCard2.setMatched(true);
			
			if (p1Turn) {
				this.calculateScore(true, p1);
				p1.setCurrentStatus();
				p1.changeText();
			} else {
				this.calculateScore(true, p2);
				p2.setCurrentStatus();
				p2.changeText();
			} 
		} else {
			clickedCard1.setFaceUp(false);
			clickedCard1.setBackImage();
			clickedCard2.setFaceUp(false);
			clickedCard2.setBackImage();
			
			if (p1Turn) {
				this.calculateScore(false, p1);
				p1.setCurrentStatus();
				p1.changeText();
			} else {
				this.calculateScore(false, p2);
				p2.setCurrentStatus();
				p2.changeText();
			} 
		}
	}
}
