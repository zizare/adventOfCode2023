package ch.gabin.matthieu.day1.part1;

import java.util.Arrays;
import java.util.Map;

import ch.gabin.matthieu.day1.Input;

public class Main {

	public static void main(String[] args) {

		var i = Arrays.stream(Input.input.split("\\n"))
			.map(s -> s.replaceAll("[^\\d.]", ""))
			.map(s -> "" + s.charAt(0) + s.charAt(s.length() - 1))
			.peek(System.out::println)
			.mapToInt(Integer::parseInt)
			.sum();

		System.out.println(i);

	}
}
