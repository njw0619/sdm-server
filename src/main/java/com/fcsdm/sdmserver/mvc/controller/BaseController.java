package com.fcsdm.sdmserver.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fcsdm.sdmserver.mvc.model.dto.FlashMessage;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

public class BaseController {	
	
	protected HttpServletRequest getServletRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra == null || sra.getRequest() == null) {
            return null;
        }
        return sra.getRequest();
    }
	
	public void addFlashMessage(FlashMessage flash){
        HttpSession session = getServletRequest().getSession();

        List<FlashMessage> flashMessages = (List<FlashMessage>)session.getAttribute("flashMessages");
        if(flashMessages == null)
            flashMessages = new ArrayList<FlashMessage>();

        flashMessages.add(flash);

        session.setAttribute("flashMessages", flashMessages);
    }
}
