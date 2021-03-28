package binlist.demo.api.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import binlist.demo.api.security.constants.SecurityEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;



public class MyAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String authorizationHeader = httpRequest.getHeader("Authorization");
		if(null == authorizationHeader) {
			httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Autorization header missing");
			return;
		}
		String[] tokenInfo = authorizationHeader.split("\\s+");
		if(tokenInfo.length != 2) {
			httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token format : Bearer [token]");
			return;
		}
		String bearer = tokenInfo[0];
		if(!bearer.equals("Bearer")) {
			httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token format : Bearer [token]");
			return;
		}
		String token = tokenInfo[1];
		Claims tokenClaims = getClaims(token, httpResponse);
		if(tokenClaims == null) return;
		chain.doFilter(httpRequest, httpResponse);
	}
	
	
	private Claims getClaims(String token, HttpServletResponse httpResponse) throws IOException{
		Claims tokenClaims = null;
		try {
			tokenClaims = Jwts.parser()
							  .setSigningKey(SecurityEnum.API_SECRET_KEY.getEnumValue())
							  .parseClaimsJws(token)
							  .getBody();
		} catch (Exception e) {
			// TODO: handle exception
			httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
		}
		return tokenClaims;
	}

}
