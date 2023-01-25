/// Mouse GUI Assignment: Pong
/// Andre Arcaina
/// Due Date: 2021/11/19 @ 11:59 PM 
/// Version 3.01

import javax.swing.*;
import java.awt.event.*;

///Extra Features
/// 1. add a win/lose screen
/// 2. reset the game when someone wins
/// 3. replaces ball with image
/// 4. replaces paddles with image
/// 5. easter egg for pong
/// 6. changed deflection based on how the ball hits the paddle for pong (if ball hits middle it goes horizontal) 
/// 7. increased brightness of background when score increases for pong

public class PongGameAndreA implements KeyListener{
	///properties
	JFrame frame = new JFrame("Pong");
	PongPanel PPanel = new PongPanel();
	
	///methods
	//deflections
	//once I release the inputted key, the following code would run
	//The code that is executed when the key has been released will stop the paddle from moving
	public void keyReleased(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			PPanel.intRectDefY = 0;
		}else if(evt.getKeyChar() == 's'){
			PPanel.intRectDefY = 0;
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			PPanel.intRectDefY2 = 0;
		}else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			PPanel.intRectDefY2 = 0;
		}
	} 
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			//move up
			PPanel.intRectDefY = -10;
		}else if(evt.getKeyChar() == 's'){
			//move down
			PPanel.intRectDefY = +10;
		}
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			//move 2nd player up
			PPanel.intRectDefY2 = -10;
		}else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			//move 2nd player down
			PPanel.intRectDefY2 = +10;
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_SPACE){
			//start game/serve the ball 
			if(PPanel.intSpaceBar == 1){
				PPanel.intSpaceBar = 2;
			}
		}
	}
	public void keyTyped(KeyEvent evt){
		if(evt.getKeyChar() == 'c'){
			if(PPanel.intP1Score == 5 || PPanel.intP2Score == 5){ 
				//resets scores to zero
				PPanel.intP1Score = 0;
				PPanel.intP2Score = 0;
			}
		} 
	}
	
	///constructor
	public PongGameAndreA(){
		frame.addKeyListener(this);
		frame.requestFocus();
		
		frame.setContentPane(PPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	
	///main program
	public static void main(String[] args){
		new PongGameAndreA();
	}
}
