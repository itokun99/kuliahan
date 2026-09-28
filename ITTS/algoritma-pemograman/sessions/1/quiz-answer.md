Week 1: Structured Work - Algorithm Design

1. Problem Overview

- Chosen Activity: Calculating the total cost when buying multiple units of an item with a fixed discount percentage.
- Goal: Calculate the final amount a customer has to pay after applying a percentage discount on the total purchase price.

2. Algorithm Definition

- Input:
  1. price_per_item: The price of a single unit (must be greater than 0).
  2. quantity: The number of items purchased (must be a positive integer).
  3. discount_percentage: The percentage discount to apply (e.g., 10 for 10%).

- Steps (Algorithm Procedure):
  1. Start
  2. Read input values for price_per_item, quantity, and discount_percentage.
  3. Calculate total price before discount:
     subtotal = price_per_item \* quantity
  4. Calculate discount amount:
     discount_amount = subtotal \* (discount_percentage / 100)
  5. Calculate final price to pay:
     final_total = subtotal - discount_amount
  6. Display final_total.
  7. Stop

- Expected Output:
  - final_total: The total price after deducting the calculated discount.

3. Verification & Analysis

- Are the steps clear?
  Yes, each calculation and operation is defined in a clear sequential order.
- Does the algorithm stop?
  Yes, the steps follow a linear path from Start (Step 1) to Stop (Step 7) without infinite loops or undefined paths.

4. Test Case & Trace

- Input Values:
  - price_per_item = Rp 50,000
  - quantity = 3
  - discount_percentage = 10%

- Step-by-Step Execution:
  1. Read inputs: price_per_item = 50,000; quantity = 3; discount_percentage = 10.
  2. Compute subtotal:
     subtotal = 50,000 \* 3 = 150,000
  3. Compute discount amount:
     discount_amount = 150,000 \* (10 / 100) = 15,000
  4. Compute final total:
     final_total = 150,000 - 15,000 = 135,000
  5. Output result: Rp 135,000

- Result Evaluation:
  Yes, the calculated result (Rp 135,000) matches expectations. Buying 3 items at Rp 50,000 each equals Rp 150,000, and deducting a 10% discount (Rp 15,000) leaves Rp 135,000.
