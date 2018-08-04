package snake_test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;


public class Snake  {
@SuppressWarnings("unused")
private int height;
@SuppressWarnings("unused")
private int width;
private int unit ;
private int x;
private int y;
private int speed;
private Dir direction;
private Image img=null;
private int length ;
private ArrayList<Snode> snake=new ArrayList<Snode>();
private boolean upPower;
private boolean downPower;
private boolean leftPower;
private boolean rightPower;

public boolean isUpPower(){
	return this.upPower;
}

public boolean isDownPower(){
	return this.downPower;
}
public boolean isLeftPower(){
	return this.leftPower;
}
public boolean isRightPower(){
	return this.rightPower;
}
//方向参数唯一性检查，对未添加此函数前防止蛇回头很头疼，加了它后效果问题得到解决！！！
public void valid(Dir dir){
	switch (dir) {
	case UP:
		this.downPower=false;
		this.upPower=this.leftPower=this.rightPower=true;
		break;
	case DOWN:
		this.upPower=false;
		this.downPower=this.leftPower=this.rightPower=true;
		break;
	case LEFT:
		this.rightPower=false;
		this.upPower=this.downPower=this.leftPower=true;
		break;
	case RIGHT:
		this.leftPower=false;
		this.upPower=this.downPower=this.rightPower=true;
		break;
	default:
		break;
	}
	
	return ;
}


public int getXcoor(){
	return this.x;
}
public int getYcoor(){
	return this.y;
}
public ArrayList<Snode> getSnakeHead(){
	return this.snake;
}
public int getSnakeLen(){
	return this.length;
}
public void setSnakeLen(int len){
	this.length=len;
}



public void setDir(Dir dir){
	direction=dir;
}


public Dir getDir(){
	return direction;
}

//模拟蛇移动的函数
public void move(Dir dir){
	this.valid(direction);
	switch (dir) {
	case UP:
	if(this.upPower)
		y-=20;
		break;
	case DOWN:
	if(this.downPower)
		y+=20;
		break;
	case LEFT:
	if(this.leftPower)
		x-=20;
		break;
	case RIGHT:
	if(this.rightPower)	
		x+=20;
		break;

	default:
		break;
	}
	
	for(int i=this.length-1;i>0;i--){
		snake.get(i).setX(snake.get(i-1).getX());
		snake.get(i).setY(snake.get(i-1).getY());
	}
	snake.get(0).setX(x);
	snake.get(0).setY(y);
}




public Snake(){
	
	super();
	this.height=200;
	this.width=300;
	this.unit=20;
	this.x=(new Random().nextInt(40)+1)*20;
	this.y=(new Random().nextInt(30)+1)*20;
	this.setSpeed(Speed_options.SECONDARY);
	direction=Dir.LEFT;
	this.img=createIconImage("head.png");
	this.length=10;
	for(int i=0;i<this.length;i++)
	{
		Snode n0=new Snode(x+i*unit,y,Color.black);
		snake.add(n0);
	}
//	for(int i=0;i<this.length;i++)
//		System.out.println(snake.get(i).getX());
//	timer.start();
}



public Snake(int height,int width,int unit,int x,int y,int speed,Dir diretion_set,String ImgPath,int length){
	super();
	this.height=height;
	this.width=width;
	this.unit=unit;
	direction=diretion_set;
	this.upPower=true;
	this.downPower=true;
	this.leftPower=true;
	this.rightPower=true;
	this.x=x;
	this.y=y;
	this.setSpeed(speed);
	this.img=createIconImage(ImgPath);
	this.length=length;
	for(int i=0;i<this.length;i++)
	{
		Snode n0=new Snode(x+i*unit,y,Color.black);
		snake.add(n0);
	}
	
}




//paintCompnonet方法在绘制Snake面板时会被调用，算是回调方法，实际是重写了父类的方法，在构造时在构成GUI界面时需要调用paint（）方法时，该方法会依次调用paintComponent,paintBorder,paintChildren.作为继承了JPanel面板的Snake也就可以如此直接隐式调用
protected void paintItself(Graphics g,JComponent observer){
	
	for(int i=0;i<this.length;i++){
	g.setColor(snake.get(i).getColor());
	g.fillOval(snake.get(i).getX(),snake.get(i).getY(),this.unit,this.unit);
	}
	g.drawImage(img, (int)snake.get(0).getX(), (int)snake.get(0).getY(), unit, unit, observer);
}



protected Image createIconImage(String path) {
	java.net.URL imgURL=Snake.class.getResource(path);
	if(imgURL!=null){
		return new ImageIcon(imgURL).getImage();
	}else return null;
	
}


public int getSpeed() {
	return speed;
}


public void setSpeed(int speed) {
	this.speed = speed;
}




}
/*
 * @Documented 
 * 贪吃蛇的主体类
 * 构造思路：把蛇再解耦出蛇头和蛇身两部分，以字段（属性）形式给出，以蛇头牵动蛇身，实现整体移动，为实现蛇头和蛇身强关联性利用了Array的复杂版本ArrayList作为蛇整体的原生类型
 * 构造蛇的属性和方法：
 * 属性解耦，                                                                					//width，height是画板属性*
 * 静态属性：unit是蛇的单位蛇体的直径，length是蛇的长度，img是蛇的头像，snake是蛇的核心模拟字段，故需要构造一个类作为相关类型存储其他属性
 * 动态属性：speed是蛇的行走速度（所以就使用了线程类使得speed有了体现），direction是蛇的行走方向，x,y是蛇头的横纵坐标。//动态属性在蛇行进时时刻处于修改状态的。如此一来方可由动态的体现。
 * 方法解耦
 * move模拟蛇移动行走，paintComponent的方法画出这种模拟行走，两者分工明确，配合起来才能达到好效果。
 * 任何类的构造方法均可视为一个动态的过程，用于模拟出动态来。
 * 内聚体现于动态方法里对静态的方法（不是一定是static方法）使用恰当，使用得好就是拼接的接口高度吻合，效果自然好。
*/



/*                              	俄罗斯方块
 * 			此为java的程序设计的核心理念，增强单个模块的完整独立性（低耦合:即"避免藕断丝连"），保证多个模块有可以拼接的接口的高度吻合性（高内聚）。
 *			批注：此处的模块不仅仅可以说是类，还可以用在方法的设计。方法往往是为读写属性而构造的。 
 * 			动态方法要严格和静态方法划清界限，不然弄得不三不四，最后程序的效果肯定不会太好的。
 *			例如在paint里模拟移动就会跟move耦合了。 
 * 
 * 			对于几乎没有关联性和关联性甚微的可以借助MVP完全解耦的思路设计
 * 			对于存在一定关联性的分清楚其关系（平等互嵌或者等级分明）借助MVC控制解耦的思路设计
 * 
 */
