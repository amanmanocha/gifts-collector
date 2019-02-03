package com.glamazon.giftscollector;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionShould {
  @Test public void
  return_new_position_based_on_direction() {
    val position = new Position(1, 1);

    assertThat(position.movedIn(Direction.U)).isEqualTo(new Position(1, 0));
    assertThat(position.movedIn(Direction.D)).isEqualTo(new Position(1, 2));
    assertThat(position.movedIn(Direction.L)).isEqualTo(new Position(0, 1));
    assertThat(position.movedIn(Direction.R)).isEqualTo(new Position(2, 1));
  }
}