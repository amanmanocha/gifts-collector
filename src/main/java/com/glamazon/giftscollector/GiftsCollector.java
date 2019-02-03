package com.glamazon.giftscollector;

import lombok.val;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toList;

public class GiftsCollector {
  public static void main(String[] args) throws IOException {
    InputParser inputParser = new InputParser(lines(Paths.get("input.config")).collect(toList()));

    val gridSize = inputParser.getGridSize();
    val startingPosition = inputParser.getStartingPosition();
    val giftsPositions = inputParser.getGiftsPositions();
    val directions = inputParser.getDirections();

    City city = new City(gridSize, giftsPositions);
    Robot robot = new Robot(city, startingPosition, directions);

    robot.collectGifts();

    System.out.println(robot.getGiftsCount());
    System.out.println(robot.getCurrentPosition());
  }
}
