package com.dengkj.mall.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author dengkj
 * @time 2022-07-20 16:38:59
 * @description
 */
@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        final List<String> apiEndpoints = Arrays.asList("/refresh", "/login");
        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {

            if(!request.getHeaders().containsKey("Authorization")){
                return this.onError(exchange, HttpStatus.UNAUTHORIZED, "Authorization header is missing in request");
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                return this.onError(exchange, HttpStatus.BAD_REQUEST,"Authorization header is invalid");
            }

            UserVO userVO = jwtUtil.getUserInfo(token);
            if(userVO.getAuth().intValue() == 0){
                return this.onError(exchange, HttpStatus.FORBIDDEN, "Forbidden");
            }

            exchange.getRequest().mutate().header("userId", userVO.getUserId().toString()).build();
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        DataBuffer dataBuffer = response.bufferFactory().wrap(msg.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(dataBuffer));
        /*return response.setComplete();*/
    }

}
