package DoodleJump;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;


/* Game takes care of all the elements needed in the game and the game logic.*/ 

public class Game{
Doodle _doodle;
Pane _pane;
int _xMaxRange;
int _xMin;
int _yMaxRange;
int _yMin;
Timeline _timeline;
Label _label;
int _count;
Pane _score;
Button _reset;
Button _start;
ArrayList<Platform> _platforms;
Game _game;
int _distance;
	public Game(Pane pane, Label label, Button reset){
		/* create the doodle and list of platforms (includes a platform at the bottom of doodle's feet)
		set doodle's reasonable jump range to help calculate platform positions. 
		set platform's x range to the screen size minus platform width.
		Make a new keyFrame to put i new Timeline to control the animation. 
		Create a new startbutton that stakes in StartHandler, and set it to proper position. 
		make the pane recognize key presses and add in the movehandler to the pane. 
		instantiate _game, _pane, _label, and _reset . */
		_game=this;
		_doodle = new Doodle();
		_platforms = new ArrayList<Platform>();
		_platforms.add(new Platform(200,495));
		_yMaxRange = 150;
		_yMin = 450;
		_xMaxRange = 400-Constants.PLATFORM_WIDTH;
		_xMin = 0;
		KeyFrame move = new KeyFrame(Duration.seconds(.025),new MoveTimeHandler());
		_timeline = new Timeline(move);
		_timeline.setCycleCount(Timeline.INDEFINITE);
		_start = new Button("START");
		_start.setOnMousePressed(new StartHandler());
		_start.setLayoutX(175);
		_start.setLayoutY(250);
		_pane=pane;
		pane.setOnKeyPressed(new MoveHandler());
		// this.makePlatforms();
		pane.getChildren().addAll(_start);
		_label=label;
		_reset=reset;


	}
	public void makePlatforms(){
		/*Make a lot of platforms by calculating the max range the platforms could reasonably be set in, 
		and radomizing it by using Math.random(). */
		while (_platforms.get(_platforms.size()-1).getY()>0){
			int x = _xMin+ (int) (Math.random()*(_xMaxRange+1));
			int y = _platforms.get(_platforms.size()-1).getY()- (int) (Math.random()*(_yMaxRange+1));
			Platform platform = new Platform(x,y);
			_platforms.add(platform);
			_pane.getChildren().addAll(platform.getImageView());
			System.out.println("d");
		}
		

	}
	private class StartHandler implements EventHandler<MouseEvent>{
		public void handle (MouseEvent e){
			/*plays the animation when start button is clicked.*/
			_game.play();
		}
	}

	public void play(){
		/* plays the timeline and adds the doodle and all the platforms on the the game pane.*/
		_timeline.play();
			for (int i=0;i<_platforms.size();i++){
				_pane.getChildren().addAll(_platforms.get(i).getImageView());
			}
			_pane.getChildren().addAll(_game.getDoodle());
			_start.setVisible(false);
	}
	private class MoveTimeHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e){
			/*request focus onto the game pane to help recognize keyinputs. 
			update doodle's position accordignly.*/
			_pane.requestFocus();
			Game.this.makePlatforms();
			_label.setText("Score: "+Integer.toString(_distance));
			_doodle.update();
			if (_doodle.getY()>550){
				/*if doodle fell off the screen, stop timeline and show reset button.*/
				_timeline.stop();
				_reset.setVisible(true);

			}
			else if (_doodle.getY()<275){
				/* if doodle is beyond halfway Y point, then calculate how much the doodle would have travelled
				and scroll the platforms accordingly.*/
				int y = 275-_doodle.getY();
				_distance+=y;
				_doodle.setY(275);
				for (int i=0;i<_platforms.size();i++){
					_platforms.get(i).setY(_platforms.get(i).getY()+y);
				}
			}
			if (_doodle.getVelocity()>0){
				/* if doodle is falling and intersects a platform, reset rebound velocity.*/
				for (int i=0;i<_platforms.size();i++){
					int x = _platforms.get(i).getX();
					int y = _platforms.get(i).getY();
					if (_doodle.getImageView().intersects(x,y,Constants.PLATFORM_WIDTH,Constants.PLATFORM_HEIGHT)){
						_doodle.setVelocity(Constants.REBOUND_VELOCITY);
					}

				}

			}
			e.consume();
		}
	}

	private class MoveHandler implements EventHandler<KeyEvent>{
		public void handle (KeyEvent e){
			/*moves the doodle right 20 units if right arrow is pressed
			moves the doodle left 20 units if left arrow is presssed*/
			KeyCode keyPressed = e.getCode();
			if (keyPressed == KeyCode.RIGHT){
				_doodle.getImageView().setX(_doodle.getImageView().getX()+20);

			}
			else if (keyPressed ==KeyCode.LEFT){
				_doodle.getImageView().setX(_doodle.getImageView().getX()-20);
			}
			
			e.consume();
		}
	}
	public Node getDoodle(){
		//returns the doodle as Node. 
		return _doodle.getImageView();
	}

}