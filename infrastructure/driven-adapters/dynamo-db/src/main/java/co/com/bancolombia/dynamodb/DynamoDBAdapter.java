package co.com.bancolombia.dynamodb;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import co.com.bancolombia.dynamodb.helper.AdapterOperations;
import co.com.bancolombia.model.stat.Stat;
import co.com.bancolombia.model.stat.gateways.StatRepository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;


@Repository
public class DynamoDBAdapter extends AdapterOperations<Stat, String, co.com.bancolombia.dynamodb.model.Stat>  implements StatRepository{

    public DynamoDBAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper) {
        super(connectionFactory, mapper, d -> mapper.map(d, Stat.class), "stats");
    }

    @Override
    public Mono<Stat> saveStat(Stat stat) {
        return super.save(stat);
    }
}
