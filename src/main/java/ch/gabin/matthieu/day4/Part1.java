package ch.gabin.matthieu.day4;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part1 {

	public static void main(String[] args) {

		String input = Input.INPUT.replaceAll("Card\\s+\\d+:", "");

		System.out.println(input);
		var result = Arrays.stream(input.split("\\n")).mapToDouble(s1 -> {
			Map<Integer, Integer> map =
				Arrays.stream(s1.split("\\|")).flatMap(s2 -> Arrays.stream(s2.split(" "))).filter(v -> !v.isEmpty())
					//					.peek(System.out::println)
					.map(Integer::parseInt).collect(Collectors.toMap(i -> i, i -> 1, Integer::sum));
			long count = map.entrySet().stream().filter(e -> e.getValue() > 1).count() - 1;

			return count >= 0 ? Math.pow(2, count) : 0;
		}).sum();

		System.out.println(result);

	}
}
