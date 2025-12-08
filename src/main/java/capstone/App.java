package capstone;

// We need the Scanner class to take input, and the List utility if we had more complex I/O,
// but for a simple run, we'll focus on the core classes.

public class App{

    public static void main(String[] args) {

        System.out.println("--- Starting Itemized Construction Estimator ---");

        // 1. Create a new ProjectEstimator object
        // Project Name, Land Size (e.g., 2500 sq ft)
        ProjectEstimator riversideProject = new ProjectEstimator("Riverside Home Build", 2500.0);

        // 2. Add Materials (Name, Unit Cost, Quantity)
        System.out.println("\nAdding Materials...");
        riversideProject.addMaterial(new Material("Concrete (Cubic Yards)", 150.0, 45.0)); // 45 units @ 150 each
        riversideProject.addMaterial(new Material("Lumber (Board Feet)", 2.50, 8000.0)); // 8000 units @ 2.50 each
        riversideProject.addMaterial(new Material("Roof Shingles (Packs)", 35.0, 150.0)); // 150 units @ 35 each

        // 3. Add Labor Items (Task, Hourly Rate, Estimated Hours)
        System.out.println("Adding Labour Items...");
        riversideProject.addLabourItem(new LabourItem("Foundation Crew", 45.0, 160.0)); // 160 hours @ 45/hr
        riversideProject.addLabourItem(new LabourItem("Framing Team", 65.0, 320.0));     // 320 hours @ 65/hr
        riversideProject.addLabourItem(new LabourItem("Plumbing/HVAC", 75.0, 80.0));     // 80 hours @ 75/hr

        // 4. Calculate and Display Results

        double totalMaterialCost = riversideProject.calculateTotalMaterialCost();
        double totalLabourCost = riversideProject.calculateTotalLabourCost();
        double finalEstimate = riversideProject.calculateFinalEstimate();

        System.out.println("\n-----------------------------------------");
        System.out.println("Project: " + riversideProject.getProjectName());
        System.out.println("Size: " + riversideProject.getLandSizeSqFt() + " sq ft");
        System.out.println("-----------------------------------------");

        // Display subtotals
        System.out.printf("Total Material Cost: $%,.2f%n", totalMaterialCost);
        System.out.printf("Total Labour Cost: $%,.2f%n", totalLabourCost);
        System.out.println("-----------------------------------------");

        // Display the Final Estimate
        System.out.printf("FINAL ESTIMATED QUOTE: $%,.2f%n", finalEstimate);
        System.out.println("-----------------------------------------");
    }
}