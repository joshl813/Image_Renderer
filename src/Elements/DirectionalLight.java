package Elements;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

	//Vector representing the direction of the light
	Vector _direction;

	//default constructor which creates a default vector as the direction of the DirectionalLight and default values for color and Ka
	public DirectionalLight(){
		_direction = new Vector();
		color = new Color(255, 255, 255);
        Ka = 1.0;
	}

	//constructor which accepts a vector as a parameter and sets the direction of the DirectionalLight accordingly and default values for color and Ka
	public DirectionalLight(Vector direction){
		_direction = direction;
        color = new Color(255, 255, 255);
        Ka = 1.0;
	}
	
	//Parameterized constructor
	public DirectionalLight(Vector direction, Color color, double kA) {
		//Sets internal variables to those passed
		_direction = direction;
		color = color;
		Ka = kA;
	}

	//Copy constructor
	public DirectionalLight(DirectionalLight other) {
		//Sets internal variables to copies of other's
		_direction = other.getdirection();
		color = other.getColor();
		Ka = other.getKa();
	}
	//getter for the direction of the DirectionalLight
	public Vector getdirection() {
		//Returns a new Vector with the same value as our _direction so that changes made at the callsite won't affect our variables
		return new Vector(_direction);
	}
	
	public Color getColor() {
		//Returns a new Color with the same RGB values as our color so that changes made at the callsite won't affect our variables
		return new Color(color.getRGB());
	}
	
	public double getKa() {
		return Ka;
	}

	//setter for the direction of the DirectionalLight
	public void setdirection(Vector direction) {
		_direction = direction;
	}
	
	public void setColor(Color color) {
		//Allows for setting of protected data member color
		color = color;
	}
	
	public void setKa(double kA) {
		//Allows for setting of protected data member color
		Ka = kA;
	}

	//Returns a version of color, where each component is scaled by Ka, representing the color
	public Color getIntensity(Point3D point) {
		int red = (int)(color.getRed()*Ka);
		int green = (int)(color.getGreen()*Ka);
		int blue = (int)(color.getBlue()*Ka);

		return new Color(red, green, blue);
	}

	//Return the vector that a photon coming from this DirectionalLight would travel along to intersect the given Point
	public Vector getL(Point3D point) {
		//Which is just the direction of the light
		return _direction.normal();
	}
}