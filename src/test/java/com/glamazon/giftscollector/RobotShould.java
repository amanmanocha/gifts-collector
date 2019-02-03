package com.glamazon.giftscollector;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.glamazon.giftscollector.Direction.*;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RobotShould {
  @Mock private City city;
  private Robot robot;

  @Before
  public void setup() {
    doReturn(true).when(city).isWithinGrid(any());
    robot = new Robot(city, new Position(1, 1), newArrayList(U, D, D, R, R, L));
  }

  @Test public void
  move_in_all_directions() {
    robot.collectGifts();

    assertThat(robot.getCurrentPosition()).isEqualTo(new Position(2, 2));
  }

  @Test public void
  should_not_move_if_falls_outside_city() {
    doReturn(false).when(city).isWithinGrid(any());

    robot.moveInDirectionAndCollectGift(U);

    assertThat(robot.getCurrentPosition()).isEqualTo(new Position(1, 1));
  }

  @Test public void
  should_move_within_city() {
    robot.moveInDirectionAndCollectGift(U);

    assertThat(robot.getCurrentPosition()).isEqualTo(new Position(1, 0));
  }

  @Test public void
  should_not_collect_gift_if_none_present_in_position_in_city() {
    doReturn(false).when(city).hasGiftAt(any());

    robot.moveInDirectionAndCollectGift(U);

    assertThat(robot.getGiftsCount()).isEqualTo(0);
  }

  @Test public void
  should_collect_gift_if_present_in_position_in_city() {
    doReturn(true).when(city).hasGiftAt(new Position(1, 0));

    robot.moveInDirectionAndCollectGift(U);

    assertThat(robot.getGiftsCount()).isEqualTo(1);
  }

  @Test public void
  should_not_collect_gift_if_already_collected() {
    doReturn(true).when(city).hasGiftAt(new Position(1, 0));

    robot.moveInDirectionAndCollectGift(U);
    robot.moveInDirectionAndCollectGift(D);
    robot.moveInDirectionAndCollectGift(U);

    assertThat(robot.getGiftsCount()).isEqualTo(1);
  }
}