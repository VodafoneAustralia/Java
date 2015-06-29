package bowling.dedios.erskine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FrameTest {

	public void shouldAddFirstAndSecondScores() {
		final Frame testFrame = new Frame();
		testFrame.addScore(1);
		testFrame.addScore(2);

		assertEquals(3, testFrame.getTotalScore());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAddingScoreIfFrameIsComplete() {
		final Frame testFrame = new Frame();
		testFrame.addScore(1);
		testFrame.addScore(2);
		testFrame.addScore(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfScoreAddedIsMoreThanMax() {
		final Frame testFrame = new Frame();
		testFrame.addScore(11);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfScoreAddedIsLessThanZero() {
		final Frame testFrame = new Frame();
		testFrame.addScore(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfSecondScoreMakesTotalScoreOverTen() {
		final Frame testFrame = new Frame();
		testFrame.addScore(9);
		testFrame.addScore(2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldDisallowAddSecondScoreAfterStrike() {
		final Frame testFrame = new Frame();
		testFrame.addScore(10);
		testFrame.addScore(1);
	}

	@Test
	public void shouldGetTotalScore() {
		final Frame testFrame = new Frame();
		testFrame.addScore(5);
		testFrame.addScore(1);
		assertEquals(6, testFrame.getTotalScore());
	}
	
	@Test
	public void shouldGetTotalStrikeScore() {
		final Frame testFrame = new Frame();
		testFrame.addScore(10);
		assertEquals(10, testFrame.getTotalScore());
	}
}
