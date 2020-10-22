/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import geometries.*;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * @author Binyamin
 */
public class TriangleTests {
    @Test
    public void testIntersections(){
        //test zero intersection
        Point3D p1 = new Point3D(0.0,1.0,-2.0);
        Point3D p2= new Point3D(1.0,-1.0,-2.0);
        Point3D p3= new Point3D (-1.0,-1.0,-2.0);
        //Triangle T1 = new Triangle(p1,p2,p3);
        Point3D P01 = new Point3D(5.0,5.0,5.0);
        Vector v1 =new Vector(1.0,1.0,1.0);
        Ray r1 = new Ray(P01,v1);
      // ArrayList<Point3D> intersections = T1.findIntersections(r1);
    //   assertEquals("Failed under no intersection", true, intersections.isEmpty()); // test for no intersection
        
        //test one intersection
         p1 = new Point3D(0.0,0.0,0.0);
         p2= new Point3D(1.0,0.0,0.0);
         p3= new Point3D (1.0,1.0,0.0);
         //T1 = new Triangle(p1,p2,p3);
         P01 = new Point3D(0.0,0.5,0.0);
         v1 =new Vector(1.0,1.0,1.0);
         r1 = new Ray(P01,v1);
         //intersections = T1.findIntersections(r1);
         //assertEquals("Failed under 1 intersection", 1, intersections.get(0).comapreTo(new Point3D(0.0,0.5,0.0))); // test for one intersection
         
         // test for 2 intersections
    }
    
}
