package co.com.bancolombia.usecase.savestat.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import co.com.bancolombia.model.stat.Stat;
import reactor.core.publisher.Mono;

public class HashUtils {
    public static Mono<Boolean> isStatHashValid(Stat stat) {
        return Mono.fromCallable(() -> {
            String stringFromData = String.format("%d,%d,%d,%d,%d,%d,%d",
                    stat.getTotalContactoClientes(),
                    stat.getMotivoReclamo(),
                    stat.getMotivoGarantia(),
                    stat.getMotivoDuda(),
                    stat.getMotivoCompra(),
                    stat.getMotivoFelicitaciones(),
                    stat.getMotivoCambio());

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(stringFromData.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            
            return hexString.toString().equals(stat.getHash());
        }).onErrorReturn(false);
    }
}
