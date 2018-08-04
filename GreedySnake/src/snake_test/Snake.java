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
//�������Ψһ�Լ�飬��δ��Ӵ˺���ǰ��ֹ�߻�ͷ��ͷ�ۣ���������Ч������õ����������
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

//ģ�����ƶ��ĺ���
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




//paintCompnonet�����ڻ���Snake���ʱ�ᱻ���ã����ǻص�������ʵ������д�˸���ķ������ڹ���ʱ�ڹ���GUI����ʱ��Ҫ����paint��������ʱ���÷��������ε���paintComponent,paintBorder,paintChildren.��Ϊ�̳���JPanel����SnakeҲ�Ϳ������ֱ����ʽ����
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
 * ̰���ߵ�������
 * ����˼·�������ٽ������ͷ�����������֣����ֶΣ����ԣ���ʽ����������ͷǣ������ʵ�������ƶ���Ϊʵ����ͷ������ǿ������������Array�ĸ��Ӱ汾ArrayList��Ϊ�������ԭ������
 * �����ߵ����Ժͷ�����
 * ���Խ��                                                                					//width��height�ǻ�������*
 * ��̬���ԣ�unit���ߵĵ�λ�����ֱ����length���ߵĳ��ȣ�img���ߵ�ͷ��snake���ߵĺ���ģ���ֶΣ�����Ҫ����һ������Ϊ������ʹ洢��������
 * ��̬���ԣ�speed���ߵ������ٶȣ����Ծ�ʹ�����߳���ʹ��speed�������֣���direction���ߵ����߷���x,y����ͷ�ĺ������ꡣ//��̬���������н�ʱʱ�̴����޸�״̬�ġ����һ�������ɶ�̬�����֡�
 * ��������
 * moveģ�����ƶ����ߣ�paintComponent�ķ�����������ģ�����ߣ����߷ֹ���ȷ������������ܴﵽ��Ч����
 * �κ���Ĺ��췽��������Ϊһ����̬�Ĺ��̣�����ģ�����̬����
 * �ھ������ڶ�̬������Ծ�̬�ķ���������һ����static������ʹ��ǡ����ʹ�õúþ���ƴ�ӵĽӿڸ߶��Ǻϣ�Ч����Ȼ�á�
*/



/*                              	����˹����
 * 			��Ϊjava�ĳ�����Ƶĺ��������ǿ����ģ������������ԣ������:��"����ź��˿��"������֤���ģ���п���ƴ�ӵĽӿڵĸ߶��Ǻ��ԣ����ھۣ���
 *			��ע���˴���ģ�鲻��������˵���࣬���������ڷ�������ơ�����������Ϊ��д���Զ�����ġ� 
 * 			��̬����Ҫ�ϸ�;�̬����������ޣ���ȻŪ�ò������ģ��������Ч���϶�����̫�õġ�
 *			������paint��ģ���ƶ��ͻ��move����ˡ� 
 * 
 * 			���ڼ���û�й����Ժ͹�������΢�Ŀ��Խ���MVP��ȫ�����˼·���
 * 			���ڴ���һ�������Եķ�������ϵ��ƽ�Ȼ�Ƕ���ߵȼ�����������MVC���ƽ����˼·���
 * 
 */
