package ch.gabin.matthieu.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {

	public static void main(String[] args) {

		String input = Input.INPUT.replaceAll("Card\\s+\\d+:", "");

		Map<Integer, Integer> numberOfScratch = new HashMap<>();

		List<String> scratch = Arrays.stream(input.split("\\n")).collect(Collectors.toList());

		for (int currentScratch = 0; currentScratch < scratch.size(); currentScratch++) {
			var s1 = scratch.get(currentScratch);
			Map<Integer, Integer> map = Arrays.stream(s1.split("\\|"))
				.flatMap(s2 -> Arrays.stream(s2.split(" ")))
				.filter(v -> !v.isEmpty())
				.map(Integer::parseInt)
				.collect(Collectors.toMap(v -> v, v -> 1, Integer::sum));
			int count = Math.toIntExact(map.entrySet().stream().filter(e -> e.getValue() > 1).count());
			int finalCurrentScratch = currentScratch + 1;
			numberOfScratch.compute(finalCurrentScratch, (k, v) -> v == null ? 1 : v + 1);

			IntStream.range(0, count).forEach(i -> numberOfScratch.compute(finalCurrentScratch + i + 1, (k, v) -> {
				if (k > scratch.size() + 1) {
					return 0;
				}

				if (v == null) {
					return numberOfScratch.get(finalCurrentScratch);
				}

				return numberOfScratch.get(finalCurrentScratch) + v;
			}));

		}

		int sum = numberOfScratch.values().stream().mapToInt(i -> i).sum();

		System.out.println(sum);

	}
}
