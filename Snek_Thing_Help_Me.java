// Nikita Berezyuk & Christopher Fernandes
// Final Cpt
// Snake and Pong
// Jan 23rd 2020
// Verified By: Sebastian Kure
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import hsa.Console;
import java.awt.image.*;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.image.*;
public class Snek_Thing_Help_Me extends JFrame
{

    static Console c;
    static int[] lastSquarex = new int [1000]; // Setting most variables at the start of the class so that they may be accessed without parameters or arguments
    static int[] lastSquarey = new int [1000]; // Creates an array for the x and y of 1000 chains
    static int currX = 10, currY = 9; // Sets snake to spawn in the middle
    static int score = 0;
    static int snakespeed = 0;
    static char move = ' ';
    static char lastMove = ' ';
    static int displayScore = 0;
    static int appleX = (int) (20 * Math.random () + 0); // Generates random location for the first apple
    static int appleY = (int) (18 * Math.random () + 0);
    static boolean death = false;
    static String paddleR = "r", paddleL = "l", paddleHit = " ", wallHit = " ", choice2;
    static boolean x, y, start;
    static int y1 = 300, y2 = 300, ballX = 470, ballY = 320, ballNumX, ballNumY, score1, score2;
    static int random1 = (int) (4 * Math.random () + 0);
    static int random2 = (int) (4 * Math.random () + 0);
    static Color transparent = new Color (0, 255, 0, 127);
    static char next;
    public static Image image1; //Image class to print an image
    public static void main (String[] args) throws InterruptedException
    {
	c = new Console (46, 150);
	intro (); // Goes to introduction screen
	menu ();
	modes ();
    }


    public static void intro () throws InterruptedException //method that makes the text "SQUIRM. IO" move across the screen
    {
	try
	{
	    Thread.sleep (100);
	}

	catch (InterruptedException e)
	{
	}

	for (int i = 0 ; i < 225 ; i++) //for loop that prints the name, clears it, shifts it, and reprints it
	{
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 5000, 5000);
	    c.setColor (Color.red);
	    c.setFont (new Font ("Helvetica", Font.BOLD, 100));
	    c.drawString ("SQUIRM. IO", 0 + i, 145);
	    Thread.sleep (5);
	}
    }


    public static void menu () throws InterruptedException //the main menu of the game
    {
	c.setColor (Color.black); //prints the background
	c.fillRect (0, 0, 5000, 5000);
	c.setColor (Color.red);
	c.setFont (new Font ("Helvetica", Font.BOLD, 100)); //title
	c.drawString ("SQUIRM. IO", 225, 145);
	c.setColor (Color.red);
	c.setFont (new Font ("TimesRoman", Font.PLAIN, 50));
	c.drawString ("Press SPACE To Select Choice", 210, 624);
	c.setColor (Color.red);
	c.fillRect (70, 200, 400, 150);
	c.setColor (Color.white);
	//prints all of the options
	c.setFont (new Font ("Helvetica", Font.PLAIN, 60));
	c.drawString ("A. Easy Mode", 90, 290);
	c.setColor (Color.red);
	c.fillRect (70, 400, 400, 150);
	c.setColor (Color.white);
	c.setFont (new Font ("Helvetica", Font.PLAIN, 60));
	c.drawString ("B. Hard Mode", 90, 490);
	c.setColor (Color.red);
	c.fillRect (550, 200, 400, 150);
	c.setColor (Color.white);
	c.setFont (new Font ("Helvetica", Font.PLAIN, 60));
	c.drawString ("C. Instructions", 560, 290);
	c.setColor (Color.black);
	c.fillRect (550, 400, 400, 150);
	c.setColor (Color.black);
	c.setFont (new Font ("Helvetica", Font.PLAIN, 53));
	c.drawString ("D. Secret Game", 560, 490); //not visisble until D is pressed
    }


    public static void modes () throws InterruptedException //the selection options of the game
    {
	while (true)
	{
	    //when they select an option it will highlight it green
	    //to confirm their selection, they press space and the game will run
	    char choice = c.getChar ();
	    if (choice == 'a')
	    {
		menu ();
		c.setColor (Color.green);
		c.fillRect (70, 200, 400, 150);
		c.setColor (Color.white);
		c.setFont (new Font ("Helvetica", Font.PLAIN, 60));
		c.drawString ("A. Easy Mode", 90, 290);
		choice2 = "a";

	    }

	    else if (choice == ' ' && choice2 == "a") //when A is confirmed, easy mode of snake will play
	    {
		c.clear ();
		loading ();
		snakespeed = 125;
		game ();

	    }

	    if (choice == 'b')
	    {
		menu ();
		c.setColor (Color.green);
		c.fillRect (70, 400, 400, 150);
		c.setColor (Color.white);
		c.setFont (new Font ("Helvetica", Font.PLAIN, 60));
		c.drawString ("B. Hard Mode", 90, 490);
		choice2 = "b";
	    }

	    else if (choice == ' ' && choice2 == "b") //when B is pressed, Hard mode of snake will play
	    {
		c.clear ();
		loading ();
		snakespeed = 75;
		game ();

	    }

	    if (choice == 'c')
	    {
		menu ();
		c.setColor (Color.green);
		c.fillRect (550, 200, 400, 150);
		c.setColor (Color.white);
		c.setFont (new Font ("Helvetica", Font.PLAIN, 60));
		c.drawString ("C. Instructions", 560, 290);
		choice2 = "c";
	    }
	    else if (choice == ' ' && choice2 == "c") //prints the instructions and credits
	    {
		c.clear ();
		instruction ();
		next = c.getChar (); //when they press any key, they can go back to the menu to play the games
		menu ();
	    }

	    if (choice == 'd') //when D is pressed, a secret new mode appears which is Pong
	    {
		menu ();
		c.setColor (Color.green);
		c.fillRect (550, 400, 400, 150);
		c.setColor (Color.white);
		c.setFont (new Font ("Helvetica", Font.PLAIN, 53));
		c.drawString ("D. Secret Game", 560, 490);
		choice2 = "d";
	    }
	    else if (choice == ' ' && choice2 == "d") //if they confirm D, Pong will play
	    {
		c.clear ();
		char input = ' ';
		secretGame (input, random1, random2);
	    }
	}
    }


    public static void loading ()  //the fake loading bar screen
    {
	c.setColor (Color.red);
	c.setFont (new Font ("Arial", Font.PLAIN, 130));
	c.drawString ("Loading", 290, 135);
	c.setColor (Color.red);
	c.setFont (new Font ("Arial", Font.PLAIN, 130));
	c.drawString (".", 770, 135); // prints three dots one after the other
	try
	{
	    Thread.sleep (500);
	}

	catch (InterruptedException e)
	{
	}
	c.setColor (Color.red);
	c.drawString (".", 800, 135);
	try
	{
	    Thread.sleep (500);
	}

	catch (InterruptedException e)
	{
	}
	c.setColor (Color.red);
	c.drawString (".", 830, 135);
	try
	{
	    Thread.sleep (100);
	}

	catch (InterruptedException e)
	{
	}

	for (int i = 0 ; i < 800 ; i++) //for loop that draws a moving bar
	{
	    c.setColor (Color.blue);
	    c.fillRect (70, 225, 70 + i, 75);
	    try
	    {
		Thread.sleep (2); //changes the speed at which it moves
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
    }


    public static void instruction ()  //when the user selects C in the menu, the instruction () method is shown
    {
	c.setColor (Color.orange);
	c.setFont (new Font ("Helvetica", Font.BOLD, 55));
	c.drawString ("MADE BY CHRIS AND NIKITA", 140, 80); //credits to Chris and Nikita
	c.setColor (Color.black);
	c.setFont (new Font ("Arial", Font.PLAIN, 45));
	c.drawString ("Menu: ", 100, 150);
	c.setFont (new Font ("Arial", Font.PLAIN, 35));
	c.drawString ("1) Use keys A, B, C, or D to select mode in menu", 100, 210);
	c.drawString ("2) Use the space key to confirm your selection ", 100, 260);
	c.setFont (new Font ("Arial", Font.PLAIN, 45));
	c.drawString ("Snake: ", 100, 320);
	c.setFont (new Font ("Arial", Font.PLAIN, 35));
	c.drawString ("3) Use W, A, S, D keys to move snake in desired location", 100, 370);
	c.setFont (new Font ("Arial", Font.PLAIN, 45));
	c.drawString ("Pong: ", 100, 430);
	c.setFont (new Font ("Arial", Font.PLAIN, 35));
	c.drawString ("4) Player on left side uses W and S keys", 100, 480);
	c.drawString ("Player on right side uses O and L keys", 140, 530);
	c.drawString ("5) First one to 5 points wins!", 100, 580);
	c.setColor (Color.orange);
	c.setFont (new Font ("Arial", Font.BOLD, 35));
	c.drawString ("Press any key to go back to the menu", 220, 630);
    }


    public static void secretGame (char input, int random1, int random2) throws InterruptedException
    {
	if (random1 > 1) //uses the random1 and random2 variables to create a 50/50 chance of where the ball with travel
	{ // random1 can either make the ball go left or right
	    ballNumX = 12;
	}
	else
	{
	    ballNumX = -12;
	}
	if (random2 > 1)
	{ // random2 can either make the ball go up or down
	    ballNumY = 12;
	}
	else
	{
	    ballNumY = -12;
	}
	char play = ' '; //declares variable play
	screen (play); //displays the front screen of Pong
	while (start)
	{
	    pongGrid ();
	    paddle1 ();
	    paddle2 ();
	    points ();
	    ball ();
	    if (score1 == 5) //if statement to check if player1 scored
	    {
		// displays the victory screen and asks whether they want to play again or not
		c.setColor (Color.red);
		c.setFont (new Font ("Arial", Font.BOLD, 90));
		c.drawString (String.valueOf (score1), 300, 75);
		c.setColor (Color.blue);
		c.setFont (new Font ("Arial", Font.BOLD, 90));
		c.drawString (String.valueOf (score2), 600, 75);
		Thread.sleep (500);
		c.clear ();
		c.setColor (Color.red);
		c.fillRect (0, 0, 1000, 1000);
		c.setColor (Color.black);
		c.setFont (new Font ("Arial", Font.BOLD, 100));
		c.drawString ("Player 1 Wins!", 250, 400);
		Thread.sleep (2000);
		gameEnd ();
	    }
	    if (score2 == 5)  //if statement to check if player2 scored
	    {
		// displays the victory screen and asks whether they want to play again or not
		Thread.sleep (500);
		c.clear ();
		c.setColor (Color.blue);
		c.fillRect (0, 0, 1000, 1000);
		c.setColor (Color.white);
		c.setFont (new Font ("Arial", Font.BOLD, 50));
		c.drawString ("Player 2 Wins!", 250, 400);
		Thread.sleep (2000);
		gameEnd ();
	    }
	    Thread.sleep (25);
	    boolean press2 = c.isCharAvail ();
	    if (press2 == true)
	    {
		input = c.getChar ();
		move1 (input);
		move2 (input);
	    }
	}
    }


    public static void gameEnd () throws InterruptedException // Resets game and returns to menu
    {
	c.setColor (Color.white);
	c.fillRect (0, 0, 1000, 1000);
	c.setColor (Color.red);
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Press Any Key To Return To Menu", 100, 400);
	char play2 = c.getChar ();
	score1 = 0;
	score2 = 0;
	ballX = 470;
	ballY = 320;
	intro ();
	menu ();
	modes ();
    }


    public static void screen (char play) throws InterruptedException //the main screen of the Pong game
    {
	c.clear ();
	for (int i = 0 ; i < 300 ; i += 60)
	{
	    Color transparent = new Color (0, 0 + i, 0 + i, 50);
	    c.setColor (transparent);
	    c.fillRect (0, 0, 5000, 5000);
	}
	imgtest ();
	Thread.sleep (500);
	c.setColor (Color.white);
	c.setFont (new Font ("Elephant", Font.PLAIN, 90));
	c.drawString ("Welcome To Pong!", 100, 150);
	c.setFont (new Font ("Elephant", Font.PLAIN, 40));
	c.drawString ("Press Any Key To Continue", 245, 450);
	play = c.getChar ();
	start = true; //as soon as they press any key, the game will begin
    }


    public static void imgtest ()
    {
	Toolkit tk = Toolkit.getDefaultToolkit ();
	image1 = tk.getImage ("pepe.png");
	tk.prepareImage (image1, -1, -1, new Observer1 ());
    }


    static class Observer1 implements ImageObserver
    {
	public boolean imageUpdate (Image img, int infoflags, int x, int y, int width, int height)
	{

	    if (infoflags == 32)
	    {
		c.drawImage (img, 0, 0, null);
	    }
	    return true;
	}
    }


    public static void pongGrid () throws InterruptedException
    {
	c.setColor (Color.BLACK);
	c.fillRect (0, 0, 5000, 5000);
	c.setColor (Color.white);
	c.fillRect (0, 80, 5000, 10);
	c.fillRect (0, 650, 5000, 10);
	c.setColor (Color.red);
	c.setFont (new Font ("Arial", Font.BOLD, 90));
	c.drawString (String.valueOf (score1), 300, 75);
	c.setColor (Color.blue);
	c.setFont (new Font ("Arial", Font.BOLD, 90));
	c.drawString (String.valueOf (score2), 600, 75);
    }


    public static void move1 (char input)
    {
	if (input == 'w')
	{
	    y1 -= 40;
	}
	if (input == 's')
	{
	    y1 += 40;
	}
	if (y1 <= 90)
	{
	    y1 = 90;
	}
	if (y1 >= 450)
	{
	    y1 = 450;
	}
    }


    public static void move2 (char input)
    {
	if (input == 'o')
	{
	    y2 -= 40;
	}
	if (input == 'l')
	{
	    y2 += 40;
	}
	if (y2 <= 90)
	{
	    y2 = 90;
	}
	if (y2 >= 450)
	{
	    y2 = 450;
	}
    }


    public static void paddle1 ()
    {
	c.setColor (Color.white);
	c.fillRect (100, y1, 35, 200);

    }


    public static void paddle2 ()
    {
	c.setColor (Color.white);
	c.fillRect (850, y2, 35, 200);

    }


    public static void ball () throws InterruptedException
    {
	c.setColor (Color.white);
	c.fillOval (ballX, ballY, 40, 40);
	ballX += ballNumX;
	ballY += ballNumY;
	//points ();
	checkCollision ();
    }


    public static void checkCollision ()
    {
	//if paddle1  is hit
	if (ballX <= 120 && ballX >= 95)
	{
	    if ((y1 + 220) >= (ballY + 40) && (y1) <= (ballY + 40))
	    {
		paddleHit = paddleL;
	    }
	}
	// if paddle2 is hit
	if (ballX >= 828 && ballX <= 863)
	{
	    if ((y2 + 220) >= (ballY + 40) && (y2) <= (ballY + 40))
	    {
		paddleHit = paddleR;
	    }
	}
	// top wall is hit
	if (ballY <= 90)
	{
	    ballNumY = 12;
	    wallHit = "top";
	}
	// Bottom wall is hit
	if (ballY >= 605)
	{
	    ballNumY = -12;
	    wallHit = "bottom";
	}

	//if it hits a paddle from the top wall
	if (wallHit.equals ("top") && paddleHit == paddleR)
	{
	    ballNumX = -12;
	    ballNumY = 12;
	}
	if (wallHit.equals ("top") && paddleHit == paddleL)
	{
	    ballNumX = 12;
	    ballNumY = 12;
	}
	// if it hits a paddle from the bottom wall

	if (wallHit.equals ("bottom") && paddleHit == paddleR)
	{
	    ballNumX = -12;
	    ballNumY = -12;
	}
	if (wallHit.equals ("bottom") && paddleHit == paddleL)
	{
	    ballNumX = 12;
	    ballNumY = -12;
	}
    }


    public static void points () throws InterruptedException
    {
	//passes the left paddle
	if (ballX < 0)
	{
	    score2 += 1; //adds score
	    ballX = 470;
	    ballY = 320;
	    random1 = (int) (4 * Math.random () + 0);
	    random2 = (int) (4 * Math.random () + 0);
	    if (random1 > 1)
	    {
		ballNumX = 12;
	    }
	    else
	    {
		ballNumX = -12;
	    }
	    if (random2 > 1)
	    {
		ballNumY = 12;
	    }
	    else
	    {
		ballNumY = -12;
	    }
	    try
	    {
		Thread.sleep (800);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
	//passes the right paddle
	if (ballX > 970)
	{
	    score1 += 1; //adds score
	    ballX = 470;
	    ballY = 300;
	    random1 = (int) (4 * Math.random () + 0);
	    random2 = (int) (4 * Math.random () + 0);
	    if (random1 > 1)
	    {
		ballNumX = 12;
	    }
	    else
	    {
		ballNumX = -12;
	    }
	    if (random2 > 1)
	    {
		ballNumY = 12;
	    }
	    else
	    {
		ballNumY = -12;
	    }
	    try
	    {
		Thread.sleep (800);
	    }
	    catch (InterruptedException e)
	    {
	    }
	}
    }


    public static void thanks ()
    {
    }


    public static void win ()
    {
	c.clear ();
	c.setColor (Color.yellow);
	c.fillRect (0, 0, 1000, 1000);
    }


    public static void game () throws InterruptedException
    {
	c.clear ();
	while (true)
	{
	    displayScore = score * 100;
	    lastSquarex [0] = currX; // Sets the current x and y value to the first previous values
	    lastSquarey [0] = currY;
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 1000, 10); // Creates border around the play space
	    c.fillRect (0, 0, 10, 900);
	    c.fillRect (0, 900, 1010, 10);
	    c.fillRect (1000, 0, 10, 900);
	    for (int i = 0 ; i < (score + 1) ; i++)
	    {
		c.setColor (Color.green);
		c.fillRect ((lastSquarex [i] * 50), (lastSquarey [i] * 50), 50, 50); // Draws head and all chains
	    }
	    for (int j = (score + 1) ; j > 0 ; j--)
	    {
		lastSquarex [j] = lastSquarex [j - 1]; // Moves previous cordinates down the array
		lastSquarey [j] = lastSquarey [j - 1];
	    }
	    c.setColor (Color.red);
	    c.fillOval (appleX * 50, appleY * 50, 50, 50); // Draws apple
	    c.setFont (new Font ("Arial", Font.BOLD, 40));
	    c.setColor (Color.black);
	    c.drawString ("Score =", 1040, 100);  // Draws Score
	    c.drawString (String.valueOf (displayScore), 1075, 150);
	    if ((currX * 50) == (appleX * 50) && (currY * 50) == (appleY * 50)) // Detects if player ate the apple
	    {
		score++;
		appleX = (int) (20 * Math.random () + 0); // Gets new cordinates for apple
		appleY = (int) (18 * Math.random () + 0);
		c.fillOval (appleX * 50, appleY * 50, 50, 50); // Draws new apple
	    }

	    boolean press = c.isCharAvail (); // Detects if player is pressing a key
	    if (press == true)
	    {
		move = c.getChar ();
		if (move == 'w' || move == 'a' || move == 's' || move == 'd')
		{
		    lastMove = move;
		}
		else
		{
		    move = lastMove;
		}
	    }
	    if (move == 'w' || move == 's') // Moves y based on key pressed
	    {
		controlsY ();
	    }
	    else if (move == 'a' || move == 'd') // Moves x based on key pressed
	    {
		controlsX ();
	    }
	    else
	    {
	    if (lastMove == 'w' || lastMove == 's') // Moves y based on last key pressed
	    {
		lastControlsY ();
	    }
	    else if (lastMove == 'a' || lastMove == 'd') // Moves x based on last key pressed
	    {
		lastControlsX ();
	    }
	    }
	}
    }


    public static void controlsY () throws InterruptedException
    {
	if (move == 'w')
	{
	    currY -= 1;
	}


	if (move == 's')
	{
	    currY += 1;
	}


	for (int i = 1 ; i < (lastSquarex.length - 1) ; i++) // Detects if player collided with themself
	{
	    if (currX == lastSquarex [i] && currY == lastSquarey [i])
	    {
		if (currX != 0 && currY != 0)
		{
		    death = true;
		    gameStop ();
		}
	    }
	}


	if (currY <= -1) // Detects if player collided with the top wall
	{
	    gameStop ();
	}


	if ((currY * 50) >= 900) // Detects if player collided with the bottom wall
	{
	    gameStop ();
	}


	try
	{
	    Thread.sleep (snakespeed);
	}


	catch (InterruptedException e)
	{
	}


	c.clear ();
    }



    public static void controlsX () throws InterruptedException
    {
	if (move == 'a')
	{
	    currX -= 1;
	}


	if (move == 'd')
	{
	    currX += 1;
	}


	for (int i = 1 ; i < (lastSquarex.length - 1) ; i++) // Detects if player collided with themself
	{
	    if (currX == lastSquarex [i] && currY == lastSquarey [i])
	    {
		if (currX != 0 && currY != 0)
		{
		    death = true;
		    gameStop ();
		}
	    }
	}


	if (currX <= -1) // Detects if player collided with the left wall
	{
	    gameStop ();
	}


	if ((currX * 50) >= 1000) // Detects if player collided with the right wall
	{
	    gameStop ();
	}


	try
	{
	    Thread.sleep (snakespeed);
	}


	catch (InterruptedException e)
	{
	}


	c.clear ();

    }
    public static void lastControlsY () throws InterruptedException
    {
	if (lastMove == 'w')
	{
	    currY -= 1;
	}


	if (lastMove == 's')
	{
	    currY += 1;
	}


	for (int i = 1 ; i < (lastSquarex.length - 1) ; i++) // Detects if player collided with themself
	{
	    if (currX == lastSquarex [i] && currY == lastSquarey [i])
	    {
		if (currX != 0 && currY != 0)
		{
		    death = true;
		    gameStop ();
		}
	    }
	}


	if (currY <= -1) // Detects if player collided with the top wall
	{
	    gameStop ();
	}


	if ((currY * 50) >= 900) // Detects if player collided with the bottom wall
	{
	    gameStop ();
	}


	try
	{
	    Thread.sleep (snakespeed);
	}


	catch (InterruptedException e)
	{
	}


	c.clear ();
    }



    public static void lastControlsX () throws InterruptedException
    {
	if (move == 'a')
	{
	    currX -= 1;
	}


	if (move == 'd')
	{
	    currX += 1;
	}


	for (int i = 1 ; i < (lastSquarex.length - 1) ; i++) // Detects if player collided with themself
	{
	    if (currX == lastSquarex [i] && currY == lastSquarey [i])
	    {
		if (currX != 0 && currY != 0)
		{
		    death = true;
		    gameStop ();
		}
	    }
	}


	if (currX <= -1) // Detects if player collided with the left wall
	{
	    gameStop ();
	}


	if ((currX * 50) >= 1000) // Detects if player collided with the right wall
	{
	    gameStop ();
	}


	try
	{
	    Thread.sleep (snakespeed);
	}


	catch (InterruptedException e)
	{
	}


	c.clear ();

    }


    public static void gameStop () throws InterruptedException
    {

	c.setFont (new Font ("Arial", Font.BOLD, 100));
	c.setColor (Color.black);
	c.drawString ("GAME OVER!", 200, 400);
	c.setFont (new Font ("Arial", Font.BOLD, 50));
	if (death) // Determines and displays cause of death
	{
	    c.drawString ("You Collided With Yourself", 200, 500);
	}
	else
	{
	    c.drawString ("You Collided With The Wall", 200, 500);
	}
	Thread.sleep (500);
	c.drawString ("Press Any Key To Return To Menu", 150, 600);
	c.setFont (new Font ("Arial", Font.BOLD, 25));
	char backToMenu = c.getChar ();
	c.clear ();
	lastSquarex = new int [1000];
	lastSquarey = new int [1000]; // Resets the array for the x and y of 1000 chains
	currX = 10;
	currY = 9; // Resets snake location
	score = 0; // Resets score
	move = ' ';
	lastMove = ' ';
	displayScore = 0;
	appleX = (int) (20 * Math.random () + 0); // Regenerates random location for the first apple
	appleY = (int) (18 * Math.random () + 0);
	boolean death = false;
	intro ();
	menu ();
	modes ();
    }
}
