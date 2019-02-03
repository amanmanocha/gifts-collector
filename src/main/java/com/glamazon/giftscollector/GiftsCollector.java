package com.glamazon.giftscollector;


import lombok.val;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.lines;
import static java.nio.file.Files.setOwner;
import static java.util.stream.Collectors.toList;

public class GiftsCollector {
  public static void main(String[] args) throws IOException {
    InputParser inputParser = new InputParser(lines(Paths.get("input.config")).collect(toList()));

    val gridSize = inputParser.getGridSize();
    val startingPosition = inputParser.getStartingPosition();
    val giftsPositions = inputParser.getGiftsPositions();
    val directions = inputParser.getDirections();

    System.out.println(gridSize);
    System.out.println(startingPosition);
    System.out.println(giftsPositions);
    System.out.println(directions);
  }
}
