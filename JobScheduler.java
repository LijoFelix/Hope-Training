import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// ==================== CUSTOM EXCEPTIONS ====================

/**
 * Custom exception thrown when a job is not found
 */
class JobNotFoundException extends Exception {
    public JobNotFoundException(String message) {
        super(message);
    }

    public JobNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Custom exception thrown when invalid job parameters are provided
 */
class InvalidJobException extends Exception {
    public InvalidJobException(String message) {
        super(message);
    }

    public InvalidJobException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Custom exception thrown when scheduler capacity is exceeded
 */
class SchedulerCapacityException extends Exception {
    public SchedulerCapacityException(String message) {
        super(message);
    }
}

/**
 * Custom exception thrown for job execution errors
 */
class JobExecutionException extends Exception {
    public JobExecutionException(String message) {
        super(message);
    }

    public JobExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}

// ==================== ENUMS ====================

/**
 * Priority levels for jobs
 */
enum JobPriority {
    CRITICAL(1, "Critical"),
    HIGH(2, "High"),
    MEDIUM(3, "Medium"),
    LOW(4, "Low");

    private final int level;
    private final String display;

    JobPriority(int level, String display) {
        this.level = level;
        this.display = display;
    }

    public int getLevel() {
        return level;
    }

    public String getDisplay() {
        return display;
    }
}

/**
 * Status of a job
 */
enum JobStatus {
    PENDING("Pending"),
    RUNNING("Running"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    private final String display;

    JobStatus(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}

// ==================== JOB INTERFACE & IMPLEMENTATION ====================

/**
 * Interface for executable jobs
 */
interface Executable {
    void execute() throws JobExecutionException;
}

/**
 * Abstract base class for jobs
 */
abstract class Job implements Executable, Comparable<Job> {
    protected String jobId;
    protected String jobName;
    protected JobPriority priority;
    protected JobStatus status;
    protected LocalDateTime createdAt;
    protected LocalDateTime startedAt;
    protected LocalDateTime completedAt;
    protected long executionTime; // in milliseconds
    protected String errorMessage;
    protected int retryCount;
    protected int maxRetries;

    public Job(String jobId, String jobName, JobPriority priority) 
            throws InvalidJobException {
        if (jobId == null || jobId.trim().isEmpty()) {
            throw new InvalidJobException("Job ID cannot be null or empty");
        }
        if (jobName == null || jobName.trim().isEmpty()) {
            throw new InvalidJobException("Job name cannot be null or empty");
        }
        if (priority == null) {
            throw new InvalidJobException("Priority cannot be null");
        }

        this.jobId = jobId;
        this.jobName = jobName;
        this.priority = priority;
        this.status = JobStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.retryCount = 0;
        this.maxRetries = 3;
        this.executionTime = 0;
    }

    /**
     * Compares jobs by priority (lower priority value = higher priority)
     */
    @Override
    public int compareTo(Job other) {
        if (this.priority.getLevel() != other.priority.getLevel()) {
            return Integer.compare(this.priority.getLevel(), other.priority.getLevel());
        }
        // If same priority, earlier creation time comes first
        return this.createdAt.compareTo(other.createdAt);
    }

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public JobPriority getPriority() {
        return priority;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) throws InvalidJobException {
        if (maxRetries < 0) {
            throw new InvalidJobException("Max retries cannot be negative");
        }
        this.maxRetries = maxRetries;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Priority: %s, Status: %s)",
                jobId, jobName, priority.getDisplay(), status.getDisplay());
    }
}

/**
 * Concrete implementation of a simple job
 */
class SimpleJob extends Job {
    private Runnable task;

    public SimpleJob(String jobId, String jobName, JobPriority priority, Runnable task) 
            throws InvalidJobException {
        super(jobId, jobName, priority);
        if (task == null) {
            throw new InvalidJobException("Task cannot be null");
        }
        this.task = task;
    }

    @Override
    public void execute() throws JobExecutionException {
        try {
            task.run();
        } catch (Exception e) {
            throw new JobExecutionException(
                    "Error executing job: " + jobName, e);
        }
    }
}

/**
 * Concrete implementation of a database job
 */
class DatabaseJob extends Job {
    private String query;

    public DatabaseJob(String jobId, String jobName, JobPriority priority, String query) 
            throws InvalidJobException {
        super(jobId, jobName, priority);
        if (query == null || query.trim().isEmpty()) {
            throw new InvalidJobException("Query cannot be null or empty");
        }
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public void execute() throws JobExecutionException {
        try {
            // Simulate database operation
            System.out.println("Executing database query: " + query);
            Thread.sleep(100); // Simulate execution time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new JobExecutionException("Database job interrupted: " + jobName, e);
        } catch (Exception e) {
            throw new JobExecutionException(
                    "Database job failed: " + jobName, e);
        }
    }
}

/**
 * Concrete implementation of an API job
 */
class ApiJob extends Job {
    private String endpoint;

    public ApiJob(String jobId, String jobName, JobPriority priority, String endpoint) 
            throws InvalidJobException {
        super(jobId, jobName, priority);
        if (endpoint == null || endpoint.trim().isEmpty()) {
            throw new InvalidJobException("Endpoint cannot be null or empty");
        }
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void execute() throws JobExecutionException {
        try {
            // Simulate API call
            System.out.println("Calling API endpoint: " + endpoint);
            Thread.sleep(150); // Simulate execution time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new JobExecutionException("API job interrupted: " + jobName, e);
        } catch (Exception e) {
            throw new JobExecutionException(
                    "API call failed: " + jobName, e);
        }
    }
}

// ==================== JOB SCHEDULER ====================

/**
 * Priority-based job scheduler with exception handling and retry mechanism
 */
class PriorityJobScheduler {
    private static final int DEFAULT_MAX_CAPACITY = 100;
    private static final int THREAD_POOL_SIZE = 3;

    private final PriorityQueue<Job> jobQueue;
    private final Map<String, Job> jobRegistry;
    private final ExecutorService executorService;
    private final int maxCapacity;
    private final Object lockObject = new Object();
    private boolean isRunning;
    private int totalJobsProcessed;
    private int totalJobsFailed;

    public PriorityJobScheduler() throws InvalidJobException {
        this(DEFAULT_MAX_CAPACITY);
    }

    public PriorityJobScheduler(int maxCapacity) throws InvalidJobException {
        if (maxCapacity <= 0) {
            throw new InvalidJobException("Max capacity must be greater than 0");
        }
        this.maxCapacity = maxCapacity;
        this.jobQueue = new PriorityQueue<>();
        this.jobRegistry = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        this.isRunning = true;
        this.totalJobsProcessed = 0;
        this.totalJobsFailed = 0;
    }

    /**
     * Submits a job to the scheduler
     */
    public synchronized void submitJob(Job job) 
            throws InvalidJobException, SchedulerCapacityException {
        if (job == null) {
            throw new InvalidJobException("Job cannot be null");
        }

        if (jobRegistry.containsKey(job.getJobId())) {
            throw new InvalidJobException(
                    "Job with ID " + job.getJobId() + " already exists");
        }

        synchronized (lockObject) {
            if (jobQueue.size() >= maxCapacity) {
                throw new SchedulerCapacityException(
                        "Scheduler capacity (" + maxCapacity + ") exceeded. " +
                        "Current queue size: " + jobQueue.size());
            }

            jobQueue.offer(job);
            jobRegistry.put(job.getJobId(), job);
        }

        System.out.println("✓ Job submitted: " + job);
    }

    /**
     * Cancels a job by ID
     */
    public synchronized void cancelJob(String jobId) throws JobNotFoundException {
        Job job = jobRegistry.get(jobId);
        if (job == null) {
            throw new JobNotFoundException("Job not found with ID: " + jobId);
        }

        synchronized (lockObject) {
            if (job.getStatus() == JobStatus.PENDING) {
                jobQueue.remove(job);
                job.setStatus(JobStatus.CANCELLED);
                System.out.println("✓ Job cancelled: " + jobId);
            } else {
                System.out.println("✗ Cannot cancel job with status: " + job.getStatus());
            }
        }
    }

    /**
     * Gets job details by ID
     */
    public Job getJobDetails(String jobId) throws JobNotFoundException {
        Job job = jobRegistry.get(jobId);
        if (job == null) {
            throw new JobNotFoundException("Job not found with ID: " + jobId);
        }
        return job;
    }

    /**
     * Processes jobs from the queue
     */
    public void processJobs() {
        if (!isRunning) {
            System.out.println("Scheduler is not running");
            return;
        }

        System.out.println("\n=== Starting Job Scheduler ===\n");

        while (isRunning && !jobQueue.isEmpty()) {
            Job job;
            synchronized (lockObject) {
                job = jobQueue.poll();
            }

            if (job != null) {
                executorService.submit(() -> executeJob(job));
            }
        }

        System.out.println("\n=== Job Scheduler Completed ===\n");
    }

    /**
     * Executes a single job with retry mechanism
     */
    private void executeJob(Job job) {
        try {
            job.setStatus(JobStatus.RUNNING);
            job.setStartedAt(LocalDateTime.now());

            System.out.println("► Executing: " + job);

            long startTime = System.currentTimeMillis();

            try {
                job.execute();
                job.setStatus(JobStatus.COMPLETED);
                job.setExecutionTime(System.currentTimeMillis() - startTime);
                totalJobsProcessed++;

                System.out.println("✓ Completed: " + job.getJobId() + 
                        " (Time: " + job.getExecutionTime() + "ms)");

            } catch (JobExecutionException e) {
                handleJobFailure(job, e);
            }

            job.setCompletedAt(LocalDateTime.now());

        } catch (Exception e) {
            System.err.println("✗ Unexpected error executing job " + job.getJobId() + 
                    ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles job failure with retry mechanism
     */
    private void handleJobFailure(Job job, JobExecutionException e) {
        System.err.println("✗ Job failed: " + job.getJobId() + " - " + e.getMessage());

        if (job.getRetryCount() < job.getMaxRetries()) {
            job.incrementRetryCount();
            System.out.println("⟳ Retrying job " + job.getJobId() + 
                    " (Attempt " + job.getRetryCount() + "/" + job.getMaxRetries() + ")");

            synchronized (lockObject) {
                jobQueue.offer(job);
                job.setStatus(JobStatus.PENDING);
            }
        } else {
            job.setStatus(JobStatus.FAILED);
            job.setErrorMessage(e.getMessage());
            totalJobsFailed++;
            System.err.println("✗ Job failed permanently: " + job.getJobId() + 
                    " after " + job.getMaxRetries() + " retries");
        }
    }

    /**
     * Stops the scheduler
     */
    public synchronized void shutdown() {
        isRunning = false;
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Scheduler shut down successfully");
    }

    /**
     * Gets queue statistics
     */
    public Map<String, Object> getStatistics() {
        synchronized (lockObject) {
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("Total Jobs Submitted", jobRegistry.size());
            stats.put("Pending Jobs", jobQueue.size());
            stats.put("Jobs Processed", totalJobsProcessed);
            stats.put("Jobs Failed", totalJobsFailed);
            stats.put("Scheduler Capacity", maxCapacity);
            stats.put("Capacity Usage %", 
                    String.format("%.2f%%", (jobQueue.size() * 100.0) / maxCapacity));
            return stats;
        }
    }

    /**
     * Lists all jobs by status
     */
    public Map<JobStatus, List<Job>> getJobsByStatus() {
        return jobRegistry.values().stream()
                .collect(Collectors.groupingBy(Job::getStatus));
    }

    /**
     * Displays scheduler report
     */
    public void displayReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCHEDULER REPORT");
        System.out.println("=".repeat(50));

        Map<String, Object> stats = getStatistics();
        stats.forEach((key, value) -> 
                System.out.printf("%-30s: %s%n", key, value));

        System.out.println("\n" + "-".repeat(50));
        System.out.println("JOBS BY STATUS:");
        System.out.println("-".repeat(50));

        getJobsByStatus().forEach((status, jobs) -> {
            System.out.println(status.getDisplay() + " (" + jobs.size() + "):");
            jobs.forEach(job -> System.out.println("  - " + job));
        });

        System.out.println("=".repeat(50) + "\n");
    }
}

// ==================== DEMO & TESTING ====================

public class JobScheduler {
    public static void main(String[] args) {
        try {
            // Create scheduler
            PriorityJobScheduler scheduler = new PriorityJobScheduler(50);

            System.out.println("╔═══════════════════════════════════════════════════╗");
            System.out.println("║   Priority Job Scheduler with OOP & Exceptions   ║");
            System.out.println("╚═══════════════════════════════════════════════════╝\n");

            // Submit jobs with different priorities
            createAndSubmitJobs(scheduler);

            // Process jobs
            scheduler.processJobs();

            // Display report
            scheduler.displayReport();

            // Demonstrate exception handling
            demonstrateExceptionHandling(scheduler);

            // Shutdown
            scheduler.shutdown();

        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createAndSubmitJobs(PriorityJobScheduler scheduler) 
            throws InvalidJobException, SchedulerCapacityException {
        
        System.out.println("Adding Jobs to Scheduler...\n");

        // Add high-priority jobs
        scheduler.submitJob(new SimpleJob("JOB-001", "Critical System Update", 
                JobPriority.CRITICAL, () -> System.out.println("  Executing critical update...")));

        scheduler.submitJob(new ApiJob("JOB-002", "Fetch User Data", 
                JobPriority.HIGH, "https://api.example.com/users"));

        scheduler.submitJob(new DatabaseJob("JOB-003", "Backup Database", 
                JobPriority.HIGH, "BACKUP DATABASE main_db TO 'backup.sql'"));

        // Add medium-priority jobs
        scheduler.submitJob(new SimpleJob("JOB-004", "Send Emails", 
                JobPriority.MEDIUM, () -> System.out.println("  Sending emails...")));

        scheduler.submitJob(new ApiJob("JOB-005", "Update Analytics", 
                JobPriority.MEDIUM, "https://api.example.com/analytics"));

        // Add low-priority jobs
        scheduler.submitJob(new SimpleJob("JOB-006", "Clean Temp Files", 
                JobPriority.LOW, () -> System.out.println("  Cleaning temp files...")));

        scheduler.submitJob(new DatabaseJob("JOB-007", "Log Cleanup", 
                JobPriority.LOW, "DELETE FROM logs WHERE created_at < NOW() - INTERVAL 30 DAY"));

        System.out.println();
    }

    private static void demonstrateExceptionHandling(PriorityJobScheduler scheduler) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EXCEPTION HANDLING DEMONSTRATION");
        System.out.println("=".repeat(50) + "\n");

        // 1. Try to submit null job
        try {
            scheduler.submitJob(null);
        } catch (InvalidJobException e) {
            System.out.println("✓ Caught InvalidJobException: " + e.getMessage());
        } catch (SchedulerCapacityException e) {
            System.out.println("✓ Caught SchedulerCapacityException: " + e.getMessage());
        }

        // 2. Try to get non-existent job
        try {
            scheduler.getJobDetails("NON-EXISTENT");
        } catch (JobNotFoundException e) {
            System.out.println("✓ Caught JobNotFoundException: " + e.getMessage());
        }

        // 3. Try to cancel non-existent job
        try {
            scheduler.cancelJob("NON-EXISTENT");
        } catch (JobNotFoundException e) {
            System.out.println("✓ Caught JobNotFoundException: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(50) + "\n");
    }
}