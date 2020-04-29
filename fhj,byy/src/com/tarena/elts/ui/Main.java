package com.tarena.elts.ui;

import java.io.InputStream;
import java.net.URL;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.service.ExamServiceImpl;
import com.tarena.elts.util.Config;

/**
 * 程序启动类
 * 该类的作用在于，将程序的各个层次创建出来，并将每个层次之间的
 * 关系建立起来，然后通过控制器开始程序
 */
public class Main {

	public static void main(String[] args) {
		/**
		 * 创建V层的所有对象
		 */
		WelcomeWindow welcomeWindow = new WelcomeWindow();
		LoginFrame loginFrame = new LoginFrame();
		MenuFrame menuFrame = new MenuFrame();
		ExamFrame examFrame = new ExamFrame();
		RegisterFrame registerFrame = new RegisterFrame();
		
		/**
		 * 创建C层对象
		 */
		ClientContext clientContext = new ClientContext();
		
		/**
		 * 创建M层（模型层）对象
		 */
		/**
		 * 创建业务逻辑类
		 */
		ExamServiceImpl examService = new ExamServiceImpl();
		/**
		 * 创建数据访问类
		 */
		Config config = new Config("client.properties");
		EntityContext entityContext = new EntityContext(config);
		
		/**
		 * 建立层次之间的关系
		 */
		/**
		 * 视图层与控制层建立
		 */
		loginFrame.setClientContext(clientContext);
		menuFrame.setClientContext(clientContext);
		examFrame.setClientContext(clientContext);
		registerFrame.setClientContext(clientContext);
		
		
		/**
		 * 控制层与视图层建立
		 */
		clientContext.setWelcomeWindow(welcomeWindow);
		clientContext.setLoginFrame(loginFrame);
		clientContext.setMenuFrame(menuFrame);
		clientContext.setExamFrame(examFrame);
		clientContext.setRegisterFrame(registerFrame);
		
		/**
		 * 控制层于业务逻辑层建立
		 */
		clientContext.setExamService(examService);
		
		/**
		 * 业务逻辑层与数据建立
		 */
		examService.setEntityContext(entityContext);
		
		/**
		 * 开启登陆窗口
		 */
		clientContext.show();
	}
}