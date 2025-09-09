import java.io.*;

public class IncidentLogger {
    private static final String LOG_FILE = "incident_logs.txt";
    private static final String KB_FILE = "knowledge_base.txt";

    // Save incident to log file
    public static void log(Incident inc) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(inc.toLogString());
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write to log file: " + e.getMessage());
        }
    }

    // Save incident to knowledge base file
    public static void addToKnowledgeBase(Incident inc) {
        try (FileWriter fw = new FileWriter(KB_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Incident ID: " + inc.getId());
            out.println("Time: " + inc.getTime());
            out.println("Severity: " + inc.getSeverity());
            out.println("Type: " + inc.getType());
            out.println("Message: " + inc.getMessage());
            out.println("Status: " + inc.getStatus());
            out.println("Resolution: " + inc.getResolution());
            out.println("-------------------------------------------------");
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to update knowledge base: " + e.getMessage());
        }
    }
}
