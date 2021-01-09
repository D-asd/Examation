package jsu.lnn.Dao;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


/**���ݿ����ɾ���*/
public class BookDaoImpl extends JFrame implements BookDao{
	private Container c = getContentPane();
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	private JTable table;
	private JScrollPane js;
	private JTextField text1,text2,text3;
	private JPanel jp;
	private int count,id;
	private JLabel change1,change2,change3;
	
	public BookDaoImpl(){
	
		
	}
	public void bookAdd(){
		
		setTitle("��Ŀ��Ϣ");
		setLayout(null);
		//���ɷŴ���С
		setResizable(false);
		setBounds(570,260,680,520);
		//�ɹر�
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0,0,680,520);
		
		JLabel jl = new JLabel("��Ŀ��Ϣ");
		
		//��������
		Font font = new Font("����",Font.BOLD,24);
		jl.setFont(font);
		
		jl.setBounds(270, 40, 200, 20);
				
		
		JButton btn4 = new JButton("���");
		btn4.setBounds(560,39,60,25);
		btn4.addActionListener(new ActionListener(){		
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/telts?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","123456");
					stm = (Statement) con.createStatement();
					
					int index = table.getSelectedRow();
					
					String id = (String) table.getValueAt(index, 0);
					String name = (String) table.getValueAt(index, 1);
					String author = (String) table.getValueAt(index, 2);
					String price = (String) table.getValueAt(index, 3);
					String horse = (String) table.getValueAt(index, 4);
				


					String sql =("insert into question values("+id+",'"+name+"','"+author+"','"+price+"','"+horse+"')");
					
					int resule2 = stm.executeUpdate(sql);
					if(resule2==1){
						JOptionPane.showMessageDialog(null,"��ӳɹ�","ȷ��",JOptionPane.PLAIN_MESSAGE);
					
					}
					if(resule2!=1){
						
						JOptionPane.showMessageDialog(null,"��Ŀ�ظ�","ȷ��",JOptionPane.PLAIN_MESSAGE);
					}
					stm.close();
					con.close();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});	
		String[] columnName=
			{"��Ŀ","������","ѡ��(��@�ָ�)","����","�Ѷȵȼ�"};
		String[][] tableValues =new String[1][columnName.length];
		table = new JTable(tableValues,columnName);								
		table.setBounds(30,100,620,320);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		js = new JScrollPane(table);
		js.setBounds(30,90,610,320);
		jp.add(js);	
		
		getBooks();	
	
		jp.add(btn4);
		
		jp.add(jl);
		c.add(jp);		
	
	
		//���ӻ�
		setVisible(true);
		
	}
	public void bookDel(){

		setTitle("��Ŀ��Ϣ");
		setLayout(null);
		//���ɷŴ���С
		setResizable(false);
		setBounds(570,260,680,520);
		//�ɹر�
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0,0,680,520);
		
		JLabel jl = new JLabel("��Ŀ��Ϣ");
		
	
		
		//��������
		Font font = new Font("����",Font.BOLD,24);
		jl.setFont(font);
		
		jl.setBounds(270, 40, 200, 20);
				
		
		
		JButton btn3 = new JButton("ɾ��");
		btn3.setBounds(560,39,60,25);
		btn3.addActionListener(new ActionListener(){		
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					//�������ݿ�
					Class.forName("com.mysql.jdbc.Driver");
					con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/telts?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","123456");
					stm = (Statement) con.createStatement();
					//��ȡ�������
					int index = table.getSelectedRow();
					//��ȡ�������
					String id = (String) table.getValueAt(index, 0);
					String sql = "delete from question where title='"+id+"'";
					
					int resule2 = stm.executeUpdate(sql);
					if(resule2==1){
						//�ɹ��򵯳���Ϣ
						
						JOptionPane.showMessageDialog(null,"ɾ���ɹ�","ȷ��",JOptionPane.PLAIN_MESSAGE);
						
					}
					stm.close();
					con.close();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});	
		//�������Ϣ
		String[] columnName=
			{"��Ŀ","������","ѡ��(@�ָ�)","����","�Ѷȵȼ�"};
		String[][] tableValues =new String[500][columnName.length];
		table = new JTable(tableValues,columnName);								
		table.setBounds(30,100,620,320);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		js = new JScrollPane(table);
		js.setBounds(30,90,610,320);
		jp.add(js);	
		
				
		getBooks();
		jp.add(btn3);
		jp.add(jl);
		c.add(jp);		
		
	
		//���ӻ�
		setVisible(true);
		
	}
	public void bookUpdate(){

		setTitle("��Ŀ��Ϣ");
		setLayout(null);
		//���ɷŴ���С
		setResizable(false);
		setBounds(570,260,680,520);
		//�ɹر�
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0,0,680,520);
		
		JLabel jl = new JLabel("��Ŀ��Ϣ");
		
		
		
		//��������
		Font font = new Font("����",Font.BOLD,24);
		jl.setFont(font);
		
		jl.setBounds(270, 40, 200, 20);
				
		
		JButton btn2 = new JButton("�޸�");
		btn2.setBounds(560,39,60,25);
		btn2.addActionListener(new ActionListener(){		
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/telts?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","123456");
					stm = (Statement) con.createStatement();
					
					int index = table.getSelectedRow();
					//��ȡ�������
					String id = (String) table.getValueAt(index, 0);
					String name = (String) table.getValueAt(index, 1);
					String author = (String) table.getValueAt(index, 2);
					String price = (String) table.getValueAt(index, 3);
					String horse = (String) table.getValueAt(index, 4);
					
				
					String sql = ("update question set title='"+id+"',answer='"+name+"',options='"+author+"',score='"+price+"',level='"+horse+"' where title='"+id+"'");
					System.out.println(sql);
					int resule2 = stm.executeUpdate(sql);
					if(resule2==1){
						
						JOptionPane.showMessageDialog(null,"�޸ĳɹ�","ȷ��",JOptionPane.PLAIN_MESSAGE);
						
					}
					stm.close();
					con.close();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});	
	
	
		String[] columnName=
			{"��Ŀ","������","ѡ��(��@�ָ�)","����","�Ѷȵȼ�"};
		String[][] tableValues =new String[500][columnName.length];
		table = new JTable(tableValues,columnName);								
		table.setBounds(30,100,620,320);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		js = new JScrollPane(table);
		js.setBounds(30,90,610,320);
		jp.add(js);	
		
		getBooks();
		jp.add(btn2);
	
		jp.add(jl);
		c.add(jp);		
		
	
		//���ӻ�
		setVisible(true);
	
	}
	
public void getBooks(){
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/telts?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","123456");
			stm = (Statement) con.createStatement();
			Statement stm1 = (Statement) con.createStatement();
			
			rs = stm.executeQuery("select *  from question");			
			ResultSet rs1 = stm1.executeQuery("select count(*) from question");
			while(rs1.next()){
				count = rs1.getInt(1);
			}
			ArrayList save = new ArrayList();
			while(rs.next()){
				String Str1 = rs.getString(1);
				String Str2 = rs.getString(2);
				String Str3 = rs.getString(3);
				String Str4 = rs.getString(4);
				String Str5 = rs.getString(5);
			
				
				
				save.add(Str1);
				save.add(Str2);
				save.add(Str3);
				save.add(Str4);
				save.add(Str5);
			
				
			}
				//���õ�����ȡ���������ݲ����ý����
				Iterator iterator = save.iterator();//��������������Ԫ��
				while(iterator.hasNext()){
					
				for(int i =0;i<count;i++){
					for(int j = 0;j<5;j++){	
						String data = (String) iterator.next();
						table.setValueAt(data, i, j);
					}	
			}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}					
	}
public void bookSearch(){

	setTitle("��Ŀ��Ϣ");
	setLayout(null);
	//���ɷŴ���С
	setResizable(false);
	setBounds(570,260,680,520);
	//�ɹر�
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	jp = new JPanel();
	jp.setLayout(null);
	jp.setBounds(0,0,680,520);
	
	JLabel jl = new JLabel("��Ŀ��Ϣ");
	
	
	
	//��������
	Font font = new Font("����",Font.BOLD,24);
	jl.setFont(font);
	
	jl.setBounds(270, 40, 200, 20);
	JLabel jl1 = new JLabel("��������Ŀ����:");
	text1 = new JTextField("",10);
	jl1.setBounds(400,39,100,25);
	text1.setBounds(500,39,60,25);
	c.add(jl1);
	c.add(text1);
	String[] columnName=
		{"��Ŀ","������","ѡ��(@�ָ�)","����","�Ѷȵȼ�"};
	String[][] tableValues =new String[500][columnName.length];
	table = new JTable(tableValues,columnName);								
	table.setBounds(30,100,620,320);
	table.getTableHeader().setReorderingAllowed(false);
	table.getTableHeader().setResizingAllowed(false);
	
	js = new JScrollPane(table);
	js.setBounds(30,90,610,320);
	jp.add(js);	
	
	getBooks();
	
	
	JButton btn2 = new JButton("��ѯ");
	btn2.setBounds(570,39,60,25);
	btn2.addActionListener(new ActionListener(){		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i =0;i<count;i++){
				for(int j = 0;j<5;j++){	
					
					table.setValueAt("", i, j);
				}	
		}
			try {
				String t1 = text1.getText();
				Class.forName("com.mysql.jdbc.Driver");
				con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/telts?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","123456");
				stm = (Statement) con.createStatement();
				rs = stm.executeQuery("select * from question where title like '%"+t1+"%'");
				
				int col = 0;
				while(rs.next()){
					
					String id = rs.getString("title");
					String name = rs.getString("answer");
					String author = rs.getString("options");
					String price = rs.getString("score");
					String horse = rs.getString("level");
					table.setValueAt(id,col, 0);
					table.setValueAt(name, col, 1);
					table.setValueAt(author, col, 2);
					table.setValueAt(price, col, 3);
					table.setValueAt(horse, 0, 4);
					col++;
					
				}
				JOptionPane.showMessageDialog(null,"��ѯ�ɹ�","ȷ��",JOptionPane.PLAIN_MESSAGE);

	
				
				rs.close();
				stm.close();
				con.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
		}
	});	

	jp.add(btn2);

	jp.add(jl);
	c.add(jp);	


	//���ӻ�
	setVisible(true);

}
public static void main(String[] agrs){
	BookDaoImpl book = new BookDaoImpl();
	book.bookSearch();

}

}
