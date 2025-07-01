package co.com.bancolombia.usecase.savestat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        stat.setTimestamp(now.format(formatter));
                        return statRepository.saveStat(stat);
                    } else {
                        return Mono.error(new RuntimeException("Received hash is not valid"));
                    }
                });
    }
}
