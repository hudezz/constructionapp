// Line 1: Package must be the first line
package capstone;

// Line 2: YOU MUST HAVE THIS LINE, followed by the opening brace {
public class LabourItem {

    // FIELDS, CONSTRUCTOR, and METHODS GO HERE

    // Fields
    private String task;
    private Double hourlyRate;
    private Double estimatedHours;

    // Constructor
    public LabourItem(String task, double hourlyRate, Double estimatedHours) {
        this.task = task;
        this.hourlyRate = hourlyRate;
        this.estimatedHours = estimatedHours;
    }

    // Method
    public double calculateTotalCost() {
        return hourlyRate * estimatedHours;
    }

    // Getters
    public String getTask() {
        return task;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public Double getEstimatedHours() {
        return estimatedHours;
    }

} // Line XX: The final closing brace for the class