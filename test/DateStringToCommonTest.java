import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateStringToCommonTest {

    @Test
    public void dateStringToHoursCorrect1() {
        LocalDate actual = DateStringToCommon.DateStringToCommon("1 June 2020");
        Assert.assertEquals(LocalDate.of(2020, 6, 1), actual);
    }

    @Test
    public void dateStringToHoursCorrect2() {
        LocalDate actual = DateStringToCommon.DateStringToCommon("6 April 2020");
        Assert.assertEquals(LocalDate.of(2020, 4, 6), actual);
    }

    @Test
    public void dateStringToHoursCorrect3() {
        LocalDate actual = DateStringToCommon.DateStringToCommon("29 August 2017");
        Assert.assertEquals(LocalDate.of(2017, 8, 29), actual);
    }

    @Test
    public void dateStringToHoursIncorrect1() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> DateStringToCommon.DateStringToCommon("1 June2020"));
    }

    @Test
    public void dateStringToHoursIncorrect2() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> DateStringToCommon.DateStringToCommon("hbhjb June reg"));
    }

    @Test
    public void dateStringToHoursIncorrect3() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> DateStringToCommon.DateStringToCommon("1 Jun 2020"));
    }

    @Test
    public void dateStringToHoursIExceptionTricky() {
        Assert.assertThrows(java.time.DateTimeException.class,
                () -> DateStringToCommon.DateStringToCommon("31 February 2020"));
    }
}