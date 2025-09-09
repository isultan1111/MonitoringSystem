import java.io.*;
import java.util.*;


public class KnowledgeBase {
private static final String KB_FILE = "knowledge_base.txt";


public static List<String> search(String keyword) {
List<String> results = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader(KB_FILE))) {
String line;
StringBuilder entry = new StringBuilder();
while ((line = br.readLine()) != null) {
if (line.equals("-------------------------------------------------")) {
String entryStr = entry.toString();
if (entryStr.toLowerCase().contains(keyword.toLowerCase())) {
results.add(entryStr);
}
entry.setLength(0);
} else {
entry.append(line).append("\n");
}
}
} catch (IOException e) {
// KB might not exist yet; return empty results
}
return results;
}
}