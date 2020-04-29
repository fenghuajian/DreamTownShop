package com.tarena.elts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.Question;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;
/**
 * 业务逻辑实现类
 * 实现了业务逻辑接口，完成相应的核心功能逻辑
 */
public class ExamServiceImpl implements ExamService {

	private int score;//得分
	private boolean finish;//考试是否结束了
	
	/**
	 * 此属性的作用是保存开始考试后生成的所有的考题，这里集合有放得是QuestionInfo
	 * 而不是Question，因为考题要包含用户的答案
	 */
	private List<QuestionInfo> paper = new ArrayList<QuestionInfo>();
	
	/**
	 * 此属性作用是保存当前的登陆用户的信息
	 */
	private User user;
	
	/**
	 * 提供数据的类
	 */
	private EntityContext entityContext;
	/**
	 * 提供该属性的setter方法
	 * 不需要提供getter方法，因为当前属性只在当前类内使用，不需提供
	 * 
	 * 作用是让当前业务逻辑类认识一个为他提供数据的类
	 * @param entityContext
	 */
	public void setEntityContext(EntityContext entityContext){
		this.entityContext = entityContext;
	}
	
	/**
	 * 实现接口的用户登陆的逻辑
	 */
	public User login(int id, String password)throws IdOrPasswordException{
		/**
		 * 1：通过提供数据的类EntityContext提供给定的用户id获得这个用户的信息（User实例）
		 * 2：判断EntityContext这个类返回的User实例是否存在,若null,说明用户输入的id是错误的
		 * 3：若User实例不为null,判断给定的密码和User实例中此人的密码是否一致,若不一致说明密码
		 *       错误,应告知控制层（抛出异常）
		 *4：若密码输入的一致，返回这个用户的实例，表示登陆成功
		 */
		User user = entityContext.findUserById(id);
		if(user == null){
			throw new IdOrPasswordException("此人不存在！！！");
		}
		if(!user.getPassword().equals(password)){
			throw new IdOrPasswordException("密码输入错误！！！");
		}
		/**
		 * 将登陆用户保存在属性上，供其他方法使用
		 */
		this.user = user;
		
		return user;
	}

	/**
	 * 开始考试
	 * 先进性判断，如果finish为true说明考试已经结束了，就抛出异常，告知用户考试结束
	 * 需要完成的事情有：生成考题,生成考试信息（ExamInfo）
	 */
	public ExamInfo start() {
		if(finish==true){
			throw new RuntimeException("考试结束！！！");
		}
		
		/**
		 * 生成考题
		 */
		createPaper();
		
		/**
		 * 创建考试信息对象(ExamInfo)，并完善内容
		 */
		ExamInfo examInfo = new ExamInfo();
		/**
		 * 考题总数
		 */
		examInfo.setQuestionCount(paper.size());
		/**
		 * 考试科目
		 */
		examInfo.setTitle(entityContext.getTitle());
		/**
		 * 考试时间
		 */
		examInfo.setTimeLimit(entityContext.getTimeLimit());
		/**
		 * 设置当前的登陆用户
		 */
		examInfo.setUser(user);
		
		return examInfo;
	}
	
	/**
	 * 生成考卷的方法
	 * 每个难度各选两个题，将这些题放入属性paper集合中作为考题
	 */
	private void createPaper(){
		Random random = new Random();
		/**
		 * 考题的题号
		 */
		int questionIndex = 0;
		/**
		 * 循环所在的难度
		 */
		for(int level = Question.LEVEl1;level<=Question.LEVEl10;level++){
			/**
			 * 根据当前等级，从EntityContext中获取该等级下的所有考题
			 */
			List<Question> questions = entityContext.findQuestionByLevel(level);
			
			/**
			 * 从集合中随机下标删除这个元素并获取到
			 */
			Question q1 = questions.remove(random.nextInt(questions.size()));
			
			/**
			 * 从集合下标中随机获取这个元素（不会重复，因为上一个元素被删除了）
			 */
			Question q2 = questions.get(random.nextInt(questions.size()));
			
			/**
			 * 将选好的两到题转换为QuestionInfo对象，这样才包含用户的答案
			 * 然后将QuestionInfo对象放入paper集合中
			 * 在转换QuestionInfo时,要给考题编号
			 */
			QuestionInfo qi1 = new QuestionInfo(questionIndex++,q1);
			QuestionInfo qi2 = new QuestionInfo(questionIndex++,q2);
			
			paper.add(qi1);
			paper.add(qi2);
		}
		
	}
	
	/**
	 * 根据题号得到考题
	 */
	public QuestionInfo getQuestion(int questionIndex){
		return paper.get(questionIndex);
	}

	/**保存用户的答案
	 * 因为所有的考题都在paper中，所以根据题号获取考题就是从paper
	 * 这个list中get出对应的题
	 * 拿到题目之后先将旧的用户的答案清除
	 * 再将新的用户的答案放入QuestionInfo
	 */
	public void saveUserAnswers(int questionIndex, List<Integer> userAnswers) {
		/**根据题号找到对应的考题QuestionInfo*/
		QuestionInfo question = paper.get(questionIndex);
		/**清除旧的答案*/
		//List<Integer> oldAnswers = question.getUserAnswers();
		//oldAnswers.clear();
		question.getUserAnswers().clear();
		/**方新的答案*/
		question.getUserAnswers().addAll(userAnswers);
	}

	/**
	 * 结束考试（交卷，判分，出成绩）
	 * 此方法包括几个功能：判题，出成绩
	 */
	public int over(){
		score = 0;
		for(QuestionInfo question:paper){
			/**获取对应的考题Question对象*/
			Question q = question.getQuestion();
			//用Question中的正确答案的集合和用户答案集合进行equals比较
			//如返回true就说明答对了
			if(q.getAnswers().equals(question.getUserAnswers())){
				score+=q.getScore();
			}
		}
		/**
		 * 设置考试已经结束了
		 */
		finish=true;
		
		return score;
	}
	
	
	/**
	 * 获取用户的分数
	 */
	public int getScore(){
		if(!finish){
			throw new RuntimeException("亲，您还没开始开始呢！！！");
		}else{
			return score;
		}
	}

	//注册
	public void register(int id,String name,String pwd) {
		String info = id +
							":" + name +
							":"+pwd +
							":" + "18877335730:" +
							"2274105767@qq.com";
	System.out.println(info);	
	entityContext.registerToFile(info);
	}

	//注册验证
	public int check(int id) {
		User u = entityContext.findUserById(id);
		if(u==null){
			return 1;
		}else{
			return 0;
		}
		
	}

}