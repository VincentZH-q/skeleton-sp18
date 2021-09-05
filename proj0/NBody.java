public class NBody {

	public NBody() {
	}

	private static String imageBackgroud = "images/starfield.jpg";

	public static double readRadius(String path) {

		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;

	}

	public static Planet[] readPlanets(String path) {

		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[n];
		for (int i=0; i < n; i++) {
			planets[i] = new Planet(in.readDouble(),
									in.readDouble(),
									in.readDouble(),
									in.readDouble(),
									in.readDouble(),
									in.readString());
		}
		return planets;
	}

	public static void main(String[] args) {

//		StdAudio.play("audio/2001.mid", );

		double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		int len = planets.length;
		double[] xForces = new double[len];
		double[] yForces = new double[len];
		Planet p = null;
		double t = 0;

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-1 * radius, radius);
		while (t <= T){
			int i = 0;
			for (i=0; i < len; i++) {
				p = planets[i];
				xForces[i] = p.calcNetForceExertedByX(planets);
				yForces[i] = p.calcNetForceExertedByY(planets);
			}
			for (i=0; i<len; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, imageBackgroud);
			for (Planet planet: planets) planet.draw();
			StdDraw.show();
			StdDraw.pause(10);
			t+=dt;
		}

		StdOut.printf("%d\n", len);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
					planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}


	}
}