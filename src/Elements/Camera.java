/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * @author Binyamin
 */
public class Camera {
    private Point3D P0;
    private Vector up;
    private Vector to;
    private Vector right;
    
    //defaault constructor
    public Camera(){
        P0= new Point3D();
        up= new Vector(0.0,1.0,0.0);
        to = new Vector(0.0,0.0,-1.0);
        right= new Vector(1.0,0.0,0.0);
        
    }
    // 4 variable constructor
    public Camera(Point3D p, Vector u, Vector t, Vector r){
        P0=p;
        up=u;
        to=t;
        right=r;
    }
    //copy constructor
    public Camera(Camera c){
        P0=c.P0;
        up=c.up;
        right=c.right;
        to=c.right;
    }
    // P0 getter
    public Point3D P0Getter(){
        return P0;
    }
    // up Getter
    public Vector upGetter(){
        return up;
    }
    // right Getter
        public Vector rightGetter(){
        return right;
    }
    //to Getter
        public Vector toGetter(){
        return to;
    }
    //P0 Setter
        public void P0setter(Point3D p){
            P0=p;
        }
     // up Setter
        public void upSetter(Vector u){
            up=u;
        }
     // right Setter
        public void rightSetter(Vector r){
            right=r;
        }
     // to Setter
        public void toSetter(Vector t){
            to=t;
        }
        
 // Construct a Ray from a Pixel
        public Ray constructRayThroughPixel (int Nx, int Ny,double x, double y, double screenDistance, double screenWidth, double screenHeight,int col, int row, int SS){
            Vector v = new Vector((to));  //create vector to
            v.scale(screenDistance); // scale the vector 
            Point3D center=P0Getter().add(v); // find the centre
            double ratioX =(screenWidth/Nx); // find the ratio in the x direction
            double ratioY=(screenHeight/Ny); // find the ration in y
            
            Vector l = new Vector(right); // create the right vector
            l.scale((((x-Nx/2.0)+((col+0.5)/SS)*ratioX))-(ratioX/2.0)); //scale the vector using the given formula taking into consideration the superSampling
            Vector r=new Vector(up); // create the up vector
            r.scale((((y-Ny/2.0)+(row+0.5)/SS)*ratioY)-(ratioY/2.0)); //scale the vector using the given formual taking into consideration the superSampling
            
            Ray ray=new Ray(P0Getter(),new Vector(center.add(l.subtract(r)))); // create the new ray
            return ray;      
             }
}

