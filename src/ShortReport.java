import java.io.FileReader;
import java.io.IOException;
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

            while (scan.hasNextLine()) {
                //This block will parse data from file into stringBuilder which would be returned
                String firstLines = scan.nextLine();
                StringBuilder oneCourseReport = new StringBuilder();
                oneCourseReport.append(System.lineSeparator()).append(firstLines.trim().split(": ")[1]);
                firstLines = scan.nextLine();
                oneCourseReport.append("(").append(firstLines.trim().split(": ")[1]).append(") - ");
                firstLines = scan.nextLine();
                String dateOfStart = firstLines.trim().split(": ")[1];

                //Counting hours between our start and finish dates
                int givenHours = HourCounter.HoursBetween(dateOfStart, dayOfReport);
                scan.nextLine();
                scan.nextLine();
                String nextLine = null;
                int neededHours = 0;
                //Getting time for each course in new cycle
                while (!Objects.equals(nextLine, "") && scan.hasNextLine()) {
                    nextLine = scan.nextLine();
                    if (!Objects.equals(nextLine, "")) {
                        neededHours += Integer.parseInt(nextLine.trim().split("\\. +")[2]);
                    }
                }
                //Ending our output string in StringBuilder
                if (neededHours <= givenHours) {
                    oneCourseReport.append("Training completed. ");
                    int difference = givenHours - neededHours;
                    int difDays = difference / 8;
                    int difHours = difference % 8;
                    if (difDays != 0) oneCourseReport.append(difDays).append(" working days ").
                            append(difHours).append(" hours have passed since the end.");
                    else oneCourseReport.append(difHours).append(" hours have passed since the end.");
                } else {
                    oneCourseReport.append("Training is not finished. ");
                    int difference = neededHours - givenHours;
                    int difDays = difference / 8;
                    int difHours = difference % 8;
                    if (difDays != 0) oneCourseReport.append(difDays).append(" working days ").
                            append(difHours).append(" hours are left until the end.");
                    else oneCourseReport.append(difHours).append(" are left until the end.");
                }
                System.out.println(oneCourseReport);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
