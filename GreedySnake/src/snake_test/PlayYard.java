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
	//为了低耦合此处的构造器就很好的实现属性赋值的单一功能
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
 *PlayYard是中央控制台，snake没有了它会很自由，有了它我们才能通过这个控制台监听到键盘事件从而控制snake方向
 *snake是它的下属，明确这种关系对该游戏的效果至关重要，MVC,MVP在这样的没有很明确的MV界限的游戏开发中仅有借鉴意义，这种桌面应用的开发最主要还是捉住一点为最高准则：模块间低耦合，模块内高内聚。这也是划分模块的最高准则。为了达到这样的效果，我们会尽可能以一个函数只做好一件事或达到一个功能即可，一个类只实现一种实体的模拟即可，不宜且绝不允许再多加增删改。
 *若借鉴MVP的完全低耦合处理snake和PlayYard即将他们作为两个完全独立的实体类，反而减少两者的密切度，使snake无法真正的“身临其境”（境即PlayYard）。
 *作为下属在上级的类里实例，使他们可以交互。
 *须得注意这种上下级关系中下属是完全独立的。所以他要实现与任何其他模块低耦合的特征。对于上级除了通过将下属作为私有成员实例化使用外，不应该再有多余的访问，即尽量与它低耦合。
 *低耦合意味着多个模块的意义和实现相互无交集，高内聚意味着单个独立模块的意义和实现尽量单一集中完整。
 *若一定要从MVC角度
 *snake是model，playYard是view， Keylistener是controller。因为Keylistener是接口需要类实现为减少冗杂的代码故用view实现，并且可以监听。
 *
 * 
 */
 