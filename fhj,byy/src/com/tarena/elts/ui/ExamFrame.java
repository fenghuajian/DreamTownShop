package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.TitledBorder;

import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;

/**
 * 考试窗口
 */
public class ExamFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	/**
	 * 记录当前考试信息的属性
	 */
	private ExamInfo examInfo;
	/**
	 * 用于显示考试信息的label
	 */
	private JLabel examInfoLabel;
	/**
	 * 用于显示问题的文本域
	 */
	private JTextArea questionArea;
	/**
	 * 显示题目数量的label
	 */
	private JLabel questionCount;
	/**
	 * 显示考试时间的label
	 */
	private JLabel timer;
	
	private JButton prev;
	private JButton next;
	
	/**
	 * Options数组，存放用户的选择
	 */
	private Option []options = new Option[4];
	
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	public ExamFrame(){
		init();//初始化窗口界面
	}
	
	/**
	 * 初始化窗口界面的方法
	 */
	private void init(){
		//设置标题
		this.setTitle("科技在线测评");
		//设置大小
		this.setSize(600,380);
		//设置位置居中
		this.setLocationRelativeTo(null);
		//设置组件
		this.setContentPane(createContentPane());
		/**
		 * 关闭安妮
		 */
		this.setDefaultCloseOperation(ExamFrame.DO_NOTHING_ON_CLOSE);
		/**
		 * WindowAdapter叫做适配器
		 * 当我们需要定义一个类（无论是不是使用匿名类方式）去实现某个接口时，我们的需要是
		 * 之对此接口中某几个抽象方法感兴趣，但是，因为接口的特点，我们的类必须要将所有的
		 * 方法都实现，无论我们是不是需要书写这些方法的逻辑，但是这样的做法会在我们的程序
		 * 中有很多的空方法（方法中没有逻辑），为了避免这样的情况发生，我们通常会使用适配
		 * 器模式
		 * 
		 * 适配器模式中，会有一个实现了接口的所有方法，我们将这个类叫做适配器类，当我们在
		 * 定义类的时候，就不需要直接实现该接口，而是去继承适配器类，这样我们只需要重写我
		 * 们感兴趣的方法就可以了，无需将所有的方法都重写
		 */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/**
				 * 关闭的时候强制交卷
				 */
				clientContext.sendPaper();
			}
		});
	}
	
	/**
	 * 创建主面板的方法
	 */
	private JPanel createContentPane(){
		JPanel panel = new JPanel(new BorderLayout());
		/**
		 * 北边：一张图片
		 * 中间：考试题目信息以及选项的面板
		 * 南边：操作按钮以及考试信息的面板
		 */
		ImageIcon icon = new ImageIcon(this.getClass().getResource("exam_title.png"));
		panel.add(new JLabel(icon),BorderLayout.NORTH);
		
		//中间面板
		panel.add(createCenterPane(),BorderLayout.CENTER);
		
		//南边的按钮
		panel.add(createToolsPane(),BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * 创建中间面板的方法
	 * 创建显示考试标题,题干的文本域以及选项的面板
	 */
	private JPanel createCenterPane(){
		JPanel panel = new JPanel(new BorderLayout());
		
		examInfoLabel = new JLabel("姓名:xxx 考试:xxx 考试时间:xxx",JLabel.CENTER);
		panel.add(examInfoLabel,BorderLayout.NORTH);
		/**
		 * 显示考题的面板
		 */
		panel.add(createQuestionPane(),BorderLayout.CENTER);
		/**
		 * 添加答案选项的复选况的面板
		 */
		panel.add(createOptionsPane(),BorderLayout.SOUTH);
		
		
		return panel;
	}
	
	/**
	 * 添加显示考题的带滚动条的面板方法,用于显示考题的文本域
	 */
	private JScrollPane createQuestionPane(){
		JScrollPane panel = new JScrollPane();
		//给面板添加标题
		panel.setBorder(new TitledBorder("题目"));
		//考题文本域！！！！！！！！！！！！！
		questionArea = new JTextArea();
		//设置文本域的内容
		questionArea.setText("问题：\nA.\nB.\nC.\nD.");
		//文本域自动换行
		questionArea.setLineWrap(true);
		//设置文本域不可编辑
		questionArea.setEditable(false);
		//把文本域添加到JSCrollPane的Viewport中,否则滚动条不正常
		panel.getViewport().add(questionArea);
		
		return panel;
	}
	
	/**
	 * 添加答案的复选框的面板方法
	 */
	private JPanel createOptionsPane(){
		JPanel panel = new JPanel();
		//！！！！！！！！！！！
		Option a = new Option(0,"A");
		Option b = new Option(1,"B");
		Option c = new Option(2,"C");
		Option d = new Option(3,"D");
		
		options[0] = a;
		options[1] = b;
		options[2] = c;
		options[3] = d;
		
		panel.add(a);
		panel.add(b);
		panel.add(c);
		panel.add(d);
		return panel;
	}
	
	private JPanel createToolsPane(){
		JPanel panel = new JPanel(new BorderLayout());
		/**
		 *西边：考题的题号显示
		 *中间：功能按钮的面板
		 *东边：剩余时间显示 		
		 */
		//！！！！！！！！！！！！！！！！！！！！
		questionCount = new JLabel("题目：20题的第1题");
		timer = new JLabel("剩余：123秒");
		
		panel.add(questionCount,BorderLayout.WEST);
		panel.add(timer,BorderLayout.EAST);
		panel.add(createBtnPane(),BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 *存放“上一题”“下一题”“交卷”的按钮的面板方法 
	 */
	private JPanel createBtnPane(){
		JPanel panel = new JPanel();
		//！！！！！！！！！！！！！！
		prev = new JButton("上一题");
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.prev();
			}
		});
		next = new JButton("下一题");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.next();
			}
		});
		JButton send = new JButton("交卷");
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/**
				 * 执行交卷流程
				 */
				clientContext.sendPaper();
			}
		});
		
		panel.add(prev);
		panel.add(next);
		panel.add(send);
		
		return panel;
	}
	
	/**
	 * 更新考试信息
	 * @param examInfo
	 */
	public void updateExamInfo(ExamInfo examInfo){
		this.examInfo = examInfo;
		/**
		 * 将考试信息在label中显示
		 */
		examInfoLabel.setText(examInfo.toString());
	}
	
	/**
	 * 更新考题
	 * @param question
	 */
	public void updateQuestion(QuestionInfo question){
		/**
		 * 将考题显示在questionArea上
		 */
		questionArea.setText(question.toString());
		int questionIndex = question.getQuestionIndex() + 1;
		int count = examInfo.getQuestionCount();
		String str = "题目: " + count + "个题的第 " + questionIndex + " 题";
		questionCount.setText(str);
		/**
		 * 更新按钮
		 */
		updateButton(question.getQuestionIndex());
		/**
		 * 更新用户答案
		 */
		updateOptions(question.getUserAnswers());
	}
	
	/**
	 * 根据用户的答案将四个选项安妮对应够选
	 * @param usersAnswers
	 */
	private void updateOptions(List<Integer> userAnswers){
		for(Option option:options){
			/**设置勾选*/
			option.setSelected(userAnswers.contains(option.value));
		}
	}
	
	/**
	 * 当当前窗口显示的是第一道考题的时候，“上一题”按钮不可点击
	 * 当当前窗口显示的是最后一道题的时候，“下一题”按钮不可点击
	 */
	private void updateButton(int questionIndex){
		prev.setEnabled(questionIndex!=0);
		next.setEnabled(questionIndex!=examInfo.getQuestionCount()-1);
	}
	
	/**
	 * 获取用户的答案
	 */
	public List<Integer> getUserAnswers(){
		List<Integer> answers = new ArrayList<Integer>();
		for(Option option:options){
			if(option.isSelected()){
				/**
				 * 如果被选中了，就添加选项的值
				 */
				answers.add(option.value);
			}
		}
		
		return answers;
	}
	
	/**
	 * 更新考试时间
	 */
	public void updateTimer(long h,long m,long s){
		if(h==0 && m<5){
			timer.setForeground(Color.RED);
			if(s%2==0){
				timer.setForeground(Color.BLACK);
			}
		}
		String str = "剩余时间："+
										 (h<10?"0"+h:h) + ":" +
										 (m<10?"0"+m:m) + ":" +
										 (s<10?"0"+s:s);
		timer.setText(str);
	}
	
	
	/**
	 * 使用一个内部类定义一个多选框的子类
	 * 目的：添加一个属性,便于日后处理用户答案时的逻辑
	 * 添加的属性,代表答案编号
	 */
	class Option extends JCheckBox{
		private static final long serialVersionUID = 1L;
		int value;//答案编号
		public Option(int value,String txt){
			/**
			 * 调用父类的JCheckBox的构造方法
			 * 传入的参数是一个字符串，作用是多选框旁边显示此字符串
			 * 作为多选框的提示
			 */
			super(txt);
			this.value = value;
		}
	}
}