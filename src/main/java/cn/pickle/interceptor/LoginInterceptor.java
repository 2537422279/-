package cn.pickle.interceptor;

import cn.pickle.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 18:19
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL
        final String uri = request.getRequestURI();
        if(uri.contains("/login")){
            return true;
        }
        //获取Session
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("login_user");
        //判断Session中是否有用户数据
        if(user!=null){
            return true;
        }
        //不符合条件，给出提示信息，并转发到登录页面
        request.setAttribute("msg","您还没有登录，请先登录");
        request.getRequestDispatcher("skip.jsp").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
