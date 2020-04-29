package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * 用户注册窗体
 */
public class RegisterFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//显示注册信息的标签
	private JLabel message;
	//帐号
	private JTextField id;
	//姓名框
	private JTextField userName;
	//密码框
	private JPasswordField password;
	
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	public RegisterFrame(){
		/**
		 * 初始化窗体
		 */
		init();
	}
	
	/**初始化窗体的方法*/
	private void init(){
		setTitle("用户注册");
		setSize(300,260);
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
	}
	
	/*创建主面板的方法*/
	private JPanel createContentPane(){
		JPanel panel = new JPanel(new BorderLayout());
		//加个边框
		panel.setBorder(new EmptyBorder(10, 6, 6, 6));
		panel.add(new JLabel("考试系统用户注册",JLabel.CENTER),BorderLayout.NORTH);
		//中间面板
		panel.add(createCenterPane(),BorderLayout.CENTER);
		
		//按钮
		panel.add(createBtnPane(),BorderLayout.SOUTH);
		
		return panel;
	}
	
	//添加中间面板
	private JPanel createCenterPane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6,6,10,6));
		//姓名密码面板
		panel.add(createNamePwdPane(),BorderLayout.NORTH);
		//显示注册错误的面把
		panel.add(createMsgPane(),BorderLayout.CENTER);
		
		return panel;
	}
	
	//姓名密码
	private JPanel createNamePwdPane(){
		JPanel panel = new JPanel(new GridLayout(3, 1, 0, 16));
		panel.add(createIdPane());
		panel.add(createNamePane());
		panel.add(createPwdPane());
		
		return panel;
	}
	
	//编号
	private JPanel createIdPane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("帐号 : ",JLabel.CENTER),BorderLayout.WEST);
		//！！！！！！！！！！
		id = new JTextField();
		panel.add(id,BorderLayout.CENTER);
		return panel;
	}
	
	//姓名面板
	private JPanel createNamePane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("姓名 : ",JLabel.CENTER),BorderLayout.WEST);
		//！！！！！！！！！！
		userName = new JTextField();
		panel.add(userName,BorderLayout.CENTER);
		
		return panel;
	}
	
	//密码面板
	private JPanel createPwdPane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("密码 : ",JLabel.CENTER),BorderLayout.WEST);
		//！！！！！！！！！！
		password = new JPasswordField();
		panel.add(password,BorderLayout.CENTER);
		return panel;
	}
	
	//按钮
	private JPanel createBtnPane(){
		JPanel panel = new JPanel();
		//提交按钮
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//调用注册方法
				clientContext.register();
			}
		});
		//登陆按钮
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clientContext.toLoginFrame();
			}
		});
		
		panel.add(register);
		panel.add(login);
		
		return panel;
	}
	
	//显示粗无的面板
	private JPanel createMsgPane(){
		JPanel panel = new JPanel(new BorderLayout());
		message = new JLabel("请认真注册！",JLabel.CENTER);
		panel.add(message,BorderLayout.CENTER);
		
		return panel;
	}
	
	//显示注册错误的jlabel
	public void showError(String msg){
		message.setText(msg);
		message.setForeground(Color.RED);
		
		//窗口口动效果
		final Timer timer = new Timer();
		//得到当前坐标
		final Point start = getLocation();
		timer.schedule(new TimerTask() {
			int[] offset = {-4,-2,0,-2,-4,2,4,2,0};
			int i = 0;
			public void run() {
				Point p = getLocation();
				p.x += offset[i++ %offset.length];
				p.y += offset[i++ %offset.length];
				setLocation(p);
			}
		}, 0, 24);
		//添加任务回到原来的位置
		timer.schedule(new TimerTask() {
			
			public void run() {
				setLocation(start);
				timer.cancel();
			}
		},500);
	}
	
	//id
	public String getUserId(){
		return id.getText();
	}
	
	//得到姓名
	public String getUserName(){
		return userName.getText();
	}
	
	//得到密码
	public String getPassword(){
		return new String(password.getPassword());
	}
}