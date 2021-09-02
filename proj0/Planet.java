public class Planet {
	/**
	 * 	double xxPos: Its current x position
	 *	double yyPos: Its current y position
	 *	double xxVel: Its current velocity in the x direction
	 *	double yyVel: Its current velocity in the y direction
	 *	double mass: Its mass
	 *	String imgFileName: The name of the file that corresponds to the image that depicts the planet 	
	 *  public static final double G: Universal gravitational constant
	 */
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

//	public Planet() {
//	}

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/**
	 *  Calculate the distance between two Planets. 
	 *  @param Planet to be calculated
	 *  @return a double describing the distance between two Planets
	 */
	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}


	/**
	 *  Calculate the force exerted on this planet by the given planet.
	 *  @param Planet to give force
	 *  @return a double describing the force exerted on this planet by the given planet
	 */
	public double calcForceExertedBy(Planet p) {
		return (G / Math.pow(calcDistance(p), 2) * this.mass * p.mass);
	}

	/**
	 *  Calcualte the force exerted in the X direction
	 *  @param Planet to be calculated
	 *  @return a double describing the force exerted in the X direction
	 */
	public double calcForceExertedByX(Planet p) {
		double cos = (p.xxPos - this.xxPos) / calcDistance(p);
		return calcForceExertedBy(p) * cos;
	}

	/**
	 *  Calcualte the force exerted in the Y direction
	 *  @param Planet to be calculated
	 *  @return a double describing the force exerted in the Y direction
	 */
	public double calcForceExertedByY(Planet p) {
		double sin = (p.yyPos - this.yyPos) / calcDistance(p);
		return calcForceExertedBy(p) * sin;
	}

	/**
	 *  Calculate the net X force exerted by all planets in a array.
	 *  @param planet[] a Planets array
	 *  @return a double describing the net X force exerted by other Planets
	 */
	public double calcNetForceExertedByX(Planet[] ps) {
		double xForce = 0;
		for(Planet p : ps) {
			if (!this.equals(p)) {
				xForce += calcForceExertedByX(p);
			}
		}

		return xForce;
	}

	/**
	 *  Calculate the net Y force exerted by all planets in a array.
	 *  @param planet[] a Planets array
	 *  @return a double describing the net Y force exerted by other Planets
	 */
	public double calcNetForceExertedByY(Planet[] ps) {
		double yForce = 0;
		for(Planet p : ps) {
			if (!this.equals(p)) {
				yForce += calcForceExertedByY(p);
			}
		}

		return yForce;
	}


//	public String toString() {
//		return "Planet{" +
//				"xxPos=" + xxPos +
//				", yyPos=" + yyPos +
//				", xxVel=" + xxVel +
//				", yyVel=" + yyVel +
//				", mass=" + mass +
//				", imgFileName='" + imgFileName + '\'' +
//				'}';
//	}

	/**
	 *  Calculate how much the forces exerted on the planet will cause that planet 
	 *  to accelerate, and the resulting change in the planet’s velocity 
	 *  and position in a small period of time dt.
	 *  @param double,double,double a small period of time dt , x- and y- forces
	 *
	 */
	public void update(double dt, double fX, double fY) {
		xxVel += dt * fX / mass;
		yyVel += dt * fY / mass;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	/**
	 *   Draw the Planet’s image at the Planet’s position
	 */
	public void draw() {
		String filename = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, filename);
	}

}
// public class Planet {

// 	public static final double G = 6.67e-11;

// 	public double xxPos;
// 	public double yyPos;
// 	public double xxVel;
// 	public double yyVel;
// 	public double mass;
// 	public String imgFileName;

// 	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
// 		this.xxPos = xP;
// 		this.yyPos = yP;
// 		this.xxVel = xV;
// 		this.yyVel = yV;
// 		this.mass = m;
// 		this.imgFileName = img;
// 	}

// 	public Planet(Planet p) {
// 		this.xxPos = p.xxPos;
// 		this.yyPos = p.yyPos;
// 		this.xxVel = p.xxVel;
// 		this.yyVel = p.yyVel;
// 		this.mass = p.mass;
// 		this.imgFileName = p.imgFileName;
// 	}

// 	public double calcDistance(Planet p) {
		
// 		double rr = (p.xxPos-this.xxPos)*(p.xxPos-this.xxPos) + (p.yyPos-this.yyPos)*(p.yyPos-this.yyPos);
// 		return Math.sqrt(rr);
// 	}

// 	public double calcForceExertedBy(Planet p) {
// 		double r = calcDistance(p);
// 		double f = (G * p.mass * this.mass) / r*r;
// 		return f;
// 		// return (G / Math.pow(calcDistance(p), 2) * this.mass * p.mass);
// 	}

// 	public double calcForceExertedByX(Planet p) {
// 		double f = calcForceExertedBy(p);
// 		double fx = f*(p.xxPos - this.xxPos);
// 		return fx / calcDistance(p);
// 	}

// 	public double calcForceExertedByY(Planet p) {
// 		double f = calcForceExertedBy(p);
// 		double fy = f*(p.yyPos - this.yyPos);
// 		return fy / calcDistance(p);
// 	}

// 	// public double calcNetForceExertedByX() {
// 	// 	return 
// 	// }

// }