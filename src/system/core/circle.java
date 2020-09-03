package system.core;

public class circle {

	//singlton
	private static circle singlton;
	
	private circle() {
		singlton = new circle();
	}
	
	public circle getinstance() {
		if (singlton == null) {
			singlton = new circle();
			return singlton;
		}
		return singlton;
	}
}
