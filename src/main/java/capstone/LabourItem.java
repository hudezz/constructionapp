package capstone;

/** A labour line item: an hourly rate and the hours the task is expected to take. */
public class LabourItem {

    private final String task;
    private final double hourlyRate;
    private final double estimatedHours;

    public LabourItem(String task, double hourlyRate, double estimatedHours) {
        if (task == null || task.isBlank()) {
            throw new IllegalArgumentException("task must not be blank");
        }
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("hourlyRate must not be negative");
        }
        if (estimatedHours < 0) {
            throw new IllegalArgumentException("estimatedHours must not be negative");
        }
        this.task = task;
        this.hourlyRate = hourlyRate;
        this.estimatedHours = estimatedHours;
    }

    /** Hourly rate times estimated hours. */
    public double calculateTotalCost() {
        return hourlyRate * estimatedHours;
    }

    public String getTask() {
        return task;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    @Override
    public String toString() {
        return String.format("%s: %,.2f h x %,.2f = %,.2f", task, estimatedHours, hourlyRate, calculateTotalCost());
    }
}
