package cn.cyp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
/**
 * 解决乱码问题
 * @author Administrator
 *
 */
public class MyFilter implements Filter {
	
	private String type;
	private String encoding;
	
	@Override
	public void destroy() {
		// 容器正常关闭 该方法会被执行..
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 过滤器业务方法编写 默认 拦截后续资源的访问的
		// 重写getParameter 方法!
		// 思想: 自己编写 HttpServletRequest接口实现类 覆盖getParameter 方法 自己定义解码 utf-8
		MyRequest myrequest = new MyRequest(request);// 构造方法注入 对象
		response.setContentType(type+";charset="+encoding);// perperties xml
		// class MYRequest implements HttpServletRequest /////{ getParamter getParameterValue getParam..Map}
		// 自己定义request 具备 getParameter系统方法 重写 保留 request其他api 的使用
		chain.doFilter(myrequest, response);// request response 传递下去 ... 将request response 对象传递后续 servlet /jsp
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		type = config.getInitParameter("type");
		encoding = config.getInitParameter("encoding");
	}

}

// 找接口 中间类 Wrapper 包装类 加强 paramter 默认iso-8859-1 继承 HttpServletRequestWrapper 加强 getParameterXX sun 公司 设计类
class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;// 获取 为加强 request 对象
	private boolean flag = false;

	public MyRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		// 首先获取请求方式 ... key 表单 input name 对应属性值 value 表单用户提交信息数据 String[]
		Map<String, String[]> map = getParameterMap();
		if (map != null && map.size() != 0) {
			String[] values = map.get(name);
			if (values != null && values.length != 0) {
				return values[0];
			}
		}
		return super.getParameter(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = getParameterMap();
		if (map != null && map.size() != 0) {
			String[] values = map.get(name);
			if (values != null && values.length != 0) {
				return values;
			}
		}
		return super.getParameterValues(name);
	}

	@Override
	public Map getParameterMap() {
		// get/post
		String method = request.getMethod();
		if ("post".equalsIgnoreCase(method)) {
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// 后续代码调用 getParameter getParameterValues 重复调用 getParameterMap
		// 引入标记 开关 boolean 1--100 素数 双色球 6 1 1-33 6个红球 01 02 ...33 08
		if ("get".equalsIgnoreCase(method)) {
			if (!flag) {
				// 必须要获取 map 遍历map 将 String[] 值获取 每一个数组元素都要 先编码后解码
				Map<String, String[]> map = request.getParameterMap();// 原始request 获取数据 iso-88590-1解码
				if (map != null && map.size() != 0) {
					for (String key : map.keySet()) {
						// 每一次循环 获取 map key
						String[] values = map.get(key);
						if (values != null && values.length != 0) {
							// 先编码 后解码 forIn 简化查询
							for (int i = 0; i < values.length; i++) {
								try {
									values[i] = new String(values[i].getBytes("iso-8859-1"), "utf-8");
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				flag = true;
				return map;
			}
		}
		return super.getParameterMap();// 其他的请求
	}

}
