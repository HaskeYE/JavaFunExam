import java.io.IOException;
import java.util.ArrayList;

/*Report type factory with input of StudentProfile list and type of report giving a StringBuilder
 formatted in appropriate way*/
public class ReportTypeFactory {
    public IReportType provide(int type, ArrayList<StudentProfile> profilesList) throws IOException {
        if (type == 1) {
            return new ShortReport(profilesList);
        }
        if (type == 2) {
            return new FullReport(profilesList);
        }
        return null;
    }
}
