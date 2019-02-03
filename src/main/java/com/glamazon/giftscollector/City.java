package com.glamazon.giftscollector;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class City {
  private final int gridSize;
  private final Set<Position> giftsPositions;

  public boolean hasGiftAt(Position position) {
    return giftsPositions.contains(position);
  }

  public boolean isWithinGrid(Position newPosition) {
    return isWithinLimit(newPosition.getX()) && isWithinLimit(newPosition.getY());
  }

  private boolean isWithinLimit(int position) {
    return position >= 0 && position < gridSize;
  }
}