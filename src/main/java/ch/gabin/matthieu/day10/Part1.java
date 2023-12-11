package ch.gabin.matthieu.day10;

import java.util.Arrays;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Function;

public class Part1 {

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

		int[][] solution = new int[maze.length][maze[0].length];

		var startedPoint = startedPoint(maze);

		solution[startedPoint.y()][startedPoint.x()] = 1;

		Integer currentDistance = 0;
		Point nextPoint = startedPoint;
		do {
			nextPoint = getNextPoint(nextPoint, maze, solution, currentDistance);
			currentDistance++;
		} while (nextPoint != null && !nextPoint.equals(startedPoint));


		System.out.println(Arrays.deepToString(solution).replaceAll("],", "],\n"));

		OptionalInt max = Arrays.stream(solution).flatMapToInt(Arrays::stream).max();

		System.out.println("max ="+max);

		return;
	}

	private static Point getNextPoint(Point startedPoint, char[][] maze, int[][] solution, int currentDistance) {
		if (startedPoint.y() - 1 >= 0 && charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[0][1] == 1
			&& charMatrix.get(maze[startedPoint.y() - 1][startedPoint.x()])[2][1] == 1 && 0 == solution[startedPoint.y()
																										- 1][startedPoint.x()]) {

			solution[startedPoint.y() - 1][startedPoint.x()] = ++currentDistance;
			return new Point(startedPoint.x(), startedPoint.y() - 1);
		}

		if (startedPoint.y() + 1 < maze.length && charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[2][1] == 1
			&& charMatrix.get(maze[startedPoint.y() + 1][startedPoint.x()])[0][1] == 1 && 0 == solution[startedPoint.y()
																										+ 1][startedPoint.x()]) {
			solution[startedPoint.y() + 1][startedPoint.x()] = ++currentDistance;
			return new Point(startedPoint.x(), startedPoint.y() + 1);
		}

		if (startedPoint.x() - 1 >= 0 && charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[1][0] == 1
			&& charMatrix.get(maze[startedPoint.y()][startedPoint.x() - 1])[1][2] == 1
			&& solution[startedPoint.y()][startedPoint.x() - 1] == 0) {
			solution[startedPoint.y()][startedPoint.x() - 1] = ++currentDistance;
			return new Point(startedPoint.x() - 1, startedPoint.y());
		}

		if (startedPoint.x() + 1 < maze[startedPoint.y()].length
			&& charMatrix.get(maze[startedPoint.y()][startedPoint.x()])[1][2] == 1
			&& charMatrix.get(maze[startedPoint.y()][startedPoint.x() + 1])[1][0] == 1
			&& solution[startedPoint.y()][startedPoint.x() + 1] == 0) {
			solution[startedPoint.y()][startedPoint.x() + 1] = ++currentDistance;
			return new Point(startedPoint.x() + 1, startedPoint.y());
		}
		return null;
	}

	private static Point startedPoint(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 'S') {
					return new Point(j, i);
				}
			}
		}
		return null;
	}

	record Point(int x, int y) {
	}

}
