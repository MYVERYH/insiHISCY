package com.web.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 把request对象中的请求参数封装到bean中
 * 
 * @author Xiao_Zhu
 * 
 */
public class WebDataUtils {

	/**
	 * 将request对象转换成T对象
	 * 
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
		/*
		 * JDK中，普通的Class.newInstance()方法的定义返回Object，要将该返回类型强制转换为另一种类型;
		 * 但是使用泛型的Class<T>，Class.newInstance()方法具有一个特定的返回类型;
		 * java反射就是从Class<T>类开始的,Class<T>是没有公共的构造方法,虽然没有构造方法,但是有相应的方法可以获取类的变量和类型
		 * “
		 * ?”是一个匹配字符，匹配任意类型；“T”匹配的是某一具体的类型，如String。如果知道Class的具体类型，可以直接使用Class<T>
		 * , 如Class<String>
		 */
		try {
			T bean = clazz.newInstance();
			// 使用枚举获取 参数-->key-value 键值对
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = request.getParameter(key);
				if (value.split(" ").length == 2) {
					if (value.split("-").length == 3
							&& value.split(":").length == 3) {
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date = new Date(dateFormat.parse(value).getTime());
						BeanUtils.setProperty(bean, key, date);
					} else {
						BeanUtils.setProperty(bean, key, value);
					}
				} else if (value.split("-").length == 3
						|| value.split(":").length == 3) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date date = new Date(dateFormat.parse(value).getTime());
					BeanUtils.setProperty(bean, key, date);
				} else {
					BeanUtils.setProperty(bean, key, value);
				}
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> request3Bean(HttpServletRequest request,
			Class<T> clazz) {
		// 创建对象集合
		List<T> beanList = new ArrayList<T>();
		try {
			// 使用枚举获取 参数（键）-->key
			// 返回所有参数名的String对象列表，如果没有输入参数，该方法返回一个空值
			Enumeration<String> e = request.getParameterNames();
			Field[] fieldArr = clazz.getDeclaredFields();// 拿到对象的所有成员字段
			T bean = clazz.newInstance();// 创建对象
			int cloumn = 0;// 定义次数，用来标识已赋值了多少次
			while (e.hasMoreElements()) {// 测试此枚举是否包含更多的元素检测
				String key = (String) e.nextElement();// 拿到键
				for (int j = 0; j < fieldArr.length; j++) {// 循环对象的所有成员字段
					if (key.indexOf(fieldArr[j].getName()) > 0) {// 在键的名称中检索成员字段的名称是否存在
						String value = request.getParameter(key);// 使用键去获取值
						BeanUtils.setProperty(bean, fieldArr[j].getName(),
								value);// 调用jar包中的方法给对象的成员字段赋值
						cloumn++;// 赋值成功次数自增
						break;// 已找到字段并赋值了就跳出循环，没有必要继续循环了
					}
				}
				if (cloumn == fieldArr.length) {// 判断已赋值的次数是否和成员字段数量相等
					beanList.add(bean);// 数量相等就把对象添加到对象集合中
					bean = clazz.newInstance();// 重新定义空对象
					cloumn = 0;// 重新设置次数
				}
			}
			return beanList;// 最后返回对象集合
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成UUID
	 * 
	 * @return
	 */
	public static String makeId() {
		return UUID.randomUUID().toString();
	}
}