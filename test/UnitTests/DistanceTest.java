/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

/**
 *
 * @author Binyamin
 */
public class DistanceTest {
    	@Test
	public void testDistance() {
		Point3D p1 = new Point3D(3.0, 3.0, 3.0);
                Point3D p2 = new Point3D(3.0,3.0,3.0);
                
		assertEquals("Zero length failed", 0.0, p1.distance(p2), 1e-10); // test for zero length
                
                Point3D p3=new Point3D(0.0,0.0,0.0);
                double a =Math.sqrt(27);
                assertEquals("distance test failed",a,p1.distance(p3),1e-10); //test for a distance
}
}
