public class Reporter {
    //Class helping to print our report into needed way - now it is only stdout
    public static void print(IReportType reportType) {
        if (reportType == null)
            System.out.println("There are no such type of report");
        else
            System.out.println(reportType.generateReport());
    }
}
