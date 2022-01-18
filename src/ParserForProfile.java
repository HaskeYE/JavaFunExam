import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ParserForProfile {
    public static ArrayList<StudentProfile> parseFromFile(String pathToData, String dayOfReport) throws IOException {
        ArrayList<StudentProfile> studentsListParsed = new ArrayList<>();
        try (FileReader reader = new FileReader(pathToData)) {
            Scanner scan = new Scanner(reader);
            /*This whole while block provides building list of studentData which is
            class with student data parsed into data class*/
            while (scan.hasNextLine()) {
                String nextLine = null;
                ArrayList<String> parseToStudentData = new ArrayList<>();
                int i = 0;
                //Getting strings and providing them to raw format
                while (!Objects.equals(nextLine, "") && scan.hasNextLine()) {
                    nextLine = scan.nextLine();
                    parseToStudentData.add(nextLine);
                }
                //Adding raw profile which will be delegated to the studentProfile constructor
                studentsListParsed.add(new StudentProfile(parseToStudentData, dayOfReport));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return studentsListParsed;
    }
}
