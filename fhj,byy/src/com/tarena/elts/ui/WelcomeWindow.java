package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class WelcomeWindow extends JWindow{
	private static final long serialVersionUID = 1L;

	public WelcomeWindow(){
		/**
		 * 调用启动初始化界面方法
		 */
		init();
	}
	
	/**
	 * 初始化界面
	 */
	private void init(){
		this.setSize(500,300);
		/**
		 * 设置位置水平居中
		 */
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
		
	}
	
	/**
	 * 创建主组件面板
	 */
	private JPanel createContentPane(){
		JPanel panel = new JPanel(new BorderLayout());
		ImageIcon image = new ImageIcon(this.getClass().getResource("welcome.png"));
		JLabel jl = new JLabel(image);
		jl.setFont(new Font("黑体", Font.BOLD, 70));
		jl.setForeground(Color.ORANGE);
		panel.add(jl,BorderLayout.CENTER);
		
		return panel;
	}
}