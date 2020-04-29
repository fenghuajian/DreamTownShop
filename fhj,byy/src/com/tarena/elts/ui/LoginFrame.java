package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 登陆界面
 * 窗口工具不使用awt,使用swing,保证跨平台良好性
 */
public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField idField;
	private JPasswordField passwordField;
	private JLabel message;
	
	/**
	 * 控制器类
	 */
	private ClientContext clientContext;
	/**
	 * 提供setter方法  用于认识
	 * @param clientContext
	 */
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	/**
	 * 构造方法
	 */
	public LoginFrame(){
		init();//初始化界面
	}
	
	/**
	 * 界面初始化方法
	 */
	private void init(){
		//设置窗口的标题
		this.setTitle("考试登陆系统");
		//设置窗可的大小
		this.setSize(280,200);
		//窗口设置为居中
		this.setLocationRelativeTo(null);
		//向窗口中添加组件
		this.setContentPane(createContentPane());
		
		this.setDefaultCloseOperation(LoginFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				clientContext.exit(LoginFrame.this);
			}
		});
	}
	
	/**
	 *创建主panel的方法
	 */
	private JPanel createContentPane(){
		JPanel panel = new JPanel();
		//设置为borderlayout布局
		panel.setLayout(new BorderLayout());
		//加边框
		panel.setBorder(new EmptyBorder(10,10,10,10));
		/**
		 * North  方一个label
		 * Center 放一个panel(两个label和两个输入框)
		 * South  放两个按钮
		 */
		panel.add(new JLabel("登陆系统",JLabel.CENTER),BorderLayout.NORTH);
		
		panel.add(createCenterPane(),BorderLayout.CENTER);
		
		panel.add(createBtnPane(),BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * 创建中间的panel的方法
	 */
	private JPanel createCenterPane(){
		JPanel panel = new JPanel(new BorderLayout());
		//加间距
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		/**
		 * North 用于存放帐号和密码的输入框
		 */
		panel.add(createIdPwdPane(),BorderLayout.NORTH);
		
		/**
		 * 添加个用于显示登陆错误的信息的标签
		 */
		message = new JLabel("",JLabel.CENTER);
		panel.add(message,BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * 用于存放帐号和密码的panel方法
	 */
	private JPanel createIdPwdPane(){
		/**
		 * 两行一列,每行存放一组内容id 输入框；密码 输入框
		 */
		JPanel panel = new JPanel(new GridLayout(2,1,0,20));
		
		//第一行 IdPanel
		panel.add(createIdPane());
		//第二行
		panel.add(createPwdPane());
		
		return panel;
	}
	
	/**
	 * 帐号panel方法
	 */
	private JPanel createIdPane(){
		JPanel panel = new JPanel(new BorderLayout(6,0/*水平间距设置6像素*/));
		panel.add(new JLabel("帐号:"),BorderLayout.WEST);
		
		//帐号输入框！！！！！
		idField = new JTextField();
		panel.add(idField,BorderLayout.CENTER);
				
		return panel;
	}
	
	/**
	 * 密码panel方法
	 */
	private JPanel createPwdPane(){
		JPanel panel = new JPanel(new BorderLayout(6,0/*水平间距设置6像素*/));
		panel.add(new JLabel("密码:"),BorderLayout.WEST);
		
		//密码输入框！！！！！
		passwordField = new JPasswordField();
		//允许密码框输入法,对应linux输入法问题
		passwordField.enableInputMethods(true);
		panel.add(passwordField,BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * 创建登陆和取消的按钮的panel方法
	 */
	private JPanel createBtnPane(){
		JPanel panel = new JPanel();
		
		//按钮！！！！！
		JButton login = new JButton("登陆");
		/**
		 * 登陆按钮添加监听器
		 */
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * 调用控制器的login()方法
				 */
				clientContext.login();
			}
		});
		
		JButton cancel = new JButton("退出");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.exit(LoginFrame.this);
			}
		});
		
		JButton register = new JButton("注册");
		register.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clientContext.toRegisterFrame();
			}
		});
		
		panel.add(login);
		panel.add(register);
		panel.add(cancel);
		
		
		return panel;
	}
	
	/**
	 * 获得用户输入的id方法
	 */
	public int getUserId(){
		return Integer.parseInt(idField.getText());
	}
	
	/**
	 * 获得用户输入的密码的方法
	 */
	public String getUserPassword(){
		/**
		 *获取密码框的内容不推荐使用getText()方法，官方推荐使用getPassword()
		 * 返回一个char[]
		 * 将char[]转换为String类型返回
		 */
		char []password = passwordField.getPassword();
		return new String(password);
	}
	
	/**
	 * 此方法用于登陆窗口显示登陆错误的信息
	 */
	public void showError(String str){
		message.setForeground(Color.RED);
		message.setText(str);
		
		/**
		 * 窗口抖动
		 */
		 final Timer timer = new Timer();
		 /**
		  * 获得当前窗口的坐标，用Point保存，其中有x和y值
		  */
		 final Point start = this.getLocation();
		 /**
		  * 给Timer加第一个任务，用于周期性改变窗口的位置，模拟晃动效果
		  */
		 timer.schedule(new TimerTask() {
			
			 int []offset/*偏移量*/ = {-4,-8,-4,0,4,8,4,0};
			 int i = 0;
			 public void run() {
				Point p = getLocation();/*获取当前坐标*/
				p.x += offset[i++ %offset.length];
				p.y += offset[i++ %offset.length];
				setLocation(p);
			}
		},0,24);
		 /**
		  * 再添加一个任务，用于若干秒之后窗口回到原来位置停下
		  */
		 timer.schedule(new TimerTask() {
			public void run() {
				setLocation(start);
				timer.cancel();
			}
		}, 500);
	}
}