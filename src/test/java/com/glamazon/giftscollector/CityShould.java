package com.glamazon.giftscollector;

import lombok.val;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class CityShould {
  @Test public void
  return_true_only_for_positions_containing_gifts() {
    val city = new City(4, newHashSet(new Position(1, 1), new Position(2, 1)));

    val positions = newArrayList(0, 1, 2, 3);
    val allPossiblePositionsInCity = positions.stream()
                                              .flatMap(x -> positions.stream().map(y -> new Position(x, y)))
                                              .collect(toList());

    assertThat(allPossiblePositionsInCity.stream().filter(city::hasGiftAt))
          .containsExactly(new Position(1, 1), new Position(2, 1));
  }

  @Test public void
  return_false_for_positions_lower_than_zero() {
    val city = new City(4, newHashSet(new Position(1, 1), new Position(2, 1)));

    assertThat(city.isWithinGrid(new Position(-1, 0))).isFalse();
    assertThat(city.isWithinGrid(new Position(0, -1))).isFalse();
  }

  @Test public void
  return_false_for_positions_greater_than_grid_size() {
    val city = new City(4, newHashSet(new Position(1, 1), new Position(2, 1)));

    assertThat(city.isWithinGrid(new Position(4, 0))).isFalse();
    assertThat(city.isWithinGrid(new Position(0, 4))).isFalse();
  }

  @Test public void
  return_true_for_boundaries() {
    val city = new City(4, newHashSet(new Position(1, 1), new Position(2, 1)));

    assertThat(city.isWithinGrid(new Position(0, 0))).isTrue();
    assertThat(city.isWithinGrid(new Position(3, 3))).isTrue();
  }


}