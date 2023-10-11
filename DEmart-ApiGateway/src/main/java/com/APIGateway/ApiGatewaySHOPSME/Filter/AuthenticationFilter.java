package com.APIGateway.ApiGatewaySHOPSME.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.APIGateway.ApiGatewaySHOPSME.util.JwtUtils;
import com.google.common.net.HttpHeaders;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	
	
	public AuthenticationFilter() {
		super(Config.class);
	}


	public static class Config {
        // Add any configuration properties you might need for JWT validation
        // For example, secretKey, issuer, audience, etc.
    }


	@Autowired
	private RestTemplate template;
	
	@Autowired
	private RouteValidator validator;
	
	@Autowired
	private JwtUtils JwtUtil;


	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
				return ((exchange,chain)->{
					System.out.println(exchange.getRequest().getHeaders());
					System.out.println(exchange.getRequest()); 
					if(!validator.isSecured.test(exchange.getRequest())) {
						boolean flag =  false;
						System.out.println("HI");
						System.out.println(exchange.getRequest().getHeaders());
						if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
							throw new RuntimeException("missing authorization headers");
						}
						
						String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
						
						if(authHeaders!=null && authHeaders.startsWith("Bearer ")) {
							authHeaders = authHeaders.substring(7);
							
						}
						try {
							//Rest call to AUTH SERVICE
						
								if(JwtUtil.isTokenExpired(authHeaders)) {
									flag = true;
									throw new RuntimeException("Token expired please login again !!");
								}
						
							
							
							//template.getForObject("http://DEMART-SECURTIY-SERVICE//validate?token" + authHeaders,String.class);
							JwtUtil.validateToken(authHeaders);
							
						
							
						}catch(Exception e) {
							if(flag == true) {
								System.out.println("Token expired please login again !!");
								throw new RuntimeException("Token expired please login again !!");
							}
							System.out.println("Error during token validation in Authentication Filter !!");
							
							throw new RuntimeException("unauthorized acess");
						}
					}
					else {
						System.out.println(exchange.getRequest().getURI().getPath());
					}
					return chain.filter(exchange);
				});

	}
}
