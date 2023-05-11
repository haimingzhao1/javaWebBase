package com.bm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * javaweb三大组件-监听器
 * 八大监听器：
 * 1. 与ServletContext相关的Listener接口
 *
 * ServletContextListener接口 监听servletContext初始化和销毁
 * ServletContextAttributeListener接口 监听servletContext中属性的创建，修改，销毁
 *
 * 2. 与会话相关的Listener接口
 *
 * HttpSessionListener接口 监听httpSession的创建与销毁
 * HttpSessionAttributeListener接口 监听httpSession属性的创建修改与删除。
 * HttpSessionBindingListener接口 监听某个对象在httpSession中的创建与删除
 * HttpSessionActivationListener接口 监听某个对象在httpSession中的序列化与反序列化
 *
 * 3. 与请求相关的Listener接口
 *
 * ServletRequestListener接口 监听request的创建与销毁
 * ServletRequestAttributeListener接口 监听request中属性的创建，修改与删除
 *
 *
 * 使用方法：
 * 1.实现以上接口
 * 2.web.xml中配置该监听器
 *   如：<listener><listener-class>com.bm.listener.ContextListenerTest</listener-class></listener>
 * @author zhaohaiming
 * @date 2023/5/11 5:37 下午
 */
public class ContextListenerTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ContextListenerTest.contextInitialized~~~~~~~~~~~~~");
        System.out.println(servletContextEvent.getClass().getCanonicalName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ContextListenerTest.contextDestroyed~~~~~~~~~~~~~");
        System.out.println(servletContextEvent.getClass().getCanonicalName());
    }
}
