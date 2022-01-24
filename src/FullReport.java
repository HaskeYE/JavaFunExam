import java.util.ArrayList;

 class FullReport implements IReportType {
    //This is our StringBuilder which would be given to printer
    StringBuilder generatedReport = new StringBuilder();
    ArrayList<StudentProfile> profilesList;

    public FullReport(ArrayList<StudentProfile> profilesList) {
        this.profilesList = profilesList;
    }

    //This is subclass which will generate the StringBuilder for Printer to print
    @Override
    public StringBuilder generateReport()
             throws IllegalArgumentException {
         //Throwing in report header to StringBuilder
         generatedReport.append("Full(Generating report date: ").append(profilesList.get(0).dayOfReport).append("):");


         //Writing to StringBuilder report for each student
         for (StudentProfile studentData : profilesList) {
             //Those lines would write name of student and his curriculum to stringBuilder

             StringBuilder oneStudentReport = new StringBuilder();
             oneStudentReport.append(System.lineSeparator()).append(studentData.name);
             oneStudentReport.append("(").append(studentData.curriculum)
                     .append("). Work time: from 10:00 till 18:00.");


                /*There are counting to realize are courses finished or not
                givenHours are hours between date of learning start and inputted from terminal date */
             int givenHours = HourCounter.HoursBetween(studentData.dateOfLearningStart, studentData.dayOfReport);
             int neededHours;

             //There are no time in input file in time of start, so i declared start of the working day
             String dateForEach = studentData.dateOfLearningStart + " - 10:00";
                /*Ending our output string in StringBuilder by adding how many hours have
                left before courses are finished/ have passed after finishing all courses*/
             for (Course course : studentData.courses) {
                 try {
                     //Hours needed to complete course which is now processing in cycle
                     neededHours = course.duration;
                 } catch (IllegalArgumentException e) {
                     throw new IllegalArgumentException(
                             String.format("Illegal argument in course %d duration in input file",
                                     Integer.parseInt(course.name)));
                 }
                 //Appending general info about the course
                 oneStudentReport.append(System.lineSeparator()).append("  Course: ").append(course.name)
                         .append(" - duration: ").append(course.duration).append(" hours")
                         .append(System.lineSeparator());
                 //Appending start date
                 oneStudentReport.append("      Date of start:").append(dateForEach).append(System.lineSeparator());
                 //Appending finish date with hour added by my subfunction hourAdder
                 try {
                     dateForEach = HourAdder.hourAdder(dateForEach, neededHours);
                     oneStudentReport.append("      Date of finish:").
                             append(dateForEach).append(System.lineSeparator());
                 } catch (IllegalArgumentException e) {
                     throw new IllegalArgumentException("Error in processing date of start in input file");
                 }

                 //Adding how much time has passed / remains until completion for each course
                 if (neededHours <= givenHours) {
                     oneStudentReport.append("  Training completed. ");
                     int difference = givenHours - neededHours;
                     int difDays = difference / 8;
                     int difHours = difference % 8;
                     if (difDays != 0) oneStudentReport.append(difDays).append(" working days ").
                             append(difHours).append(" hours have passed since the end.");
                     else oneStudentReport.append(difHours).append(" hours have passed since the end.");
                 } else {
                     oneStudentReport.append("  Training is not finished. ");
                     int difference = neededHours - givenHours;
                     int difDays = difference / 8;
                     int difHours = difference % 8;
                     if (difDays != 0) oneStudentReport.append(difDays).append(" working days ").
                             append(difHours).append(" hours are left until the end.");
                     else oneStudentReport.append(difHours).append(" hours are left until the end.");
                 }
                 //Decrementing givenHours by value of hours of each course
                 givenHours -= neededHours;
             }
             generatedReport.append(oneStudentReport).append(System.lineSeparator());
         }
         return generatedReport;
     }
}
