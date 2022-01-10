import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
// Класс со статик методом, который будет использоваться в мейн методе,
// чтобы вычислять различные промежутки времени в часах, учитывая рабочие и нерабочие дни недели и рабочее время
class HourCounter {
     static int HoursBetween(String startDate, String finishDate) throws IllegalArgumentException {
        int out = 0;
        try {
            //Проверки входных данных даты начала и последующее разбиение этих данных на приемлимые
            // для восприятния подфункциями строки
            if (startDate.trim().split(" - ").length != 2)
                throw new IllegalArgumentException("Wrong date format in input file");
            String dateOfStart = startDate.trim().split(" - ")[0];
            String weekDayOfStart = startDate.trim().split(" - ")[1];
            LocalDate start = DateStringToCommon.DateStringToCommon(dateOfStart).
                    plusDays(HourCounter.plusToRoundUp(weekDayOfStart));

            //Проверки входных данных даты окончания и последующее разбиение этих данных на приемлимые
            // для восприятния подфункциями строки
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
            //Вычисляем разницу в днях между нашими датами, учитывая дни недели как основу
            LocalDate finish = DateStringToCommon.DateStringToCommon(dateOfFinish).
                    minusDays(7 - HourCounter.plusToRoundUp(weekDayOfFinish));
            out = (int) ChronoUnit.DAYS.between(start, finish);
            if (out < 0) {
                out = HourCounter.plusToRoundUp(weekDayOfStart) - HourCounter.plusToRoundUp(weekDayOfFinish);
            } else {
                out = (out / 7 * 5) + (HourCounter.plusToRoundUp(weekDayOfStart) - 2) +
                        (7 - HourCounter.plusToRoundUp(weekDayOfFinish));
            }

            // Переводим дни в часы и прибавляем часы последнего дня запрошенного периода,
            // которые не учитывались в вычислениях выше
             out = out * 8 + (Integer.parseInt(timeOfFinish.trim().split(":")[0]) - 10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return out;
    }

    private static int plusToRoundUp(String weekDayOfStart) throws IllegalArgumentException {
        int out = 0;
        switch (weekDayOfStart) {
            case "Monday":
                out = 7;
                break;
            case "Tuesday":
                out = 6;
                break;
            case "Wednesday":
                out = 5;
                break;
            case "Thursday":
                out = 4;
                break;
            case "Friday":
                out = 3;
                break;
            default:
                throw new IllegalArgumentException("Illegal weekday of start input");
        }
        return out;
    }
}
