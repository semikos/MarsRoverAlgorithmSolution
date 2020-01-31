package com.bnpcid.interview;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bnpcid.interview.Position.Direction;

public class MarsRover {

	public static void main(String[] args) throws Exception {
		File inputFile = new File(args[0]);
		try (Stream<String> gameStream = Files.lines(inputFile.toPath())) {
			List<String> gameLines = gameStream.collect(Collectors.toList());
			RoverGrid r = null;
			Position initPos = null;
			Position finalPos = null;
			for (int i = 0; i < gameLines.size(); i++) {
				String gameLine = gameLines.get(i);
				if (i == 0) {
					if (!gameLine.matches("\\d+\\s\\d+")) {
						System.err.print("Invalid grid dimensions");
						break;
					}
					String[] endGridLine = gameLines.get(0).split(" ");
					r = new RoverGrid(Integer.parseInt(endGridLine[0]), Integer.parseInt(endGridLine[1]));
				} else if (i % 2 == 1) {
					if (!gameLine.matches("\\d+\\s\\d+\\s[NEWS]")) {
						System.err.print("Invalid initial position");
						break;
					}
					String[] initPosArray = gameLine.split(" ");
					initPos = new Position(Integer.parseInt(initPosArray[0]), Integer.parseInt(initPosArray[1]),
							Direction.valueOf(initPosArray[2]));
				} else {
					if (!gameLine.matches("[MRL]+")) {
						System.err.print("Invalid controls");
						break;
					}
					char[] controls = gameLine.toCharArray();
					finalPos = r.findFinalPosition(initPos, controls);
					System.out.println(finalPos);
				}
			}
		} catch (IOException | OutOfGridException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
