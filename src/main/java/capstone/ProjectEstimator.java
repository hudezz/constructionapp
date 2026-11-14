package capstone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * One construction project: its materials, its labour, and the costed total.
 *
 * The final quote is the direct costs (materials + labour), plus a percentage
 * for overhead, plus a flat contingency per square foot.
 */
public class ProjectEstimator {

    /** Share of direct costs added for profit and administration. */
    public static final double OVERHEAD_RATE = 0.15;

    /** Flat risk allowance charged per square foot of land. */
    public static final double CONTINGENCY_PER_SQ_FT = 10.0;

    private final String projectName;
    private final double landSizeSqFt;
    private final List<Material> materials = new ArrayList<>();
    private final List<LabourItem> labourItems = new ArrayList<>();

    public ProjectEstimator(String projectName, double landSizeSqFt) {
        if (landSizeSqFt < 0) {
            throw new IllegalArgumentException("landSizeSqFt must not be negative");
        }
        this.projectName = projectName;
        this.landSizeSqFt = landSizeSqFt;
    }

    public void addMaterial(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("material must not be null");
        }
        materials.add(material);
    }

    public void addLabourItem(LabourItem labourItem) {
        if (labourItem == null) {
            throw new IllegalArgumentException("labourItem must not be null");
        }
        labourItems.add(labourItem);
    }

    public double calculateTotalMaterialCost() {
        double totalCost = 0.0;
        for (Material m : materials) {
            totalCost += m.calculateTotalCost();
        }
        return totalCost;
    }

    public double calculateTotalLabourCost() {
        double totalCost = 0.0;
        for (LabourItem l : labourItems) {
            totalCost += l.calculateTotalCost();
        }
        return totalCost;
    }

    /** Materials + labour, before overhead and contingency. */
    public double calculateSubtotal() {
        return calculateTotalMaterialCost() + calculateTotalLabourCost();
    }

    public double calculateOverhead() {
        return calculateSubtotal() * OVERHEAD_RATE;
    }

    public double calculateContingency() {
        return CONTINGENCY_PER_SQ_FT * landSizeSqFt;
    }

    /** The quote: subtotal + overhead + contingency. */
    public double calculateFinalEstimate() {
        return calculateSubtotal() + calculateOverhead() + calculateContingency();
    }

    public String getProjectName() {
        return projectName;
    }

    public double getLandSizeSqFt() {
        return landSizeSqFt;
    }

    /** Unmodifiable, so the line items can only change through addMaterial. */
    public List<Material> getMaterials() {
        return Collections.unmodifiableList(materials);
    }

    /** Unmodifiable, so the line items can only change through addLabourItem. */
    public List<LabourItem> getLabourItems() {
        return Collections.unmodifiableList(labourItems);
    }
}
