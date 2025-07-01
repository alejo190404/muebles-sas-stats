package co.com.bancolombia.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class Handler {

    public Mono<ServerResponse> processStat(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("Works");
    }
}
