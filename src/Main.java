import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*Need hoursToWorkdays - turning estimated hours to date which needs to be plusDay to our date
    Test data ahead
    18 January 2021 - Tuesday - 15:00 resources/file1.txt
    2 July 2020 - Thursday - 16:00*/
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            //Gaining date of estimate finish from InputStream
            System.out.println("""
                Hello, i will help you to get results for each student courses but for which date you want to see results?
                Please input in format:
                d Month year - Day of week - Time in format hh:mm""");
            String dayOfReport = scanner.nextLine();


            //Getting path to file with student data
            System.out.println("Please paste path to the list of student data");
            String pathToData = scanner.nextLine();
            //Parsing that data in my "raw" format StudentProfile
            ArrayList<StudentProfile> studentsListParsed = ParserForProfile.parseFromFile(pathToData, dayOfReport);


            //Asking user about type of report
            System.out.println("""
                Please write down type of report you want to see if format:\s
                 1 - if you want short one
                 2 - if you want full one""");
            int type = Integer.parseInt(scanner.next());
            //Going into internal submethods varying by type of report

            ReportTypeFactory reportTypeFactory = new ReportTypeFactory();
            Reporter.print(reportTypeFactory.provide(type, studentsListParsed));
        } catch (IOException | IllegalArgumentException e) {
            if (e.getClass() == IllegalArgumentException.class)
                System.out.println("Error in type of report definition");
            System.out.println("Exception occurred - stopping work");
        }
        System.out.println("\nProgram finished its work\nThank you for using it");
    }
}
