package com.team4.util;
/**
 * 
 * @author penny Deng
 * Date: 2018-3-21
 *
 */
public class ResponseData {

	/**
	 * 数据封装类，封装的是angularjs前端请求的数据，angularJS请求的返回数据必须要是一个对象或者是一个json格式
	 * 所以将请求到的数据封装在这个类当中，返回的response就是一个对象了，而不是一个页面modelAndView
	 */
	public Object data;

	public ResponseData(Object data) {
		super();
		this.data = data;
	}
	
}
