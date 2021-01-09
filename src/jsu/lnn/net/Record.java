package jsu.lnn.net;

import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import jsu.lnn.entity.User;

public class Record  extends JFrame{
	 Connection con;
	 Statement stm;
	 ResultSet rs;
	 JTable table;
	 JScrollPane js;
	 JTextField text1,text2,text3;
	 JPanel jp;
	 int count,id;
	 JLabel change1,change2,change3;
	 Container c = getContentPane();
	public void recordSearch(User user,List<String> ls,List<String> userrecord){
		
		setTitle("考试记录");
		setLayout(null);
		//不可放大缩小
		setResizable(false);
		setBounds(500,200,680,520);
		//可关闭
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0,0,680,520);
		
		JLabel jl = new JLabel("考试记录");
		
		
		
		//设置字体
		Font font = new Font("宋体",Font.BOLD,24);
		jl.setFont(font);
		
		jl.setBounds(270, 40, 200, 20);

		//text1 = new JTextField("",10);
		//jl1.setBounds(400,39,100,25);
		//text1.setBounds(500,39,60,25);
		//c.add(text1);
		String[] columnName=
			{"用户id","分数","时间"};
		String[][] tableValues =new String[500][columnName.length];
		table = new JTable(tableValues,columnName);								
		table.setBounds(30,100,620,320);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		js = new JScrollPane(table);
		js.setBounds(30,90,610,320);
		jp.add(js);	
		userrecord = new ArrayList();
		for(String s : ls){
			String[] s1 = s.split("@");
			if((user.getId()+"").equals(s1[0])){
				userrecord.add(s);
			}
		}
		ArrayList save = new ArrayList();
		if(userrecord.size()>0){
			for(int i =0;i<userrecord.size();i++){
				String[] s1 = userrecord.get(i).split("@");
				for(int j = 0;j<3;j++){	
					table.setValueAt(s1[j], i, j);
				}	
			}	
		}
	


		jp.add(jl);
		c.add(jp);	


		//可视化
		setVisible(true);

	}
}
