package co.com.bancolombia.dynamodb.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


/* Enhanced DynamoDB annotations are incompatible with Lombok #1932 https://github.com/aws/aws-sdk-java-v2/issues/1932*/

@DynamoDbBean
public class Stat {
    private String timestamp;
    private Integer totalContactoClientes;
    private Integer motivoReclamo;
    private Integer motivoGarantia;
    private Integer motivoDuda;
    private Integer motivoCompra;
    private Integer motivoFelicitaciones;
    private Integer motivoCambio;
    private String hash;

    

    public Stat() {
    }

    public Stat(String hash, Integer motivoCambio, Integer motivoCompra, Integer motivoDuda, Integer motivoFelicitaciones, Integer motivoGarantia, Integer motivoReclamo, String timestamp, Integer totalContactoClientes) {
        this.hash = hash;
        this.motivoCambio = motivoCambio;
        this.motivoCompra = motivoCompra;
        this.motivoDuda = motivoDuda;
        this.motivoFelicitaciones = motivoFelicitaciones;
        this.motivoGarantia = motivoGarantia;
        this.motivoReclamo = motivoReclamo;
        this.timestamp = timestamp;
        this.totalContactoClientes = totalContactoClientes;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @DynamoDbAttribute("totalContactoClientes")
    public Integer getTotalContactoClientes() {
        return totalContactoClientes;
    }

    @DynamoDbAttribute("motivoReclamo")
    public Integer getMotivoReclamo() {
        return motivoReclamo;
    }

    @DynamoDbAttribute("motivoGarantia")
    public Integer getMotivoGarantia() {
        return motivoGarantia;
    }

    @DynamoDbAttribute("motivoDuda")
    public Integer getMotivoDuda() {
        return motivoDuda;
    }

    @DynamoDbAttribute("motivoCompra")
    public Integer getMotivoCompra() {
        return motivoCompra;
    }

    @DynamoDbAttribute("motivoFelicitaciones")
    public Integer getMotivoFelicitaciones() {
        return motivoFelicitaciones;
    }

    @DynamoDbAttribute("motivoCambio")
    public Integer getMotivoCambio() {
        return motivoCambio;
    }

    @DynamoDbAttribute("hash")
    public String getHash() {
        return hash;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTotalContactoClientes(Integer totalContactoClientes) {
        this.totalContactoClientes = totalContactoClientes;
    }

    public void setMotivoReclamo(Integer motivoReclamo) {
        this.motivoReclamo = motivoReclamo;
    }

    public void setMotivoGarantia(Integer motivoGarantia) {
        this.motivoGarantia = motivoGarantia;
    }

    public void setMotivoDuda(Integer motivoDuda) {
        this.motivoDuda = motivoDuda;
    }

    public void setMotivoCompra(Integer motivoCompra) {
        this.motivoCompra = motivoCompra;
    }

    public void setMotivoFelicitaciones(Integer motivoFelicitaciones) {
        this.motivoFelicitaciones = motivoFelicitaciones;
    }

    public void setMotivoCambio(Integer motivoCambio) {
        this.motivoCambio = motivoCambio;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    
}
