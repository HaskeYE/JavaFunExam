public class Course{
    String name;
    int duration;
    public Course(String courseString) throws IllegalArgumentException {
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
