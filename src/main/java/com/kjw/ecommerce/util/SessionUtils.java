package com.kjw.ecommerce.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kjw.ecommerce.dto.session.SessionDto;
import com.kjw.ecommerce.jpa.entity.user.User;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionUtils {

	public void createUserSession(User user) {
		ServletRequestAttributes sessionAttr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session = sessionAttr.getRequest().getSession(true);

		SessionDto sessionDto = new SessionDto();
		sessionDto.setUserId(user.getId());

		session.setAttribute("userSession", sessionDto);
	}

	public void removeUserSession() {
		ServletRequestAttributes sessionAttr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session = sessionAttr.getRequest().getSession(false);
		session.invalidate();
	}

}
