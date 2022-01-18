import java.util.ArrayList;

public interface ReportType {
    StringBuilder generateReport(ArrayList<StudentProfile> profilesList);
}
