package cn.ghn.sm.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends GenericServlet {

    private ApplicationContext context;

    public void init() throws ServletException {
        super.init();
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        /*
        *   /staff/add.do     /login.do
        *   staffController
        *   public void add(HttpServletRequest request, HttpServletResponse response){}
        * */
        String path = request.getServletPath().substring(1);
        String beanName = null;
        String methodName = null;
        int index = path.indexOf('/');
        if (index != -1) {
            beanName = path.substring(0, index) + "Controller";
            methodName = path.substring(index + 1, path.indexOf(".do"));
        } else {
            beanName = "selfController";
            methodName = path.substring(0, path.indexOf(".do"));
        }
        Object obj = context.getBean(beanName);
        Method method = null;
        try {
            method = obj.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            method.invoke(obj, request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
