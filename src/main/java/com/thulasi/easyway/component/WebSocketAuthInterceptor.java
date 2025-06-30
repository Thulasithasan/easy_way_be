package com.thulasi.easyway.component;

import com.thulasi.easyway.constant.AuthConstants;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.User;
import com.thulasi.easyway.repository.UserRepository;
import com.thulasi.easyway.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

	private final JwtService jwtService;

	private final UserRepository userRepository;

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, @NonNull ServerHttpResponse response,
			@NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
		URI uri = request.getURI();
		String query = uri.getQuery();
		String token = getQueryParam(query);

		if (token != null && !jwtService.isTokenExpired(token) && !jwtService.isRefreshToken(token)) {
			String userEmail = jwtService.extractUserEmail(token);

			Optional<User> optionalUser = userRepository.findByEmail(userEmail);
			if (optionalUser.isEmpty()) {
				throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND);
			}

			attributes.put(AuthConstants.USER_ID, optionalUser.get().getId().toString());
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
			@NonNull WebSocketHandler wsHandler, Exception exception) {
		log.info("After Handshake {}", request.getURI());
	}

	private String getQueryParam(String query) {
		if (query != null && !query.isEmpty()) {
			String[] params = query.split("&");
			for (String p : params) {
				String[] pair = p.split("=");
				if (pair.length == 2 && pair[0].equals(AuthConstants.TOKEN)) {
					return pair[1];
				}
			}
		}
		return null;
	}

}
