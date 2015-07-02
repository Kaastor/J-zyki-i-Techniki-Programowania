package Classes;
/**
 * Description:
 * Class that is description of Tank object
 */
public class Tank 
{
	static final int MAX_X = 780;
	static final int MAX_Y = 780;
	private int id;
	private int dx=0;
	private int dy=0;
	private int x=0;
	private int y=0;
	private int nextX;
	private int nextY;
	private int angle;
	private int tank_col;
	private int tank_row;
	private int TANK_SIZE =30;
	private boolean hitwall = false;
	/**
	 * Description:
	 * Constructor of object Tank
	 * @param id - identifier of tank 
	 * @param x - starting x coordinate
	 * @param y - starting y coordinate
	 */
	public Tank(int id, int x, int y)
	{
		this.id = id;
		this.x = x;
		this.y = y;	
	}
	/**
	 * Description:
	 * Method that is responsible for moving tank
	 * and checking if tank hit the borders of window
	 * @param hitWall - true if tank hit the wall
	 */
	public void MoveTank1(boolean hitWall)
	{
		if (!hitWall)
		{
			this.x = nextX;
			this.y = nextY;
		}
		// Don't let him go off the sides of the screen
		if (this.x > MAX_X - TANK_SIZE)
		{
			this.x = 0;
			this.dx = 0;
		}
		if (this.x < 0)
		{
			this.x=MAX_X - TANK_SIZE;
			this.dx = 0;
		}
		// Don't let him go off the top or bottom of the screen
		if (this.y > MAX_Y - TANK_SIZE)
		{
			this.y = 0;
			this.dy = 0;
		}
		if (this.y < 0)
		{
			this.y = MAX_Y - TANK_SIZE;
			this.dy = 0;
		}
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
	public void SetNextX()
	{
		SetHitWall(false);
		this.nextX = GetX() + GetDX();
	}
	public void SetNextY()
	{
		SetHitWall(false); //reset variable to false
		this.nextY = GetY() + GetDY();
	}
	public int GetNextX()
	{
		return this.nextX;
	}
	public int GetNextY()
	{
		return this.nextY;
	}
	
	public int GetAngle()
	{
		return this.angle;
	}
	public void SetAngle(int val)
	{
		this.angle = val;
	}
	public int GetId()
	{
		return this.id;
	}
	public boolean GetHitWall()
	{
		return this.hitwall;
	}
	public void SetHitWall(boolean val)
	{
		this.hitwall = val;
	}
	public void SetX(int x)
	{
		this.x = x;
	}
	public void SetY(int y)
	{
		this.y = y;
	}
	public int GetCol()
	{
		return this.tank_col;
	}
	public int GetRow()
	{
		return this.tank_row;
	}
	public void SetCol(int xCorner)
	{
		this.tank_col = (GetNextX() + xCorner) / TANK_SIZE;
	}
	public void SetRow(int yCorner)
	{
		this.tank_row = (GetNextY() + yCorner) / TANK_SIZE;
	}
	/**
	 * Description:
	 * Method that putting tanks in starting positions
	 * @param x
	 * @param y
	 */
	public void ResetPosition(int x, int y)
	{
		SetX(x); 
		SetY(y);
	}
	
}
