package com.burksnet.code.games.rain.entity.mob;

public enum Direction {

	NORTH(0, "North"), NORTH_WEST(7, "North West"), NORTH_EAST(4, "North East"), EAST(1, "East"), SOUTH(2,
			"South"), SOUTH_EAST(5, "South East"), SOUTH_WEST(6, "South West"), WEST(3, "West");

	public final int dir;
	public final String name;

	private Direction(int i, String name) {
		this.dir = i;
		this.name = name;
	}

}
