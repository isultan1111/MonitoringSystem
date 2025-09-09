import java.util.Random;


public class ServiceSimulator {
private final Random rand = new Random();


// Simulates handling a request; sometimes slow, sometimes throws exceptions
public String handleRequest() throws Exception {
int r = rand.nextInt(100);
if (r < 10) {
// 10% chance of critical exception
throw new RuntimeException("Database connection failed");
} else if (r < 30) {
// 20% chance of slow response
Thread.sleep(800 + rand.nextInt(700)); // ~800-1500 ms
return "SLOW_RESPONSE";
} else {
Thread.sleep(50 + rand.nextInt(100));
return "OK";
}
}
}