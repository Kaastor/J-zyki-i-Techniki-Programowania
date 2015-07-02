/** 
 *Description:
 *Package that contains game engine and runnable applet  
 */
package Applet;
import java.awt.*;
import Classes.*;
import javax.swing.ImageIcon;

import Classes.Bullet;
import Classes.Tank;

public final class GameTanks extends java.applet.Applet implements Runnable {

	private static final long serialVersionUID = 1L;
	Thread updateThread;// thread in which the game will run
	long startTime;			// used to keep track of timing and to prevent applet from running too fast
	Graphics gBuf;	// used for double-buffered graphics
	Image imgBuf;	// also used for double-buffered graphics
	static final int MAX_X = 780;
	static final int MAX_Y = 780;
	int k=0;
	final static int MAZE_SIZE = 26;
	//map array
	int[][] mazeArray =
		{ 	{2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,1,1,1,1,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,1,1,1,1,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,1,1,1,1,1,1,0,0,2,2,2,2,0,0,1,1,1,1,1,1,0,0,1},
			{1,0,0,1,1,1,1,1,1,0,0,2,2,2,2,0,0,1,1,1,1,1,1,0,0,1},
			{1,0,0,1,1,1,1,1,1,0,0,2,2,2,2,0,0,1,1,1,1,1,1,0,0,1},
			{1,0,0,1,1,1,1,1,1,0,0,2,2,2,2,0,0,1,1,1,1,1,1,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,1,1,1,1,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,0,0,0,0,1,1,1,1,0,0,0,0,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,0,0,0,0,1},
			{1,0,0,0,0,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
		};
	static final int TANK_SIZE = 30;
	static final int WALL_SIZE = 30;

	Tank Tank1 = new Tank(1, 30, 30);
	Tank Tank2 = new Tank(2, 720, 720);
	Bullet Bullet1 = new Bullet();
	Bullet Bullet2 = new Bullet();
	
	Image wall = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\wall.png");
	Image grass = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\grass.png");
	Image tank0 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank0.png");
	Image tank90 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank90.png");
	Image tank180 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank180.png");
	Image tank270 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank270.png");
	
	Image tank20 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank20.png");
	Image tank290 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank290.png");
	Image tank2180 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank2180.png");
	Image tank2270 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\tank2270.png");
	
	Image bullet0 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\bullet0.png");
	Image bullet90 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\bullet90.png");
	Image bullet180 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\bullet180.png");
	Image bullet270 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Przemek\\Documents\\Java2\\World of Tanks\\Images\\bullet270.png");

/** 
*Description:
*Method with start parameters, settings  
*/	
public void init()
{
	// Make the applet window the size we want it to be
	resize(MAX_X, MAX_Y);
	// Load the images we will use from the web
	getMainGraphics();
	// Garbage collection call.  Not really needed.
	System.gc();
	// Make a black background
	setBackground(Color.black);
	// Set up double-buffered graphics.
	// This allows us to draw without flickering.
	imgBuf = createImage(MAX_X, MAX_Y);
	gBuf = imgBuf.getGraphics();
	// try to grab the keyboard focus.
	requestFocus();
}
/** 
 *Description:
 *Load and process the most common graphics 
 */
public void getMainGraphics()
{
	MediaTracker tracker;
	int i = 0;
	tracker = new MediaTracker(this);
}
/** 
 *Description:
 *Method responsible for checking tanks collision with bullets
 *@return true if collision occurred
 */
public boolean TankBulletCollision()
{
	boolean collision=false;
	if( 	
			  Tank1.GetX() + 15 == Bullet2.GetX() + 15 && Tank1.GetY()      == Bullet2.GetY() + 15
			  ||Tank1.GetX() + 15 == Bullet2.GetX() + 15 && Tank1.GetY() + 30 == Bullet2.GetY() + 15
			  ||Tank1.GetX() + 30 == Bullet2.GetX() + 15 && Tank1.GetY() + 15 == Bullet2.GetY() + 15
			  ||Tank1.GetX() + 15 == Bullet2.GetX() + 15 && Tank1.GetY() + 30 == Bullet2.GetY() + 15)
		{
			Tank1.ResetPosition(30, 30); 
			Bullet2.SetShootBullet(false);
			DrawTank(Tank1);
			collision=true;
		}
	
	if( 
			  Tank2.GetX() + 15 == Bullet1.GetX() + 15 && Tank2.GetY()      == Bullet1.GetY() + 15
			  ||Tank2.GetX() + 15 == Bullet1.GetX() + 15 && Tank2.GetY() + 30 == Bullet1.GetY() + 15
			  ||Tank2.GetX() + 30 == Bullet1.GetX() + 15 && Tank2.GetY() + 15 == Bullet1.GetY() + 15
			  ||Tank2.GetX() + 15 == Bullet1.GetX() + 15 && Tank2.GetY() + 30 == Bullet1.GetY() + 15)
				{
				    Tank2.ResetPosition(720, 720); 
					Bullet1.SetShootBullet(false);
					DrawTank(Tank2);
					collision=true;
				
				}
	k++;
	if(Bullet1.GetShootBullet())
	{
		Bullet1.MoveBullet(false);
		if(k%2!=0) DrawBullet(Bullet1);
	}
	if(Bullet2.GetShootBullet())
	{
		Bullet2.MoveBullet(false);
		if(k%2!=0) DrawBullet(Bullet2);
	}
	
	
	return collision;
}
/** 
 *Description:
 *Method that is core of the game, drawing applet window, tanks, bullets, map
 *and checking collisions tanks with walls
 */
public void run()
{
	while (updateThread != null)
	{
		try
		{
			long sleepTime = Math.max(startTime - System.currentTimeMillis(),1);
			updateThread.sleep(sleepTime);
		}
		catch (InterruptedException e)
		{
		}
		startTime = System.currentTimeMillis() + 40;
		gBuf.clearRect(0, 0, MAX_X, MAX_Y);
		
		//next move for tanks
		Tank1.SetNextX(); 		Tank1.SetNextY();
		Tank2.SetNextX();		Tank2.SetNextY();

		//collisions with walls
		for (int xCorner = 0; xCorner < TANK_SIZE; xCorner += TANK_SIZE - 1)
		{
			for (int yCorner = 0; yCorner < TANK_SIZE; yCorner += TANK_SIZE - 1)
			{
				//tanks and bullets positions on map
				Tank1.SetCol(xCorner); Tank1.SetRow(yCorner);
				Tank2.SetCol(xCorner); Tank2.SetRow(yCorner);
				Bullet1.SetCol(xCorner); Bullet1.SetRow(yCorner);
				Bullet2.SetCol(xCorner); Bullet2.SetRow(yCorner);
				//collisions
				if ((Tank1.GetRow() < MAZE_SIZE && Tank1.GetCol() < MAZE_SIZE))
				{
					if (mazeArray[Tank1.GetRow()][Tank1.GetCol()] == 1)
					{
						Tank1.SetHitWall(true);	
					}
				}
				if ((Tank2.GetRow() < MAZE_SIZE && Tank2.GetCol() < MAZE_SIZE))
				{
					if (mazeArray[Tank2.GetRow()][Tank2.GetCol()] == 1)
					{
						Tank2.SetHitWall(true);	
					}
				}
				if ((Bullet1.GetRow() < MAZE_SIZE && Bullet1.GetCol() < MAZE_SIZE))
				{
					if (mazeArray[Bullet1.GetRow()][Bullet1.GetCol()] == 1)
					{
						Bullet1.SetShootBullet(false);
						mazeArray[Bullet1.GetRow()][Bullet1.GetCol()] = 0;
						
					}
				}
				if ((Bullet2.GetRow() < MAZE_SIZE && Bullet2.GetCol() < MAZE_SIZE))
				{
					if (mazeArray[Bullet2.GetRow()][Bullet2.GetCol()] == 1)
					{
						Bullet2.SetShootBullet(false);
						mazeArray[Bullet2.GetRow()][Bullet2.GetCol()] = 0;
					}
				}
			}
		}
		//Collisions Tank - Bullet
		if( TankBulletCollision() == true) continue;
		gBuf.setColor(Color.cyan);
		if(!Bullet1.GetShootBullet())
		{
			Bullet1.SetX(Tank1.GetX());
			Bullet1.SetY(Tank1.GetY());
			Bullet1.SetAngle(Tank1.GetAngle());
		}

		if( TankBulletCollision() == true) continue;
		if(!Bullet2.GetShootBullet())
		{
			Bullet2.SetX(Tank2.GetX());
			Bullet2.SetY(Tank2.GetY());
			Bullet2.SetAngle(Tank2.GetAngle());
		}
		//moving tanks
		Tank2.MoveTank1(Tank2.GetHitWall());
		Tank1.MoveTank1(Tank1.GetHitWall());
		DrawTank(Tank1);
		DrawTank(Tank2);
		
		//drawing map
		for (int row = 0; row < MAZE_SIZE; row++)
		{
			for (int col = 0; col < MAZE_SIZE; col++)
			{
				if (mazeArray[row][col] == 1)
				{
					gBuf.drawImage( wall , col * (TANK_SIZE) , row * (TANK_SIZE) , this);
				}
				if (mazeArray[row][col] == 2)
				{
					gBuf.drawImage( grass , col * (TANK_SIZE) , row * (TANK_SIZE) , this);
				}
			}
		}
		repaint();
	}
}
/** 
 *Description:
 *Method that drawing actual position tank on the map 
 *according to tank angle and coordinates
 *@param Tank
 */
void DrawTank(Tank Tank)
{

	if(Tank.GetAngle() == 0)
	{
		if(Tank.GetId() ==1)
			gBuf.drawImage( tank0 , Tank.GetX() , Tank.GetY() , this);
		else
			gBuf.drawImage( tank20 , Tank.GetX() , Tank.GetY() , this);
			
	}
	if(Tank.GetAngle() == 90)
	{
		if(Tank.GetId() ==1)
			gBuf.drawImage( tank90 , Tank.GetX() , Tank.GetY() , this);
		else
			gBuf.drawImage( tank290 , Tank.GetX() , Tank.GetY() , this);
	}
	if(Tank.GetAngle() == 180)
	{
		if(Tank.GetId() ==1)
			
			gBuf.drawImage( tank180 , Tank.GetX() , Tank.GetY() , this);
		else
			
			gBuf.drawImage( tank2180 , Tank.GetX() , Tank.GetY() , this);
	}
	if(Tank.GetAngle() ==  270)
	{
		if(Tank.GetId() ==1)
			
			gBuf.drawImage( tank270 , Tank.GetX() , Tank.GetY() , this);
		else
			
			gBuf.drawImage( tank2270 , Tank.GetX() , Tank.GetY() , this);
	}

}
/**
 * Description:
 * Method that drawing actual position tank on the map 
 *according to tank angle and coordinates
 * @param bullet that will be drawn
 */
void DrawBullet(Bullet bullet)
{
	gBuf.setColor(Color.cyan);
	if(bullet.GetShootBullet() != false)
	{	if(bullet.GetAngle() == 0)
		{
			gBuf.drawImage( bullet0 ,bullet.GetX() , bullet.GetY(), this);
		}
		if(bullet.GetAngle() == 90)
		{
			gBuf.drawImage( bullet90 , bullet.GetX() , bullet.GetY() , this);
		}
		if(bullet.GetAngle() == 180)
		{
			gBuf.drawImage( bullet180 ,bullet.GetX() , bullet.GetY() , this);
		}
		if(bullet.GetAngle() == 270)
		{
			gBuf.drawImage( bullet270 , bullet.GetX() , bullet.GetY() , this);
		}
	}
}

/**
 * Description:
 * Method responsible for keyboard handling
 * @param e
 * @param key
 */
public boolean keyDown(java.awt.Event e, int key)
{
	if (key == 1006) // left arrow
	{
	   	Tank1.SetDX(-15);
	   	Tank1.SetDY(0);
	   	Tank1.SetAngle(180);
		return false;
	}
	if (key == 1007) // right arrow
	{
		Tank1.SetDX(15);
	   	Tank1.SetDY(0);
	   	Tank1.SetAngle(0);
	    return false;
	}

	if (key == 1004) // up arrow
	{
		Tank1.SetDX(0);
	   	Tank1.SetDY(-15);
	   	Tank1.SetAngle(90);
		return false;
	}
	if (key == 1005) // down arrow
	{
		Tank1.SetDX(0);
	   	Tank1.SetDY(15);
	   	Tank1.SetAngle(270);
		return false;
	}
	if (key == 10) // shoot
	{
		Bullet1.SetShootBullet(true);
	 	return false;
	}
	
	if (key == 'a') // left arrow
	{
	   	Tank2.SetDX(-15);
	   	Tank2.SetDY(0);
	   	Tank2.SetAngle(180);
		return false;
	}
	if (key == 'd') // right arrow
	{
		Tank2.SetDX(15);
	   	Tank2.SetDY(0);
	   	Tank2.SetAngle(0);
	    return false;
	}

	if (key == 'w') // up arrow
	{
		Tank2.SetDX(0);
	   	Tank2.SetDY(-15);
	   	Tank2.SetAngle(90);
		return false;
	}
	if (key == 's') // down arrow
	{
		Tank2.SetDX(0);
	   	Tank2.SetDY(15);
	   	Tank2.SetAngle(270);
		return false;
	}
	if (key == 'f') // shoot
	{
		Bullet2.SetShootBullet(true);
	 	return false;
	}
	
	return false;
}
/**
 * Description:
 * Method responsible for keyboard handling
 * @param e
 * @param key
 */
public boolean keyUp(java.awt.Event e, int key)
{
	if (key == 1006 || key == 1007)  // left or right key released
	{
		Tank1.SetDX(0);
		return false;
	}
	if (key == 1004 || key == 1005) // up or down key released.
	{
		Tank1.SetDY(0);
		return false;
	}
	if (key == 'a' || key == 'd')  // left or right key released
	{
		Tank2.SetDX(0);
		return false;
	}
	if (key == 'w' || key == 's') // up or down key released.
	{
		Tank2.SetDY(0);
		return false;
	}
	return false;
}
/**
 * Description:
 * Method that is called when applet thread is starting
 */
public void start()
{
	if (updateThread == null)
	{
		updateThread = new Thread(this, "Payer1");
		updateThread.start();
		startTime = System.currentTimeMillis();
	}
	
}
/**
 * Description:
 * Method that is called when applet thread is stopping
 */
public void stop()
{
	updateThread = null;
}
/**
 * Description:
 * Method responsible for mouse handling, requesting focus on applet window
 * @param e
 * @return false
 */
public boolean mouseDown(java.awt.Event e)
{
	requestFocus();
	return false;
}
/**
 * Description:
 * Method that clears the applet background
 */
public void update(Graphics g)
{
	g.drawImage(imgBuf, 0, 0, this);
}

	
}