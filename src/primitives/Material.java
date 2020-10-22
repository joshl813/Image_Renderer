/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 *
 * @author Binyamin
 */
public class Material {
    double Kd;
    double Ks;
    int shiny;
    double Kr;
    double Kt;
    
    public Material(){
        Kd=0.8;
        Ks=0.99;
        shiny=5;
        Kr=0.5;
        Kt=0.5;
        
}
    public Material(double a, double b, int c, double d, double e){
        Kd=a;
        Ks=b;
        shiny=c;
        Kr=d;
        Kt=e;
    }
    public Material(Material m){
        Kd=m.Kd;
        Ks=m.Ks;
    }
    public double getKd(){
        return Kd;
    }
    public double getKr(){
        return Kr;
    }
    public double getKt(){
        return Kt;
    }
    public double getKs(){
        return Ks;
    }
    public int getShiny(){
        return shiny;
    }
    public void setKd(double k){
        Kd=k;
    }
    public void setKs(double k){
        Ks=k;
    }
    public void setShiny(int s){
        shiny=s;
    }
}

