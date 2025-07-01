package co.com.bancolombia.usecase.savestat;

import co.com.bancolombia.model.stat.Stat;
import co.com.bancolombia.model.stat.gateways.StatRepository;
import co.com.bancolombia.usecase.savestat.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
public class SaveStatUseCase {
    
    private final StatRepository statRepository;

    public Mono<Stat> saveStat(Stat stat) {
        return HashUtils.isStatHashValid(stat)
            .flatMap(valid -> {
                if (valid) {
                    return statRepository.saveStat(stat);
                }
                else {
                    return Mono.error(new RuntimeException("Received hash is not valid"));
                }
            });
    }
}
