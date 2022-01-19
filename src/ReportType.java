import java.util.ArrayList;

//Interface for type of the report for report factory
public interface ReportType {
    StringBuilder generateReport(ArrayList<StudentProfile> profilesList);
}
