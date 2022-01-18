import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentProfileTest {

    @Test
    public void studentProfileGenerationTest() {
        ArrayList<String> testData = new ArrayList<>();
        testData.add("STUDENT: Ivanov Ivan");
        testData.add("CURRICULUM: Java Developer");
        testData.add("START_DATE: 1 June 2020 - Monday");
        testData.add("COURSE                DURATION (hrs)");
        testData.add("--------------------------------------------");
        testData.add("1. Java.                                                   16");
        testData.add("2. JDBC.                                    24");
        testData.add("3. Spring.                                    16");
        testData.add("");

        StudentProfile generated = new StudentProfile(testData, "2 July 2020 - Thursday - 16:00");
        StudentProfile manually = new StudentProfile();
        manually.name = "Ivanov Ivan";
        manually.dayOfReport = "2 July 2020 - Thursday - 16:00";
        manually.dateOfLearningStart = "1 June 2020 - Monday";
        ArrayList<Course> testDataCourses = new ArrayList<>();
        testDataCourses.add(new Course("1. Java.                                                   16"));
        testDataCourses.add(new Course("2. JDBC.                                    24"));
        testDataCourses.add(new Course("3. Spring.                                    16"));
        manually.courses = testDataCourses;
        manually.curriculum = "Java Developer";
        if (generated.name.equals(manually.name) && generated.dayOfReport.equals(manually.dayOfReport)
                && generated.dateOfLearningStart.equals(manually.dateOfLearningStart)
                && generated.curriculum.equals(manually.curriculum))
        assertTrue(true);
        else fail();
    }
}