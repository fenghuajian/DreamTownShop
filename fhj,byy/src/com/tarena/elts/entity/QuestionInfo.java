package com.tarena.elts.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 考卷上的一道题对象
 */
public class QuestionInfo {

	/**
	 * 问题对象
	 */
	private Question question;
	
	/**
	 * 在试卷中的序号
	 */
	private int questionIndex;
	
	/**
	 * 用户的答案
	 */
	private List<Integer> userAnswers = new ArrayList<Integer>();
	
	/**
	 * 默认的构造方法
	 */
	public QuestionInfo(){
		
	}
	
	/**
	 * 带参数的构造方法
	 */
	public QuestionInfo(int questionIndex,Question question){
		this.question = question;
		this.questionIndex = questionIndex;
	}
	
	public QuestionInfo(int questionIndex,Question question,List<Integer> userAnswers){
		this.questionIndex = questionIndex;
		this.question = question;
		this.userAnswers = userAnswers;
	}
	
	/**
	 * 重写toString()
	 */
	public String toString(){
		return question.toString();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}

	public List<Integer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<Integer> userAnswers) {
		this.userAnswers = userAnswers;
	}
}