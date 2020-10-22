/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import Elements.*;
import java.awt.Color;
import Renderer.ImageWriter;
import Renderer.Render;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class ImageWriterTest{

    @Test 

	public void SuperSamplingTest(){

		
		Scene scene = new Scene(); // create a new scene
                scene.setScreenDistance(20); //set the screendistance to 50
                scene.setBackGround(Color.WHITE);
                
		
		scene.addGeometry(new Sphere( new Point3D(0.0, 0.0, -149),50,new Material(),(Color.BLACK))); // add a sphere
		               /*
                //add the 4 triangles
		Triangle triangle = new Triangle(new Point3D( 100, 0, -149), 
				 						 new Point3D(  0, 100, -149),
				 						 new Point3D( 100, 100, -149), new Material(),
				 						 new Color(0,255,0));
		
		Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
				 			 			  new Point3D(  0, -100, -149),
				 			 			  new Point3D( 100,-100, -149),new Material(),
					 						 new Color(0,0,255));
		
		Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
				 						  new Point3D(  0, 100, -149),
				 						  new Point3D(-100, 100, -149),new Material(),
				 						  new Color(255,255,0));
		
		Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
				 			 			  new Point3D(  0,  -100, -149),
				 			 			  new Point3D(-100, -100, -149),new Material(),
					 						 new Color(255,0,255));
		
		scene.addGeometry(triangle); //add the triangles to teh scene
		scene.addGeometry(triangle2);
		scene.addGeometry(triangle3);
		scene.addGeometry(triangle4);
		
*/
		ImageWriter imageWriter = new ImageWriter("Tests32", 500, 500, 500, 500); //create the file
		
		Render render = new Render(scene,imageWriter);
		
		render.RenderImage(); //render the image
		//render.printGrid(50);
		imageWriter.writeToimage(); // print the image
	}

    
    
}
