package com.tarena.elts.ui;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;
import com.tarena.elts.service.ExamService;
import com.tarena.elts.service.IdOrPasswordException;

/**
 * 控制器   MVC的C层
 * 提供界面流程的控制逻辑
 */
public class ClientContext {

	private Timer timer;//考试计时器
	
	/**
	 * 记录当前考题，以便执行流程
	 */
	private QuestionInfo currentQuestion;
	
	/**
	 * 视图层：登陆界面
	 */
	private LoginFrame loginFrame;
	/**
	 * 提供该属性的setter方法,用于让控制器认识
	 * @param loginFrame
	 */
	public void setLoginFrame(LoginFrame loginFrame){
		this.loginFrame = loginFrame;
	}
	
	private WelcomeWindow welcomeWindow;
	public void setWelcomeWindow(WelcomeWindow welcomeWindow) {
		this.welcomeWindow = welcomeWindow;
	}

	/**
	 * 视图层：菜单界面
	 */
	private MenuFrame menuFrame;
	/**
	 * 提供该属性的setter方法 用于让控制器认识
	 * @param examFrame
	 */
	public void setMenuFrame(MenuFrame menuFrame){
		this.menuFrame = menuFrame;
	}
	
	/**
	 * 视图层：考试界面
	 */
	private ExamFrame examFrame;
	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}

	//注册界面
	private RegisterFrame registerFrame;
	public void setRegisterFrame(RegisterFrame registerFrame) {
		this.registerFrame = registerFrame;
	}

	/**
	 * 业务逻辑层
	 */
	private ExamService examService;
	/**
	 * 提供该属性的setter方法方便控制器认识
	 * @param examService
	 */
	public void setExamService(ExamService examService){
		this.examService = examService;
	}
	
	/**
	 * 控制器的业务逻辑流程控制方法
	 */
	/**
	 * 登陆流程
	 * 不处理登陆的逻辑，只是将视图层数据获取到，交给业务逻辑层去处理登陆，
	 * 然后在根据登陆结果跳转不同的视图（窗口）
	 * 
	 * 1：从登陆窗口获取用户输入的Id和密码
	 * 2：将id和密码交给业务逻辑类ExamService的等落方法
	 * 3：两种情况：
	 *       3.1：调用ExamService的login()方法报错，这说明用户输入的用户名
	 *                或者密码有错误，应该捕获异常IdOrPasswordException并通过
	 *                登陆窗口告知用户此错误
	 *       3.2：正常登陆完毕，返回User对象，就应该将登陆窗口关闭，并展示菜单
	 *                窗口（MenuFrame）
	 */
	public void login(){
		try{
			/**
			 * 从视图层获得用户输入的ID
			 */
			int id = loginFrame.getUserId();
			/**
			 * 从视图层获得用户输入的密码
			 */
			String password = loginFrame.getUserPassword();
			/**
			 * 调用ExamService的login()方法
			 */
			User user = examService.login(id, password);
			/**
			 * 如果验证成功了loginFrame窗口隐藏  examFrame窗口显示
			 */
			loginFrame.setVisible(false);
			/**
			 * 登陆成功之后更新菜单窗口的欢迎信息表单
			 */
			menuFrame.updateView(user);
			menuFrame.setVisible(true);
			
		}catch(NumberFormatException e){
			e.printStackTrace();
			loginFrame.showError("帐号只能是数字！！！");
		}catch(IdOrPasswordException e){
			e.printStackTrace();
			loginFrame.showError(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			loginFrame.showError(e.getMessage());
		}
	}
	
	//显示注册界面
	public void toRegisterFrame(){
		loginFrame.setVisible(false);
		registerFrame.setVisible(true);
	}
	
	/**
	 * 控制器中最初的方法，作用是将登陆窗口显示出来
	 */
	public void show(){
		final Timer ti = new Timer();
		welcomeWindow.setVisible(true);
		ti.schedule(new TimerTask() {
			public void run() {
				welcomeWindow.setVisible(false);
				loginFrame.setVisible(true);
				ti.cancel();
			}
		}, 2000);
	}
	
	/**
	 * 关闭的方法
	 */
	public void exit(JFrame jframe){
		int jp = JOptionPane.showConfirmDialog(jframe, "确定关闭？");
		if(jp==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	
	/**
	 * 开始考试的流程逻辑
	 * 1：调用ExamService的start()生成考题和考试信息并获取考试信息ExamInfo
	 * 2：将考试信息交给ExamFrame，用于在考试窗口显示
	 * 3：从ExamService中取第一到考题
	 * 4：将第一道考题交给ExamFrame，并在窗口显示
	 * 5：将菜单窗口关闭
	 * 6：打开考试窗口
	 */
	public void start(){
		try{
			
			/**
			 * ExamService的start方法可能会抛出异常
			 * 若抛出异常，将异常信息作为提示信息在MenuFrame窗口弹框
			 */
			
			ExamInfo examInfo = examService.start();
			
			QuestionInfo  question = examService.getQuestion(0);
			/**
			 * 把当前的考题记录下来
			 */
			currentQuestion = question;
			
			/**
			 * 更新考试信息
			 */
			examFrame.updateExamInfo(examInfo);
			/**
			 * 更新考题
			 */
			examFrame.updateQuestion(question);
			menuFrame.setVisible(false);
			examFrame.setVisible(true);
			/**
			 * 开始考试计时
			 */
			startTimer(examInfo.getTimeLimit());
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	
	/**
	 * 开始计时，需要传入总共倒计时的分钟数
	 * @param timerLimit
	 */
	private void startTimer(int timerLimit){
		final long end = System.currentTimeMillis() +
									timerLimit*60*1000;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				long show = end-System.currentTimeMillis();
				long h = show/1000/60/60;
				long m = show/1000/60%60;
				long s = show/1000%60;
				examFrame.updateTimer(h, m, s);
			}
		}, 0, 1000);
		/**
		 * 此任务用于规定时间到来时，停止计时器
		 */
		timer.schedule(new TimerTask() {
			public void run() {
				gameOver();//强行交卷
				timer.cancel();
			}
		}, new Date(end));
	}
	
	/**
	 * 下一道考题
	 * 1：根据当前考题的题号，并对当前题号＋1，得到下一题的题号
	 * 2：获取当前考题的用户答案，并交给ExamService去保存答案
	 * 3：通过ExamService根据下一题的题号获取考题
	 * 4：将老题交给ExamFrame显示
	 */
	public void next(){
		try{
			int index = currentQuestion.getQuestionIndex();
			/**
			 * 从考试界面获取用户的答案
			 */
			List<Integer> userAnswers = examFrame.getUserAnswers();
			examService.saveUserAnswers(currentQuestion.getQuestionIndex(), userAnswers);
			index++;
			QuestionInfo question = examService.getQuestion(index);
			currentQuestion = question;
			examFrame.updateQuestion(currentQuestion);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 上一道考题
	 */
	public void prev(){
		try{
			int index = currentQuestion.getQuestionIndex();
			List<Integer> userAnswers = examFrame.getUserAnswers();
			examService.saveUserAnswers(currentQuestion.getQuestionIndex(), userAnswers);
			index--;
			QuestionInfo question = examService.getQuestion(index);
			currentQuestion = question;
			examFrame.updateQuestion(currentQuestion);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 交卷流程
	 * 1：询问用户是否真的要交卷，使用弹出信息况的形式
	 * 2：若确定交卷执行真实的交卷流程
	 */
	public void sendPaper(){
		/**
		 *JOPtionPane的静态方法showConfirmDialog方法的作用是
		 *在某个窗口弹框
		 *需要两个参数
		 *第一个是需要弹窗的的窗口
		 *第二个是弹框的提示信息 
		 *返回值是点击的是确定按钮还是取消按钮
		 */
		int value = JOptionPane.showConfirmDialog(examFrame, "您确定要交卷吗？");
		if(value==JOptionPane.YES_OPTION){
			gameOver();
		}
	}
	
	/**
	 * 交卷的实际流程
	 * 1：将当前考题的用户答案保存
	 * 2：调用ExamService进行判题并返回分数
	 * 3：
	 * 4：弹个框，提示用户的得分
	 *5：然后将考试窗口隐藏6
	 *6：将菜单窗口显示
	 */
	public void gameOver(){
		try{
			int questionIndex = currentQuestion.getQuestionIndex();
			List<Integer> userAnswers = examFrame.getUserAnswers();
			examService.saveUserAnswers(questionIndex, userAnswers);
			
			int score = examService.over();
			
			/**
			 * 停止考试计时
			 */
			if(timer!=null){
				timer.cancel();
			}
			
			JOptionPane.showMessageDialog(examFrame, "您的得分是：" + score + " 分！");
			
			examFrame.setVisible(false);
			menuFrame.setVisible(true);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看成绩
	 * 1：判断是否参加了考试，如果没有考过始，抛出异常，提醒用户您还没有参加考试呢
	 * 2：如果考过试就弹框显示成绩
	 */
	public void seeScore(){
		try{
			
			/**
			 * 得到分数
			 */
			int myScore = examService.getScore();
			JOptionPane.showMessageDialog(menuFrame, "您的考试成绩是：" + myScore + " 分");
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	
	//从注册界面到登陆界面
	public void toLoginFrame(){
		registerFrame.setVisible(false);
		loginFrame.setVisible(true);
	}
	
	/**
	 * 用户注册方法
	 */
	public void register(){
		//id
		String id = registerFrame.getUserId();
		//姓名
		String name = registerFrame.getUserName();
		//密码
		String password = registerFrame.getPassword();
		//判断
		if(id.equals("") || name.equals("") || password.equals("")){
			registerFrame.showError("帐号或姓名或密码不可空！");
		}else if(!id.matches("\\d*")){
			registerFrame.showError("帐号只能为数字！");
		}else{
			if(examService.check(Integer.parseInt(id))==0){
				registerFrame.showError("该帐号已经存在！");
			}else{
				examService.register(Integer.parseInt(id), name, password);
				registerFrame.showError("注册成功！");
			}
		}
	}
}