package com.tarena.elts.entity;
/**
 * 考试实体
 * 描述的一场考试
 */
public class ExamInfo {

	/**
	 * 考试的科目
	 */
	private String title;
	
	/**
	 * 考生
	 */
	private User user;
	
	/**
	 * 考试的时间限制
	 */
	private int timeLimit;
	
	/**
	 * 考试的题目数量
	 */
	private int questionCount;
	
	/**
	 * 构造方法
	 */
	public ExamInfo(){
		
	}
	public ExamInfo(String title,User user,int timeLimit,int questionCount){
		this.title = title;
		this.user = user;
		this.timeLimit = timeLimit;
		this.questionCount = questionCount;
	}
	
	/**
	 * 重写toString()
	 */
	public String toString(){
		if(user == null){
			return "暂时无信息";
		}else{
			return "姓名：" + user.getName() +
							   "    编号：" + user.getId() +
							   "    考试时间：" + timeLimit + "分钟" +
							   "    考试科目：" + title +
							   "    题目数量：" + questionCount;
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
	
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	public int getQuestionCount() {
		return questionCount;
	}
	
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
}