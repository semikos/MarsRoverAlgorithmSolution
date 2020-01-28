package com.bnpcid.interview;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarsRover {

	public static void main(String[] args) throws Exception {

		File inputFile = new File(args[0]);
		try (Stream<String> gameStream = Files.lines(inputFile.toPath())) {
			List<String> gameLines = gameStream.collect(Collectors.toList());
			Rover r = null ;
			Position currentPos = null;
			for (int i = 0 ; i < gameLines.size();i++) {
				String gameLine = gameLines.get(i);
				if(i == 0) {
					String[] endGridLine = gameLines.get(0).split(" ");
					r = new Rover(Integer.parseInt(endGridLine[0]), Integer.parseInt(endGridLine[1]));
				} 
				else if (i % 2 == 1) {
					String[] initPosArray = gameLine.split(" ");
					currentPos = new Position(Integer.parseInt(initPosArray[0]), Integer.parseInt(initPosArray[1]),
							Direction.valueOf(initPosArray[2]));
				} else {
					  char[] controls  = gameLine.toCharArray();
					  for (char control : controls) {
						  currentPos = r.findNextPosition(currentPos, control);
						}
					  System.out.println(String.join(" ",Integer.toString(currentPos.x), Integer.toString(currentPos.y), currentPos.facing.toString()));
				}
			}		
		}
	}
}

enum Direction {
	N, E, S, W
}

class Rover {
	int endx, endy;

	Rover(int endx, int endy) {
		this.endx = endx;
		this.endy = endy;
	}

	Position findNextPosition(Position currentPosition, char control) {
		Position nextPos = currentPosition;
		switch (control) {
		case 'L':
			nextPos = turnRoverLeft(currentPosition);
			break;
		case 'R':
			nextPos = turnRoverRight(currentPosition);
			break;
		case 'M':
			nextPos = moveRover(currentPosition);
			break;
		}
		return nextPos;

	}

	private Position moveRover(Position currentPosition) {

		Position nextPosition = currentPosition;
		if (currentPosition.facing == Direction.N && currentPosition.y < endy) {
			nextPosition.y++;
		} else if (currentPosition.facing == Direction.S && currentPosition.y > 0) {
			nextPosition.y--;
		} else if (currentPosition.facing == Direction.E && currentPosition.x < endx) {
			nextPosition.x++;
		} else if (currentPosition.facing == Direction.W && currentPosition.x > 0) {
			nextPosition.x--;
		}
		return nextPosition;
	}

	private Position turnRoverLeft(Position currentPosition) {

		int ordFacing = (currentPosition.facing.ordinal() - 1) < 0 ? 3 : currentPosition.facing.ordinal() - 1;

		return new Position(currentPosition.x, currentPosition.y, Direction.values()[ordFacing]);
	}

	private Position turnRoverRight(Position currentPosition) {

		int ordFacing = (currentPosition.facing.ordinal() + 1) > 3 ? 0 : currentPosition.facing.ordinal() + 1;
		return new Position(currentPosition.x, currentPosition.y, Direction.values()[ordFacing]);

	}

}

class Position {
	int x, y;
	Direction facing;

	Position(int x, int y, Direction facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}
}
