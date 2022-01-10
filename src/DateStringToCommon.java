import java.time.DateTimeException;
import java.time.LocalDate;
//беру даты - потом считаю промежуток в днях между ними - добиваю до начала недели, вычитаю из дней это кол-во
// и добиваю финальную дату до начала недели - беру разницу дат в днях, делю на 7, умножаю на пять
// прибавляю первые дни и последние - получаю кол-во дней от и до - умножаю на 8, после чего
// прибавляю к этому разницу финального времени и 10 00 - профит

class DateStringToCommon {

    public static LocalDate DateStringToCommon(String str) throws IllegalArgumentException{
        LocalDate date;
        try {
            String[] ourDate = str.trim().split(" ");
            if (ourDate.length != 3) throw new IllegalArgumentException("Illegal date format");
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
