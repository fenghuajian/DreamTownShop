package com.tarena.elts.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tarena.elts.ui.Main;
import com.tarena.elts.util.Config;

/**
 * 实体数据访问类
 * 此类包含两个功能
 *			1：读取并解析配置文件，将数据以实体类的实例保存
 * 		2：为其他类提供数据（提供相应数据的实体类的实例）
 */
public class EntityContext {

	/**
	 * 用于读取properties文件的类
	 */
	private Config config;
	
	/**
	 * 使用HashMap保存所有的用户对象
	 * key:用户的ID
	 * value:对应此用户的User对象
	 * 使用hashMap保存数据的好处在于，根据程序的需求分析后发现，
	 * 当用户输入ID和密码进行登陆时，我们想获取对应这个ID的用户
	 * 时，使用HashMap更利于查找此用户（省去使用List还需自己
	 * 写遍历以及判断的逻辑，而HashMap还可提高查询的效率）
	 */
	private Map<Integer, User> users = new HashMap<Integer, User>();
	
	/**
	 *key:考题的难易程度（level）
	 *value:对应难度的一组考题的List<Question>
	 */
	private Map<Integer, List<Question>> questions = 
														new HashMap<Integer, List<Question>>();
	
	public EntityContext(Config config){
		/**
		 * 构造方法作用
		 * 将读取指定properties的Config传入，用于以后提供给其他类
		 * 
		 * 读取user.txt文件,将文件中的每行数据转换为一个User实例，并
		 * 将这些User实例保存起来，用于以后提供给其他类
		 * 
		 * 读取corejava.txt文件，将文件中的每个考题信息转换为
		 * 一个Question实例,并将这些Question实例保存起来,用于以后提
		 * 供给其他类
		 */
		this.config = config;
		
		/**
		 * 加载用户信息
		 */
		loadUser(config.getString("UserFile"));
		
		/**
		 * 加载考题信息
		 */
		loadQuestion(config.getString("QuestionFile"));
	}
	
	/**
	 * 根据给定的文件名读取并解析用户信息,并将每个User对象放入当前类
	 * 的属性users这个HashMap中,key为当前用户的ID
	 */
	private void loadUser(String fileName){
		try{
			InputStream is=this.getClass().getResourceAsStream("/com/tarena/elts/ui/"+fileName);
			InputStreamReader reader = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(reader);
			
			String str;
			while((str=br.readLine())!=null){
				/**
				 * 判断：若以＃开头或是一行空的字符串,就省略
				 */
				if(str.equals("")||str.startsWith("#")){
					continue;
				}
				
				/**
				 * 解析一个用户信息转换为User的一个对象,并将每个对象
				 * 放到hashMap中
				 */
				User user = parseUser(str);
				users.put(user.getId(), user);
			}
			/**
			 * 关闭流
			 */
			br.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加载考题信息
	 * 解析考题,并放入questions的HashMap中
	 */
	private void loadQuestion(String fileName){
		try{
			
			InputStream is=this.getClass().getResourceAsStream("/com/tarena/elts/ui/"+fileName);
			InputStreamReader reader = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(reader);
			
			String str;
			while((str=br.readLine())!=null){
				/**
				 * 去掉两头的空白
				 */
				str = str.trim();
				
				/**
				 * 跳过注释和空行
				 */
				if(str.startsWith("#") || str.equals("")){
					continue;
				}
				Question question = parseQuestion(str, br);
				/**
				 * 将考题放入map中
				 */
				addQuestion(question);				
			}
			/**
			 * 关闭流
			 */
			br.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将一个字符串代表的用户信息解析转换为User的一个实例
	 */
	private User parseUser(String info){
		User user = new User();
		String []data = info.split(":");
		user.setId(Integer.parseInt(data[0]));
		user.setName(data[1]);
		user.setPassword(data[2]);
		user.setPhone(data[3]);
		user.setEmail(data[4]);
		
		return user;
	}
	
	/**
	 * 将一个字符串代表的考题信息解析转换为Question的一个实例
	 * 
	 * 参数有两个，第一个参数为考题配置文件中的第一行
	 * 第二个参数为读取该配置文件的输入流，传入的目的是继续读取
	 * 五行数据,用于完成一道考题的解析
	 */
	private Question parseQuestion(String info,BufferedReader br)throws IOException{
		Question question = new Question();
		/**
		 * 解析第一行（info）
		 * 第一行：@answer=2/3,score=5,level=5
		 * 以“@a-z=”根据正则表达式进行拆分
		 */
		String []data = info.split("[@,][a-z]+=");
		
		/**
		 * 从data的第二个元素开始取
		 */
		question.setAnswers(parseAnswer(data[1]));
		/**
		 * 设置题目的分数
		 */
		question.setScore(Integer.parseInt(data[2]));
		/**
		 *设置题目的等级 
		 */
		question.setLevel(Integer.parseInt(data[3]));
		/**
		 * 设置题干
		 */
		question.setTitle(br.readLine());
		/**
		 * 读取选项
		 * 4个选项的集合
		 */
		List<String> options = new ArrayList<String>();
		options.add(br.readLine());
		options.add(br.readLine());
		options.add(br.readLine());
		options.add(br.readLine());
		/**
		 * 设置选项
		 */
		question.setOptions(options);
		
		/**
		 * 设置类型：单选或者多选
		 */
		if(question.getAnswers().size()==1){
			question.setType(Question.SINGLE_SELECTION);
		}else{
			question.setType(Question.MULTI_SELECTION);
		}

		return question;
	}
	
	/**
	 * 将正确的答案的字符串转换为对应的List集合
	 * 正确答案的字符串的格式为：0/1/2
	 */
	private List<Integer> parseAnswer(String info){
		/**
		 * 1：创建一个集合
		 * 2：拆分字符串
		 * 3：循环字符串数组，将每个元素放入集合中
		 * 4：将集合返回
		 */
		List<Integer> answers = new ArrayList<Integer>();
		String []data = info.split("/");
		for(String str:data){
			answers.add(Integer.parseInt(str));
		}
		return answers;
	}
	
	/**
	 * 将Question对象放入Map questions中
	 * 这里有两种情况：
	 * 		1：根据当前考题的难易度level在map中找到对应的key，并
	 * 			  获取对应的集合，那么直接将当前question对象放入集合
	 * 		2：根据当前考题Question对象的难易度level在map这没有
	 * 			  此key,那么应先创建一个集合,以此题的key存入map中，再
	 * 			  将此题放入集合
	 */
	private void addQuestion(Question question){
		/**
		 * 1：根据question的level从map中获取list集合
		 * 2：判断该集合是否存在，若不存在就创建集合并以question
		 *       的level作为key存入map
		 *3：将question放入List集合中
		 */
		List<Question> list = questions.get(question.getLevel());
		if(list == null){
			list = new ArrayList<Question>();
			questions.put(question.getLevel(), list);
		}
		list.add(question);
	}
	
	/**
	 * 根据给定的用户的id查找并返回此用户的User实例
	 */
	public User findUserById(int id){
		return users.get(id);
	}
	
	/**
	 * 根据考题的难易度获取对应的题目
	 */
	public List<Question> findQuestionByLevel(int level){
		/**
		 * 这里不直接返回map中的某个集合
		 * 因为一旦将map中的某个考题集合返回，那么外界若对此集合元素进行
		 * 删减操作，那么考题信息就不完全了
		 * 所以，返回的集合应使用几个的复制构造器复制一份集合返回
		 */
		return new ArrayList<Question>(questions.get(level));
	}
	
	/**
	 * 根据配置文件返回考试时间
	 */
	public int getTimeLimit(){
		/**
		 * 读取client.properties的是Config类
		 * 所一要从Congig中读取所要的值
		 */
		return config.getInt("TimeLimit");
	}
	
	/**
	 * 根据配置文件返回考试的科目
	 */
	public String getTitle(){
		return config.getString("PaperTitle");
	}

	/**
	 *将注册信息写入文件 
	 */
	public void registerToFile(String info){
		try{
			String fileName = config.getString("UserFile");
			FileOutputStream fos = new FileOutputStream(fileName,true);
			//FileOutputStream fos = new FileOutputStream("C:\\Users\\封华健\\IdeaProjects\\fhj,byy\\src\\com\\tarena\\elts\\ui\\user.txt");
			OutputStreamWriter writer = new OutputStreamWriter(fos,"UTF-8");
			PrintWriter pw = new PrintWriter(writer);
			//pw.append("\n");
			pw.append(info + "\n");
		//	pw.write(info + "\n");
			System.out.println("info:"+info);
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试方法
	 */
	public static void main(String[] args){
		Config config = new Config("client.properties");
		EntityContext entityContext = new EntityContext(config);
		
		System.out.println(entityContext.users);
		
		System.out.println(entityContext.questions);
	}
}