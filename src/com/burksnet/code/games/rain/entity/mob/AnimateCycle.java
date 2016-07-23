package com.burksnet.code.games.rain.entity.mob;

@Deprecated
public enum AnimateCycle {

	player(3);

	AnimateCycle(int phases) {

		this.phases = phases;

	}

	public void next() {
		if (anim < phases) {
			anim++;
		} else {
			anim = 0;
		}
	}

	private int phases;
	public int anim;

}
