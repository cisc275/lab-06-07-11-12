

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(view.drawDelay, view.drawAction);
				t.start();
			}
		});
		
			
		}
		
	public static void main(String[]args) {
	 Controller c = new Controller();
	 c.start();
	}

	
}

