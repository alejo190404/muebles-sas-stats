package co.com.bancolombia.dynamodb.helper;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

import org.reactivecommons.utils.ObjectMapper;

import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public abstract class AdapterOperations<E, V> {
    private final Class<V> dataClass;
    protected ObjectMapper mapper;
    private final DynamoDbAsyncTable<V> table;

    @SuppressWarnings("unchecked")
    protected AdapterOperations(DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient,
                                        ObjectMapper mapper,
                                        Function<V, E> toEntityFn,
                                        String tableName,
                                        String... index) {
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<V>) genericSuperclass.getActualTypeArguments()[1];
        table = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(dataClass));
    }

    public Mono<E> save(E model) {
        return Mono.fromFuture(table.putItem(toEntity(model))).thenReturn(model);
    }

    protected V toEntity(E model) {
        return mapper.map(model, dataClass);
    }
}