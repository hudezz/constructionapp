package capstone;

import java.util.Scanner;

/**
 * Entry point for the itemized construction estimator.
 *
 * Run it with no arguments to enter your own project, or with {@code --demo}
 * for the worked example.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("--- Itemized Construction Estimator ---");

        boolean demo = args.length > 0 && args[0].equalsIgnoreCase("--demo");

        ProjectEstimator project = demo ? buildDemoProject() : buildProjectFromInput();
        if (project == null) {
            System.out.println("Nothing to estimate. Exiting.");
            return;
        }

        printReport(project);
    }

    /**
     * Asks for the project, its materials and its labour, one line at a time.
     * Returns null if standard input runs out before a project is named, which
     * is what happens when the program is run with no console attached.
     */
    private static ProjectEstimator buildProjectFromInput() {
        Scanner in = new Scanner(System.in);

        System.out.println("(Press Enter on an empty line to finish each list, or run with --demo for an example.)\n");

        System.out.print("Project name: ");
        if (!in.hasNextLine()) {
            return null;
        }
        String projectName = in.nextLine().trim();
        if (projectName.isEmpty()) {
            projectName = "Unnamed project";
        }

        double landSize = readPositiveDouble(in, "Land size (sq ft): ");

        ProjectEstimator project = new ProjectEstimator(projectName, landSize);

        System.out.println("\n-- Materials --");
        while (true) {
            System.out.print("Material name (blank to stop): ");
            if (!in.hasNextLine()) {
                break;
            }
            String name = in.nextLine().trim();
            if (name.isEmpty()) {
                break;
            }
            double unitCost = readPositiveDouble(in, "  Cost per unit: ");
            double quantity = readPositiveDouble(in, "  Quantity: ");
            project.addMaterial(new Material(name, unitCost, quantity));
        }

        System.out.println("\n-- Labour --");
        while (true) {
            System.out.print("Task name (blank to stop): ");
            if (!in.hasNextLine()) {
                break;
            }
            String task = in.nextLine().trim();
            if (task.isEmpty()) {
                break;
            }
            double hourlyRate = readPositiveDouble(in, "  Hourly rate: ");
            double hours = readPositiveDouble(in, "  Estimated hours: ");
            project.addLabourItem(new LabourItem(task, hourlyRate, hours));
        }

        return project;
    }

    /** Keeps asking until the answer is a number that is not negative. */
    private static double readPositiveDouble(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!in.hasNextLine()) {
                return 0.0;
            }
            String line = in.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (value < 0) {
                    System.out.println("  That cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a number, for example 150 or 2.5.");
            }
        }
    }

    /** The worked example this program used to run unconditionally. */
    private static ProjectEstimator buildDemoProject() {
        ProjectEstimator project = new ProjectEstimator("Riverside Home Build", 2500.0);

        project.addMaterial(new Material("Concrete (cubic yards)", 150.0, 45.0));
        project.addMaterial(new Material("Lumber (board feet)", 2.50, 8000.0));
        project.addMaterial(new Material("Roof shingles (packs)", 35.0, 150.0));

        project.addLabourItem(new LabourItem("Foundation crew", 45.0, 160.0));
        project.addLabourItem(new LabourItem("Framing team", 65.0, 320.0));
        project.addLabourItem(new LabourItem("Plumbing/HVAC", 75.0, 80.0));

        return project;
    }

    /** Prints the itemized breakdown and the final quote. */
    private static void printReport(ProjectEstimator project) {
        String rule = "-".repeat(56);

        System.out.println("\n" + rule);
        System.out.println("Project: " + project.getProjectName());
        System.out.printf("Size: %,.0f sq ft%n", project.getLandSizeSqFt());
        System.out.println(rule);

        if (!project.getMaterials().isEmpty()) {
            System.out.println("MATERIALS");
            for (Material m : project.getMaterials()) {
                System.out.printf("  %-32s %,10.2f%n", truncate(m.getName()), m.calculateTotalCost());
            }
        }

        if (!project.getLabourItems().isEmpty()) {
            System.out.println("LABOUR");
            for (LabourItem l : project.getLabourItems()) {
                System.out.printf("  %-32s %,10.2f%n", truncate(l.getTask()), l.calculateTotalCost());
            }
        }

        System.out.println(rule);
        printTotal("Total material cost", project.calculateTotalMaterialCost());
        printTotal("Total labour cost", project.calculateTotalLabourCost());
        printTotal(String.format("Overhead (%.0f%%)", ProjectEstimator.OVERHEAD_RATE * 100),
                project.calculateOverhead());
        printTotal(String.format("Contingency ($%.0f/sq ft)", ProjectEstimator.CONTINGENCY_PER_SQ_FT),
                project.calculateContingency());
        System.out.println(rule);
        printTotal("FINAL ESTIMATED QUOTE", project.calculateFinalEstimate());
        System.out.println(rule);
    }

    private static void printTotal(String label, double amount) {
        System.out.printf("%-26s $%,12.2f%n", label + ":", amount);
    }

    private static String truncate(String text) {
        return text.length() <= 32 ? text : text.substring(0, 29) + "...";
    }
}
