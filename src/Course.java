//Class to define the Course instance
public class Course {
    //Name of the course - first parameter in input file
    String name;
    //Duration of this course - int because there were declared only integers in the example
    int duration;

    public Course(String courseString) throws IllegalArgumentException {
        //Method gains raw string from input file and splits it into its internal fields
        String[] splitted = courseString.trim().split("\\. +");
        if (splitted.length != 3) throw new IllegalArgumentException("Typo in course definition string in input file");
        name = splitted[1];

        try {
            duration = Integer.parseInt(splitted[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Typo in course hours amount in input file");
        }
    }
}
