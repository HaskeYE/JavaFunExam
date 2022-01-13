import java.io.IOException;
import java.util.Scanner;

public class Main {
    /*Need hoursToWorkdays - turning estimated hours to date which needs to be plusDay to our date
    Test data ahead
    18 January 2021 - Tuesday - 15:00 resources/file1.txt
    2 July 2020 - Thursday - 16:00*/
    public static void main(String[] args) {
        //Some dialogues to interact with user
        System.out.println("""
                Hello, please write down type of report you want to see if format:\s
                 1 - if you want short one
                 2 - if you want full one""");
        Scanner scanner = new Scanner(System.in);
        try {
            int type = Integer.parseInt(scanner.next());
            //Going into internal submethods varying by type of report
            switch (type) {
                case 1 -> ShortReport.generateShortReport();
                case 2 -> FullReport.generateFullReport();
                default -> System.out.println("There is typo in report type definition - please rerun program");
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Exception occurred - stopping work");
        }
        System.out.println("\nProgram finished its work\nThank you for using it");
    }
}
