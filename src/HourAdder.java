import java.time.LocalDate;

public class HourAdder {

    static String hourAdder(String startDate, int hoursToAdd) throws IllegalArgumentException {
        String[] startDateSplit = startDate.split(" - ");
        LocalDate nowIs = DateStringToCommon.DateStringToCommon(startDateSplit[0]);
        int leftHours = hoursToAdd;
        if (startDate.split(" - ").length == 3) {
            while (leftHours >= 8) {
                switch (nowIs.getDayOfWeek()) {
                    case SATURDAY, SUNDAY -> nowIs = nowIs.plusDays(1);
                    default -> {
                        nowIs = nowIs.plusDays(1);
                        leftHours -= 8;
                    }
                }
            }
            if (leftHours != 0) {
                /*If hours added to the last day's time are out of working day then
                overHours must be thrown to the next day*/
                int hoursOfEnd = leftHours + Integer.parseInt(startDateSplit[2].split(":")[0]);

                if (hoursOfEnd > 18) {
                    nowIs = nowIs.plusDays(1);
                    startDateSplit[2] = (10 + (hoursOfEnd - 18)) + ":00";
                } else {
                    startDateSplit[2] = hoursOfEnd + ":00";
                }
            }
        }
        else throw new IllegalArgumentException();
        return String.format("%d %s %d - %s - %s", nowIs.getDayOfMonth(),
                localDateMonthToMonth(nowIs.getMonth().toString()), nowIs.getYear(),
                localDateWeekDayToString(nowIs.getDayOfWeek().toString()), startDateSplit[2]);
    }
    private static String localDateWeekDayToString(String fromGetDayOfWeek) throws IllegalArgumentException {
        String stringDayOfWeek;
        switch (fromGetDayOfWeek) {
            case "MONDAY" -> stringDayOfWeek = "Monday";
            case "TUESDAY" -> stringDayOfWeek = "Tuesday";
            case "WEDNESDAY" -> stringDayOfWeek = "Wednesday";
            case "THURSDAY" -> stringDayOfWeek = "Thursday";
            case "FRIDAY" -> stringDayOfWeek = "Friday";
            case "SATURDAY" -> stringDayOfWeek = "Saturday";
            case "SUNDAY" -> stringDayOfWeek = "Sunday";
            default -> throw new IllegalArgumentException("Error in counting day of week for course");
        }
        return stringDayOfWeek;
    }
    private static String localDateMonthToMonth(String fromGetDayOfWeek) throws IllegalArgumentException {
        String stringDayOfWeek;
        switch (fromGetDayOfWeek) {
            case "JANUARY" -> stringDayOfWeek = "January";
            case "FEBRUARY" -> stringDayOfWeek = "February";
            case "MARCH" -> stringDayOfWeek = "March";
            case "APRIL" -> stringDayOfWeek = "April";
            case "MAY" -> stringDayOfWeek = "May";
            case "JUNE" -> stringDayOfWeek = "June";
            case "JULY" -> stringDayOfWeek = "July";
            case "AUGUST" -> stringDayOfWeek = "August";
            case "SEPTEMBER" -> stringDayOfWeek = "September";
            case "OCTOBER" -> stringDayOfWeek = "October";
            case "NOVEMBER" -> stringDayOfWeek = "November";
            case "DECEMBER" -> stringDayOfWeek = "December";
            default -> throw new IllegalArgumentException("Error in counting month for course");
        }
        return stringDayOfWeek;
    }
}
