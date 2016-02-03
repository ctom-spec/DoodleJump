package DoodleJump;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//I mage platform an image to complement the doodle. 

public class Platform{
	ImageView _platform;


	public Platform(int x, int y){
		/*Create a imageview to put the image of platform in. Then, set platform's X and Y according to the arguments x and y. */
		_platform = new ImageView();
		Image doodleImage = new Image (getClass().getResource("p-green.png").toExternalForm(),Constants.PLATFORM_WIDTH,Constants.PLATFORM_HEIGHT,false,true);
		_platform.setImage(doodleImage);
		_platform.setX(x);
		_platform.setY(y);
		_platform.setCache(true);

	}

	public ImageView getImageView(){
		//returns the platform as ImageView
		return _platform;
	}

	public void setX(int x){
		//sets the x position of the platform.
		_platform.setX(x);
	}

	public void setY(int y){
		//sets the y position of the platform. 
		_platform.setY(y);
	}

	public int getY(){
		//returns the y position of the platform.
		return (int) _platform.getY();
	}
	public int getX(){
		//returns the x position of the platform. 
		return (int) _platform.getX();
	}


}
