package lab4;
//Anna Bortle
public class Model {
	
	static int xloc = 0;
	static int yloc = 0;
	static int xIncr = 2;
	static int yIncr = 1;
	static int frameWidth;
	static int frameHeight;
	static int imgWidth;
	static int imgHeight;
	public static Direction d;
	public static boolean movementFlag;
	public static boolean fireFlag;
	public static boolean jumpFlag;
	public static boolean buttonFlag;
	
	//constructor
	public Model(int width, int height, int imageWidth, int imageHeight) {
		frameWidth = width;
		frameHeight = height;
		imgWidth = imageWidth;
		imgHeight = imageHeight;
		d = Direction.SOUTHEAST;	//starting direction
		movementFlag = true;
	}

	public static void updateLocationAndDirection() {
		
		//test for collisions
		if (xloc + imgWidth > frameWidth) { //hitting right boundary
			xIncr = -xIncr;
			switch (d){		
			case EAST:
				d = Direction.WEST;
				break;
			case NORTHEAST:
				d = Direction.NORTHWEST;
				break;
			case SOUTHEAST:
				d = Direction.SOUTHWEST;
				break;
			default:
				break;
			}
		}
		
		if (xloc < 0) {	//hitting left boundary
			xIncr = -xIncr;
			switch (d) {
			case WEST:
				d = Direction.EAST;
				break;
			case NORTHWEST:
				d = Direction.NORTHEAST;
				break;
			case SOUTHWEST:
				d = Direction.SOUTHEAST;
				break;
			default:
				break;
			}
		}
		
		if (yloc < 0 ) {	//hitting top boundary
			yIncr = -yIncr;
			switch (d) {
			case NORTH:
				d = Direction.SOUTH;
				break;
			case NORTHEAST:
				d = Direction.SOUTHEAST;
				break;
			case NORTHWEST:
				d = Direction.SOUTHWEST;
				break;
			default:
				break;
			}
		}
		
		if (yloc + imgHeight > frameHeight) {		//hitting bottom boundary
			yIncr = -yIncr;
			switch (d) {
			case SOUTH:
				d = Direction.NORTH;
				break;
			case SOUTHEAST:
				d = Direction.NORTHEAST;
				break;
			case SOUTHWEST:
				d = Direction.NORTHWEST;
				break;
			default:
				break;
			}
		}
		
		//update locations
		xloc = xloc + xIncr;
		yloc = yloc + yIncr;
	}

	public static int getX() {
		if (movementFlag && !fireFlag) {
			updateLocationAndDirection();
		}
		return xloc;
	}
	public static int getY() {
		if (movementFlag && !fireFlag) {
			updateLocationAndDirection();
		}
		return yloc;
	}

	public static Direction getDirect() {
		return d;
	}
	public static boolean getMovement() {
		return movementFlag;
	}
}
