public class NBody {
	// public String filename;
	public static double readRadius(String filename){
		In in = new In(filename);
		// int n = in.readInt();
		in.readInt();
		double radius = in.readDouble();
		return radius;

	}
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int number = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[number];
		int i = 0;
		while ( i < number){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String image = in.readString();
			Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, image);
			planets[i] = p;
			i = i + 1;
		}
		return planets;
	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int number = new In(filename).readInt();

		StdDraw.enableDoubleBuffering();
		/*
		set the scale so that it matches the radius of the universe
		**/
		StdDraw.setScale(-radius, radius);
		

		for (double time = 0; time < T; time += dt ){
			double[] xForces = new double[number];
			double[] yForces = new double[number];
		    StdDraw.clear();
		    /* Then draw the image starfield.jpg as the background. */
		    String imageToDraw = "images/starfield.jpg";
		    StdDraw.picture(0, 0, imageToDraw, radius*2, radius*2);

			for (int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
		        /* Clears the drawing window. */
				planets[i].draw();
			}

            StdDraw.show();
			StdDraw.pause(10);

			for (int i = 0; i < planets.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
		}
		StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }


	}
}