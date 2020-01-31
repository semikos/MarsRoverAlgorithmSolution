package com.test.bnpcid.interview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Before;
import org.junit.Test;

import com.bnpcid.interview.OutOfGridException;
import com.bnpcid.interview.Position;
import com.bnpcid.interview.Position.Direction;
import com.bnpcid.interview.RoverGrid;

public class MarsRoverTest {
	private RoverGrid rover;
	@Before
	public void initRover() {
		rover = new RoverGrid(6, 8);
	}
	@Test
	public void testNormalMove() {
		Position initPosition = new Position(1, 2, Direction.N);
		Position finalPosition = rover.findFinalPosition(initPosition, "LMLMLMLMM".toCharArray());
		assertThat(finalPosition.getX()).isEqualTo(1);
		assertThat(finalPosition.getY()).isEqualTo(3);	
		assertThat(finalPosition.getFacing()).isEqualTo(Direction.N);
	}
	@Test
	public void testOutOfGridMove() {
		Position initPosition = new Position(2, 3, Direction.W);
		Throwable thrown = catchThrowable(() -> rover.findFinalPosition(initPosition, "MMM".toCharArray()));
		assertThat(thrown).isInstanceOf(OutOfGridException.class).hasMessage("Out of the grid move: currentPosition in 0 3 W");
	}
	@Test
	public void testNormalRotate() {
		Position position = new Position(0, 0, Direction.N);
		Position finalPosition = rover.findFinalPosition(position, "R".toCharArray());
		assertThat(finalPosition.getFacing()).isEqualTo(Direction.E);
	}
	@Test
	public void testFullRotate() {
		Position position = new Position(0, 0, Direction.N);
		Position finalPosition = rover.findFinalPosition(position, "RRRR".toCharArray());
		assertThat(finalPosition.getFacing()).isEqualTo(Direction.N);
	}
}
