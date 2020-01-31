package com.bnpcid.interview;

import com.bnpcid.interview.Position.Direction;

public class RoverGrid {
	private int endx, endy;
	public int getEndx() {
		return endx;
	}

	public int getEndy() {
		return endy;
	}
	public RoverGrid(int endx, int endy) {
		this.endx = endx;
		this.endy = endy;
	}

	public Position findFinalPosition(final Position initialPosition, char[] controls) {

		Position currentPosition = initialPosition;
		for (char control : controls) {
			switch (control) {
			case 'L':
				currentPosition = turnRoverLeft(currentPosition);
				break;
			case 'R':
				currentPosition = turnRoverRight(currentPosition);
				break;
			case 'M':
				currentPosition = moveRover(currentPosition);
				break;
			}
		}
		return currentPosition;
	}

	private Position moveRover(final Position currentPosition) {

		if ((currentPosition.getFacing() == Direction.N && currentPosition.getY() == endy)
				|| (currentPosition.getFacing() == Direction.S && currentPosition.getY() == 0)
				|| (currentPosition.getFacing() == Direction.E && currentPosition.getX() == endx)
				|| (currentPosition.getFacing() == Direction.W && currentPosition.getX() == 0)) {
			throw new OutOfGridException("Out of the grid move: currentPosition in " + currentPosition);
		}
		Position nextPosition = currentPosition;
		switch (currentPosition.getFacing()) {
		case N:
			nextPosition.setY(currentPosition.getY() + 1);
			break;
		case S:
			nextPosition.setY(currentPosition.getY() - 1);
			break;
		case E:
			nextPosition.setX(currentPosition.getX() + 1);
			break;
		case W:
			nextPosition.setX(currentPosition.getX() - 1);
			break;
		}

		return nextPosition;
	}

	private Position turnRoverLeft(final Position currentPosition) {
		Position nextPosition = currentPosition;
		int ordFacing = (currentPosition.getFacing().ordinal() - 1) < 0 ? 3 : currentPosition.getFacing().ordinal() - 1;
		nextPosition.setFacing(Direction.values()[ordFacing]);
		return nextPosition;
	}

	private Position turnRoverRight(final Position currentPosition) {
		Position nextPosition = currentPosition;
		int ordFacing = (currentPosition.getFacing().ordinal() + 1) > 3 ? 0 : currentPosition.getFacing().ordinal() + 1;
		nextPosition.setFacing(Direction.values()[ordFacing]);
		return nextPosition;
	}

}