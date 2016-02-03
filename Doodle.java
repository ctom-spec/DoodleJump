package DoodleJump;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/*I made doodle an image instead of a composite shape, because I thought it looked better. 
In doodle, I made the methods to allow it to y coordinate and velocity according to physics.*/

public class Doodle{
	ImageView _doodle;
	int _position;
	int _velocity;

	public Doodle(){
		/*Create a new imageview to contain the doodle image in. Set the doodle at the bottom center of the screen
		and instantiate _velocity to rebound velocity. */

		_doodle = new ImageView();
		Image doodleImage = new Image (getClass().getResource("doodleR.png").toExternalForm(),Constants.DOODLE_WIDTH,Constants.DOODLE_HEIGHT,false,true);
		_doodle.setImage(doodleImage);
		_doodle.setX(200);
		_doodle.setY(470);
		_doodle.setCache(true);
		_velocity=Constants.REBOUND_VELOCITY;

	}

	public ImageView getImageView(){
		//returns the imageview
		return _doodle;
	}

	public int getY(){
		//returns doodle's y position
		return (int) _doodle.getY();
	}

	public void setY(int y){
		//sets doodle's y position 
		_doodle.setY(y);
	}

	public void setVelocity(int v){
		//sets doodle's velocity by setting _velocity to v. 
		_velocity = v;
	}
	public int getVelocity(){
		//returns doodle's velocity
		return _velocity;
	}
	public void update(){
		//updates the velocity and the y position according to the gravity and duration at each frame.
		_velocity+=(Constants.GRAVITY*Constants.DURATION)/1000;
		_doodle.setY(_doodle.getY()+(_velocity*Constants.DURATION)/1000);
	}



}
