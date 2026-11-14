package capstone;

/** A material line item: a unit cost and how many units the project needs. */
public class Material {

    private final String name;
    private final double unitCost;
    private final double quantity;

    public Material(String name, double unitCost, double quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (unitCost < 0) {
            throw new IllegalArgumentException("unitCost must not be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must not be negative");
        }
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    /** Unit cost times quantity. */
    public double calculateTotalCost() {
        return unitCost * quantity;
    }

    public String getName() {
        return name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%s: %,.2f x %,.2f = %,.2f", name, quantity, unitCost, calculateTotalCost());
    }
}
