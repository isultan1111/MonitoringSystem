public class Metrics {
private final long responseTimeMs;
private final long usedMemoryBytes;


public Metrics(long responseTimeMs, long usedMemoryBytes) {
this.responseTimeMs = responseTimeMs;
this.usedMemoryBytes = usedMemoryBytes;
}


public long getResponseTimeMs() { return responseTimeMs; }
public long getUsedMemoryBytes() { return usedMemoryBytes; }


@Override
public String toString() {
return String.format("responseTime=%dms, usedMemory=%dKB", responseTimeMs, usedMemoryBytes / 1024);
}
}