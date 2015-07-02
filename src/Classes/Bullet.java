package Classes;
/**
 * Description:
 * Class that is description of Bullet object
 */
public class Bullet 
{
	static final int MAX_X = 780;
	static final int MAX_Y = 780;
	static final int TANK_SIZE = 30;
	private int dx=0;
	private int dy=0;
	private int x=0;
	private int y=0;
	private int nextX = 15;
	private int nextY = 15;
	private int angle;
	private int bullet_col;
	private int bullet_row;
	private boolean shootBullet = false;
	/**
	 * Description:
	 * Method that is responsible for moving bullet
	 * and checking if bullet hit the borders of window
	 * @param hitWall2 - true if bullet hit the wall
	 */
	public void MoveBullet(boolean hitWall2)
	{
		if (this.x > MAX_X - TANK_SIZE)
		{
			shootBullet = false;
		}
		if (this.x < 0)
		{
			shootBullet = false;
		}
		// Don't let him go off the top or bottom of the screen
		if (this.y > MAX_Y - TANK_SIZE)
		{
			shootBullet = false;
		}
		if (this.y < 0)
		{
			shootBullet = false;
		}
		
		if (!hitWall2)
		{
			
			if(this.angle == 0)
			{
				this.x = this.x + this.nextX;
			}
			if(this.angle == 90)
			{
				this.y = this.y - this.nextY;
			}
			if(this.angle == 180)
			{
				this.x = this.x - this.nextX;
			}
			if(this.angle == 270)
			{
				this.y = this.y + this.nextY;
			}
		}
		// Don't let him go off the sides of the screen
		
	}
	
	public int GetX()
	{
		return this.x;
	}
	public int GetY()
	{
		return this.y;
	}
	public void SetDX(int val)
	{
		this.dx = val;
	}
	public void SetDY(int val)
	{
		this.dy = val;
	}
	public int GetDX()
	{
		return this.dx;
	}
	public int GetDY()
	{
		return this.dy;
	}
	public void SetNextX(int val)
	{
		this.nextX = val;
	}
	public void SetNextY(int val)
	{
		this.nextY = val;
	}
	public void SetX(int val)
	{
		this.x = val;
	}
	public void SetY(int val)
	{
		this.y = val;
	}
	public int GetAngle()
	{
		return this.angle;
	}
	public void SetAngle(int val)
	{
		this.angle = val;
	}
	public int GetNextX()
	{
		return this.nextX;
	}
	public int GetNextY()
	{
		return this.nextY;
	}
	public void SetShootBullet(boolean val)
	{
		this.shootBullet = val;
	}
	public boolean GetShootBullet()
	{
		return this.shootBullet;
	}
	public int GetCol()
	{
		return this.bullet_col;
	}
	public int GetRow()
	{
		return this.bullet_row;
	}
	public void SetCol(int xCorner)
	{
		this.bullet_col = (GetX() + GetDX() + xCorner) / TANK_SIZE;
	}
	public void SetRow(int yCorner)
	{
		this.bullet_row = (GetY() + GetDY() + yCorner) / TANK_SIZE;
	}
}
