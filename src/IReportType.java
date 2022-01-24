import java.util.ArrayList;

//Interface for type of the report for report factory
public interface IReportType {
    StringBuilder generatedReport = null;
    ArrayList<StudentProfile> profilesList = null;


    StringBuilder generateReport();
}
