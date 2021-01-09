package jsu.lnn.test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**2
 * 空布局演示
 */
public class NullLayoutDemo {
	public static void main(String[] args) {
		ImageIcon ico =new ImageIcon(
				NullLayoutDemo.class.getResource("course.png"));
		
		JFrame frame = new JFrame("自定义布局演示");
		JPanel panel = new JPanel();
		JLabel label = new JLabel(ico);
		JButton ok = new JButton("OK");//按钮
		JButton cancel = new JButton("Cancel");
		
		frame.setSize(464,321);
		//清除默认的布局管理，使用自定义布局，组件的位置和大小
		//panel.setLayout(null);//layout:布局 ，null:没有
		
		label.setLocation(1, 1);
		label.setSize(300,300);
		ok.setLocation(291,262);//按钮所在位置
		ok.setSize(76,23);//按钮大小
		cancel.setLocation(371,262);
		cancel.setSize(76,23);
		
		panel.add(label);
		panel.add(ok);
		panel.add(cancel);
		
		
		frame.setContentPane(panel);//ContentPane swing 标准动作
		//调用 panel 面板
		frame.setVisible(true);
	}
}
