import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Incident {
private static int counter = 0;
private final int id;
private final String time;
private final String type;
private final String message;
private String resolution;
private String status;
private final String severity;


public Incident(String type, String message, String severity) {
this.id = ++counter;
this.time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
this.type = type;
this.message = message;
this.resolution = "";
this.status = "OPEN";
this.severity = severity;
}


public int getId() { return id; }
public String getTime() { return time; }
public String getType() { return type; }
public String getMessage() { return message; }
public String getResolution() { return resolution; }
public void setResolution(String res) { this.resolution = res; this.status = "CLOSED"; }
public String getStatus() { return status; }
public String getSeverity() { return severity; }


public String toLogString() {
return String.format("ID:%d | Time:%s | Severity:%s | Type:%s | Message:%s | Status:%s | Resolution:%s",
id, time, severity, type, message, status, resolution == null ? "" : resolution);
}
}