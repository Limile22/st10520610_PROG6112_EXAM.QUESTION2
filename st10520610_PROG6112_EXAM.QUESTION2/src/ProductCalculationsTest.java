

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    public class ProductCalculationsTest {

        @Test
        public void GetVat_WithValidData_ReturnsCorrectValue() {
// Arrange: price = 3500.0 -> VAT at 15% should be 525.0
            ProductCalculations calc = new ProductCalculations(3500.0);

// Act
            double actualVat = calc.GetVAT();

// Assert
            assertEquals(525.0, actualVat, 0.001);
        }

        @Test
        public void GetTotal_WithValidData_ReturnsCorrectValue() {
// Arrange: price = 3500.0 -> Total (Price + 15% VAT + 5% Commission) should be 4200.0
            ProductCalculations calc = new ProductCalculations(3500.0);

// Act
            double actualTotal = calc.GetTotal();

// Assert
            assertEquals(4200.0, actualTotal, 0.001);
        }
    }

