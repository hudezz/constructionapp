package capstone;
public class Material {

    //fields describing what were naming in this class

    private String itemname;
    private Double unitCost;
    private Double quantity;

    //constructor
    public Material (String name, double unitCost, double quantity) {
        this.itemname = name;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    //methods used this here are to calculate costs
    public double calculateTotalCost() {
        return unitCost * quantity;
    }


    //getter here were gonna now make it able to display the materilas we classified as private in the fileds

    public String getName() {
        return itemname;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double  getQuantity() {
        return quantity;
    }

}