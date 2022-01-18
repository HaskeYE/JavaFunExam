import java.io.IOException;
import java.util.ArrayList;

public class ReportTypeFactory {
    public StringBuilder provide(int type, ArrayList<StudentProfile> profilesList) throws IOException {
        if (type == 1) {
            return new ShortReport().generateReport(profilesList);
        }
        if (type == 2) {
            return new FullReport().generateReport(profilesList);

        }
        return null;
    }
}
