package com.tarena.elts.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述考题信息的实体
 * 包含：题干／选项／得分／正确答案／考题难度等信息
 */
public class Question {

	/**
	 * 以常量的形式描述考题的等级
	 * 常量的作用：
	 * 		通常在某个方法中需要传递某个值来进行相关的操作，但是此值
	 * 		是比较抽象的，不便于对此方法参数的理解，所以为了解决相应
	 * 		的问题，我们会引入常量，将常量的名字定义为比较容易理解的
	 * 		的字符常量值为对应的值，这样我们在使用方法时传递相关参数
	 * 		时，就不需要记忆那些比较抽象的实参值了，而是传递相应的常
	 * 		量
	 * 例如：
	 * 	int sex = p.getSex();//sex的值为int值,0是男还是女?
	 * 	Person类一般会对这样的值给出相应的常量
	 * 	class Person{
	 * 			public static final int MAN = 1;
	 * 			Public static final int WOMAN = 0;
	 * 		}
	 * 	if(sex == Person.MAN){
	 * 			System.out.println("男士");
	 * 		}
	 */
	public static final int LEVEl1 = 1;
	public static final int LEVEl2 = 2;
	public static final int LEVEl3 = 3;
	public static final int LEVEl4 = 4;
	public static final int LEVEl5 = 5;
	public static final int LEVEl6 = 6;
	public static final int LEVEl7 = 7;
	public static final int LEVEl8 = 8;
	public static final int LEVEl9 = 9;
	public static final int LEVEl10 = 10;
	
	/**
	 * 定义所选还是单选的常量
	 */
	public static final int SINGLE_SELECTION = 0;
	public static final int MULTI_SELECTION = 1;
	
	/**
	 * 编号
	 */
	private int id;
	
	/**
	 * 题干  标题
	 */
	private String title;
	
	/**
	 * 题目的选项集合
	 */
	private List<String> options = new ArrayList<String>();
	
	/**
	 * 答案的集合
	 */
	private List<Integer> answers = new ArrayList<Integer>();
	
	/**
	 * 分数
	 */
	private int score;
	
	/**
	 * 等级
	 */
	private int level;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 构造方法
	 */
	public Question(){
		
	}

	/**
	 * 重写toString()
	 * 显示的效果：
	 * 		下列说法错误的是：
	 * 	A.我是个传奇
	 * 	B.你不是好人
	 * 	c.你真漂亮
	 * 	D.相信自己我是好孩子
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer(title + "\n");
		for(int i=0;i<options.size();i++){
			sb.append((char)(i + 'A') + "." + options.get(i) + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	/**
	 * 重写equals()
	 */
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj instanceof Question){
			Question other = (Question)obj;
			return other.id == this.id;
		}
		return false;
	}
	
	/**
	 * 重写hashCode()
	 */
	public int hashCode(){
		return id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Integer> answers) {
		this.answers = answers;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}