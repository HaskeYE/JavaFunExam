import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Class with only static method to count difference in workdays between two dates in String
class HourCounter {
     static int HoursBetween(String startDate, String finishDate) throws IllegalArgumentException {
        int out;
        try {
            //Granting correctness of input date of start and putting into shape for going through program
            if (startDate.trim().split(" - ").length != 2)
                throw new IllegalArgumentException("Wrong date format in input file");
            String dateOfStart = startDate.trim().split(" - ")[0];
            String weekDayOfStart = startDate.trim().split(" - ")[1];

            //Rounding start day to the start of the next week to achieve ability to count days till
            //start of the final week using methods of LocalDate and then separate weekends from this value easily
            LocalDate start = DateStringToCommon.DateStringToCommon(dateOfStart).
                    plusDays(HourCounter.plusToRoundUp(weekDayOfStart));

            //Granting correctness of input date of finish and putting into shape for going through program
            if (finishDate.trim().split(" - ").length != 3)
                throw new IllegalArgumentException("Wrong date format in input file");
            String dateOfFinish = finishDate.trim().split(" - ")[0].trim();
            String weekDayOfFinish = finishDate.trim().split(" - ")[1].trim();
            String timeOfFinish = finishDate.trim().split(" - ")[2].trim();
            if (timeOfFinish.trim().split(":").length != 2)
                throw new IllegalArgumentException("Illegal input time format"); else {
                try {
                    int hours = Integer.parseInt(timeOfFinish.trim().split(":")[0]);
                    int minutes = Integer.parseInt(timeOfFinish.trim().split(":")[0]);
                    if (hours < 10 || hours > 18 || minutes < 0 || minutes > 60)
                        throw new IllegalArgumentException();
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Illegal input time format");
                }
            }

            //counting difference between our dates relying on day of week
            LocalDate finish = DateStringToCommon.DateStringToCommon(dateOfFinish).
                    minusDays(7 - HourCounter.plusToRoundUp(weekDayOfFinish));
            out = (int) ChronoUnit.DAYS.between(start, finish);
            if (out < 0) {
                out = HourCounter.plusToRoundUp(weekDayOfStart) - HourCounter.plusToRoundUp(weekDayOfFinish);
            } else {
                out = (out / 7 * 5) + (HourCounter.plusToRoundUp(weekDayOfStart) - 2) +
                        (7 - HourCounter.plusToRoundUp(weekDayOfFinish));
            }

            //Days to hours plus hours of last day of work
             out = out * 8 + (Integer.parseInt(timeOfFinish.trim().split(":")[0]) - 10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return out;
    }

    //function to round up to the start of the next week
    private static int plusToRoundUp(String weekDayOfStart) throws IllegalArgumentException {
        return switch (weekDayOfStart) {
            case "Monday" -> 7;
            case "Tuesday" -> 6;
            case "Wednesday" -> 5;
            case "Thursday" -> 4;
            case "Friday" -> 3;
            default -> throw new IllegalArgumentException("Illegal weekday of start input");
        };
    }
}
