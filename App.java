package DoodleJump;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/* Top level class
Includes PaneOrganizer that takes care of all the panes and the Scene that sets
the view of the program set on stage. 
*** EXTRA CREDIT ***
1. button to start the game. 
2. keeps track of score
3. doodle and platforms are images.  
4. button to reset the game. 
* @author jhong2 */

public class App extends Application {

    @Override
	public void start(Stage stage) {
		stage.setTitle("Doodle Jump");
		stage.show();
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(),400,600);
		stage.setScene(scene);	
	}

	/*
	* Here is the mainline! No need to change this.  
	*/
	public static void main(String[] argv) {
        	// launch is a method inherited from Application
		launch(argv);
	}
}
