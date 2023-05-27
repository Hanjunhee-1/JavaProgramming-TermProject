package controller;

import components.PlayerComponent;

public class GameController {
	
	public void calculateScore(boolean isPair, PlayerComponent player) {
		if (isPair) {
			player.setScore(player.score() + 1);
		} else {
			player.setScore(player.score() > 0 ? player.score() - 1 : player.score());
		}
	}
}
