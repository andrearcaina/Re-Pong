/// Mouse GUI Assignment: Pong Panel
/// Andre Arcaina
/// Due Date: 2021/11/19 @ 11:59 PM 
/// Version 3.02

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PongPanel extends JPanel implements ActionListener{
	///properties
	Timer timer = new Timer(1000/60, this);
	
	//user 1 rectangle
	int intRectY = 260;
	int intRectDefY = 0;
	
	//user 2 rectangle
	int intRectY2 = 260;
	int intRectDefY2 = 0; 
	
	//pong ball
	int intBallX = 395; 
	int intBallY = 295;
	
	//player scores 
	int intP1Score = 0;
	int intP2Score = 0;

	//serve ball and acts as a "boolean" but with integer values
	int intSpaceBar = 1;
	
	//speed of ball
	int intSpeedX = 10;
	int intSpeedY = 0; //it goes straight straight right first
	
	//images of ball and paddles instead of just drawing a rectangle 
	BufferedImage Paddle1; 
	BufferedImage Paddle2; 
	BufferedImage Ball; 
	
	//Font
	Font font = new Font("OCR A Extended", Font.TRUETYPE_FONT, 70);
	Font font2 = new Font("OCR A Extended", Font.TRUETYPE_FONT, 30);
	Font font3 = new Font("OCR A Extended", Font.TRUETYPE_FONT, 20);
	
	///methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		} 
	}
	
	// override paint component
	public void paintComponent(Graphics g){
		if(intP1Score == 0 || intP2Score == 0){
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, 800, 600); 
		}
		if(intP1Score == 1 || intP2Score == 1){
			g.setColor(new Color(50, 50, 50));
			g.fillRect(0, 0, 800, 600);
		}
		if(intP1Score == 2 || intP2Score == 2){
			g.setColor(new Color(100, 100, 100));
			g.fillRect(0, 0, 800, 600);
		}
		if(intP1Score == 3 || intP2Score == 3){
			g.setColor(new Color(150, 150, 150));
			g.fillRect(0, 0, 800, 600);
		}
		if(intP1Score == 4 || intP2Score == 4){
			g.setColor(new Color(230, 230, 230));
			g.fillRect(0, 0, 800, 600);
		}
		
		//draws the outline of the game  
		g.setColor(Color.GRAY); 
		g.fillRect(0, 0, 10, 600); //Player 2 must hit this rectangle to score
		g.fillRect(790, 0, 10, 600); //Player 1 must hit this rectangle to score  
		g.fillRect(0, 0, 800, 10); // top rectangle outline
		g.fillRect(0, 590, 800, 10); // botton rectangle outline
		
		/*for(intCount = 15; intCount > 0; intCount--){
			g.setColor(Color.BLACK);
			g.fillRect(395, intDottedLineSquare, 10, 20);
			intDottedLineSquare = intDottedLineSquare + 40;
		}*/
		
		//draws a dotted line
		int intDrawY = 10;
		for(int intCount = 0; intCount < 15; intCount++){
			g.fillRect(395, intDrawY, 10, 20);
			intDrawY = intDrawY + 40;
		} 
		
		//draws paddles for user 1 and restricts it from moving past the screen when it goes to the max height and min height
		g.setColor(Color.WHITE);
		if(intRectY >= 10 && intRectY <= 510){ //draws the actual player one paddle
			g.drawImage(Paddle1, 40, intRectY, null);
			intRectY = intRectY + intRectDefY;
		}else if(intRectY < 10){ //once it hits the top rectangle it stops it from moving and redraws it so it doesn't lag
			intRectY = intRectY + 10; // keeps on deflecting once it hits the top
			g.drawImage(Paddle1, 40, intRectY, null);
		}else if(intRectY > 510){ //once it hits the bottom rectangle it stops it from moving and redraws it so it doesn't lag
			intRectY = intRectY - 10; // keeps on deflecting once it hits the bottom
			g.drawImage(Paddle1, 40, intRectY, null);
		}
		//draws paddles for user 2 and restricts it from moving past the screen when it goes to the max height and min height
		if(intRectY2 >= 10 && intRectY2 <= 510){ // draws the actual player two paddle
			g.drawImage(Paddle2, 750, intRectY2, null);
			intRectY2 = intRectY2 + intRectDefY2;
		}else if(intRectY2 < 10){ //once it hits the top rectangle it stops it from moving and redraws it so it doesn't lag
			intRectY2 = intRectY2 + 10;
			g.drawImage(Paddle2, 750, intRectY2, null);
		}else if(intRectY2 > 510){ //once it hits the bottom rectangle it stops it from moving and redraws it so it doesn't lag
			intRectY2 = intRectY2 - 10;
			g.drawImage(Paddle2, 750, intRectY2, null);
		}
		
		//score
		g.setColor(Color.GRAY);
		g.setFont(font);
		g.drawString("0"+intP1Score+"  0"+intP2Score, 275, 70);
		
		//draw pong ball
		//draws pong ball first without pressing space
		if(intSpaceBar == 1){
			g.setColor(Color.GRAY);
			g.setFont(font3);
			g.drawString("Press space to start.", 265, 285);
			
			g.drawImage(Ball, intBallX, intBallY, null);
		}else if(intSpaceBar == 2){ //draws pongball moving at certain speed at x and y
			g.drawImage(Ball, intBallX, intBallY, null);
			intBallX = intBallX + intSpeedX; 
			intBallY = intBallY + intSpeedY;
		}
		//collision into top and bottom rectangle
		if(intBallY < 13){
			intSpeedY = 10;
		}else if(intBallY > 577){
			intSpeedY = -10;
		}
		//collision into player one paddle
		if(intBallY >= intRectY && intBallY + 20 <= intRectY + 100 && intBallX <= 50 && intBallX >= 25){
			if(intBallY >= intRectY + 40 && intBallY + 20 <= intRectY + 60){
				intSpeedY = 0; //for the middle of the paddle
			}else if(intBallY >= intRectY && intBallY + 20 <= intRectY + 40){
				intSpeedY = -10; // for the bottom of the paddle
			}else if(intBallY >= intRectY + 60 && intBallY + 20 <= intRectY + 100){
				intSpeedY = 10; // for the top of the paddle
			}
			intSpeedX = 10;
		}
		//collision into player two paddle
		if(intBallY >= intRectY2 && intBallY + 20 <= intRectY2 + 100 && intBallX >= 740 && intBallX <= 780){
			if(intBallY >= intRectY2 + 40 && intBallY + 20 <= intRectY2 + 60){
				intSpeedY = 0; //for the middle of the ball
			}else if(intBallY >= intRectY2 && intBallY + 20 <= intRectY2 + 40){
				intSpeedY = -10; // for the bottom of the paddle
			}else if(intBallY >= intRectY2 + 60 && intBallY + 20 <= intRectY2 + 100){
				intSpeedY = 10; // for the top of the paddle
			}
			intSpeedX = -10;
		} 
		//collision into left and right rectangle to hit
		if(intBallX < 20){
			intP2Score = intP2Score + 1;
			intSpaceBar = 1;
			intBallX = 395;
		}else if(intBallX > 780){
			intP1Score = intP1Score + 1;
			intSpaceBar = 1;
			intBallX = 395;
		}
		//end screen
		if(intP1Score == 5){
			//resets paddle positions and ball positions back to what it originally was 
			intSpaceBar = 1;
			intBallX = 395;
			intBallY = 295;
			intBallY = 295;
			intRectY = 250;
			intRectY2 = 250;
			
			//draws screen with player one 
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("PLAYER ONE WON!", 50, 200);
			g.setFont(font2);
			g.drawString("Press 'c' to go back to game.", 20, 400);
		}else if(intP2Score == 5){
			//resets paddle positions and ball positions back to what it originally was
			intSpaceBar = 1;
			intBallX = 395;
			intBallY = 295;
			intBallY = 295;
			intRectY = 250;
			intRectY2 = 250;
			
			//draws screen with player two win
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("PLAYER TWO WON!", 50, 200);
			g.setFont(font2);
			g.drawString("Press 'c' to go back to game.", 20, 400);
		}
		
		// easter egg
		if(intP1Score == 5 && intP2Score == 0 || intP1Score == 0 && intP2Score == 5){
			//resets paddle positions and ball positions back to what it originally was
			intSpaceBar = 1;
			intBallX = 395;
			intBallY = 295;
			intBallY = 295;
			intRectY = 250;
			intRectY2 = 250;
			
			//draws screen with easter egg
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("FUNNY JOKE HAHA!", 50, 200);
			g.setFont(font2);
			g.drawString("Press 'c' to go back to game.", 20, 400);
		}
	}
	
	///constructor
	public PongPanel(){
		super();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(800, 600));
		timer.start();
		
		try{
			Ball = ImageIO.read(new File("Ball.png")); //reading image 
			Paddle1 = ImageIO.read(new File("Paddle1.png")); //reading image 
			Paddle2 = ImageIO.read(new File("Paddle2.png")); //reading image 
		}catch(IOException e){
			System.out.println("Unable to load image."); //catching errors
		}
	}
	
}
