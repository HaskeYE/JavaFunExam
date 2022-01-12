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
                case "s" -> ShortReport.generateShortReport();
                case "f" -> generateFullReport();
                default -> System.out.println("There is typo in report type definition - please rerun program");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred - stopping work");
        }
        System.out.println("\nProgram finished its work\nThank you for using it");
    }


    static void generateFullReport() throws IllegalArgumentException {

    }
}
