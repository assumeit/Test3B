package com.example.pizzaasmit1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillCalculatorTest {



    @Test
    public void testCalculateTotalBill_L() {
        double result = BillCalculator.calculateTotalBill("L", 2);
        assertEquals(17.25, result, 0.001);
    }





    @Test
    public void testCalculateTotalBill_XL_MaxToppings() {
        double result = BillCalculator.calculateTotalBill("XL", 20);
        assertEquals(51.75, result, 0.001);
    }

    @Test
    public void testCalculateTotalBill_L_MaxToppings() {
        double result = BillCalculator.calculateTotalBill("L", 20);
        assertEquals(48.3, result, 0.001);
    }

    @Test
    public void testCalculateTotalBill_M_MaxToppings() {
        double result = BillCalculator.calculateTotalBill("M", 20);
        assertEquals(46.0, result, 0.001);
    }

    @Test
    public void testCalculateTotalBill_S_MaxToppings() {
        double result = BillCalculator.calculateTotalBill("S", 20);
        assertEquals(43.7, result, 0.001);
    }
    @Test
    public void testCalculateTotalBill_NegativeToppings() {
        assertThrows(IllegalArgumentException.class, () -> {
            BillCalculator.calculateTotalBill("M", -1);
        });
    }
    @Test
    public void testCalculateTotalBill_NonStandardSize() {
        assertThrows(IllegalArgumentException.class, () -> {
            BillCalculator.calculateTotalBill("XXL", 2);
        });
    }

    @Test
    public void testCalculateTotalBill_InvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> {
            BillCalculator.calculateTotalBill("Invalid", 3);
        });
    }
}
