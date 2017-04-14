package com.miao.test.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 *
 * @ClassName: UrlInterceptor
 * @Description: (这里用一句话描述这个类的作用)
 * @author kunka kong0609@163.com
 * @date 2016年1月29日 下午3:48:49
 *
 */
public class UrlInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 权限拦截
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param handler
	 *            处理
	 * 调整加入ajax判断
	 * @return boolean true继续进入controller，false直接返回
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;

	}

	/**
	 * 判断ajax请求
	 * 
	 * @param request
	 * @return
	 */
	boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest"
				.equals(request.getHeader("X-Requested-With").toString()));
	}

}
