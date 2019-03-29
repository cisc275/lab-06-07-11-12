package lab4;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller implements ActionListener , KeyListener {
	private View view;
	private Model model;
	
    
	
	public Controller(){
		view = new View();
		view.addControllerToButton(this);
		view.addControllerToKeys(this);
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

	@Override
	public void actionPerformed(ActionEvent a) {
		model.movementFlag = !model.movementFlag;
		view.buttFlag = !view.buttFlag;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 70 ) {
			System.out.println("F Key Pressed");
		}
		if (e.getKeyCode() == 74) {
			System.out.println("J Key Pressed");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == 70) {
			System.out.println("F Key Released");
		}
		if (e.getKeyCode() == 74) {
			System.out.println("J Key Released");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

