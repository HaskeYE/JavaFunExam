import java.util.ArrayList;
import java.util.Objects;

public class StudentProfile {
    String name;
    String curriculum;
    String dateOfLearningStart;
    String dayOfReport;
    ArrayList<Course> courses = new ArrayList<>();

    public StudentProfile(ArrayList<String> data, String dayOfReport) throws IllegalArgumentException {
        //Parsing values from strings into corresponding fields
        this.dayOfReport = dayOfReport;
        try {
            String[] probablyName = data.get(0).split(": ");
            if (Objects.equals(probablyName[0], "STUDENT"))
                name = probablyName[1].trim();
            else throw new IllegalArgumentException("There is error reading the file with student data where " +
                    "must student name be");

            String[] probablyCurriculum = data.get(1).split(": ");
            if (Objects.equals(probablyCurriculum[0], "CURRICULUM"))
                curriculum = probablyCurriculum[1].trim();
            else throw new IllegalArgumentException("There is error reading the file with student data where " +
                    "must curriculum name be");

            String[] probablyDateOfLeaningStart = data.get(2).split(": ");
            if (Objects.equals(probablyDateOfLeaningStart[0], "START_DATE"))
                dateOfLearningStart = probablyDateOfLeaningStart[1].trim();
            else throw new IllegalArgumentException("There is error reading the file with student data where " +
                    "must date of learning start be");

            int linesInInputData = data.size();
            int linesLeft = linesInInputData - 5;
            while (linesLeft > 1) {
                courses.add(new Course(data.get(linesInInputData - linesLeft)));
                linesLeft--;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException();
        }
    }


}
