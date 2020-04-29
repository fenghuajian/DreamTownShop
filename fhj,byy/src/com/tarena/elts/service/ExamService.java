package com.tarena.elts.service;

import java.util.List;

import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;

/**
 * 业务逻辑接口
 * 定义了所有软件的核心功能
 */
public interface ExamService {

	/**
	 * 用户登陆方法
	 */
	public User login(int id, String password)throws IdOrPasswordException;
	
	/**
	 * 开始考试的方法,用于生成考卷以及考试的信息
	 */
	public ExamInfo start();
	
	/**
	 * 根据题号获取考题
	 * @param questionIndex
	 * @return
	 */
	public QuestionInfo getQuestion(int questionIndex);
	
	/**
	 *  保存用户的答案
	 * @param questionIndex   考题的编号
	 * @param userAnswers     对应考题的用户的答案
	 */
	public void saveUserAnswers(int questionIndex, List<Integer> userAnswers);
	
	/**
	 * 结束考试（交卷，判分，出成绩）
	 */
	public int over();
	
	/**
	 * 获取用户的分数
	 */
	public int getScore();
	
	//注册
	public void register(int id, String name, String pwd);
	
	//注册验证
	public int check(int id);
}