# Itemized Construction Estimator

A small Java command-line tool that costs a construction project. You enter the
materials and the labour, and it prints an itemized breakdown and a final quote.

The quote is:

```
materials + labour            (direct costs)
+ 15% of direct costs         (overhead: profit and administration)
+ $10 per square foot         (contingency: risk allowance)
= final estimated quote
```

## Running it

Needs JDK 17 or newer.

```bash
mvn compile
mvn exec:java                       # enter your own project
mvn exec:java -Dexec.args=--demo    # the worked example
```

Or without Maven:

```bash
javac -d out $(find src/main/java -name '*.java')
java -cp out capstone.App --demo
```

### Entering a project

It asks for a name and a land size, then for materials and labour, one line item
at a time. Press Enter on an empty name to finish a list. Anything that isn't a
number is rejected and asked for again, and negative values aren't accepted.

```
Project name: My House
Land size (sq ft): 1200

-- Materials --
Material name (blank to stop): Cement
  Cost per unit: 120
  Quantity: 30
Material name (blank to stop):

-- Labour --
Task name (blank to stop): Bricklaying
  Hourly rate: 50
  Estimated hours: 80
Task name (blank to stop):
```

```
--------------------------------------------------------
Project: My House
Size: 1,200 sq ft
--------------------------------------------------------
MATERIALS
  Cement                             3,600.00
LABOUR
  Bricklaying                        4,000.00
--------------------------------------------------------
Total material cost:       $    3,600.00
Total labour cost:         $    4,000.00
Overhead (15%):            $    1,140.00
Contingency ($10/sq ft):   $   12,000.00
--------------------------------------------------------
FINAL ESTIMATED QUOTE:     $   20,740.00
--------------------------------------------------------
```

## The classes

| Class | Holds |
|---|---|
| `Material` | A name, a cost per unit and a quantity. `calculateTotalCost()` multiplies them. |
| `LabourItem` | A task, an hourly rate and estimated hours. Same idea. |
| `ProjectEstimator` | The project: its line items, the subtotal, the overhead, the contingency and the final quote. |
| `App` | The command-line front end. |

The overhead rate and the contingency per square foot are the constants
`ProjectEstimator.OVERHEAD_RATE` and `ProjectEstimator.CONTINGENCY_PER_SQ_FT`.

## Tests

```bash
mvn test
```

`ProjectEstimatorTest` covers the line-item arithmetic, the overhead and
contingency, an empty project, and the input validation.
