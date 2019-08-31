package com.wunaidage.sbamwe.webfluxbackend.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class ForwardToFrontendFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        if (serverWebExchange.getRequest().getURI().getPath().equals("/")) {
            return webFilterChain.filter(serverWebExchange.mutate().request(serverWebExchange.getRequest().mutate().path("/index.html").build()).build());
        }

        return webFilterChain.filter(serverWebExchange);
    }
}
