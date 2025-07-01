package co.com.bancolombia.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.bancolombia.model.stat.Stat;
import co.com.bancolombia.usecase.savestat.SaveStatUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class Handler {
    private final SaveStatUseCase saveStatUseCase;

    public Mono<ServerResponse> processStat(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Stat.class)
                .flatMap(saveStatUseCase::saveStat)
                .flatMap(savedStat -> ServerResponse
                        .ok()
                        .bodyValue("Successfully saved stat with timestamp: " + savedStat.getTimestamp()))
                .onErrorResume(error -> {
                    return ServerResponse
                            .badRequest()
                            .bodyValue("Error processing the request: " + error.getMessage());
                });
    }
}
