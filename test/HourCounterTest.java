import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class HourCounterTest {

    @Test
    public void hoursBetween() {
        int expected = 37;
        Assert.assertEquals(expected, HourCounter.HoursBetween("3 January 2021 - Monday",
                "7 January 2021 - Friday - 15:00"));
    }
    @Test
    public void hoursBetween1() {
        int expected = 21;
        Assert.assertEquals(expected, HourCounter.HoursBetween("3 January 2021 - Monday",
                "5 January 2021 - Wednesday - 15:00"));
    }
    @Test
    public void hoursBetween2() {
        int expected = 61;
        Assert.assertEquals(expected, HourCounter.HoursBetween("3 January 2021 - Monday",
                "12 January 2021 - Wednesday - 15:00"));
    }

    @Test
    public void hoursBetween3() {
        int expected = 93;
        Assert.assertEquals(expected, HourCounter.HoursBetween("3 January 2021 - Monday",
                "18 January 2021 - Tuesday - 15:00"));
    }

    @Test
    public void hoursBetween4() {
        int expected = 5;
        Assert.assertEquals(expected, HourCounter.HoursBetween("3 January 2021 - Monday",
                "3 January 2021 - Monday - 15:00"));
    }

    @Test
    public void hoursBetweenException() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> HourCounter.HoursBetween("3 January 2021 -Monday",
                        "3 January 2021 - Monday - 15:00"));
    }

    @Test
    public void hoursBetweenException1() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> HourCounter.HoursBetween("3 January 2021 - Monday",
                        "3 January 2021 - Monday - 1500"));
    }
}