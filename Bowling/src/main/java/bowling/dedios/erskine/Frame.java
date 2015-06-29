package bowling.dedios.erskine;

public class Frame {
	
	private final int MAX_FRAME_SCORE = 10;

	private final int[] scores = new int[] {-1, -1};
	
	public void addScore(final int rollScore) {
		if (rollScore > 10) {
			throw new IllegalArgumentException("Roll score cannot be greater than 10.  You have rolled " + rollScore);
		}
		
		if (rollScore > MAX_FRAME_SCORE || rollScore < 0) {
			throw new IllegalArgumentException("Cannot have a roll score of more than 10 or less than 0.");
		}
		
		if (scores[0] == -1) {
			scores[0] = rollScore;
		} else if (scores[1] == -1 && scores[0] != 10) {
			if (scores[0] + rollScore <= MAX_FRAME_SCORE) {
				scores[1] = rollScore;	
			} else {
				throw new IllegalArgumentException("Second roll score of " + rollScore + " is invalid.  Total frame score cannot exceed 10.");
			}
		} else {
			throw new IllegalArgumentException("Cannot add rollScore " + rollScore + ".  Frame is complete. " + scores[0] + " " + scores[1]);
		}
	}
	
	public boolean isComplete() {
		return scores[0] == 10 || (scores[0] != -1 && scores[1] != -1);
	}
	
	public int getTotalScore() {
		int total = 0;
		if (scores[0] != -1) {
			total = total + scores[0];
		}
		
		if (scores[1] != -1) {
			total = total + scores[1];
		}
		
		return total;
	}
	
	public boolean isStrike() {
		if (scores[0] == 10) {
			return true;
		}
		
		return false;
	}
	
	public int getFirstScore() {
		return scores[0];
	}
	
	public int getSecondScore() {
		return scores[1];
	}
	
	public boolean isSpare() {
		if (scores[0] != 10 && getTotalScore() == 10) {
			return true;
		}
		
		return false;
	}
}
