package ch.gabin.matthieu.day10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {

	static Integer[][] DOT = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	static Integer[][] L = { { 0, 1, 0 }, { 0, 1, 1 }, { 0, 0, 0 } };
	static Integer[][] J = { { 0, 1, 0 }, { 1, 1, 0 }, { 0, 0, 0 } };
	static Integer[][] F = { { 0, 0, 0 }, { 0, 1, 1 }, { 0, 1, 0 } };
	static Integer[][] SEVEN = { { 0, 0, 0 }, { 1, 1, 0 }, { 0, 1, 0 } };
	static Integer[][] V_PIPE = { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 } };
	static Integer[][] H_PIPE = { { 0, 0, 0 }, { 1, 0, 1 }, { 0, 0, 0 } };
	static Integer[][] S = { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
	static Map<Character, Integer[][]> charMatrix =
		Map.of('.', DOT, 'L', L, 'J', J, 'F', F, '7', SEVEN, '|', V_PIPE, '-', H_PIPE, 'S', S);

	public static void main(String[] args) {

		String input = Input.INPUT;
		char[][] maze = Arrays.stream(input.split("\\n")).map(String::toCharArray).toArray(char[][]::new);

		System.out.println(Arrays.deepToString(maze).replaceAll("],", "],\n"));

		int[][] distance = new int[maze.length][maze[0].length];
		int[][] solution = new int[maze.length][maze[0].length];

		var startedPoint = startedPoint(maze);

		distance[startedPoint.y()][startedPoint.x()] = 1;

		Part1.Point nextPoint = startedPoint;
		do {
			nextPoint = getNextPoint(nextPoint, maze, distance, 1);
		} while (nextPoint != null && !nextPoint.equals(startedPoint));

		for (int y = 0; y < distance.length; y++) {
			int borderCount = 0;
			for (int x = 0; x < distance[y].length; x++) {
				if (borderCount % 2 == 1 && distance[y][x] == 0) {
					solution[y][x] = 1;
				}
				boolean isBorder =
					distance[y][x] >0 && maze[y][x]!='-' && maze[y][x]!='L'&& maze[y][x]!='J';
				if (isBorder) {
					borderCount += distance[y][x];
				}

			}
		}

		System.out.println(Arrays.deepToString(distance).replaceAll("],", "],\n"));
		System.out.println(Arrays.deepToString(solution).replaceAll("],", "],\n"));

		int sum = Arrays.stream(solution).flatMapToInt(Arrays::stream).sum();

		System.out.println("sum =" + sum);

		return;
	}

	private static Part1.Point getNextPoint(Part1.Point startedPoint, char[][] maze, int[][] solution,
		int currentDistance) {
		if (startedPoint.y() - 1 >= 0 && charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[0][1] == 1
			&& charMatrix.get(maze[startedPoint.y() - 1][startedPoint.x()])[2][1] == 1 && 0 == solution[startedPoint.y()
																										- 1][startedPoint.x()]) {

			solution[startedPoint.y() - 1][startedPoint.x()] = currentDistance;
			return new Part1.Point(startedPoint.x(), startedPoint.y() - 1);
		}

		if (startedPoint.y() + 1 < maze.length && charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[2][1] == 1
			&& charMatrix.get(maze[startedPoint.y() + 1][startedPoint.x()])[0][1] == 1 && 0 == solution[startedPoint.y()
																										+ 1][startedPoint.x()]) {
			solution[startedPoint.y() + 1][startedPoint.x()] = currentDistance;
			return new Part1.Point(startedPoint.x(), startedPoint.y() + 1);
		}

		if (startedPoint.x() - 1 >= 0 && charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[1][0] == 1
			&& charMatrix.get(maze[startedPoint.y()][startedPoint.x() - 1])[1][2] == 1
			&& solution[startedPoint.y()][startedPoint.x() - 1] == 0) {
			solution[startedPoint.y()][startedPoint.x() - 1] = currentDistance;
			return new Part1.Point(startedPoint.x() - 1, startedPoint.y());
		}

		if (startedPoint.x() + 1 < maze[startedPoint.y()].length
			&& charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[1][2] == 1
			&& charMatrix.get(maze[startedPoint.y()][startedPoint.x() + 1])[1][0] == 1
			&& solution[startedPoint.y()][startedPoint.x() + 1] == 0) {
			solution[startedPoint.y()][startedPoint.x() + 1] = currentDistance;
			return new Part1.Point(startedPoint.x() + 1, startedPoint.y());
		}
		return null;
	}

	private static Part1.Point startedPoint(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 'S') {
					return new Part1.Point(j, i);
				}
			}
		}
		return null;
	}

}
