package temp.java;
//TEAM 11-12
//B.Azueta, A.Bortle, H.Bridge, K.Chan, C.Lemus

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Controller implements ActionListener {
	private View view;
	private Model model;
	
	//constructor
	public Controller(){
		view = new View();
		view.addControllerToButton(this); //add Controller as ActionListener
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());	
	}
	
	//run the simulation
	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(view.drawDelay, view.drawAction); //call drawAction every (drawDelay) msecs
				t.start();
				}
		});
			
	}	
	
	//main to run java project: create and start a controller	
	public static void main(String[]args) {
	 Controller c = new Controller();
	 c.start();
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		Model.movementFlag = !Model.movementFlag; //if button is pressed, start/stop the orc movement
	}
	

}


