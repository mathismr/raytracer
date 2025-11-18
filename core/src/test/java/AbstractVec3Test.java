import com.raytracer.core.geometry.AbstractVec3;
import com.raytracer.core.imaging.Color;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractVec3Test {

    @Test
    void addition() {
        AbstractVec3 addition = new Vector(1.0, 1.0, 1.0).addition(new Vector(1.0, 1.0, 1.0));
        AbstractVec3 addition2 = new Point(1.0, 1.0, 1.0).addition(new Vector(2.0, 2.0, 2.0));
        assertEquals(new Vector(2.0,2.0,2.0).toString(), addition.toString());
        assertEquals(new Point(3.0, 3.0, 3.0).toString(), addition2.toString());
    }

    @Test
    void subtraction() {
        AbstractVec3 subtract = new Point(1.0, 1.0, 1.0).subtraction(new Vector(2.0, 2.0, 2.0));
        AbstractVec3 subtract2 = new Vector(4.0, 4.0, 4.0).subtraction(new Vector(2.0, 2.0, 2.0));
        AbstractVec3 subtract3 = new Point(6.0, 6.0, 6.0).subtraction(new Vector(4.0, 4.0, 4.0));
        assertEquals(new Point(-1.0, -1.0, -1.0).toString(), subtract.toString());
        assertEquals(new Vector(2.0, 2.0, 2.0).toString(), subtract2.toString());
        assertEquals(new Point(2.0, 2.0, 2.0).toString(), subtract3.toString());
    }

    @Test
    void scalarMultiplication() {
        AbstractVec3 scalar = new Point(1.0, 1.0, 1.0).scalarMultiplication(10.0);
        AbstractVec3 scalar2 = new Vector(1.0, 1.0, 1.0).scalarMultiplication(12.0);
        assertEquals(new Point(10.0, 10.0, 10.0).toString(), scalar.toString());
        assertEquals(new Vector(12.0, 12.0, 12.0).toString(), scalar2.toString());
    }

    @Test
    void scalarProduct() {
        double scalar = new Vector(1.0, 1.0, 1.0).scalarProduct(new Vector(2.0, 2.0, 2.0));
        assertEquals(6.0, scalar);
    }

    @Test
    void vectorProduct() {
        AbstractVec3 product = new Vector(3.0, 3.0, 3.0).vectorProduct(new Vector(2.0, 2.0, 2.0));
        assertEquals(new Vector(0.0, 0.0, 0.0).toString(), product.toString());
    }

    @Test
    void schurProduct() {
        AbstractVec3 schur = new Vector(3.0, 3.0, 3.0).schurProduct(new Vector(2.0, 2.0, 2.0));
        assertEquals(new Vector(6.0, 6.0, 6.0).toString(), schur.toString());
    }

    @Test
    void length() {
        double length = new Vector(1.0, 2.0, 3.0).length();
        assertEquals(Math.sqrt(14.0), length);
    }

    @Test
    void normalize() {
        AbstractVec3 normal = new Vector(1.0, 1.0, 1.0).normalize();
        assertEquals(new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258).toString(), normal.toString());
    }
}