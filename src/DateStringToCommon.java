import java.time.DateTimeException;
import java.time.LocalDate;

//Class with one static method which will parse raw String date into LocalDate
class DateStringToCommon {
    //String input of Date to common for program type of data
    public static LocalDate DateStringToCommon(String str) throws IllegalArgumentException{
        LocalDate date;
        try {
            //Checking correctance of input date at all
            String[] ourDate = str.trim().split(" ");
            if (ourDate.length != 3) throw new IllegalArgumentException("Illegal date format");
            //Casting month into appropriate form
            switch (ourDate[1]) {
                case "January" -> ourDate[1] = "1";
                case "February" -> ourDate[1] = "2";
                case "March" -> ourDate[1] = "3";
                case "April" -> ourDate[1] = "4";
                case "May" -> ourDate[1] = "5";
                case "June" -> ourDate[1] = "6";
                case "July" -> ourDate[1] = "7";
                case "August" -> ourDate[1] = "8";
                case "September" -> ourDate[1] = "9";
                case "October" -> ourDate[1] = "10";
                case "November" -> ourDate[1] = "11";
                case "December" -> ourDate[1] = "12";
                default -> throw new IllegalArgumentException("Typo in month description");
            }
            try {
                date = LocalDate.of(Integer.parseInt(ourDate[2]), Integer.parseInt(ourDate[1]),
                        Integer.parseInt(ourDate[0]));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Illegal date format");
            }
        } catch (IllegalArgumentException | DateTimeException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return date;
    }
}
