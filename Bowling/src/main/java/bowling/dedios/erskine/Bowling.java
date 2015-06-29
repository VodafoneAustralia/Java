package bowling.dedios.erskine;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

	private final int MAX_FRAMES = 10;
	
	private List<Frame> frames = new ArrayList<Frame>();
	
	public Bowling() {
		frames.add(new Frame());
	}
	
	public void roll(int ... pinScores) {
		for (int pinsDowned : pinScores) {
			roll(pinsDowned);
		}
	}

	public void roll(int pinsDowned) {
		final Frame currentFrame = frames.get(frames.size() - 1);
		
		if (!currentFrame.isComplete() && frames.size() <= MAX_FRAMES) {
			currentFrame.addScore(pinsDowned);
		} else if (currentFrame.isComplete() && frames.size() < MAX_FRAMES) {
			final Frame newFrame = new Frame();
			newFrame.addScore(pinsDowned);
			frames.add(newFrame);
		} else if (currentFrame.isComplete() && frames.size() == MAX_FRAMES && (currentFrame.isSpare() || currentFrame.isStrike())) {
			final Frame newFrame = new Frame();
			newFrame.addScore(pinsDowned);
			frames.add(newFrame);
		} else if (!currentFrame.isComplete() && frames.size() == MAX_FRAMES + 1 && frames.get(MAX_FRAMES - 1).isStrike()) {
			currentFrame.addScore(pinsDowned);
		} else if (currentFrame.isComplete() && frames.size() == MAX_FRAMES + 1 && frames.get(MAX_FRAMES - 1).isStrike() && frames.get(MAX_FRAMES - 1).isStrike() && currentFrame.isStrike()) {
			final Frame newFrame = new Frame();
			newFrame.addScore(pinsDowned);
			frames.add(newFrame);			
		} else {
			throw new IllegalArgumentException("Game is complete.  Cannot add score " + pinsDowned + ".");
		}
	}

	public int tallyScore() {
		int total = 0;

		for (int frameCount = 0; (frameCount < frames.size() && frameCount < 10); frameCount++) {
			final Frame currentFrame = frames.get(frameCount);
			total = total + currentFrame.getTotalScore();
			if (currentFrame.isSpare() && frameCount < frames.size() - 1) {
				total = total + frames.get(frameCount + 1).getFirstScore();
			} else if (currentFrame.isStrike()) {
				if (frameCount < frames.size() - 1) {
					total = total + frames.get(frameCount + 1).getFirstScore();					

					if (frames.get(frameCount + 1).getSecondScore() != -1) {
						total = total + frames.get(frameCount + 1).getSecondScore();
					} else if (frameCount < frames.size() - 2) {
						total = total + frames.get(frameCount + 2).getFirstScore();
					}
				}
			}
		}

		return total;
	}

	public static void main(final String[] args) {
		final Bowling bowl = new Bowling();
		for (String arg : args) {
			try {
				bowl.roll(Integer.parseInt(arg));	
			} catch (final NumberFormatException e) {
				System.out.println("Invalid score " + arg + ". Ignoring score.");
			}
		}

		final int result = bowl.tallyScore();
		System.out.println(result);
	}
}
