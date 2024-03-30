package io.github.im2back.credit.appraiser.infra.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && !token.isEmpty()) {
            // Armazenar o token em algum lugar acessível pelo Feign RequestInterceptor
            TokenStorage.setToken(token);
            
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Limpar o token após o processamento da requisição
        TokenStorage.clearToken();
    }
}
