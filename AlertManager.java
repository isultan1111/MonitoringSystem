public class AlertManager {
private final long responseTimeThresholdMs;


public AlertManager(long responseTimeThresholdMs) {
this.responseTimeThresholdMs = responseTimeThresholdMs;
}


public void evaluateAndAlert(String result, long responseTimeMs, Exception ex) {
if (ex != null) {
Incident inc = new Incident("EXCEPTION", ex.toString(), "CRITICAL");
System.err.println("[ALERT] CRITICAL: Exception detected: " + ex.getMessage());
IncidentLogger.log(inc);
IncidentLogger.addToKnowledgeBase(inc);
return;
}


if (responseTimeMs > responseTimeThresholdMs) {
Incident inc = new Incident("PERFORMANCE", "High response time: " + responseTimeMs + "ms", "MAJOR");
System.out.println("[ALERT] PERFORMANCE issue: responseTime=" + responseTimeMs + "ms");
IncidentLogger.log(inc);
} else {
System.out.println("[INFO] Service healthy. responseTime=" + responseTimeMs + "ms");
}
}
}