package com.ticket.base.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**  
 * 自定义jsonp的视图处理器
 *
 * @author wujunjun 
 * @date 2015-1-5 下午4:20:39
 * @since 1.0  
 * @Copyright 2014 东莞市邮政局All rights reserved.  
 */
public class MyJsonpView extends MappingJacksonJsonView {

	/**
     * Default content type. Overridable as bean property.
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

    @Override
    public String getContentType() {
        return DEFAULT_CONTENT_TYPE;
    }
    
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("1111111111");
    	if ("GET".equals(request.getMethod().toUpperCase())) {
            @SuppressWarnings("unchecked")
            Map<String, String[]> params = request.getParameterMap();

            if (params.containsKey("callback")) {
                response.getOutputStream().write(new String(params.get("callback")[0] + "(").getBytes());
                super.render(model, request, response);
                response.getOutputStream().write(new String(");").getBytes());
                response.setContentType(DEFAULT_CONTENT_TYPE);
            } else {
                super.render(model, request, response);
            }
        } else {
            super.render(model, request, response);
        }
    }
}
