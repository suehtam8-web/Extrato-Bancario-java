import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String caminho = "operacoes.csv";

        List<Transacao> lista = new ArrayList<>();

        // 📌 1. Ler arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;
            br.readLine(); // pula cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");

                Transacao t = new Transacao(
                        partes[0],
                        partes[1],
                        partes[2],
                        partes[3],
                        partes[4],
                        LocalDateTime.parse(partes[5])
                );

                lista.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 📌 2. Remover duplicados (HashSet)
        Set<Transacao> semDuplicados = new HashSet<>(lista);

        // 📌 3. Converter novamente para lista
        List<Transacao> listaFinal = new ArrayList<>(semDuplicados);

        // 📌 4. Ordenar por data
        listaFinal.sort(Comparator.comparing(t -> t.dataHora));

        // 📌 5. Agrupar por titular (Map)
        Map<String, Double> saldos = new HashMap<>();

        for (Transacao t : listaFinal) {
            saldos.putIfAbsent(t.titular, 0.0);

            double valor = 100; // valor fixo só para exemplo

            if (t.operacao.equalsIgnoreCase("DEPOSITO")) {
                saldos.put(t.titular, saldos.get(t.titular) + valor);
            } else {
                saldos.put(t.titular, saldos.get(t.titular) - valor);
            }
        }

        // 📌 6. Mostrar resultados
        System.out.println("=== TRANSAÇÕES ORDENADAS ===");
        for (Transacao t : listaFinal) {
            System.out.println(t);
        }

        System.out.println("\n=== SALDOS ===");
        for (String titular : saldos.keySet()) {
            System.out.println(titular + ": " + saldos.get(titular));
        }
    }
}