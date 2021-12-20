public class Planet{
	public double xxPos;
	/**Its current x position*/
	public double yyPos;
	/**Its current y position*/
    public double xxVel;
    /**Its current velocity in the x direction*/
    public double yyVel;
    /**Its current velocity in the y direction*/
    public double mass;
    public String imgFileName;
    public static double G = 6.67E-11;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }

    public Planet(Planet p){
    	xxPos = p.xxPos;
    	yyPos = p.yyPos;
    	xxVel = p.xxVel;
    	yyVel = p.yyVel;
    	mass = p.mass;
    	imgFileName = p.imgFileName;
    }
    /**all methods should be non-static*/
    public double calcDistance(Planet p){
    	double Distance;
    	double dx = p.xxPos - xxPos;
    	double dy = p.yyPos - yyPos;
    	Distance = Math.pow((dx*dx + dy*dy), 0.5);
    	return Distance;
    }

    public double calcForceExertedBy(Planet p){
    	double ForceExertedBy = 
    	mass*p.mass*G/(calcDistance(p)*calcDistance(p));
    	return ForceExertedBy;
    }

    public double calcForceExertedByX(Planet p){
    	double dx = p.xxPos - xxPos;
    	double ForceExertedByX =
    	calcForceExertedBy(p)*dx/calcDistance(p);
    	return ForceExertedByX;
    }

    public double calcForceExertedByY(Planet p){
    	double dy = p.yyPos - yyPos;
    	double ForceExertedByY =
    	calcForceExertedBy(p)*dy/calcDistance(p);
    	return ForceExertedByY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
    	double NetForceExertedByX = 0;
    	for (int i = 0; i < allPlanets.length; i ++){
        if (this.equals(allPlanets[i])){
          continue;
        }
    		NetForceExertedByX = NetForceExertedByX +
    		calcForceExertedByX(allPlanets[i]);
    	}
    	return NetForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
    	double NetForceExertedByY = 0;
    	for (int i = 0; i < allPlanets.length; i ++){
    		if (this.equals(allPlanets[i])){
    			continue;
    		}
    		NetForceExertedByY = NetForceExertedByY +
    		calcForceExertedByY(allPlanets[i]);
    	}
    	return NetForceExertedByY;
    }

    public void update(double dt,double xF, double yF ){
      double ax = xF/mass;
      double ay = yF/mass;
      xxVel = xxVel + ax*dt;
      yyVel = yyVel + ay*dt;
      xxPos = xxPos + xxVel*dt;
      yyPos = yyPos + yyVel*dt;

    }
    public void draw(){
      String imageToDraw = "images/" + imgFileName;
      StdDraw.picture(xxPos, yyPos, imageToDraw);


    }
}