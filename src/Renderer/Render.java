/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import Elements.LightSource;
import Elements.Scene;
import geometries.FlatGeometry;
import geometries.Geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.lang.Math;
import primitives.Coordinate;

/**
 *
 * @author Binyamin
 */

public class Render {
     Scene scene;
     ImageWriter imageWriter;
    
    //default constructor
    public Render(){
        scene=new Scene();
        imageWriter=new ImageWriter("",0,0,0,0);
    }
    //end of default contructor
    
    //copy constructor
    public Render(Render r){
        scene=r.scene;
        imageWriter=r.imageWriter;
    }
    //end of copy constructor
    
    //variable constructor
    public Render(Scene s, ImageWriter i){
        scene=s;
        imageWriter=i;
    }
    //end of variable constructor
    
    	public Color scaleColor(Color color, double factor) {
		int newRed = ((int)(color.getRed()*factor) <= 255 ? (int)(color.getRed()*factor) : 255);
		newRed = (newRed >= 0 ? newRed : 0);
		int newGreen = ((int)(color.getGreen()*factor) <= 255 ? (int)(color.getGreen()*factor) : 255);
		newGreen = (newGreen >= 0 ? newGreen : 0);
		int newBlue = ((int)(color.getBlue()*factor) <= 255 ? (int)(color.getBlue()*factor) : 255);
		newBlue = (newBlue >= 0 ? newBlue : 0);
		
		return new Color(newRed, newGreen, newBlue);
	}
        
        
    int SS =  6; //define the amount of subpixels per pixel for 
    //Renders the image from the intersection points
    public void RenderImage(){

        for(int i = 0; i < imageWriter.getWidth(); i++){ //traverse through the scene
            for(int j = 0; j < imageWriter.getHeight(); j++){
               Color color=new Color(0,0,0);
               int r=0;
               int b=0;
               int g=0;
         for(int row=0; row<SS;row++){
               for(int column=0;column<SS; column++){

            Ray ray = scene.getCamera().constructRayThroughPixel(imageWriter.getWidth(), imageWriter.getHeight(),i,j, scene.getScreenDistance(), imageWriter.getNx(), imageWriter.getNy(),column,row,SS);//define a ray
                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersectionPoints(ray); //find where the ray intersects the geometries

                if (intersectionPoints.isEmpty()){ //no intersection points     
                    imageWriter.writePixel(i, j, scene.getBackground()); //render and empty scene
                }
                
                else {
                    Map<Geometry, Point3D> closestPointMap = getClosestPoint(intersectionPoints, ray.POOgetter()); //find closest intersection
                    Geometry geometry=(Geometry)closestPointMap.keySet().toArray()[0]; //assign geometry to that cosest point
                    Point3D point = (Point3D) closestPointMap.values().toArray()[0];
                   Color color1=calcColor(geometry, point, ray); //calculates the color
                   r+=color1.getRed()+color.getRed();
                   b +=color1.getBlue()+color.getBlue();
                   g +=color1.getGreen()+color.getGreen();

		    

                }
            }
                    }
               color=new Color(r/(SS*SS),g/(SS*SS),b/(SS*SS)); //find the averagge
               imageWriter.writePixel(i, j,color ); //"writes" the closest point to the geometry
             }
 
       }

        
    }
    //end of RenderImage
    
      
         int RECURSION_LEVEL =3;

	private Color calcColor(Geometry geometry, Point3D point, Ray ray, int level) {
		
            if(level==RECURSION_LEVEL) return Color.BLACK; // test if reached the level of recursion
		int numberOfLights = scene.lights.size();
		//Grabs the ambient light from the scene
		Color ambientLight = scene.getAmbient().getIntensity();
		//Grabs the light coming out of the geometry itself
		Color emissionLight = geometry.getEmission();
		
		int totalRed = ambientLight.getRed() + emissionLight.getRed(); //calc total red
		int totalGreen = ambientLight.getGreen() + emissionLight.getGreen(); //total green
		int totalBlue = ambientLight.getBlue() + emissionLight.getBlue(); //total blue

		//Gets a list of all the lights in the place
		Iterator<LightSource> lights = scene.getLightsIterator();

		while(lights.hasNext()) {
			LightSource light = lights.next();

			//Check if not blocked
			if(Math.signum(light.getL(point).dotProduct(geometry.normal(point))) != Math.signum(new Vector(scene.getCamera().P0Getter().subtract(point)).dotProduct(geometry.normal(point)))) {
				//Checks to see if the point would even get hit by the LightSource (ie that it isn't in the shadow of something else)
				//Creates a new Ray starting at the point in question with a direction opposite the L from the light to the point (going back to the light)
                                Vector v= new Vector(light.getL(point));
                                v.scale(-1);
                                v.normal();
				Ray rayBackToLight = new Ray(point,v);
				//Adjusts its source a little bit so a geometry doesn't block itself (sliding it up the vector a little closer to the light)
                                Vector v1=rayBackToLight.directionGetter();
                                v1.scale(.0000001);
                                rayBackToLight.POOsetter(rayBackToLight.POOgetter().add(v1));
				//Checks to see if thee's any other geometry along the path
				Map<Geometry, List<Point3D>> geometriesBlockingLight = getSceneRayIntersectionPoints(rayBackToLight);
                                if(geometry instanceof FlatGeometry){
                                    geometriesBlockingLight.remove(geometry); // if it is a flat geometry then remove
                        }

				if(geometriesBlockingLight.isEmpty()) {
					//Calculates the diffused light generated by this light source
					Color calculatedDiffusedLight = calcDiffuseComp(geometry.getMaterial().getKd(), geometry.normal(point), light.getL(point), light.getIntensity(point));
					//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
				//	totalRed += calculatedDiffusedLight.getRed()/2;
				//	totalGreen += calculatedDiffusedLight.getGreen()/2;
				//	totalBlue += calculatedDiffusedLight.getBlue()/2;

					//Calculates the diffused light generated by this light source
					Color calculatedSpecularLight = calcSpecularComp(geometry.getMaterial().getKs(), new Vector(scene.getCamera().P0Getter().subtract(point)), geometry.normal(point), light.getL(point), geometry.getMaterial().getShiny(), light.getIntensity(point));
					//Adds the new diffused light to the Color holding all the light so far, but scales it down so as not to go past the bounds of color values
				//	totalRed += calculatedSpecularLight.getRed()/2;
				//	totalGreen += calculatedSpecularLight.getGreen()/2;
				//	totalBlue += calculatedSpecularLight.getBlue()/2;

			}
			}

		}

		//Combines them into a new color

              
                Ray reflectedRay = constructReflectedRay(geometry.normal(point),point,ray);
                Map<Geometry, List<Point3D>> reflectedEntry = getSceneRayIntersectionPoints(reflectedRay);
                Color reflectedColor=scene.getBackground();
                if(!reflectedEntry.isEmpty()){
                Map<Geometry, Point3D> closestPoints = getClosestPoint(reflectedEntry, reflectedRay.POOgetter());
                Geometry g = (Geometry)closestPoints.keySet().toArray()[0];
                Point3D p = (Point3D) closestPoints.values().toArray()[0];
                reflectedColor=calcColor(g,p,reflectedRay,level+1);

                }
                double Kr=geometry.getMaterial().getKr();
                
                Ray refractedRay = constructRefractedRay(point, ray);
                Map<Geometry, List<Point3D>> refractedEntry = getSceneRayIntersectionPoints(reflectedRay);
                Color refractedColor=scene.getBackground();
                if(!refractedEntry.isEmpty()){
                Map<Geometry, Point3D> closestPoints = getClosestPoint(refractedEntry, refractedRay.POOgetter());
                Geometry g = (Geometry)closestPoints.keySet().toArray()[0];
                Point3D p = (Point3D) closestPoints.values().toArray()[0];
                reflectedColor=calcColor(g,p,reflectedRay,level+1);
                refractedColor = calcColor(g,p, refractedRay,level+1);
                }
                
                Color reflectedLight= scaleColor(reflectedColor,Kr);
                double Kt = geometry.getMaterial().getKt();
                Color refractedLight = scaleColor(refractedColor,Kt);
                
                
                totalRed+=((reflectedLight.getRed()+refractedLight.getRed())); // add reflective and refracrtive lights
                totalBlue+=((reflectedLight.getBlue()+refractedLight.getBlue()));
                totalGreen+=((reflectedLight.getGreen()+refractedLight.getGreen()));

                Color combinedColor = new Color((totalRed/(numberOfLights+2)), (totalGreen/(numberOfLights+2)), (totalBlue/(numberOfLights+2)));
                

		return combinedColor;
	}
  private Ray constructReflectedRay(Vector v, Point3D p, Ray r){
      Vector v1= r.directionGetter();
      double a = 2*v1.dotProduct(v); //find dot product 
      v.scale(a);//scale the vector
      Vector v2 =(v.subtract(v1)); // find the vector
      Ray reflected = new Ray(p,v2);
      Vector v3= reflected.directionGetter();
      v3.scale(0.0001);
      reflected.POOsetter(p.add(v3));
 
      return reflected;
      
  }

  public Ray constructRefractedRay(Point3D p, Ray r){
      Vector v=r.directionGetter().normal();
      v.scale(0.0001);
      Point3D origin = p.add(v); // since refractive index is 1 then direciton is the same.
      return new Ray(origin,v);
  }
 
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay) {

        return calcColor(geometry, point, inRay, 0); // calls the recursive version starting from 0
} 

	// private method to calculate the diffuse light (according to the formula from slides)
	private Color calcDiffuseComp(double kd, Vector normal, Vector l, Color intensity) {
            double dot=normal.dotProduct(l);
            double scalar=kd*dot;
            return scaleColor(intensity, scalar);
	}
	
	// private method to calculate the specular light (according to the formula from slides)
	private Color calcSpecularComp(Double ks, Vector v, Vector normal, Vector l, int shininess, Color intensity) {
                double a=2*l.dotProduct(normal);
                normal.scale(a);
		Vector r = l.subtract(normal);
		double factor = ks*Math.abs(Math.pow(v.normal().dotProduct(r.normal()), shininess));
		return scaleColor(intensity, factor);
	}

    
    //Finds where the a given Ray intersects our scene 
    private Map<Geometry,List<Point3D>> getSceneRayIntersectionPoints(Ray ray){   
            Iterator<Geometry> geometries = scene.getGeometryIterator(); //create iterator to traverse the geometries
            Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();//createamap of intersection points
            while (geometries.hasNext()){ //go through all geometries
                Geometry geometry = geometries.next();
              
                List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);//add all intersection points to the list
                if(!geometryIntersectionPoints.isEmpty()){
                intersectionPoints.put(geometry,geometryIntersectionPoints);
                
            }
        }
         return intersectionPoints;
    }
    //end of gerRayIntersectionPoints


    //Find closest point to geometry       
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> map, Point3D POOgetter) { 

        double distance = Double.MAX_VALUE; //distace between the scene and geometry
        Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();

        for (Geometry geometry: map.keySet()){
            for(Point3D point:map.get(geometry)){
                if (scene.getCamera().P0Getter().distance(point)<distance){
                    distance = scene.getCamera().P0Getter().distance(point); //assigns new closest distance
                    closestPoint.clear();
                    closestPoint.put(geometry, new Point3D(point));
                }
            }
        }
        return closestPoint;
    }
    //end of getClosestPoint
}