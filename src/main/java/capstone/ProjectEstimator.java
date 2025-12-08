package capstone;

import java.util.ArrayList;
import java.util.List; //

public class ProjectEstimator {

    // 1. FIELDS (Correct)
    private String projectName;
    private Double landSizeSqFt;
    private List<Material> materials = new ArrayList<>();
    private List<LabourItem> labourItems = new ArrayList<>();

    // 2. CONSTRUCTOR (Corrected)
    public ProjectEstimator (String projectName, Double landSizeSqFt){
        this.projectName = projectName;
        this.landSizeSqFt = landSizeSqFt;
    }

    // 3. MANAGEMENT METHODS
    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void addLabourItem(LabourItem labourItem) {
        labourItems.add(labourItem);
    }


    // 4. CALCULATION METHODS

    public double calculateTotalMaterialCost() {
        double totalCost = 0.0;
        for (Material m : materials) {
            totalCost += m.calculateTotalCost();
        }
        return totalCost;
    }

    // calculateTotalLabourCost()
    public double calculateTotalLabourCost() {
        double totalCost = 0.0;
        for (LabourItem l : labourItems) {
            totalCost += l.calculateTotalCost();
        }
        return totalCost; // : Added return statement***
    }

    // calculateFinalEstimate() (The grand total method)
    public double calculateFinalEstimate() {

        // Step 1: Calculate the Subtotal (Direct Costs)
        // Call the aggregation methods already defined in this class!
        double subtotal = calculateTotalMaterialCost() + calculateTotalLabourCost();

        // Step 2: Defining Business Logic Costs (15% Overhead and $10/sq ft Contingency)
        double overhead = subtotal * 0.15; // 15% of the direct costs for profit/admin
        double contingency = 10.0 * landSizeSqFt; // $10 per square foot risk fee

        // Step 3: Calculate the Final Estimate
        return subtotal + overhead + contingency;
    }

    // 5. GETTERS (Corrected)
    public String getProjectName() {
        return projectName;
    }

    public double getLandSizeSqFt() {
        return landSizeSqFt;
    }
}