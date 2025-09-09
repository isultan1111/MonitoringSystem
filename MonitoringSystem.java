import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Main Monitoring System
public class MonitoringSystem {
    public static void main(String[] args) {
        ServiceSimulator service = new ServiceSimulator();
        AlertManager alertManager = new AlertManager(500); // threshold: 500 ms
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable monitorTask = () -> {
            long start = System.currentTimeMillis();
            Exception ex = null;
            String result = null;
            try {
                result = service.handleRequest();
            } catch (Exception e) {
                ex = e;
            }
            long resp = System.currentTimeMillis() - start;
            long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            Metrics m = new Metrics(resp, usedMemory);
            System.out.println("[METRICS] " + m);
            alertManager.evaluateAndAlert(result, resp, ex);
        };

        scheduler.scheduleAtFixedRate(monitorTask, 0, 5, TimeUnit.SECONDS);

        // ✅ use try-with-resources so scanner is always closed
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Monitoring started. Type 'kb <keyword>' to search knowledge base, 'listlogs' to show logs, 'exit' to stop.");
            while (true) {
                String line = scanner.nextLine();
                if (line == null) continue;
                line = line.trim();
                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Shutting down...");
                    scheduler.shutdown();
                    break;
                } else if (line.startsWith("kb ")) {
                    String kw = line.substring(3).trim();
                    List<String> res = KnowledgeBase.search(kw);
                    System.out.println("Found " + res.size() + " results:");
                    for (String r : res) {
                        System.out.println(r);
                        System.out.println("-------------------------------------------------");
                    }
                } else if (line.equalsIgnoreCase("listlogs")) {
                    printFile("incident_logs.txt");
                } else if (line.equalsIgnoreCase("help")) {
                    System.out.println("Commands: kb <keyword>, listlogs, exit, help");
                } else {
                    System.out.println("Unknown command. Type 'help'.");
                }
            }
        }
    }

    // ✅ Utility method placed inside the class but outside main
    public static void printFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("[INFO] No logs found yet.");
        }
    }
}
