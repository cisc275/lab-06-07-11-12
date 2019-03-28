package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller{

	private Model model;
	private View view;
    
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());	
	}
	
        //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++){
			if (Model.getMovement()) {
				//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection();
			}
			view.update(model.getX(), model.getY(), model.getDirect());
			}
			
		}
		
	public static void main(String[]args) {
	 Controller c = new Controller();
	 c.start();
	}

	
}

