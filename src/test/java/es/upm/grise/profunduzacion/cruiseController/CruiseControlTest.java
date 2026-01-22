package es.upm.grise.profunduzacion.cruiseController;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CruiseControlTest {

    private CruiseControl cruiseControl;

    @BeforeEach
    void setUp() {
        cruiseControl = new CruiseControl();
    }

    // ===== CASO 1: speedSet válida (> 0) =====
    @Test
    void testSetSpeedSetValid() throws Exception {
        cruiseControl.setSpeedSet(80);
        assertEquals(80, cruiseControl.getSpeedSet());
    }

    // ===== CASO 2: speedSet = 0 → excepción =====
    @Test
    void testSetSpeedSetZeroThrowsException() {
        assertThrows(IncorrectSpeedSetException.class, () -> {
            cruiseControl.setSpeedSet(0);
        });
    }

    // ===== CASO 3: speedSet negativa → excepción =====
    @Test
    void testSetSpeedSetNegativeThrowsException() {
        assertThrows(IncorrectSpeedSetException.class, () -> {
            cruiseControl.setSpeedSet(-10);
        });
    }

    // ===== CASO 4: speedSet > speedLimit → excepción =====
    @Test
    void testSpeedSetAboveSpeedLimitThrowsException() throws Exception {
        cruiseControl.setSpeedLimit(90);

        assertThrows(SpeedSetAboveSpeedLimitException.class, () -> {
            cruiseControl.setSpeedSet(100);
        });
    }

    // ===== CASO 5: speedSet <= speedLimit → válido =====
    @Test
    void testSpeedSetBelowSpeedLimitIsValid() throws Exception {
        cruiseControl.setSpeedLimit(120);
        cruiseControl.setSpeedSet(100);

        assertEquals(100, cruiseControl.getSpeedSet());
    }
}
