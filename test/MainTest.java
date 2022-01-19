import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;


    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }


    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testShortGeneration() {
        final String testString = "2 July 2020 - Thursday - 16:00\nresources/file1.txt\n1";
        provideInput(testString);
        Main.main(new String[0]);

        assertEquals(
                """
                        Hello, i will help you to get results for each student courses but for which date you want to see results?
                        Please input in format:
                        d Month year - Day of week - Time in format hh:mm
                        Please paste path to the list of student data
                        Please write down type of report you want to see in format:\040
                         1 - if you want short one
                         2 - if you want full one
                        Short(Generating report date: 2 July 2020 - Thursday - 16:00):
                        Ivanov Ivan(Java Developer) - Training completed. 16 working days 6 hours have passed since the end.
                        Sidorov Ivan(AQE) - Training completed. 20 working days 4 hours have passed since the end.

                        Program finished its work
                        Thank you for using it
                        """,
                getOutput());
    }

    @Test
    public void testFullGeneration() {
        final String testString = "2 July 2020 - Thursday - 16:00\nresources/file1.txt\n2";
        provideInput(testString);
        Main.main(new String[0]);

        assertEquals(
                """
                        Hello, i will help you to get results for each student courses but for which date you want to see results?
                        Please input in format:
                        d Month year - Day of week - Time in format hh:mm
                        Please paste path to the list of student data
                        Please write down type of report you want to see in format:
                         1 - if you want short one
                         2 - if you want full one
                        Full(Generating report date: 2 July 2020 - Thursday - 16:00):
                        Ivanov Ivan(Java Developer). Work time: from 10:00 till 18:00.
                          Course: Java - duration: 16 hours
                              Date of start:1 June 2020 - Monday - 10:00
                              Date of finish:3 June 2020 - Wednesday - 10:00
                          Training completed. 21 working days 6 hours have passed since the end.
                          Course: JDBC - duration: 24 hours
                              Date of start:3 June 2020 - Wednesday - 10:00
                              Date of finish:6 June 2020 - Saturday - 10:00
                          Training completed. 18 working days 6 hours have passed since the end.
                          Course: Spring - duration: 16 hours
                              Date of start:6 June 2020 - Saturday - 10:00
                              Date of finish:10 June 2020 - Wednesday - 10:00
                          Training completed. 16 working days 6 hours have passed since the end.
                                                
                        Sidorov Ivan(AQE). Work time: from 10:00 till 18:00.
                          Course: Test design - duration: 10 hours
                              Date of start:1 June 2020 - Monday - 10:00
                              Date of finish:2 June 2020 - Tuesday - 12:00
                          Training completed. 22 working days 4 hours have passed since the end.
                          Course: Page Object - duration: 16 hours
                              Date of start:2 June 2020 - Tuesday - 12:00
                              Date of finish:4 June 2020 - Thursday - 12:00
                          Training completed. 20 working days 4 hours have passed since the end.
                                                
                                                
                        Program finished its work
                        Thank you for using it
                        """,
                getOutput());
    }

    @Test
    public void testFileNotFound() {
        final String testString = "2 July 2020 - Thursday - 16:00\nresources/nosuchfile.txt\n2";
        provideInput(testString);

        Main.main(new String[0]);
        assertEquals("""
                Hello, i will help you to get results for each student courses but for which date you want to see results?
                Please input in format:
                d Month year - Day of week - Time in format hh:mm
                Please paste path to the list of student data
                resources/nosuchfile.txt (No such file or directory)
                Exception occurred - stopping work
                                
                Program finished its work
                Thank you for using it
                """, getOutput());
    }

    @Test
    public void testIllegalInput() {
        final String testString = "2 July 2020 - rgegrge - 16:00\nresources/file1.txt\n2";
        provideInput(testString);

        Main.main(new String[0]);
        assertEquals("""
                Hello, i will help you to get results for each student courses but for which date you want to see results?
                Please input in format:
                d Month year - Day of week - Time in format hh:mm
                Please paste path to the list of student data
                Please write down type of report you want to see in format:
                 1 - if you want short one
                 2 - if you want full one
                Illegal weekday of start input
                Error in type of report definition
                Exception occurred - stopping work
                               
                Program finished its work
                Thank you for using it
                """, getOutput());
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}

