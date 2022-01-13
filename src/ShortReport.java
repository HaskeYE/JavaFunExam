import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ShortReport {
    //This is subclass of the main method which will do all work related to printing short report
    static void generateShortReport() throws IllegalArgumentException, IOException {
        //Starting accessing file with student data via try with resources
        System.out.println("Please paste a path to the list of students data");
        Scanner scanner = new Scanner(System.in);
        String pathToData = scanner.nextLine();

        //Gaining date of estimate finish from InputStream
        try (FileReader reader = new FileReader(pathToData)) {
            System.out.println("""
                    For which date you want to see results?
                    Please input in format:
                    d Month year - Day of week - Time in format hh:mm""");
            String dayOfReport = scanner.nextLine();
            System.out.println(String.format("Short(Generating report date: %s ):", dayOfReport));
            Scanner scan = new Scanner(reader);

            ArrayList<StudentData> studentsListParsed = new ArrayList<>();
            /*This whole while block provides building list of studentData which is
            class with student data parsed into data class*/
            while (scan.hasNextLine()) {
                String nextLine = null;
                ArrayList<String> parseToStudentData = new ArrayList<>();
                int i = 0;
                while (!Objects.equals(nextLine, "") && scan.hasNextLine()) {
                    nextLine = scan.nextLine();
                    parseToStudentData.add(nextLine);
                }
                studentsListParsed.add(new StudentData(parseToStudentData));
            }

            //Writing to terminal report for each student
            for (StudentData studentData : studentsListParsed) {
                //Those lines would write name of student and his curriculum to stringBuilder
                StringBuilder oneStudentReport = new StringBuilder();
                oneStudentReport.append(System.lineSeparator()).append(studentData.name);
                oneStudentReport.append("(").append(studentData.curriculum).append(") - ");

                /*There are counting to realize are courses finished or not
                givenHours are hours between date of learning start and inputted from terminal date */
                int givenHours = HourCounter.HoursBetween(studentData.dateOfLearningStart, dayOfReport);
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
                System.out.println(oneStudentReport);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
