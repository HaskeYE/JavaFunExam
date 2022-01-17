public class Reporter {
    public static void print(StringBuilder strToPrint) {
        if (strToPrint == null)
            System.out.println("There are no such type of report");
        else
            System.out.println(strToPrint);
    }
}
