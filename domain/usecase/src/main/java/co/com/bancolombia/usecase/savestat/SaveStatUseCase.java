package co.com.bancolombia.usecase.savestat;

import co.com.bancolombia.model.stat.Stat;
import co.com.bancolombia.model.stat.gateways.StatRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
public class SaveStatUseCase {
    private final StatRepository statRepository;

    public Mono<Stat> saveStat(Stat stat) {
        return statRepository.saveStat(stat);
    }
}
