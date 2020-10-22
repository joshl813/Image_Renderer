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
public class PlaneTests {
    @Test
    public void testIntersections(){
       //test for no intersection
       Point3D a1 =new Point3D(-1.0,2.0,4.0);
       Point3D b1= new Point3D(-5.0, 3.3, -6.7);
       Point3D c1= new Point3D(54.3,1.0,0.0);
       Point3D P01 = new Point3D(1.0,1.0,1.0);
       //Plane p1 = new Plane(a1,b1,c1);
       Vector v1 =new Vector(0.0,0.0,0.0);
       Ray r1 = new Ray(P01,v1);
      // ArrayList<Point3D> intersections = p1.findIntersections(r1);
       //assertEquals("Failed under no intersection", true, intersections.isEmpty()); // test for no intersection
       
      // test for one intersection
       Point3D a2 =new Point3D(1.0,2.0,3.0);
       Point3D b2= new Point3D(2.0,5.0,6.0);
       Point3D c2= new Point3D(4.0,2.0,9.0);
      // Plane p2 = new Plane(a2,b2,c2);
       Point3D P02 = new Point3D(0.0,1.0,1.0);
       Vector v2 =new Vector(1.0,1.0,1.0);
       Ray r2 = new Ray(P02,v2);
       //ArrayList<Point3D> intersections1 = p2.findIntersections(r2);
       //assertEquals("Failed under 1 intersection", 1, intersections1.get(0).comapreTo(new Point3D(0.25,1.25,1.25))); // test for one intersection
       
       //test if line if ray is contained in plane, infinite (0) intersections
       Point3D a3= new Point3D(0.0,1.0,0.0);
       Point3D b3 = new Point3D(0.0,0.0,0.0);
       Point3D c3=new Point3D(3.0,0.0,-1.0);
       Point3D P03 =new Point3D(1.0,0.0,0.0);
    //   Plane p3 = new Plane(a3,b3,c3);
       Vector v3= new Vector(-3.0,0.0,1.0);
       Ray r3= new Ray(P03,v3);
     //  ArrayList<Point3D> intersections2 = p3.findIntersections(r3);
     // assertEquals("Failed under 1 intersection", true, intersections2.isEmpty()); // test for infinite intersections, which we count as 0


    }
}
