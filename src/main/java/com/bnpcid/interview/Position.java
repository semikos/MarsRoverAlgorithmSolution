package com.bnpcid.interview;

public class Position {
	private int x, y;
	private Direction facing;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getFacing() {
		return facing;
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
	}

	public Position(int x, int y, Direction facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	@Override
	public String toString() {
		return String.join(" ", Integer.toString(this.getX()), Integer.toString(this.getY()),
				this.getFacing().toString());
	}

	public enum Direction {
		N, E, S, W
	}
}
