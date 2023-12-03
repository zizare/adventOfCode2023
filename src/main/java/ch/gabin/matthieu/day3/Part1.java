package ch.gabin.matthieu.day3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

	public static void main(String[] args) {

		String[] row = Input.INPUT.split("\\n");
		char[][] chars = Arrays.stream(row).map(String::toCharArray).toArray(char[][]::new);

		var result = 0;

		for (int line = 0; line < chars.length; line++) {
			var startDigit = -1;
			for (int column = 0; column < chars[line].length; column++) {
				if (Character.isDigit(chars[line][column])) {
					if (startDigit == -1) {
						startDigit = column;
					}
				} else {
					if (startDigit != -1) {
						if (isAdjadcentToASmbol(line, startDigit, column - 1, chars)) {
							int number = Integer.parseInt(String.valueOf(chars[line], startDigit, column - startDigit));
							result += number;
						}
						startDigit = -1;
					}
				}
			}
			if (startDigit != -1) {
				if (isAdjadcentToASmbol(line, startDigit, chars[line].length - 1, chars)) {
					int number = Integer.parseInt(String.valueOf(chars[line], startDigit, chars[line].length - startDigit));
					result += number;
				}
				startDigit = -1;
			}
		}

		System.out.println(result);
	}

	private static boolean isAdjadcentToASmbol(int initialLine, int startDigit, int endLine, char[][] chars) {
		// top line

		var startLine = Math.max(initialLine - 1, 0);
		var endline = Math.min(initialLine + 1, chars.length - 1);

		var startColumn = Math.max(startDigit - 1, 0);
		var endColumn = Math.min(endLine + 1, chars[initialLine].length - 1);

		for (int line = startLine; line <= endline; line++) {
			for (int column = startColumn; column <= endColumn; column++) {
				if (!Character.isDigit(chars[line][column]) && chars[line][column] != '.') {
					return true;
				}
			}
		}

		return false;
	}
}
