package snake_test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {


private int x;
private int y;

private final int unit;

private Color color;
public Food() {
	this.x=(new Random().nextInt(40)+1)*20;
	this.y=(new Random().nextInt(30)+1)*20;
	
	this.unit=20;
	
	this.color=Color.BLUE;
	
	
	// TODO Auto-generated constructor stub
}
public Food(int x,int y){
	this.x=x;
	this.y=y;
	
	this.unit=20;
	
	this.color=Color.BLUE;
	
}
protected  void  paintItself(Graphics g){
	g.setColor(this.color);
	g.fillOval(x, y, this.unit, this.unit);
	
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

}
