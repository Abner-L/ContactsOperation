package com.yezhizhen.contactoperation;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import android.util.Log;

public class FieldResources {
	/**
	 * 本类中 提供了联系人各个字段的资源 实现了 联系人姓名 手机号 的随机生成
	 */

	// 定义
	String[] name = new String[] { nameMaker() };
	String[] nickname = new String[] { nameMaker() };
	// 手机号的随机生成
	String[] phone = new String[] { phoneNumberMaker() };
	String[] email = new String[] { emailMaker() };
	String[] website = new String[] { websiteMaker() };
	String[] address = new String[] { addressMaker() };
	String[] orgInfo = new String[] { orgInfoMaker() };
	String[] event = new String[] { timelyInfoMaker() };
	String[] timelyInfo = new String[] { timelyInfoMaker() };

	public FieldResources() {

	}

	// 定义一个随机生成手机号的方法
	public String phoneNumberMaker() {

		String[] numberHead = new String[] { "134", "135", "136", "137", "138",
				"139", "147", "150", "151", "152", "157", "158", "159", "181",
				"182", "183", "187", "188", "130", "131", "132", "155", "156",
				"185", "186", "133", "153", "180", "189" };
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
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
		for (int i = 0; i < random.nextInt(2) + 2; i++) {

			sbName.append(randomWordMaker());
		}
		return sbName.toString();
	}

	// 定义地址随机组合的方法
	public String addressMaker() {
		Random random = new Random();
		StringBuilder sbAddress = new StringBuilder();

		for (int j = 0; j < random.nextInt(2) + 2; j++) {

			sbAddress.append(randomWordMaker());
		}
		sbAddress.append("路");
		for (int j = 0; j < random.nextInt(2) + 2; j++) {

			sbAddress.append(randomWordMaker());
		}
		sbAddress.append("街" + random.nextInt(500) + "号");
		return sbAddress.toString();
	}

	// 定义生成随机组织信息的方法
	public String orgInfoMaker() {

		Random random = new Random();
		StringBuilder sbOrgInfo = new StringBuilder();
		for (int i = 0; i < random.nextInt(3) + 4; i++) {

			sbOrgInfo.append(randomWordMaker());
		}
		sbOrgInfo.append("有限责任公司");
		return sbOrgInfo.toString();
	}

	// 定义一个生成email的方法
	public String emailMaker() {

		Random random = new Random();
		StringBuilder sbEmail = new StringBuilder();
		for (int i = 0; i < random.nextInt(3) + 5; i++) {

			sbEmail.append((char) (random.nextInt(26) + 97));
		}
		sbEmail.append("@");
		for (int i = 0; i < random.nextInt(3) + 3; i++) {

			sbEmail.append((char) (random.nextInt(26) + 97));
		}
		sbEmail.append(".com");
		return sbEmail.toString();
	}

	// 定义一个生成website的方法
	public String websiteMaker() {

		Random random = new Random();
		StringBuilder sbWebsite = new StringBuilder();
		sbWebsite.append("http://www.");
		for (int i = 0; i < random.nextInt(5) + 3; i++) {

			sbWebsite.append((char) (random.nextInt(26) + 97));
		}
		sbWebsite.append(".com");
		return sbWebsite.toString();
	}

	// 定义一个生成即时信息的方法
	public String timelyInfoMaker() {
		Random random = new Random();
		StringBuilder sbTimelyInfo = new StringBuilder();
		for (int i = 0; i < random.nextInt(4) + 5; i++) {

			sbTimelyInfo.append(randomWordMaker());
		}
		return sbTimelyInfo.toString();
	}

	// 定义生成随机汉字的方法
	public String randomWordMaker() {
		// 使用gb2312编码集
		String[] indexBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f" };
		Random random = new Random();
		// 定义第一个位段的值，为了不出现一些不常用的字，根据gb2312的编码表将一些不常用字所对应的编码剔除
		int r1 = random.nextInt(3) + 11;// 生成11-13随机数，将编码表E开头的汉字剔除
		int r2;
		if (r1 == 13) {
			r2 = random.nextInt(8);// 生成1-7随机数，将编码表d开头，第二位大于7的汉字剔除
		} else {
			r2 = random.nextInt(16);
		}
		String areaOne = indexBase[r1] + indexBase[r2];
		// 定义第二个位段的值
		int r3 = random.nextInt(6) + 10;
		int r4;
		if (r3 == 10) {
			r4 = random.nextInt(15) + 1; // 生成1到16之间的随机数，编码表有一位是空值，将其剔除
		} else if (r3 == 15) {
			r4 = random.nextInt(15); // 生成0到15之间的随机数，同上，将空值编码剔除
		} else {
			r4 = random.nextInt(16); // 生成0到16之间的随机数
		}
		String areaTwo = indexBase[r3] + indexBase[r4];

		// 将位段值转byte存储在byte［］中，用于转gb2312 编码方式string类型时使用
		Byte areaOneByte = (byte) Integer.parseInt(areaOne, 16);
		Byte areaTwoByte = (byte) Integer.parseInt(areaTwo, 16);
		byte[] area = { areaOneByte, areaTwoByte };
		// 转成汉字
		String word = "";
		try {
			word = new String(area, "gb2312");
			word = new String(word.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("tag", "汉字生成失败");
			e.printStackTrace();
		}
		return word;
	}
}
