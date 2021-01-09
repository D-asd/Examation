package jsu.lnn.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**3
 * 布局
 */
public class LayoutDemo {
	public static void main(String[] args) {
		JFrame frame = new JFrame("布局管理");
		//面板
		JPanel content = new JPanel(new BorderLayout());//内容
		JPanel bottom = new JPanel(new BorderLayout());
		JPanel btnPanel1 = new JPanel(new FlowLayout());
		JPanel btnPanel = new JPanel(new FlowLayout());
		//按钮
		JButton help = new JButton("HELP?");
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		
		btnPanel1.add(help);
		btnPanel.add(ok);
		btnPanel.add(cancel);
		bottom.add(BorderLayout.WEST,btnPanel1);
		bottom.add(BorderLayout.EAST,btnPanel);
		content.add(BorderLayout.SOUTH,bottom);
		
		frame.setContentPane(content);
		frame.setSize(380,300);
		frame.setVisible(true);
		//互联网上有swing 皮肤  很旋
		//swing 天生带有皮肤
	}

}
