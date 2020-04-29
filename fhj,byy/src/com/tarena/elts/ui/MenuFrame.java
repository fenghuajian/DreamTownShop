package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tarena.elts.entity.User;

/**
 * 菜单窗口设计
 */
public class MenuFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel info;
	
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	public MenuFrame(){
		init();
	}
	
	/**
	 * 窗口初始化的方法
	 */
	private void init(){
		//设置标题
		this.setTitle("在线测评");
		//设置窗口的大小
		this.setSize(600,400);
		//设置位置居中
		this.setLocationRelativeTo(null);
		//设置窗口固定大小
		this.setResizable(false);
		//设置组件
		this.setContentPane(createContentPane());
		this.setDefaultCloseOperation(MenuFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				clientContext.exit(MenuFrame.this);
			}
			
		});
	}
	
	/**
	 * 添加主面板的方法
	 */
	private JPanel createContentPane(){
		JPanel panel = new JPanel(new BorderLayout());
		
		/**
		 * North  一张图片
		 * Center 一个panel 4个图片按钮
		 * South  一个字符串
		 */
		/**
		 * 显示图片的步骤
		 * 1：创建一个ImageIcon
		 * 2：创建一个Label显示ImageIcon
		 */
		ImageIcon image = new ImageIcon(this.getClass().getResource("title.png"));
		JLabel label = new JLabel(image);
		/**
		 * title标签
		 */
		panel.add(label,BorderLayout.NORTH);
		/**
		 * 中间部分
		 */
		panel.add(createMenuPane(),BorderLayout.CENTER);
		/**
		 * south的版权信息		
		 */
		panel.add(new JLabel("版权所有 盗版必究",JLabel.RIGHT),BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * 添加中间面板的方法
	 */
	private JPanel createMenuPane(){
		JPanel panel = new JPanel(new BorderLayout());
		/**
		 * North    欢迎文字
		 * Center   存放四个图片按钮的面板
		 */
		/**
		 * 用于显示欢迎信息的label
		 */
		info = new JLabel("",JLabel.CENTER);
		/**
		 * 添加文字标签
		 */
		panel.add(info,BorderLayout.NORTH);
		/**
		 * 添加四个图片按钮
		 */
		panel.add(createBtnPane(),BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * 添加四个图片按钮的的面板的方法
	 */
	private JPanel createBtnPane(){
		JPanel panel = new JPanel();

		//注意！！！！！！！！！！！！！！！！！！！！！！！！
		/**
		 * 开始按钮
		 */
		JButton start = createImageButton("exam.png", "开始考试");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * 调用控制器里面的开始考试的方法
				 */
				clientContext.start();
			}
		});
		/**
		 * 分数按钮
		 */
		JButton result = createImageButton("result.png","查看分数");
		//查看分数的点击事件
		result.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clientContext.seeScore();
			}
		});
		/**
		 * 考试规则按钮
		 */
		JButton message = createImageButton("message.png","考试规则");
		message.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MenuFrame.this, "1.考生应带好二代身份证件及准考证（外国籍考生可用护照籍）。\n"
						+ "2.考生务必准时进入考场，迟到者按放弃考试不得入场。\n"
						+ "3.考生进入考场后须将身份证件放于桌面上方，以便检查。\n"
						+ "4.考生除身份证件和考试用品外，其它个人物品，应放置在考场内指定的位置。\n"
						+ "5.严禁将手机等电子设备带入考场。违者按作弊处理。\n"
						+ "6.考生应在答题纸上正确填写个人信息和答案，并承担填写错误的后果。\n"
						+ "7.请考生在考场内保持安静，不得交谈和走动。违规者，考试成绩将被取消。\n"
						+ "8.考试中途不得离开考场。否则考试成绩将被取消。\n"
						+ "9.在考试过程中有任何疑问等情况，请举手示意监考老师解决，不得大声喧哗。\n");
			}
		});
		
		/**
		 * 离开按钮
		 */
		JButton exit = createImageButton("exit.png","离开考试");
		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				clientContext.exit(MenuFrame.this);
			}
		});
		
		panel.add(start);
		panel.add(result);
		panel.add(message);
		panel.add(exit);
		
		return panel;
	}
	
	/**
	 * 创建图片按钮的方法
	 */
	private JButton createImageButton(String imageName,String txt){
		/**
		 * 创建图片按钮的步骤
		 * 1：创建ImageIcon
		 * 2：创建Button,使用重载的构造方法JButton(String str,ImageIcon icon)
		 * 	  或者后期设置图片,调用JButton的setIcon(ImageIcon icon)
		 */
		ImageIcon icon = new ImageIcon(this.getClass().getResource(imageName));
		
		JButton btn = new JButton(txt,icon);
		/**
		 * 设置按钮文字的方向在中下方：水平在中间，垂直在下面
		 */
		btn.setVerticalTextPosition(JButton.BOTTOM);
		btn.setHorizontalTextPosition(JButton.CENTER);
		
		return btn;
	}
	
	/**
	 * 用于更新菜单窗口中的欢迎信息的标签
	 * 参数为登陆成功后的用户对象
	 */
	public void updateView(User user){
		info.setText("欢迎考生：" + user.getName() + " 参加在线测试");
	}
}