package jsu.lnn.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;


public class AdminFrame extends JFrame{
	public Container c = getContentPane();
	private JTextArea ta1;
	private JTextArea ta2;
	private JTextArea ta3;
	private JPanel imagePanel;
	
	public AdminFrame(){
		setBounds(500,200,900,650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			
		setTitle("������");
		setResizable(false);
		setLayout(new BorderLayout());
		setBackground(Color.black);
		//���뱳��ͼƬ
		ImageIcon img = new ImageIcon(AdminFrame.class.getResource("bg1.jpg"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setIcon(img);
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

		imagePanel = (JPanel) getContentPane();
		imagePanel.setOpaque(false);
		
		
		Menu();
		tool();
		setVisible(true);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		
	}
	
public void tool(){
		
		JButton btn1 = new JButton("��Ŀ��ѯ");
		JButton btn2 = new JButton("��Ŀ����");
		JButton btn3 = new JButton("��Ŀɾ��");
		JButton btn4 = new JButton("��Ŀ�޸�");
		JButton btn6 = new JButton("�˳�");
		
		
		//����¼�����
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				BookSearch search = new BookSearch();
				
			}
			
		});
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				BookAdd add = new BookAdd();
			}
			
		});
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				BookDelete delete = new BookDelete();
						
				
			}
			
		});
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				BookUpdate update = new BookUpdate();
				
			}
			
		});
		
		btn6.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		
		
		//������
		JToolBar tool = new JToolBar();
		tool.setBounds(0,0,30,30);
		tool.add(btn1);
		tool.add(btn2);
		tool.add(btn3);
		tool.add(btn4);
		tool.add(btn6);
	

		c.add(tool,BorderLayout.NORTH);
	}
	public void Menu(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
	}
	
	

	public static void main(String [] args){
		AdminFrame admin = new AdminFrame();
		admin.setVisible(true);
	}

}

