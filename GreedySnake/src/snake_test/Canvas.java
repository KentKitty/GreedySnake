package snake_test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel implements KeyListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Snake snake;
	private Food food;

//���������ڣ���Ч��������ʵ�ּ�����Ҫ
//private boolean power;
private int code;

public Canvas() {
	snake = new Snake();
	food = new Food();
//	this.power=true;
	this.code=0;
	this.setFocusable(true);
	this.addKeyListener(this);
	this.timer.start();
	// TODO Auto-generated constructor stub
}

public Food foodProdect(){
	int xTmp=(new Random().nextInt(40)+1)*20;
	int yTmp=(new Random().nextInt(30)+1)*20;
	ArrayList<Snode> snaker=snake.getSnakeHead();
	int len=snake.getSnakeLen();
		try {
			while(true){
				int i;
				for(i=0;i<len;i++){
					if((snaker.get(i).getX()==xTmp)&&(snaker.get(i).getY()==yTmp)){
						 break;
					} 
				}
				if(i==len)return new Food(xTmp,yTmp);
				else{
					 xTmp=(new Random().nextInt(40)+1)*20;
					 yTmp=(new Random().nextInt(30)+1)*20;
						
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());// TODO: handle exception
			return new Food();
		}
}


//����һ���߳��ּ࣬�ڶ��ߵ��ƶ��ٶȽ��п��ƣ�����������۾�
Thread timer = new Thread(new Runnable(){
	private OperationCheck operationCheck=new OperationCheck() {
	
		
		
		@Override
		public void eatFood(){
			
			
			if(
					(snake.getXcoor()==food.getX())
					&&(snake.getYcoor()==food.getY())
														){
				
				Snode lastSnode=snake.getSnakeHead().get(snake.getSnakeLen()-1);
//				buildBody();
				snake.getSnakeHead().add(new Snode(lastSnode.getX(),lastSnode.getY(),Color.green));
				
				snake.setSnakeLen(snake.getSnakeLen()+1);
				
				food =foodProdect(); 
			}
			
			
		}
		
		@Override
		public void crackItself(){
			try {
				ArrayList<Snode> shead=snake.getSnakeHead();
				for(int i=2;i<snake.getSnakeLen();i++){
					if((shead.get(i).getX()==snake.getXcoor())&&(shead.get(i).getY()==snake.getYcoor())){
						JDialog d0=new JDialog();
						JLabel l0=new JLabel("Snake crack itself!",JLabel.CENTER);
						l0.setFont(new Font("Jlabel",Font.CENTER_BASELINE,20));						d0.setTitle("FAILED");
						d0.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e){
								System.exit(0);
							}
						});
						d0.setTitle("Failed");
						d0.add(l0);
						d0.setSize(294, 109);
						d0.setResizable(false);
						d0.setVisible(true);
						this.wait();
						return;
					}
						
				}
				
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				
				// TODO: handle exception
			}
	}
	@Override
		public void crackWall(){
		try {
			if((snake.getXcoor()<0)||(snake.getYcoor()<0)||(snake.getXcoor()>1094)||(snake.getYcoor()>674)){
				JDialog d0=new JDialog();
				JLabel l0=new JLabel("Snake cracked the wall!",JLabel.CENTER);
				l0.setFont(new Font("Jlabel", Font.CENTER_BASELINE, 20));
				d0.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}
				});
				d0.setTitle("Failed");
				d0.add(l0);
				d0.setSize(294, 109);	
				d0.setResizable(false);
				d0.setVisible(true);
				this.wait();
				return;
			}
		
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			
			// TODO: handle exception
		}
					
		
	}
};
	//��ΪoperationCheck����߳������һ����ϵ�����԰�������snake���ڣ������ǹ����ԵĴ�С���ˡ�
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				
				Thread.sleep(snake.getSpeed());
				repaint();//�ػ淽��ʹsnake���ʱ��Update
				snake.move(snake.getDir());
				operationCheck.eatFood();
				operationCheck.crackItself();
				operationCheck.crackWall();
				} catch (InterruptedException e) {
				// TODO: handle exception
			e.printStackTrace();
			}
			
		}
	}
});
@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		snake.paintItself(g, this);
		food.paintItself(g);
		
	}
@Override
public void keyTyped(KeyEvent e) {
}

@Override
public void keyReleased(KeyEvent e) {
	//if(this.code==e.getKeyCode())this.power=true;//��仰ֻ����released�в������Ч��
}

@Override
public void keyPressed(KeyEvent e) {
 
//keyValid(snake.getDir());//
//if(this.power){
	code =e.getKeyCode();
	switch (code) {
case KeyEvent.VK_UP:
	if(snake.isUpPower()){
		snake.setDir(Dir.UP);
	//	this.power=false;
		}
	break;
case KeyEvent.VK_DOWN:
	if(snake.isDownPower()){
		snake.setDir(Dir.DOWN);
	//	this.power=false;
		}
	break;
case KeyEvent.VK_LEFT:
	if(snake.isLeftPower()){
		snake.setDir(Dir.LEFT);
	//	this.power=false;
	}
	break;
case KeyEvent.VK_RIGHT:
	if(snake.isRightPower()){
		snake.setDir(Dir.RIGHT);
	//	this.power=false;
	}
	break;
default:
	break;
	}

//}
//keyReValid(snake.getDir());
//if(this.code==e.getKeyCode())this.power=true;//��仰ֻ����released�в������Ч��

/*if(this.power){
	snake.move(snake.getDir());//������仰�ӿ��ƶ��ٶȣ�������˲��Ч�������޷��Ե����������ʳ�
	this.power=false;
}*/
}	
//��ͷ��Ч
}
