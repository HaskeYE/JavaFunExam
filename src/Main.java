import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    //Test data ahead
    //18 January 2021 - Tuesday - 15:00 resources/file1.txt
    public static void main(String[] args) {
        //Some dialogues to interact with user
        System.out.println("""
                Hello, please write down type of report you want to see if format:\s
                 s - if you want short one
                 f - if you want full one""");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.next();
        //Going into internal submethods varying by type of report
        try {
            switch (type) {
                case "s" -> generateShortReport();
                case "f" -> generateFullReport();
                default -> System.out.println("There is typo in report type definition - please rerun program");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred - stopping work");
        }
        System.out.println("\nProgram finished its work\nThank you for using it");
    }

    static private void generateShortReport() throws IllegalArgumentException, IOException {
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
            String dateOfFinish = scanner.nextLine();
            System.out.println("Short(Generating report date: " + dateOfFinish + "):");
            Scanner scan = new Scanner(reader);

            while (scan.hasNextLine()) {
                //This block will parse data from file into stringBuilder which would be returned
                String firstLines = scan.nextLine();
                StringBuilder oneReport = new StringBuilder();
                oneReport.append("\n").append(firstLines.trim().split(": ")[1]);
                firstLines = scan.nextLine();
                oneReport.append("(").append(firstLines.trim().split(": ")[1]).append(") - ");
                firstLines = scan.nextLine();
                String dateOfStart = firstLines.trim().split(": ")[1];

                //Counting hours between our start and finish dates
                int givenHours = HourCounter.HoursBetween(dateOfStart, dateOfFinish);
                scan.nextLine();
                scan.nextLine();
                String nextLine = null;
                int neededHours = 0;
                //Getting time for each course in new cycle
                while (!Objects.equals(nextLine, "") && scan.hasNextLine()) {
                    nextLine = scan.nextLine();
                    if (!Objects.equals(nextLine, "")) {
                        String[] test = nextLine.trim().split("\\. +");
                        neededHours += Integer.parseInt(test[2]);
                    }
                }
                //Ending our output string in StringBuilder
                if (neededHours <= givenHours) {
                    oneReport.append("Training completed. ");
                    int difference = givenHours - neededHours;
                    int difDays = difference / 8;
                    int difHours = difference % 8;
                    if (difDays != 0) oneReport.append(difDays).append(" working days ").
                            append(difHours).append(" hours have passed since the end.");
                    else oneReport.append(difHours).append(" hours have passed since the end.");
                } else {
                    oneReport.append("Training is not finished. ");
                    int difference = neededHours - givenHours;
                    int difDays = difference / 8;
                    int difHours = difference % 8;
                    if (difDays != 0) oneReport.append(difDays).append(" working days ").
                            append(difHours).append(" hours are left until the end.");
                    else oneReport.append(difHours).append(" are left until the end.");
                }
                System.out.println(oneReport);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    static void generateFullReport() throws IllegalArgumentException {

    }
}
