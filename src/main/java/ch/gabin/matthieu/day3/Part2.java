package ch.gabin.matthieu.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

	public static void main(String[] args) {

		String[] row = Input.INPUT.split("\\n");
		char[][] chars = Arrays.stream(row).map(String::toCharArray).toArray(char[][]::new);

		var result = 0;

		for (int line = 0; line < chars.length; line++) {
			for (int column = 0; column < chars[line].length; column++) {
				if (chars[line][column] == '*') {
					List<Integer> numbers = findNumber(chars, line, column);
					if (numbers.size() == 2) {
						result += numbers.get(0) * numbers.get(1);
					}
				}
			}
		}

		System.out.println(result);
	}

	private static List<Integer> findNumber(char[][] chars, int line, int column) {
		List<Integer> result = new ArrayList<>();
		for (int l = Math.max(line - 1, 0); l <= Math.min(line + 1, chars.length - 1); l++) {
			for (int c = Math.max(column - 1, 0); c <= Math.min(column + 1, chars[l].length - 1); c++) {
				if (Character.isDigit(chars[l][c])) {
					var start = foundStart(chars[l], c);
					var end = foundEnd(chars[l], c);
					int num = Integer.parseInt(String.valueOf(chars[l], start, end - start + 1));
					result.add(num);
					c = end;
				}
			}
		}
		return result;

	}

	private static int foundEnd(char[] aChar, int c) {
		if (c == aChar.length) {
			return c - 1;
		}
		if (!Character.isDigit(aChar[c])) {
			return c - 1;
		}

		return foundEnd(aChar, c + 1);
	}

	private static int foundStart(char[] aChar, int c) {
		if (c < 0) {
			return 0;
		}
		if (!Character.isDigit(aChar[c])) {
			return c + 1;
		}

		return foundStart(aChar, c - 1);
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
