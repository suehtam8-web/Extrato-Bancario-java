import java.time.LocalDateTime;
import java.util.Objects;

public class Transacao {
    String agencia;
    String conta;
    String banco;
    String titular;
    String operacao;
    LocalDateTime dataHora;

    public Transacao(String agencia, String conta, String banco,
                     String titular, String operacao, LocalDateTime dataHora) {
        this.agencia = agencia;
        this.conta = conta;
        this.banco = banco;
        this.titular = titular;
        this.operacao = operacao;
        this.dataHora = dataHora;
    }

    // 🔥 ESSENCIAL: define duplicação
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao t = (Transacao) o;

        return operacao.equals(t.operacao)
                && dataHora.equals(t.dataHora)
                && titular.equals(t.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operacao, dataHora, titular);
    }

    @Override
    public String toString() {
        return titular + " - " + operacao + " - " + dataHora;
    }
}