/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *
 * @author Binyamin
 */
public class pointLight extends Light implements LightSource{ //inherits from abstract class 'Light'
   public Point3D position;
   public double Kc,Kl,Kq;
    //double distance;
    
    //default constructor
    public pointLight(){
        position=new Point3D();
        Kc = 0.00000001;
        Kl = 0.00000001;
        Kq = 0.00000001;
        color=new Color(255,255,255);
        Ka=1.0;
    }
    //end of default constructor
    
    //variable constructor
    public pointLight(Color cl, Point3D p, double a, double b, double c){
        color = cl;
        position=p;
        Kc=a;
        Kl=b;
        Kq=c;
    }
    //end of variable constructor
    
    //one variable pointList constructor
    public pointLight(Point3D p){
    //Sets the position to the origin
    position = p;
    Kc = 0.000000001;
    Kl = 0.000000001;
    Kq = 0.000000001;
    //Sets color to a default white
    color = new Color(255, 255, 255);
    //Sets _kA to a default 1.0
    Ka = 1.0;
    }
    //end of one variable constructor
    
    //getters and setters
    public Color getColor(){
        return color;
    }
    public double getKc(){
        return Kc;
    }
    public double getKl(){
        return Kl;
    }
     public double getKq(){
        return Kq;
    }
     
   public void setKc(double a){
       Kc=a;
   }
   
   public void setKl(double b){
       Kl=b;
   }
      
      public void setKq(double c){
       Kq=c;
   }
      
   public Point3D getPosition(){
       return position;
   }
   
   public void setPosition(Point3D p){
       position=p;
   }
   //end of getters and setters

    @Override
    public Color getIntensity(Point3D point) {
        double d = position.distance(point);
	double factor = (Kc + Math.pow(Kl, d) + Math.pow(Kq, d*d));
        return scaleColor(getColor(),factor);
    }

		


	public Vector getL(Point3D point) {
		return new Vector(point.subtract(position)).normal();
	}
        
   	public Color scaleColor(Color color, double factor) {
		int newRed = ((int)(color.getRed()*factor) <= 255 ? (int)(color.getRed()*factor) : 255);
		newRed = (newRed >= 0 ? newRed : 0);
		int newGreen = ((int)(color.getGreen()*factor) <= 255 ? (int)(color.getGreen()*factor) : 255);
		newGreen = (newGreen >= 0 ? newGreen : 0);
		int newBlue = ((int)(color.getBlue()*factor) <= 255 ? (int)(color.getBlue()*factor) : 255);
		newBlue = (newBlue >= 0 ? newBlue : 0);
		
		return new Color(newRed, newGreen, newBlue);
	}
}