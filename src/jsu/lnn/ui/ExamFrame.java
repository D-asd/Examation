package jsu.lnn.ui;//视图包

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import jsu.lnn.entity.ExamInfo;
import jsu.lnn.entity.QuestionInfo;

public class ExamFrame extends JFrame{
	
	private static final long serialVersionUID = -5355432125621015300L;
	//选项集合，方便答案读取的处理
	private Option[] options = new Option[4];
	
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	public ExamFrame(){
		init();
		//this.setVisible(true);
	}
	private void init(){
		this.setTitle("在线评测");
		this.setSize(600, 380);
		this.setLocationRelativeTo(null);
		setContentPane(createContentPane());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				clientContext.send();
			}
		});
	}
	private JPanel createContentPane(){
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(6,6,6,6));
		
		p.add(BorderLayout.CENTER,createCenterPane());
		p.add(BorderLayout.SOUTH,createSouthPane());
		return p;
	}
	private JPanel createCenterPane(){
		JPanel p = new JPanel(new BorderLayout());
		//注意
		JLabel info = new JLabel(
				"姓名：XXX  考试：XXX  考试时间：XXX  考试科目：XXX  题目数量：XX",
				JLabel.CENTER);
		this.examInfo = info; 
		
		p.add(BorderLayout.NORTH,info);
		p.add(BorderLayout.CENTER,createQuestionPane());//问题面板
		p.add(BorderLayout.SOUTH,createOptionsPane());//选择面板
		return p;
	}
	private JScrollPane createQuestionPane(){
		JScrollPane p = new JScrollPane();
		p.setBorder(new TitledBorder("题目"));//标题框
		//注意
		/*JTextArea*/ questionArea = new JTextArea();
		
		questionArea.setText("问题\nA.\nB.");
		questionArea.setLineWrap(true);//允许折行显示
		questionArea.setEditable(false);//不能够编辑内容
		//JTextArea 必须放到 JScrollPane 的视图区域中(Viewport)
		p.getViewport().add(questionArea);		
		return p;
	}
	private JPanel createOptionsPane(){
		JPanel p = new JPanel();
		
		Option a = new Option(0,"A");
		Option b = new Option(1,"B");
		Option c = new Option(2,"C");
		Option d = new Option(3,"D");
		
		options[0] = a;
		options[1] = b;
		options[2] = c;
		options[3] = d;
		
		p.add(a);
		p.add(b);
		p.add(c);
		p.add(d);
		return p;
	}
	
	private JPanel createSouthPane(){
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(0,10,0,10));
		//注意
		questionCount = new JLabel("题目:20 的 1题");
		timer = new JLabel("剩余时间：222秒");
		
		p.add(BorderLayout.WEST,questionCount);
		p.add(BorderLayout.EAST,timer);
		p.add(BorderLayout.CENTER,createBtnPane());
		return p;
	}
	private JPanel createBtnPane(){
		JPanel p = new JPanel(new FlowLayout());
		
		 prev = new JButton("<<上一题");
		 next = new JButton("下一题>>");
		 JButton send = new JButton("交卷");
		
		 getRootPane().setDefaultButton(next);
		 
		p.add(prev);
		p.add(next);
		p.add(send);
		
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clientContext.prev();
			}
		});
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clientContext.next();
			}
		});
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clientContext.send();
			}
		});
		return p;
	}
	/** 使用内部类扩展了 JCheckBox 增加了val属性 ，代表答案值*/
	class Option extends JCheckBox{
		private static final long serialVersionUID = -5357210068923979044L;
		//java约定  凡是实现序列化接口  最好写一个序列号
		//java API 基本都显现了序列化接口
		int value;
		public Option(int val,String txt){
			super(txt);
			this.value = val;
		}
	}
	
	private JLabel examInfo;
	private JTextArea questionArea;
	public void updateView(ExamInfo examInfo, QuestionInfo questionInfo) {
		//更新界面
		//System.out.println("mingzi");
		this.examInfo.setText(examInfo.toString());
		questionArea.setText(questionInfo.toString());
		
		updateButton(examInfo.getQuestionCount(),
				questionInfo.getQuestionIndex());
		updateQuestionCount(examInfo.getQuestionCount(),
				questionInfo.getQuestionIndex());
		updateOptions(questionInfo.getUserAnswers());
	}
	private JButton prev;
	private JButton next;
	private JLabel questionCount;
	
	private void updateOptions(List<Integer> userAnswers){
		for(Option option : options){
			option.setSelected(userAnswers.contains(option.value));
		}
	}
	
	private void updateQuestionCount(int questionCount,int questionIndex){
		String str = "题目:"+questionCount + " 的："+(questionIndex+1)+"题.";
		this.questionCount.setText(str);
	}
	
	private void updateButton(int questionCount,int questionIndex){
		prev.setEnabled(questionIndex != 0);
		next.setEnabled((questionCount-1)!= questionIndex);
	}
	
	public List<Integer> getUserAnswers() {
		List<Integer> list = new ArrayList<Integer>();
		for(Option option:options){
			if(option.isSelected()){
				list.add(option.value);
			}
		}
		return list;
	}
	private JLabel timer;
	public void updateTime(long h,long m,long s){
		String time ="剩余时间："+ h+":"+m+":"+s;
		if(h==0 && m<5){
			timer.setForeground(new Color(0xc85848));
		}else{
			timer.setForeground(Color.BLUE);
		}
		timer.setText(time);
	}
	
}

