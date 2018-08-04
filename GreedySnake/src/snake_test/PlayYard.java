package snake_test;



import java.awt.Color;

import javax.swing.JFrame;
public class PlayYard extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int width;
	private final int height;
//	private Font font;
	private Color color;
	private final String title; 
	private Canvas canvas;
	//Ϊ�˵���ϴ˴��Ĺ������ͺܺõ�ʵ�����Ը�ֵ�ĵ�һ����
	public PlayYard(){
		super();
		this.width=1094;
		this.height=694;
//		this.font=new Font("Title",Font.BOLD, 20);
		this.color=Color.GREEN;
		this.title="Dynamic Snake Model";
		this.setTitle(title);
//		this.setFont(font);
		this.setSize(width, height);
		this.setBackground(this.color);
		
		canvas=new Canvas();
		//this.canvas.setFocusable(true);
		this.setContentPane(canvas);
	}
	
	public void initate(){
		this.setVisible(true);
	}
	
	
}
/*
 *PlayYard���������̨��snakeû������������ɣ����������ǲ���ͨ���������̨�����������¼��Ӷ�����snake����
 *snake��������������ȷ���ֹ�ϵ�Ը���Ϸ��Ч��������Ҫ��MVC,MVP��������û�к���ȷ��MV���޵���Ϸ�����н��н�����壬��������Ӧ�õĿ�������Ҫ����׽סһ��Ϊ���׼��ģ������ϣ�ģ���ڸ��ھۡ���Ҳ�ǻ���ģ������׼��Ϊ�˴ﵽ������Ч�������ǻᾡ������һ������ֻ����һ���»�ﵽһ�����ܼ��ɣ�һ����ֻʵ��һ��ʵ���ģ�⼴�ɣ������Ҿ��������ٶ����ɾ�ġ�
 *�����MVP����ȫ����ϴ���snake��PlayYard����������Ϊ������ȫ������ʵ���࣬�����������ߵ����жȣ�ʹsnake�޷������ġ������侳��������PlayYard����
 *��Ϊ�������ϼ�������ʵ����ʹ���ǿ��Խ�����
 *���ע���������¼���ϵ����������ȫ�����ġ�������Ҫʵ�����κ�����ģ�����ϵ������������ϼ�����ͨ����������Ϊ˽�г�Աʵ����ʹ���⣬��Ӧ�����ж���ķ��ʣ���������������ϡ�
 *�������ζ�Ŷ��ģ��������ʵ���໥�޽��������ھ���ζ�ŵ�������ģ��������ʵ�־�����һ����������
 *��һ��Ҫ��MVC�Ƕ�
 *snake��model��playYard��view�� Keylistener��controller����ΪKeylistener�ǽӿ���Ҫ��ʵ��Ϊ�������ӵĴ������viewʵ�֣����ҿ��Լ�����
 *
 * 
 */
 