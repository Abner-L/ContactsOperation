package com.yezhizhen.contactoperation;

import java.util.Random;

import android.R.string;

public class FieldResources {
	

	// 定义
	String[] name = new String[] {nameMaker()};
	String[] nickname = new String[] { "nick Liu", "nick Stallone",
			"nick Zhang", "nick Wang", "nick Dong", "nick Sui", "nick Lenka",
			"nick Li", "nick Jason", "nick Air" };
	// 手机号的随机生成
	String[] phone = new String[] { phoneNumberMaker() };
	String[] email = new String[] { "Liu dehua@nimei.com",
			"Stallone@nimei.com", "Zhang guorong@nimei.com",
			"Wang lihong@nimei.com", "Dong chao@nimei.com", "Sui li@nimei.com",
			"Lenka@nimei.com", "Li lianjie@nimei.com",
			"Jason Statham@nimei.com", "Air Jordan@nimei.com" };
	String[] website = new String[] { "www.Liu dehua.com", "www.Stallone.com",
			"www.Zhang guorong.com", "www.Wang lihong.com",
			"www.Dong chao.com", "www.Sui li.com", "www.Lenka.com",
			"www.Li lianjie.com", "www.Jason Statham.com", "www.Air Jordan.com" };
	String[] address = new String[] { "天堂路地狱街0号", "天堂路地狱街1号", "天堂路地狱街2号",
			"天堂路地狱街3号", "天堂路地狱街4号", "天堂路地狱街5号", "天堂路地狱街6号", "天堂路地狱街7号",
			"天堂路地狱街8号", "天堂路地狱街9号" };
	String[] org = new String[] { "华瑞网研", "华瑞网研", "华瑞网研", "华瑞网研", "华瑞网研",
			"华瑞网研", "华瑞网研", "华瑞网研", "华瑞网研", "华瑞网研" };
	String[] event = new String[] { "唱歌", "吃饭", "喝水", "写代码", "聊天", "打架", "单挑",
			"回家", "跑步", "想不出来啦" };
	String[] im = new String[] { "吃饭啦", "发工资啦", "睡觉啦", "擦！上班了", "尼玛！报错",
			"下班啦！", "加班！！", "王哥请吃饭", "涨工资啦", "我是一个好孩子" };

	public FieldResources() {

	}

	// 定义一个随机生成手机号的方法
	public String phoneNumberMaker() {

		String[] numberHead = new String[] { "134", "135", "136", "137", "138",
				"139", "147", "150", "151", "152", "157", "158", "159", "181",
				"182", "183", "187", "188", "130", "131", "132", "155", "156",
				"185", "186", "133", "153", "180", "189" };
		StringBuilder sb = new StringBuilder();
		Random	random = new Random();
		sb.append(numberHead[random.nextInt(30)]);
		for (int i = 0; i < 8; i++) {
			sb.append(random.nextInt(10));
		}

		return sb.toString();
	}

	// 定义姓名随机组合的方法
	public String nameMaker() {
		Random random = new Random();
		StringBuilder sbName = new StringBuilder();
		for (int i = 0; i < random.nextInt(2)+2; i++) {
			char c = (char) (0x4e00 + random.nextInt(20901));
			sbName.append(String.valueOf(c));
		}

		return sbName.toString();
	}
}
