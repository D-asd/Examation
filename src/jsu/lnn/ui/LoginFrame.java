package jsu.lnn.ui;//ui 程序包

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * create 简单工厂方法，封装了复杂对象的创建过程，返回一个对象实例
 */
/** 登录界面 是一个具体窗口框*/
public class LoginFrame extends JFrame{
	private static final long serialVersionUID = -5402512922068320807L;
	
	public LoginFrame(){//构造器调用 init()
		init();
	}
	/** 初始化界面组件和布局的*/
	private void init(){
		this.setTitle("登录系统");  //设置窗口框的名字
		JPanel contentPane = createContentPane(); //
		this.setContentPane(contentPane);
		
		//设置窗口相对于指定组件的位置
		setLocationRelativeTo(null);//居中
		
		this.setSize(270, 188);
		//this.setVisible(true);
		
		//设置关闭按钮   点击关闭按钮时的操作 
		//DO_NOTHING_ON_CLOSE  不做任何操作
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				clientContext.exit(LoginFrame.this);
			}
		});
		
	}
	private JPanel createContentPane(){//创建面板对象
		JPanel p = new JPanel(new BorderLayout());
		//setBorder()设置边框    EmptyBorder() 设置空白边框
		p.setBorder(new EmptyBorder(8,8,8,8)); //上左下右 逆时针
		//面板北边面板 加上文字标签
		p.add(BorderLayout.NORTH,new JLabel("登录考试系统",JLabel.CENTER));
		//面板中间面板 调用createCententPane()方法 添加内容
		p.add(BorderLayout.CENTER,createCenterPane());//封装
		//面板南边面板 调用createBtnPane()方法 添加按钮面板
		p.add(BorderLayout.SOUTH,createBtnPane());
		return p;
	}
	private JPanel createBtnPane(){
		JPanel p = new JPanel(new FlowLayout());
		JButton login= new JButton("Login");
		JButton cancel = new JButton("Cancel");
		
		getRootPane().setDefaultButton(login);//回车的默认按钮
		
		p.add(login);
		p.add(cancel);
		
		//给按钮添加动作
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//System.out.println("click login button");//监听
				clientContext.login();
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//LoginFrame.this 是当前LoginFrame对象的实例
				//就是登录窗口对象的引用
				clientContext.exit(LoginFrame.this);
				//在ClientContext中引用登录界面的窗口
			}
		});
		return p;
	}
	private JPanel createCenterPane(){
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(10,0,0,0));
		p.add(BorderLayout.NORTH,createIdPwdPane());
		message = new JLabel("",JLabel.CENTER);
		p.add(BorderLayout.CENTER,message);
		return p;
	}
	private JPanel createIdPwdPane(){
		//GridLayout() 表格布局
		//可以指定行数列数  2行1列     行间距，列间距 
		JPanel p = new JPanel(new GridLayout(2,1,0,6));
		//安顺序排列
		p.add(createIdPane());
		p.add(createPwdPane());
		return p;
	}
	private JPanel createIdPane(){
		JPanel p = new JPanel(new BorderLayout(6,0));
		p.add(BorderLayout.WEST,new JLabel("编号："));
		//JTextField() 文本域控件 文本输入框 输入单行文本
		JTextField idField = new JTextField();
		p.add(BorderLayout.CENTER,idField);
		//将实例变量idField 应用到界面控件上
		this.idField = idField;//如果不加这句话，
		//下面发生空指针异常
		return p;
	}
	private JPanel createPwdPane(){
		JPanel p = new JPanel(new BorderLayout(6,0));
		p.add(BorderLayout.WEST,new JLabel("密码："));
		//JPasswordField()  密码输入框  以“*”显示 
		JPasswordField pwdField = new JPasswordField();
		pwdField.enableInputMethods(true);//允许打开输入法
		p.add(BorderLayout.CENTER,pwdField);
		this.pwdField = pwdField;
		return p;
	}
	
	private ClientContext clientContext;//声明
	//赋值  IOC  依赖注入   这个方法需要在main方法引用
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	private JTextField idField;//不能随便
	private JPasswordField pwdField;
	private JLabel message;
	public int getUserId(){
		return Integer.parseInt(idField.getText());	//返回十进制表示的整数值
		//parseInt(s) 将字符串参数作为有符号的十进制整数进行解析  返回得到的整数值
		//如果字符串不包含可解析的整数 抛出NumberFormatException  数字格式异常
		//s 包含要解析的int 表现形式的String   
		//idField.getText()//返回idField中包含的文本
	}
	public String getPassword(){
		char[] pwd = pwdField.getPassword();
		//返回pwdField中包含的文本
		return new String(pwd);
		//分配一个新的String,使其表示字符数组参数中当前包含的字符序列
	}
	public void showMessage(String message){
		this.message.setText(message);
		//定义此组件将要显示的单行文本
	}
}
