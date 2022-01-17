import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ShortReport {
    //This is our StringBuilder which would be given to printer
    StringBuilder generatedReport = new StringBuilder();

    //This is subclass which will generate the StringBuilder for Printer to print
    public StringBuilder generateShortReport(ArrayList<StudentProfile> profilesList) throws IllegalArgumentException {
        //Throwing in report header to StringBuilder
        generatedReport.append("Short(Generating report date: ").append(profilesList.get(0).dayOfReport).append("):");

        //Writing to terminal report for each student
        for (StudentProfile studentData : profilesList) {
            //Those lines would write name of student and his curriculum to stringBuilder
            StringBuilder oneStudentReport = new StringBuilder();
            oneStudentReport.append(System.lineSeparator()).append(studentData.name);
            oneStudentReport.append("(").append(studentData.curriculum).append(") - ");

                /*There are counting to realize are courses finished or not
                givenHours are hours between date of learning start and inputted from terminal date */
            int givenHours = HourCounter.HoursBetween(studentData.dateOfLearningStart, studentData.dayOfReport);
            int neededHours = 0;
            for (Course course : studentData.courses) {
                neededHours += course.duration;
            }

                /*Ending our output string in StringBuilder by adding how many hours have
                left before courses are finished/ have passed after finishing all courses*/
            if (neededHours <= givenHours) {
                oneStudentReport.append("Training completed. ");
                int difference = givenHours - neededHours;
                int difDays = difference / 8;
                int difHours = difference % 8;
                if (difDays != 0) oneStudentReport.append(difDays).append(" working days ").
                        append(difHours).append(" hours have passed since the end.");
                else oneStudentReport.append(difHours).append(" hours have passed since the end.");
            } else {
                oneStudentReport.append("Training is not finished. ");
                int difference = neededHours - givenHours;
                int difDays = difference / 8;
                int difHours = difference % 8;
                if (difDays != 0) oneStudentReport.append(difDays).append(" working days ").
                        append(difHours).append(" hours are left until the end.");
                else oneStudentReport.append(difHours).append(" are left until the end.");
            }
            generatedReport.append(oneStudentReport);
        }
        return generatedReport;
    }
}
