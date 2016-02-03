package DoodleJump;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import java.awt.event.ActionEvent;
import javafx.scene.layout.VBox;

//pane organizer takes care of all the panes in the program and sets them the way I want it.

public class PaneOrganizer{
	BorderPane _pane;
	Label _label;
	PaneOrganizer _PaneOrganizer;
	HBox _scorePane;
	Button _reset;
	Button _quit;
	Game _game;
	public PaneOrganizer(){
		/*Create a new borderpane and call a method to configure the borderpane properly with the game. 
		instantiate _PaneOrganizer so I could access it in the handler class. */
		_pane = new BorderPane();
		this.setUpGame();
		_PaneOrganizer=this;

	}

	public Pane getRoot(){
		//returns the borderpane as pane 
		return _pane;
	}

	public void setUpGame(){
		/*sets up new pane in the center to put the game in
		sets up new HBox at the top to put the label that keeps track of the score and the buttons to reset and quit.*/

		Pane gamePane = new Pane();
		gamePane.setPrefSize(400,550);
		gamePane.setStyle("-fx-background-color: white");
		_pane.setCenter(gamePane);
		HBox scorePane = new HBox(50);
		scorePane.setPrefSize(400,50);
		scorePane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);");
		scorePane.setAlignment(Pos.CENTER);
		Label label = new Label("Doodle Jump");
		Button reset = new Button ("Reset");
		reset.setOnMousePressed(new ResetHandler());
		reset.setVisible(false);
		Button quit = new Button ("Quit");
		quit.setOnMousePressed(new QuitHandler());
		quit.setVisible(true);
		_game = new Game(gamePane,label,reset);
		scorePane.getChildren().addAll(reset,label,quit);
		_pane.setTop(scorePane);

	}



	private class ResetHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle (MouseEvent e){
			//calls setUpGame and plays the game when reset button is clicked.
			_PaneOrganizer.setUpGame();
			_game.play();
			e.consume();
		}
	}
	private class QuitHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle (MouseEvent e){
			//exits the game when quit button is clicked. 
			Platform.exit();
			e.consume();
		}
	}

	


}