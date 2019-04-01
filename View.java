package lab4;
//Anna Bortle

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class View extends JFrame {
	
	int frameCount = 10;
	int picNum = 0;
	BufferedImage[][] forwardpics;
	BufferedImage[][] idlepics;
	BufferedImage[][] jumppics;
	BufferedImage[][] firepics;
	
	final static int frameStartSize = 800;
	final static int drawDelay = 60; //msec
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	
	DrawPanel drawPanel = new DrawPanel();
	Action drawAction;
	
	Button b;
	
	
	
	public View() {
		
		
	
		Direction[]	directions = new Direction[]{Direction.NORTH, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST
				, Direction.SOUTH, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};
		Direction[] idle1 = new Direction[] {Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH};
		Direction[] idle2 = new Direction[] {Direction.NORTHWEST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.SOUTHEAST};
			
		//load in buffered images into 2d array
		forwardpics = new BufferedImage[directions.length][frameCount];
		jumppics = new BufferedImage[directions.length][8];
		firepics = new BufferedImage[directions.length][4];
		
		for (Direction d: directions) {
			BufferedImage img = createForwardImage(d.getName());
			for(int i = 0; i < frameCount; i++) {
				forwardpics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, 0, getImageWidth(), getImageHeight());
			}
			BufferedImage img2 = createFireImage(d.getName());
			for(int i = 0; i < 4; i++) {
				firepics[d.ordinal()][i] = img2.getSubimage(getImageWidth()*i, 0, getImageWidth(), getImageHeight());
			}
			BufferedImage img3 = createJumpImage(d.getName());
			for(int i = 0; i < 8; i++) {
				jumppics[d.ordinal()][i] = img3.getSubimage(getImageWidth()*i, 0, getImageWidth(), getImageHeight());
			}
		}
		idlepics = new BufferedImage[directions.length][4];
		int j=0;
		for (Direction d: idle1) {
			BufferedImage img = createIdleImage("ewns");
			for(int i=0; i < 4; i++) {
				idlepics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, getImageHeight()*j, getImageWidth(), getImageHeight());
			}
			j++;
		}
		j=0;
		for (Direction d: idle2) {
			BufferedImage img = createIdleImage("nwneswse");
			for(int i=0; i < 4; i++) {
				idlepics[d.ordinal()][i] = img.getSubimage(getImageWidth()*i, getImageHeight()*j, getImageWidth(), getImageHeight());
			}
			j++;
		}
		
		
		
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
			}
		};
		
		add(drawPanel);
		drawPanel.setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameStartSize, frameStartSize);
		b = new Button("Click to stop/move");
		b.setLocation(50, 50);
		drawPanel.add(b);
		drawPanel.setFocusable(true);
	    drawPanel.requestFocus();
		setVisible(true);
		pack();
		
		
		
	}
		
	//Read image from file and return
	private BufferedImage createForwardImage(String direction){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_"+direction+".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Reads in orc firing images
	private BufferedImage createFireImage(String direction){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_fire_"+direction+".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//reads orc jumping images
	private BufferedImage createJumpImage(String direction){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_jump_"+direction+".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//reads orc idling images
	private BufferedImage createIdleImage(String direction){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_idle_"+direction+".png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public int getWidth() {
		return frameStartSize;
	}
	public int getHeight() {
		return frameStartSize;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	
	

	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel{
		int picNum = 0;
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			if (Model.getMovement()) {
				if (Model.jumpFlag) {
					picNum = (picNum + 1) % 8;
					g.drawImage(jumppics[Model.getDirect().ordinal()][picNum], Model.getX(), Model.getY(), Color.gray, this);
					}
				else{
					picNum = (picNum + 1) % frameCount;
					g.drawImage(forwardpics[Model.getDirect().ordinal()][picNum], Model.getX(), Model.getY(), Color.gray, this);
				}
			}
			
			if (!Model.getMovement()) {
				if (Model.fireFlag) {
					picNum = (picNum + 1) % 4;
					g.drawImage(firepics[Model.getDirect().ordinal()][picNum], Model.getX(), Model.getY(), Color.gray, this);
				}
				else if (Model.jumpFlag) {
					picNum = (picNum + 1) % 8;
					g.drawImage(jumppics[Model.getDirect().ordinal()][picNum], Model.getX(), Model.getY(), Color.gray, this);
				}
				else {
					picNum = (picNum + 1) % 4;
					g.drawImage(idlepics[Model.getDirect().ordinal()][picNum], Model.getX(), Model.getY(), Color.gray, this);
				}
			}
		    drawPanel.requestFocus();
		}
			
		public Dimension getPreferredSize() {
				return new Dimension(frameStartSize, frameStartSize);
		}
	}
	

	public void addControllerToButton(Controller c) {
		b.addActionListener(c);
	}
	public void addControllerToKeys(Controller c) {
		drawPanel.addKeyListener(c);
	}
	
		
}
