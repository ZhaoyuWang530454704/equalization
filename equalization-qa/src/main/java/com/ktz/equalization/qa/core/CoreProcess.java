package com.ktz.equalization.qa.core;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.sun.javaws.IconUtil;
import lombok.val;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.sources.In;
import org.apache.spark.streaming.StreamingContext;

import java.io.*;
import java.util.*;

/**
 * Spark贝叶斯分类器 + HanLP分词器 + 实现问题语句的抽象+模板匹配+关键性语句还原
 * @blob   http://blog.csdn.net/appleyk
 * @date   2018年5月9日-上午10:07:52
 */
public class CoreProcess {


	/**
	 * 分类标签号和问句模板对应表
	 */
	Map<Double, String> questionsPattern;

	/**
	 * Spark贝叶斯分类器
	 */
	NaiveBayesModel nbModel;

	/**
	 * 词语和下标的对应表   == 词汇表
	 */
	Map<String, Integer> vocabulary;

	/**
	 * 关键字与其词性的map键值对集合 == 句子抽象
	 */
	Map<String, String> abstractMap;

	/**
	 * 指定问题question及字典的txt模板所在的根目录
	 */
//    String rootDirPath = "D:/";
	String rootDirPath = "D:/HLP汉语言处理/";

    /**
     * 分类模板索引
     */
    int modelIndex = 0;

	public CoreProcess() throws Exception{
		questionsPattern = loadQuestionsPattern();
		vocabulary = loadVocabulary();
		nbModel = loadClassifierModel();
	}


	public CoreProcess(String rootDirPath) throws Exception{
		this.rootDirPath = rootDirPath+'/';
		questionsPattern = loadQuestionsPattern();
		vocabulary = loadVocabulary();
		nbModel = loadClassifierModel();
	}

	public ArrayList<String> analyQuery(String queryString) throws Exception {

//		/**
//		 * 打印问句
//		 */
//		System.out.println("原始句子："+queryString);
//		System.out.println("========HanLP开始分词========");
////
//		/**
//		 * 抽象句子，利用HanPL分词，将关键字进行词性抽象
//		 */
//		String abstr = queryAbstract(queryString);
//		System.out.println("句子抽象化结果："+abstr);
//
//		/**
//		 * 将抽象的句子与spark训练集中的模板进行匹配，拿到句子对应的模板
//		 */
//		String strPatt = queryClassify(abstr);
//		System.out.println("句子套用模板结果："+strPatt);


		/**
		 * 模板还原成句子，此时问题已转换为我们熟悉的操作
		 */
		//这里原来是strPatt
		String finalPattern = queryExtenstion(queryString);
		System.out.println("原始句子替换成系统可识别的结果："+finalPattern);
		System.out.println("modelIndex: "+modelIndex);

		ArrayList<String> resultList = new ArrayList<String>();
		resultList.add(String.valueOf(modelIndex));
		String[] finalPattArray = finalPattern.split(" ");
		for (String word : finalPattArray){
			System.out.println(word);
			resultList.add(word);}
        //返回的finalPattern应该是还原后的问句
		return resultList;
	}

//	public ArrayList<String>

public  String queryAbstract(String querySentence) {
	// 句子抽象化
	Segment segment = HanLP.newSegment().enableCustomDictionary(true);
	List<Term> terms = segment.seg(querySentence);
	for (Term term : terms) {
		System.out.print(term+"  ");
	}
	StringBuilder abstractQuery = new StringBuilder();
	abstractMap = new HashMap<String, String>();
	for (Term term : terms) {
		String word = term.word;
		System.out.println(word);
		String termStr = term.toString();
		if (termStr.contains("ly")) {
			abstractQuery.append("ly ");
			abstractMap.put("ly", word);
		} else if (termStr.contains("xm")) {
			abstractQuery.append("xm ");
			abstractMap.put("xm", word);
		}else if (termStr.contains("smsx")) {
			abstractQuery.append("smsx ");
			abstractMap.put("smsx", word);
		}else if (termStr.contains("bm")) {
			abstractQuery.append("bm ");
			abstractMap.put("bm", word);
		} else if (termStr.contains("dp")) {
			abstractQuery.append("dp ");
			abstractMap.put("dp", word);
		}
		else {
			abstractQuery.append(word).append(" ");
		}
	}
//    System.out.println("========HanLP分词结束========");
	return abstractQuery.toString();
}


	public  String queryExtenstion(String queryPattern) {
		//queryPattern是问题模板
		// 句子还原

		Set<String> set = abstractMap.keySet();

		for (String key : set) {
			System.out.println("yes");
			/**
			 * 如果句子模板中含有抽象的词性
			 */
			if (queryPattern.contains(key)) {

				/**
				 * 则替换抽象词性为具体的值
				 */
				String value = abstractMap.get(key);
				queryPattern = queryPattern.replace(key, value);
			}
		}
		String extendedQuery = queryPattern;
		/**
		 * 当前句子处理完，抽象map清空释放空间并置空，等待下一个句子的处理
		 */
		abstractMap.clear();
		abstractMap = null;
		return extendedQuery;
	}


	/**
	 * 加载词汇表 == 关键特征 == 与HanLP分词后的单词进行匹配
	 * @return
	 */
	public  Map<String, Integer> loadVocabulary() {
		Map<String, Integer> vocabulary = new HashMap<String, Integer>();
//		File file = new File(rootDirPath + "questiontxt/vocabulary.txt");

		BufferedReader br = null;
		try {
			InputStreamReader ls=new InputStreamReader(new FileInputStream(rootDirPath + "questiontxt/vocabulary.txt"),"UTF-8");
			br = new BufferedReader(ls);
//			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] tokens = line.split(" ");
				int index = Integer.parseInt(tokens[0]);
				String word = tokens[1];
				vocabulary.put(word, index);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vocabulary;
	}

	/**
	 * 加载文件，并读取内容返回
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public  String loadFile(String filename) throws IOException {
		File file = new File(rootDirPath + filename);
//		BufferedReader br = new BufferedReader(new FileReader(file));
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String content = "";
		String line;
		while ((line = br.readLine()) != null) {
			/**
			 * 文本的换行符暂定用"`"代替
			 */
			content += line + "`";
		}
		/**
		 * 关闭资源
		 */
		br.close();
		return content;
	}

	/**
	 * 句子分词后与词汇表进行key匹配转换为double向量数组
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	public  double[] sentenceToArrays(String sentence) throws Exception {

		double[] vector = new double[vocabulary.size()];
		/**
		 * 模板对照词汇表的大小进行初始化，全部为0.0
		 */
		for (int i = 0; i < vocabulary.size(); i++) {
			vector[i] = 0;
		}

		/**
		 * HanLP分词，拿分词的结果和词汇表里面的关键特征进行匹配
		 */
		Segment segment = HanLP.newSegment();
		List<Term> terms = segment.seg(sentence);
		for (Term term : terms) {
			String word = term.word;
			/**
			 * 如果命中，0.0 改为 1.0
			 */
			if (vocabulary.containsKey(word)) {
				int index = vocabulary.get(word);
				vector[index] = 1;
			}
		}
		return vector;
	}

	/**
	 * Spark朴素贝叶斯(naiveBayes)
	 * 对特定的模板进行加载并分类
	 * 欲了解Spark朴素贝叶斯，可参考地址：https://blog.csdn.net/appleyk/article/details/80348912
	 * @return
	 * @throws Exception
	 */
	public  NaiveBayesModel loadClassifierModel() throws Exception {

		/**
		 * 生成Spark对象
		 * 一、Spark程序是通过SparkContext发布到Spark集群的
		 * Spark程序的运行都是在SparkContext为核心的调度器的指挥下进行的
		 * Spark程序的结束是以SparkContext结束作为结束
		 * JavaSparkContext对象用来创建Spark的核心RDD的
		 * 注意：第一个RDD,一定是由SparkContext来创建的
		 *
		 * 二、SparkContext的主构造器参数为 SparkConf
		 * SparkConf必须设置appname和master，否则会报错
		 * spark.master   用于设置部署模式
		 * local[*] == 本地运行模式[也可以是集群的形式]，如果需要多个线程执行，可以设置为local[2],表示2个线程 ，*表示多个
		 * spark.app.name 用于指定应用的程序名称  ==
		 */

		/**
		 * 题外话
		 * 贝叶斯是谁？
		 * 贝叶斯(约1701-1763) Thomas Bayes，英国数学家。
		 * 1702年出生于伦敦，做过神甫。
		 * 1742年成为英国皇家学会会员。
		 * 1763年4月7日逝世。
		 * 贝叶斯在数学方面主要研究概率论 == 贝叶斯公式是概率论中较为重要的公式
		 */
		SparkConf conf = new SparkConf().setAppName("NaiveBayesTest").setMaster("local[*]");
		JavaSparkContext sc = new JavaSparkContext(conf);

		/**
		 * 训练集生成
		 * labeled point 是一个局部向量，要么是密集型的要么是稀疏型的
		 * 用一个label/response进行关联。在MLlib里，labeled points 被用来监督学习算法
		 * 我们使用一个double数来存储一个label，因此我们能够使用labeled points进行回归和分类
		 */
		List<LabeledPoint> train_list = new LinkedList<LabeledPoint>();
		String[] sentences = null;


		/**
		 * 领域对有服务项目是什么
		 */
		String scoreQuestions = loadFile("questiontxt/【0】服务项目.txt");
		sentences = scoreQuestions.split("`");
		for (String sentence : sentences) {
			double[] array = sentenceToArrays(sentence);
			LabeledPoint train_one = new LabeledPoint(0.0, Vectors.dense(array));
			train_list.add(train_one);
		}

		/**
		 * ...是什么
		 */
		String timeQuestions = loadFile("questiontxt/【1】概念.txt");
		sentences = timeQuestions.split("`");
		for (String sentence : sentences) {
			double[] array = sentenceToArrays(sentence);
			LabeledPoint train_one = new LabeledPoint(1.0, Vectors.dense(array));
			train_list.add(train_one);
		}


		/**
		 * ..的研究热点是什么
		 */
		String styleQuestions = loadFile("questiontxt/【2】研究热点.txt");
		sentences = styleQuestions.split("`");
		for (String sentence : sentences) {
			double[] array = sentenceToArrays(sentence);
			LabeledPoint train_one = new LabeledPoint(2.0, Vectors.dense(array));
			train_list.add(train_one);
		}


		/**
		 * 领域的学者有谁
		 */
		String storyQuestions = loadFile("questiontxt/【3】学者.txt");
		sentences = storyQuestions.split("`");
		for (String sentence : sentences) {
			double[] array = sentenceToArrays(sentence);
			LabeledPoint train_one = new LabeledPoint(3.0, Vectors.dense(array));
			train_list.add(train_one);
		}

		/**
		 * 领域由哪些部门负责
		 */
		String actorsQuestion = loadFile("questiontxt/【4】负责.txt");
		sentences = actorsQuestion.split("`");
		for (String sentence : sentences) {
			double[] array = sentenceToArrays(sentence);
			LabeledPoint train_one = new LabeledPoint(4.0, Vectors.dense(array));
			train_list.add(train_one);
		}


		/**
		 * 地区的均等化水平如何
		 */
		String actorInfos = loadFile("questiontxt/【5】均等化水平.txt");
		sentences = actorInfos.split("`");
		for (String sentence : sentences) {
			double[] array = sentenceToArrays(sentence);
			LabeledPoint train_one = new LabeledPoint(5.0, Vectors.dense(array));
			train_list.add(train_one);
		}


		/**
		 * SPARK的核心是RDD(弹性分布式数据集)
		 * Spark是Scala写的,JavaRDD就是Spark为Java写的一套API
		 * JavaSparkContext sc = new JavaSparkContext(sparkConf);    //对应JavaRDD
		 * SparkContext	    sc = new SparkContext(sparkConf)    ;    //对应RDD
		 */
		JavaRDD<LabeledPoint> trainingRDD = sc.parallelize(train_list);
		NaiveBayesModel nb_model = NaiveBayes.train(trainingRDD.rdd());

		/**
		 * 记得关闭资源
		 */
		sc.close();

		/**
		 * 返回贝叶斯分类器
		 */
		return nb_model;

	}

	/**
	 * 加载问题模板 == 分类器标签
	 * @return
	 */
	public  Map<Double, String> loadQuestionsPattern() {
		Map<Double, String> questionsPattern = new HashMap<Double, String>();
//		File file = new File(rootDirPath + "questiontxt/question_classification.txt");

		BufferedReader br = null;
		try {
			InputStreamReader ls=new InputStreamReader(new FileInputStream(rootDirPath + "questiontxt/question_classification.txt"),"UTF-8");
			br = new BufferedReader(ls);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(":");
				double index = Double.valueOf(tokens[0]);
				String pattern = tokens[1];
				questionsPattern.put(index, pattern);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return questionsPattern;
	}

	/**
	 * 贝叶斯分类器分类的结果，拿到匹配的分类标签号，并根据标签号返回问题的模板
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	public  String queryClassify(String sentence) throws Exception {


		System.out.println("sentence: " + sentence);
		double[] testArray = sentenceToArrays(sentence);
		/**
		 * sentence:abstr: 基本 公共服务 的 哪些 学者 研究 的 多
		 * testArray:0.0 1.0 0.0 0.0 0.0 1.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
		 */

		Vector v = Vectors.dense(testArray);

		/**
		 * 对数据进行预测predict
		 * 句子模板在 spark贝叶斯分类器中的索引【位置】
		 * 根据词汇使用的频率推断出句子对应哪一个模板
		 */
		//
		//[0.0,1.0,0.0,0.0,1.0,0.0,0.0,0.0,1.0,1.0,0.0,1.0,0.0,0.0,0.0,1.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0]
		double index = nbModel.predict(v);                        //nbModel  Spark贝叶斯分类器
		modelIndex = (int)index;                                  //xxxxxxxx   index值错误
		System.out.println("the model index is " + index);
		Vector vRes = nbModel.predictProbabilities(v);
		System.out.println("问题模板分类概率："+vRes.toArray()[0]+ "  "+vRes.toArray()[1]+"  "+vRes.toArray()[2]+"  "+vRes.toArray()[3]);
		System.out.println("index= "+questionsPattern.get(index));
		return questionsPattern.get(index);

	}




	public static void main(String[] agrs) throws Exception {
		System.out.println("Hello World !");
	}
}
