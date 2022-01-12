import java.io.FileReader;
import java.io.IOException;
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
            String dateOfFinish = scanner.nextLine();
            System.out.println("Full(Generating report date: " + dateOfFinish + "):");
            Scanner scan = new Scanner(reader);

            while (scan.hasNextLine()) {
                //This block will parse data from file into stringBuilder which would be returned System.lineSeparator()
                String firstLines = scan.nextLine();
                StringBuilder oneReport = new StringBuilder();
                oneReport.append(System.lineSeparator()).append(firstLines.trim().split(": ")[1]);
                firstLines = scan.nextLine();
                oneReport.append("(").append(firstLines.trim().split(": ")[1])
                        .append("). Worktime: from 10:00 till 18:00");
                firstLines = scan.nextLine();
                String dateOfStart = firstLines.trim().split(": ")[1];

                //Counting hours between our start and finish dates
                int givenHours = HourCounter.HoursBetween(dateOfStart, dateOfFinish);
                scan.nextLine();
                scan.nextLine();
                String nextLine = null;
                int neededHours;
                String dateForEach = dateOfStart;
                //Getting time for each course in new cycle
                while (!Objects.equals(nextLine, "") && scan.hasNextLine()) {
                    nextLine = scan.nextLine();
                    if (!Objects.equals(nextLine, "")) {
                        String[] program = nextLine.trim().split("\\. +");
                        neededHours = Integer.parseInt(program[2]);
                        //Appending general info about the course
                        oneReport.append(System.lineSeparator()).append("  Course: ").append(program[1])
                                .append(" - duration: ").append(program[2]).append(" hours")
                                .append(System.lineSeparator());
                        //Appending start date
                        oneReport.append("      Date of start:").append(dateForEach).append(System.lineSeparator());
                        //Appending finish date
                        oneReport.append("      Date of finish:").append("smth").append(System.lineSeparator());

                        //Adding how much time has passed / remains until completion for each course
                        if (neededHours <= givenHours) {
                            oneReport.append("  Training completed. ");
                            int difference = givenHours - neededHours;
                            int difDays = difference / 8;
                            int difHours = difference % 8;
                            if (difDays != 0) oneReport.append(difDays).append(" working days ").
                                    append(difHours).append(" hours have passed since the end.");
                            else oneReport.append(difHours).append(" hours have passed since the end.");
                        } else {
                            oneReport.append("  Training is not finished. ");
                            int difference = neededHours - givenHours;
                            int difDays = difference / 8;
                            int difHours = difference % 8;
                            if (difDays != 0) oneReport.append(difDays).append(" working days ").
                                    append(difHours).append(" hours are left until the end.");
                            else oneReport.append(difHours).append(" are left until the end.");
                        }
                        //Decrementing givenHours by value of hours of each course
                        givenHours -= neededHours;
                    }
                }
                System.out.println(oneReport);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
