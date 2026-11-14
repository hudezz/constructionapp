package capstone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectEstimatorTest {

    private static final double TOLERANCE = 0.000001;

    @Test
    void materialCostIsUnitCostTimesQuantity() {
        Material concrete = new Material("Concrete", 150.0, 45.0);
        assertEquals(6750.0, concrete.calculateTotalCost(), TOLERANCE);
    }

    @Test
    void labourCostIsRateTimesHours() {
        LabourItem crew = new LabourItem("Foundation crew", 45.0, 160.0);
        assertEquals(7200.0, crew.calculateTotalCost(), TOLERANCE);
    }

    @Test
    void subtotalAddsUpEveryLineItem() {
        ProjectEstimator project = new ProjectEstimator("Test", 100.0);
        project.addMaterial(new Material("Concrete", 150.0, 45.0));   // 6750
        project.addLabourItem(new LabourItem("Crew", 45.0, 160.0));   // 7200

        assertEquals(6750.0, project.calculateTotalMaterialCost(), TOLERANCE);
        assertEquals(7200.0, project.calculateTotalLabourCost(), TOLERANCE);
        assertEquals(13950.0, project.calculateSubtotal(), TOLERANCE);
    }

    @Test
    void finalEstimateAddsOverheadAndContingency() {
        ProjectEstimator project = new ProjectEstimator("Test", 100.0);
        project.addMaterial(new Material("Concrete", 100.0, 10.0));   // 1000

        // 1000 subtotal + 15% overhead + 100 sq ft x $10 contingency
        assertEquals(150.0, project.calculateOverhead(), TOLERANCE);
        assertEquals(1000.0, project.calculateContingency(), TOLERANCE);
        assertEquals(2150.0, project.calculateFinalEstimate(), TOLERANCE);
    }

    @Test
    void anEmptyProjectStillChargesContingency() {
        ProjectEstimator project = new ProjectEstimator("Empty", 50.0);

        assertEquals(0.0, project.calculateSubtotal(), TOLERANCE);
        assertEquals(500.0, project.calculateFinalEstimate(), TOLERANCE);
    }

    @Test
    void lineItemListsCannotBeModifiedFromOutside() {
        ProjectEstimator project = new ProjectEstimator("Test", 10.0);
        project.addMaterial(new Material("Concrete", 1.0, 1.0));

        assertThrows(UnsupportedOperationException.class,
                () -> project.getMaterials().add(new Material("Sneaky", 1.0, 1.0)));
        assertTrue(project.getLabourItems().isEmpty());
    }

    @Test
    void negativeAndBlankValuesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Material("", 1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Material("Concrete", -1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new LabourItem("Crew", 1.0, -1.0));
        assertThrows(IllegalArgumentException.class, () -> new ProjectEstimator("Test", -1.0));
    }
}
