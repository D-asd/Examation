package jsu.lnn.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

/** 闪屏 */
public class WelcomeWindow extends JWindow{

	private static final long serialVersionUID = -3203121876800708448L;
	//private static final long serialVersionUID = -2970626103134592561L;
	public WelcomeWindow(){
		init();
	}
	private void init(){
		setSize(430,300);
		setLocationRelativeTo(null);
		JPanel p = new JPanel(new BorderLayout());
		ImageIcon ico = new ImageIcon(getClass().getResource("welcome.png"));
		JLabel l = new JLabel(ico);
		p.add(BorderLayout.CENTER,l);
		p.setBorder(new LineBorder(Color.GRAY));
		setContentPane(p);
	}
	public static void main(String[] args){
		WelcomeWindow w = new WelcomeWindow();
		w.setVisible(true);
	}
}
