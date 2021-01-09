package jsu.lnn.test;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**1
 * 报名基本是域名倒写 + 项目名称 
 * test 测试包
 */
/**图片加载测试*/
public class JFrameTest {
	public static void main(String[] args) {
		//从package中加载资源(image,text,等...)
		//JFrameTest.class 与 img.png 在同一个包里
		//URL ur1 = JFrameTest.class.getResource("img.jpg");//获取 img 资源
		//也可以使用据对package路径加载资源： 如“
		URL ur1 = JFrameTest.class.getResource(
				"/com/tarena/elts/test/img.jpg");
		ImageIcon ico= new ImageIcon(ur1); 
		JFrame frame = new JFrame("测试图片加载");
		JPanel panel = new JPanel();
		JLabel label = new JLabel(ico);
		panel.add(label);
		frame.setContentPane(panel);
		frame.setSize(300,200);
		frame.setVisible(true);
	}

}
