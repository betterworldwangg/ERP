package org.erp.util.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.resource.service.service.ResourceService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AllResourceListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//获得applicationContext对象
		ServletContext servletContext = event.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		//获得所有资源
		ResourceService rs = (ResourceService) wac.getBean("resourceServ");
		List<ResourceModel> rsms = rs.findAll();
		//将资源放到所有用户都可以访问的地方
		StringBuilder sb = new StringBuilder();
		
		for (ResourceModel resourceModel : rsms) {
			sb.append(resourceModel.getUrl());
			sb.append("-");
		}
		servletContext.setAttribute("allResource", sb.toString());
	}

}
