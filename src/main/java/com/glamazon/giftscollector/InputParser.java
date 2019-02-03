package com.glamazon.giftscollector;

import lombok.NonNull;
import lombok.val;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class InputParser {
  private final List<String> lines;

  public InputParser(@NonNull List<String> lines) {
    if (lines.size() != 4)
      throw new IllegalArgumentException("Exactly four lines must be passed to InputParser!");

    this.lines = lines;
  }

  public int getGridSize() {
    try {
      return new Integer(lines.get(0));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Grid size must be an integer, passed " + lines.get(0), e);
    }
  }

  public Position getStartingPosition() {
    String positionText = lines.get(1);
    return parsePosition(positionText);
  }

  public Set<Position> getGiftsPositions() {
    if (lines.get(2) == null || lines.get(2).isEmpty())
      return emptySet();

    val parts = lines.get(2).split(" ");
    return stream(parts).map(this::parsePosition)
                        .collect(toSet());
  }

  private Position parsePosition(@NonNull String positionText) {
    val parts = positionText.split(",");
    try {
      return new Position(new Integer(parts[0]), new Integer(parts[1]));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Position must be specified in format 'int,int' found " + positionText, e);
    }
  }

  public List<Direction> getDirections() {
    if (lines.get(3) == null || lines.get(3).isEmpty())
      return emptyList();

    val parts = lines.get(3).split("");
    return stream(parts).map(Direction::valueOf).collect(Collectors.toList());
  }
}
