package com.glamazon.giftscollector;

import lombok.val;
import org.junit.Test;

import static com.glamazon.giftscollector.Direction.*;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class InputParserShould {
  @Test(expected = NullPointerException.class) public void
  throw_exception_for_null_input() {
    new InputParser(null);
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_empty_input() {
    new InputParser(newArrayList());
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_less_than_four_lines() {
    new InputParser(newArrayList(null, null, null));
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_more_than_four_lines() {
    new InputParser(newArrayList(null, null, null, null, null));
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_non_integer_grid_size() {
    val inputParser = new InputParser(newArrayList("NON_INTEGER", null, null, null));
    inputParser.getGridSize();
  }

  @Test public void
  return_integer_grid_size() {
    val inputParser = new InputParser(newArrayList("" + 4, null, null, null));

    assertThat(inputParser.getGridSize()).isEqualTo(4);
  }

  @Test(expected = NullPointerException.class) public void
  throw_exception_for_null_starting_position() {
    val inputParser = new InputParser(newArrayList(null, null, null, null));

    inputParser.getStartingPosition();
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_empty_starting_position() {
    val inputParser = new InputParser(newArrayList(null, "", null, null));

    inputParser.getStartingPosition();
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_non_integer_starting_position() {
    val inputParser = new InputParser(newArrayList(null, "NON_INTEGER,NON_INTEGER", null, null));

    inputParser.getStartingPosition();
  }

  @Test public void
  return_starting_position() {
    val inputParser = new InputParser(newArrayList(null, "2,3", null, null));

    assertThat(inputParser.getStartingPosition()).isEqualTo(new Position(2, 3));
  }

  @Test public void
  return_empty_list_for_null_gifts_positions() {
    val inputParser = new InputParser(newArrayList(null, null, null, null));

    assertThat(inputParser.getGiftsPositions()).isEmpty();
  }

  @Test public void
  return_empty_list_for_empty_gifts_positions() {
    val inputParser = new InputParser(newArrayList(null, null, "", null));

    assertThat(inputParser.getGiftsPositions()).isEmpty();
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_non_integer_gifts_positions() {
    val inputParser = new InputParser(newArrayList(null, null, "NON_INTEGER,NON_INTEGER", null));

    inputParser.getGiftsPositions();
  }

  @Test public void
  return_gifts_positions() {
    val inputParser = new InputParser(newArrayList(null, null, "2,3 4,5", null));

    assertThat(inputParser.getGiftsPositions()).containsExactly(new Position(2, 3), new Position(4, 5));
  }

  @Test public void
  return_empty_list_for_null_directions() {
    val inputParser = new InputParser(newArrayList(null, null, null, null));

    assertThat(inputParser.getDirections()).isEmpty();
  }

  @Test public void
  return_empty_list_for_empty_directions() {
    val inputParser = new InputParser(newArrayList(null, null, null, ""));

    assertThat(inputParser.getDirections()).isEmpty();
  }

  @Test(expected = IllegalArgumentException.class) public void
  throw_exception_for_non_directions_character_direction() {
    val inputParser = new InputParser(newArrayList(null, null, null, "X"));

    inputParser.getDirections();
  }

  @Test public void
  return_directions() {
    val inputParser = new InputParser(newArrayList(null, null, null, "UDLR"));

    assertThat(inputParser.getDirections()).containsExactly(U, D, L, R);
  }
}