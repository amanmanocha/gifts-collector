package com.glamazon.giftscollector;

import lombok.Data;
import lombok.NonNull;

import static java.lang.String.format;

@Data
public class Position {
  private final int x;
  private final int y;

  public Position movedIn(@NonNull Direction direction) {
    switch (direction) {
      case U: return new Position(x, y - 1);
      case D: return new Position(x, y + 1);
      case L: return new Position(x - 1, y);
      case R: return new Position(x + 1, y);
      default: return null;
    }
  }

  @Override
  public String toString() {
    return format("(%s, %s)", x, y);
  }
}
