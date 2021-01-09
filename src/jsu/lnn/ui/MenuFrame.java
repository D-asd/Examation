package jsu.lnn.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import jsu.lnn.entity.User;
import jsu.lnn.net.FileUtil;
import jsu.lnn.net.Record;

/** 主菜单界面 */
public class MenuFrame extends JFrame {
	
	private static final long serialVersionUID = -1796834130339697339L;
	private JTable table;

	private JScrollPane js;
	private ClientContext clientContext;
	List<String> userrecord;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	public MenuFrame() {
		init();
	}

	/** 初始化界面组件和布局 */
	private void init() {//
		setLocationRelativeTo(null);
		this.setTitle("在线评测");
		// JPanel contentPane = createContentPane();
		// this.setContentPane(contentPane);
		
		this.setContentPane(createContentPane());
		this.setSize(600, 400);
		//this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				//e.getOldState();//e 以前的状态
				clientContext.exit(MenuFrame.this);
			}
		});
	}

	private JPanel createContentPane() {
		JPanel p = new JPanel(new BorderLayout());

		//ImageIcon ico = new ImageIcon(this.getClass().getResource("title.png"));
		// MunuFrame.class.getResource("title.png");
		// p.setBorder(new EmptyBorder(8,8,8,8));
		//p.add(BorderLayout.NORTH, new JLabel(ico));
		p.add(BorderLayout.CENTER, createCenterPane());
		p.add(BorderLayout.SOUTH,
				new JLabel("XXXX", JLabel.RIGHT));
		return p;
	}

	private JPanel createCenterPane() {
		JPanel p = new JPanel(new BorderLayout());
		// 务必将info 引用到界面控件对象
		//p.add(BorderLayout.NORTH, new JLabel("欢迎：聂云云 同学", JLabel.CENTER));
	    info = new JLabel("欢迎:XXX 同学你好！",JLabel.CENTER);
		p.add(BorderLayout.NORTH,info);
		p.add(BorderLayout.CENTER, createBtnPane());
		return p;
	}

	private JPanel createBtnPane() {
		JPanel p = new JPanel(new FlowLayout());

		JButton start = createImgBtn("exam.png", "开始");
		JButton result = createImgBtn("result.png", "考试成绩");
		JButton msg = createImgBtn("message.png", "考试规则");
		JButton exit = createImgBtn("exit.png", "离开");
		JButton record = createImgBtn("exam.png", "考试记录");

		getRootPane().setDefaultButton(start);//回车的默认按钮
		
		p.add(start);
		p.add(result);
		p.add(msg);
		p.add(exit);
		p.add(record);
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clientContext.start();
			}
		});
		
		
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("clsi");
				clientContext.exit(MenuFrame.this);
			}
		});
		result.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//e 代表发生了什么
				//long clickTime = e.getWhen();//获得事情发生时间
				clientContext.result();
			}
		});
		msg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clientContext.msg();
			}
		});
		record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//考试记录查看
				List<String> ls = FileUtil.getFile("record.txt");
				new Record().recordSearch(user,ls,userrecord);
			}
		});
		return p;
	}

	
	/** 创建图片按钮的方法 */
	private JButton createImgBtn(String img, String txt) {
		ImageIcon ico = new ImageIcon(this.getClass().getResource(img));
		JButton button = new JButton(txt, ico);
		//button.setIcon(ico);
		//垂直文本对齐位置
		button.setVerticalTextPosition(JButton.BOTTOM);
		//水平文本对齐位置
		button.setHorizontalTextPosition(JButton.CENTER);
		return button;
	}
	private JLabel info;
	private User user;
	
	public void updateView(User user) {
		info.setText("你好： "+user+" 小童鞋，欢迎进入考试系统 ");
		this.user = user;
	}
	public static void main(String[] args) {
		List ls = new ArrayList();
		ls.add("11@w@w");
	//	new MenuFrame().recordSearch(ls);
	}
}
