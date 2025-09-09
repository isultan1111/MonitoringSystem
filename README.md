## Overview

The Monitoring System continuously simulates service requests at a fixed interval, collects metrics such as **response time** and **memory usage**, and generates **alerts** for:

- Performance issues (slow responses)  
- Critical exceptions (e.g., database failures)  

All incidents are **logged** and stored in a **knowledge base**, allowing users to search and review historical problems.  

---

## Features

- **Simulated Service Requests:** Randomly generates normal, slow, or failing requests.  
- **Metrics Collection:** Captures response time and memory usage for every request.  
- **Alert Management:** Automatically generates alerts for exceptions and slow responses.  
- **Incident Logging:** All alerts are saved in `incident_logs.txt`.  
- **Knowledge Base:** Stores critical incidents in `knowledge_base.txt` for future reference.  
- **Console Commands:** Interactive console interface to check logs or search the knowledge base.  

---

## Architecture

1. **MonitoringSystem.java** – Main class that schedules periodic service monitoring and handles user commands.  
2. **ServiceSimulator.java** – Simulates service responses and occasional failures.  
3. **AlertManager.java** – Evaluates metrics and generates alerts for exceptions or performance issues.  
4. **Incident.java** – Represents an incident with details such as type, severity, status, and resolution.  
5. **IncidentLogger.java** – Logs incidents to files and updates the knowledge base.  
6. **Metrics.java** – Stores response time and memory usage metrics.  
7. **KnowledgeBase.java** – Allows searching of past incidents using keywords.  

---

## How to Run

```bash
# Compile all Java files
javac *.java

# Run the monitoring system
java MonitoringSystem