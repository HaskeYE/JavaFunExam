import java.util.ArrayList;
import java.util.Objects;

public class StudentProfile {
    String name;
    String curriculum;
    String dateOfLearningStart;
    String dayOfReport;
    ArrayList<Course> courses = new ArrayList<>();

    //Two constructors here - empty one for tests

    public StudentProfile() {
    }

    //Constructor to fill up all data from one input
    public StudentProfile(ArrayList<String> dataOfStudentsRaw, String dayOfReport) throws IllegalArgumentException {
        //Parsing values from strings into corresponding fields
        this.dayOfReport = dayOfReport;

        try {
            //Parsing string with name of student definition
            String[] probablyName = dataOfStudentsRaw.get(0).split(": ");
            if (Objects.equals(probablyName[0], "STUDENT"))
                name = probablyName[1].trim();
            else throw new IllegalArgumentException("There is error reading the file with student data where " +
                    "must student name be");

            //Parsing string curriculum definition
            String[] probablyCurriculum = dataOfStudentsRaw.get(1).split(": ");
            if (Objects.equals(probablyCurriculum[0], "CURRICULUM"))
                curriculum = probablyCurriculum[1].trim();
            else throw new IllegalArgumentException("There is error reading the file with student data where " +
                    "must curriculum name be");

            //Parsing string with start date of student learning
            String[] probablyDateOfLeaningStart = dataOfStudentsRaw.get(2).split(": ");
            if (Objects.equals(probablyDateOfLeaningStart[0], "START_DATE"))
                dateOfLearningStart = probablyDateOfLeaningStart[1].trim();
            else throw new IllegalArgumentException("There is error reading the file with student data where " +
                    "must date of learning start be");

            //Parsing courses strings into Course Array
            int linesInInputData = dataOfStudentsRaw.size();
            int linesLeft = linesInInputData - 5;
            while (linesLeft > 1) {
                courses.add(new Course(dataOfStudentsRaw.get(linesInInputData - linesLeft)));
                linesLeft--;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException();
        }
    }
}
