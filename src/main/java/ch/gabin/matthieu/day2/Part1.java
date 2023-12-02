package ch.gabin.matthieu.day2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part1 {

	static Map<String, Integer> MAX_BY_COLOR = Map.of("red", 12, "green", 13, "blue", 14);

	public static void main(String[] args) {

		String input = Input.INPUT.replace("Game ", "");

		List<GameNumberWithData> list = Arrays.stream(input.split("\\n")).map(line -> {
			String[] game = line.split(":");
			String[] set = game[1].split(";");

			List<List<NumberOfCubes>> setByTurn =
				Arrays.stream(set).map(setS -> Arrays.stream(setS.split(",")).map(numberColor -> {
					List<String> numColor = Arrays.stream(numberColor.split(" ")).filter(s -> !s.isBlank()).toList();
					return new NumberOfCubes(Integer.parseInt(numColor.get(0)), numColor.get(1));
				}).toList()).toList();

			return new GameNumberWithData(Integer.parseInt(game[0]), setByTurn);
		}).toList();

		int sum = list.stream()
			.peek(g -> System.out.println("before " + g.toString()))
			.filter(game -> !isNotvalid(game))
			.peek(gameNumberWithData -> System.out.println("after " + gameNumberWithData.toString()))
			.mapToInt(GameNumberWithData::num)
			.sum();

		System.out.println(sum);

	}

	private static boolean isNotvalid(GameNumberWithData game) {

		return game.d()
			.stream()
			.anyMatch(
				turn -> turn.stream().anyMatch(e -> MAX_BY_COLOR.getOrDefault(e.color, Integer.MAX_VALUE) < e.num()));
	}

	record GameNumberWithData(int num, List<List<NumberOfCubes>> d) {
	}

	record NumberOfCubes(int num, String color) {
	}

}
