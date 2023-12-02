package ch.gabin.matthieu.day1.part2;

import java.util.Arrays;
import java.util.Map;

import ch.gabin.matthieu.day1.Input;

public class Main {

	public static void main(String[] args) {
		Map<String, String> lettertoInteger =
			Map.of("one", "1", "two", "2", "three", "3", "four", "4", "five", "5", "six", "6", "seven", "7", "eight",
				"8", "nine", "9", "zero", "0");

		var input = Input.input;

		for (String k : lettertoInteger.keySet()) {
			input = input.replaceAll(k, k.charAt(0) + lettertoInteger.get(k) + k.charAt(k.length() - 1));
		}

		var i = Arrays.stream(input.split("\\n"))
			.map(s -> s.replaceAll("[^\\d.]", ""))
			.map(s -> "" + s.charAt(0) + s.charAt(s.length() - 1))
			.peek(System.out::println)
			.mapToInt(Integer::parseInt)
			.sum();

		System.out.println(i);

	}
}
