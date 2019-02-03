package com.glamazon.giftscollector;

import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Log
@AllArgsConstructor
public class Robot {
  private final City city;
  @Getter private Position currentPosition;
  private final List<Direction> directions;
  private final Set<Position> giftsCollectedAt = new HashSet<>();

  public void collectGifts() {
    for (Direction direction: directions)
      moveInDirectionAndCollectGift(direction);
  }

  @VisibleForTesting
  void moveInDirectionAndCollectGift(Direction direction) {
    if (!canMoveIn(direction, currentPosition)) {
      log.warning(format("Robot at %s not moving in direction %s as it will take it outside city.", currentPosition, direction));
      return;
    }

    moveIn(direction);
    if (city.hasGiftAt(currentPosition))
      collectGift();
  }

  public boolean canMoveIn(Direction direction, Position position) {
    val newPosition = position.movedIn(direction);
    return city.isWithinGrid(newPosition);
  }

  private void moveIn(Direction direction) {
    currentPosition = currentPosition.movedIn(direction);
  }

  private void collectGift() {
    giftsCollectedAt.add(currentPosition);
  }

  public int getGiftsCount() {
    return giftsCollectedAt.size();
  }
}
