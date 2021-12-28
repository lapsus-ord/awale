package fr.solo.awale.logic;


import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum AILevel {
	EASY("easy"),
	MEDIUM("medium"),
	HARD("hard"),
	EXTREME("extreme");

	private String value;

	private AILevel(String value) {
		this.value = value;
	}

	public static AILevel fromString(String str) {
		return Arrays.stream(values())
				.filter(el -> el.value.equalsIgnoreCase(str))
				.findFirst()
				.orElse(EASY);
	}
}
