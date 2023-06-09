package controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import components.CardComponent;
import components.PlayerComponent;

public class GameController {
	
	public void calculateScore(boolean isPair, PlayerComponent player) {
		if (isPair) {
			player.setScore(player.score() + 1);
		} else {
			player.setScore(player.score() > 0 ? player.score() - 1 : player.score());
		}
	}
	
	public PlayerComponent calculateWinner(PlayerComponent p1, PlayerComponent p2) {
		if (p1.score() > p2.score()) {
			return p1;
		} else if (p1.score() < p2.score()) {
			return p2;
		} else {
			return null;
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
	
	public boolean checkAllFaceUp(ArrayList<CardComponent> cards) {
		for (int i=0; i<cards.size(); i++) {
			if (!cards.get(i).isFaceUp()) {
				return false;
			}
		}
		return true;
	}
}
