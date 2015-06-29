package bowling.dedios.erskine;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BowlingTest {

	@Test
	public void shouldGetTotalScore() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 2, 3, 4);
		assertEquals(10, bowling.tallyScore());
	}
	
	@Test
	public void shouldGetTotalScoreWithSpare() {
		Bowling bowling = new Bowling();
		bowling.roll(9, 1, 9, 1);
		assertEquals(29, bowling.tallyScore());
	}
	
	@Test
	public void shouldGetTotalScoreWithStrike() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 1, 1, 1, 10, 1, 1);
		assertEquals(18, bowling.tallyScore());
	}
	
	@Test
	public void shouldGetPerfectScore() {
		Bowling bowling = new Bowling();
		bowling.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		assertEquals(300, bowling.tallyScore());
	}
	
	@Test
	public void shouldAllowExtra2RollsWithStrikeOnTenthFrame() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10, 10, 10);
		assertEquals(48, bowling.tallyScore());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDisAllowExtra3RollsWithStrikeOnTenthFrame() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10, 10, 10, 1);
		assertEquals(30, bowling.tallyScore());
	}
	
	@Test
	public void shouldAllowExtra1RollWithSpareOnTenthFrame() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 1, 10);
		assertEquals(38, bowling.tallyScore());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldDisallowExtra2RollsWithSpareOnTenthFrame() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 1, 10, 1);
		assertEquals(29, bowling.tallyScore());
	}
	
	@Test
	public void shouldGetIncompleteScore() {
		Bowling bowling = new Bowling();
		bowling.roll(1, 2, 3);
		assertEquals(6, bowling.tallyScore());
	}
	
	@Test
	public void shouldGetZeroScoreIfNoRolls() {
		Bowling bowling = new Bowling();
		assertEquals(0, bowling.tallyScore());
	}
}
