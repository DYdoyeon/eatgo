package kr.co.fastcampus.eatgos.interfaces;

public class RestaurantNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	public RestaurantNotFoundException(long id) {
        super("Could not find restaurant "+id);

	}

}
