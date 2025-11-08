import com.raytracer.core.geometry.AbstractVec3;
import com.raytracer.core.imaging.Color;
import com.raytracer.core.geometry.Point;
import com.raytracer.core.geometry.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractVec3Test {

    @Test
    void addition() {
        Vector addition = AbstractVec3.addition(
                new Vector(1.0,1.0,1.0),
                new Vector(1.0,1.0,1.0)
        );
        assertEquals(new Vector(2.0,2.0,2.0).toString(), addition.toString());

        Point addition2 =  AbstractVec3.addition(
                new Point(1.0, 1.0, 1.0, new Color()),
                new Vector(2.0, 2.0, 2.0)
        );
        assertEquals(new Point(3.0, 3.0, 3.0, new Color()).toString(), addition2.toString());
    }

    @Test
    void subtraction() {
        Point subtract = AbstractVec3.subtraction(
                new Point(1.0, 1.0, 1.0, new Color()),
                new Vector(2.0, 2.0, 2.0)
        );
        assertEquals(new Point(-1.0, -1.0, -1.0, new Color()).toString(), subtract.toString());

        Vector subtract2 = AbstractVec3.subtraction(
                new Point(5.0, 5.0, 5.0, new Color()),
                new Point(3.0, 3.0, 3.0, new Color())
        );
        assertEquals(new Vector(2.0, 2.0, 2.0).toString(), subtract2.toString());

        Vector subtract3 = AbstractVec3.subtraction(
                new Vector(6.0, 6.0, 6.0),
                new Vector(2.0, 2.0, 2.0)
        );
        assertEquals(new Vector(4.0, 4.0, 4.0).toString(), subtract3.toString());
    }

    @Test
    void scalarMultiplication() {
        Point scalar = AbstractVec3.scalarMultiplication(
                new Point(5.0, 5.0, 5.0, new Color()),
                2
        );
        assertEquals(new Point(10.0, 10.0, 10.0, new Color()).toString(), scalar.toString());

        Vector scalar2 = AbstractVec3.scalarMultiplication(
                new Vector(6.21, 6.125, 6.0),
                2
        );
        assertEquals(new Vector(12.42, 12.250, 12.0).toString(), scalar2.toString());
    }

    @Test
    void scalarProduct() {
        double scalar = AbstractVec3.scalarProduct(
                new Vector(6.0, 6.0, 6.0),
                new Vector(2.0, 2.0, 2.0)
        );
        assertEquals(36.0, scalar);
    }

    @Test
    void vectorProduct() {
        Vector product = AbstractVec3.vectorProduct(
                new Vector(3.0, 3.0, 3.0),
                new Vector(5.0, 5.0, 5.0)
        );
        assertEquals(new Vector(0.0, 0.0, 0.0).toString(), product.toString());
    }

    @Test
    void schurProduct() {
        Vector schur = AbstractVec3.schurProduct(
                new Vector(1.0, 2.0, 3.0),
                new Vector(4.0, 5.0, 6.0)
        );
        assertEquals(new Vector(4.0, 10.0, 18.0).toString(), schur.toString());
    }

    @Test
    void length() {
        double calc = AbstractVec3.length(
                new Vector(1.0, 2.0, 3.0)
        );
        assertEquals(Math.sqrt(14.0), calc);
    }

    @Test
    void normalize() {
        Vector normal =  AbstractVec3.normalize(
                new Vector(1.0, 1.0, 1.0)
        );
        assertEquals(new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258).toString(), normal.toString());
    }
}