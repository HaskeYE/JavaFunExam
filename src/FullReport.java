import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FullReport {
    static void generateFullReport() throws IllegalArgumentException, IOException {
        //Starting accessing file with student data via try with resources
        System.out.println("Please paste path to list of student data");
        Scanner scanner = new Scanner(System.in);
        String pathToData = scanner.nextLine();

        //Gaining date of estimate finish from InputStream
        try (FileReader reader = new FileReader(pathToData)) {
            System.out.println("""
                    For which date you want to see results?
                    Please input if format:
                    d Month year - Day of week - Time in format hh:mm""");
            String dayOfReport = scanner.nextLine();
            System.out.println("Full(Generating report date: " + dayOfReport + "):");
            Scanner scan = new Scanner(reader);


            ArrayList<StudentProfile> studentsListParsed = new ArrayList<>();
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
                studentsListParsed.add(new StudentProfile(parseToStudentData));
            }

            //Writing to terminal report for each student
            for (StudentProfile studentData : studentsListParsed) {
                //Those lines would write name of student and his curriculum to stringBuilder
                StringBuilder oneStudentReport = new StringBuilder();
                oneStudentReport.append(System.lineSeparator()).append(studentData.name);
                oneStudentReport.append("(").append(studentData.curriculum)
                        .append("). Work time: from 10:00 till 18:00.");


                /*There are counting to realize are courses finished or not
                givenHours are hours between date of learning start and inputted from terminal date */
                int givenHours = HourCounter.HoursBetween(studentData.dateOfLearningStart, dayOfReport);
                int neededHours = 0;

                //There are no time in input file in time of start, so i declared start of the working day
                String dateForEach = studentData.dateOfLearningStart + " - 10:00";
                /*Ending our output string in StringBuilder by adding how many hours have
                left before courses are finished/ have passed after finishing all courses*/
                for (Course course : studentData.courses) {
                    try {
                        //Hours needed to complete course which is now processing in cycle
                        neededHours = course.duration;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(
                                String.format("Illegal argument in course %d duration in input file",
                                        Integer.parseInt(course.name)));
                    }
                    //Appending general info about the course
                    oneStudentReport.append(System.lineSeparator()).append("  Course: ").append(course.name)
                            .append(" - duration: ").append(course.duration).append(" hours")
                            .append(System.lineSeparator());
                    //Appending start date
                    oneStudentReport.append("      Date of start:").append(dateForEach).append(System.lineSeparator());
                    //Appending finish date with hour added by my subfunction hourAdder
                    try {
                        dateForEach = HourAdder.hourAdder(dateForEach, neededHours);
                        oneStudentReport.append("      Date of finish:").
                                append(dateForEach).append(System.lineSeparator());
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Error in processing date of start in input file");
                    }

                    //Adding how much time has passed / remains until completion for each course
                    if (neededHours <= givenHours) {
                        oneStudentReport.append("  Training completed. ");
                        int difference = givenHours - neededHours;
                        int difDays = difference / 8;
                        int difHours = difference % 8;
                        if (difDays != 0) oneStudentReport.append(difDays).append(" working days ").
                                append(difHours).append(" hours have passed since the end.");
                        else oneStudentReport.append(difHours).append(" hours have passed since the end.");
                    } else {
                        oneStudentReport.append("  Training is not finished. ");
                        int difference = neededHours - givenHours;
                        int difDays = difference / 8;
                        int difHours = difference % 8;
                        if (difDays != 0) oneStudentReport.append(difDays).append(" working days ").
                                append(difHours).append(" hours are left until the end.");
                        else oneStudentReport.append(difHours).append(" are left until the end.");
                    }
                    //Decrementing givenHours by value of hours of each course
                    givenHours -= neededHours;
                }
                System.out.println(oneStudentReport);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
