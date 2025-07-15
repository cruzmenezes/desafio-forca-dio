package servicos;

import modelo.Jogador;
import modelo.StatusJogo;
import excecoes.JogadaInvalidaException;

import java.util.Scanner;

public class JogoService {
    private StatusJogo status = StatusJogo.EM_ANDAMENTO;

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        String nome = scanner.nextLine();

        Jogador jogador = new Jogador(nome);

        while (status == StatusJogo.EM_ANDAMENTO) {
            try {
                System.out.println("Digite uma jogada (ou 'sair' para encerrar): ");
                String entrada = scanner.nextLine();

                if (entrada.equalsIgnoreCase("sair")) {
                    status = StatusJogo.FINALIZADO;
                } else if (entrada.length() < 3) {
                    throw new JogadaInvalidaException("Jogada muito curta!");
                } else {
                    jogador.adicionarPonto();
                    System.out.println("Boa jogada! Pontuação: " + jogador.getPontuacao());
                }

            } catch (JogadaInvalidaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        System.out.println("Jogo encerrado. Pontuação final: " + jogador.getPontuacao());
        scanner.close();
    }
}
