/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moinhomultiplayer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruhan Conceição
 */
public abstract class Game {
    private static Jogador playerA;
    private static Jogador playerB;
    private static Tabuleiro tabuleiro;
    private static Turno turno;
    private static Player quemJoga;
    private static int pecasInseridas;
    private static int[] espacoSelec;
    private static boolean jaSelecionado;
    
    public static void initGame(){
        playerA = null;
        playerB = null;
        pecasInseridas = 0;
        tabuleiro = new Tabuleiro();
        espacoSelec = new int[3];
        espacoSelec[0] = -1;
        espacoSelec[1] = -1;
        espacoSelec[2] = -1;
        jaSelecionado = false;
    }
    
    public static void resetGame(){
        playerA = null;
        playerB = null;
        pecasInseridas = 0;
        tabuleiro.apagaTabuleiro();
        espacoSelec[0] = -1;
        espacoSelec[1] = -1;
        espacoSelec[2] = -1;
        jaSelecionado = false;
    }
    
    public static void setJogadores (TipoJogadores tipo){
        if (tipo == TipoJogadores.HUMANvsHUMAN){
            playerA = new Jogador(true,Player.PLAYER_A);
            playerB = new Jogador(true,Player.PLAYER_B);
            FrameManager.bloqueiaSelecaoModo();
            
            turno = Turno.INSERCAO_PECAS;
            quemJoga = Player.PLAYER_A;
            
            FrameManager.escreveMensagem("Player_A Começa");
            
            
        }else{
            System.out.println("Tipo de Jogo ainda não suportado");
        }
    }
    
    
    
    public static void botaoPressionado(int i, int j, int k){
        
        tabuleiro.getEspaco(i, j, k).getjButtonAssociado().setEnabled(false);
        
        if (turno == Turno.REMOCAO_PECAS){
            removePecaAdversaria (i,j,k);
        }
        else if (turno == Turno.INSERCAO_PECAS){
            inserePeca (i, j, k);
        }
        else if (turno == Turno.DESLOCAMENTO_PECAS){
            if (!jaSelecionado){
                selecionaPeca(i,j,k);
            }else{
                deslocaPeca(i,j,k);
            }
        }
        
        tabuleiro.getEspaco(i, j, k).getjButtonAssociado().setEnabled(true);
    }
    
    private static void passaVez(){
        String jogada;
        if (pecasInseridas >= 18){
            turno = Turno.DESLOCAMENTO_PECAS;
            jogada = "desloca";
        }else{
            turno = Turno.INSERCAO_PECAS;
            jogada = "insere";
        }
        
        if (quemJoga == Player.PLAYER_A){
            quemJoga = Player.PLAYER_B;
            FrameManager.escreveMensagem("Player B "+jogada);
        }else{
            quemJoga = Player.PLAYER_A;
            FrameManager.escreveMensagem("Player A "+jogada);
        }
    }
    
    private static void remocao(){
        turno = Turno.REMOCAO_PECAS;
        FrameManager.escreveMensagem("Remova uma peca!");
    }
    
    private static void removePecaAdversaria (int i, int j, int k){
        Espaco escolhido;
        escolhido = tabuleiro.getEspaco(i, j, k);
        if (escolhido.getPeca() == quemJoga){
            FrameManager.escreveMensagem("Impossivel remover sua propria peca!");
            return;
        }
        if (escolhido.getPeca() == Player.NONE){
            FrameManager.escreveMensagem("Selecione um espaco nao vazio!");
            return;
        }
        
        try {
            
            escolhido.removePeca();
            if (quemJoga == Player.PLAYER_A){
                playerB.decNumeroPecas();
            }else{
                playerA.decNumeroPecas();
            }
            passaVez();
            
        } catch (Exception ex) {
            System.out.println("Erro encontrado!");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void inserePeca (int i, int j, int k){
        Espaco escolhido;
        escolhido = tabuleiro.getEspaco(i, j, k);
        
        if (escolhido.getPeca() != Player.NONE){
            FrameManager.escreveMensagem("Insira em uma posicao vazia!");
            return;
        }
        
        try {
            
            escolhido.setPeca(quemJoga);
            if (quemJoga == Player.PLAYER_A){
                playerA.incNumeroPecas();
            }else{
                playerB.incNumeroPecas();
            }
            pecasInseridas++;
            if (verificaMoinho(i,j,k)){
                remocao();
                
            }else{
                passaVez();
            }
            
        } catch (Exception ex) {
            System.out.println("Erro na insercao");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static boolean verificaMoinho (int i, int j, int k){
        if (j == 1 && k == 1){
            System.out.println("ERRO DE ACESSO (X-1-1)");
            return false;
        }
        
        if (j == 1 || k == 1){
            int counter = 0;
            while (counter < 3){
                if (tabuleiro.getEspaco(counter, j, k).getPeca() != quemJoga){
                    break;
                }
                counter++;
            }
            if (counter == 3) return true;
        }
        
        if (k != 1){
            int counter = 0;
            while (counter < 3){
                if (tabuleiro.getEspaco(i, counter, k).getPeca() != quemJoga){
                    break;
                }
                counter++;
            }
            if (counter == 3) return true;
        }
        
        if (j != 1){
            int counter = 0;
            while (counter < 3){
                if (tabuleiro.getEspaco(i, j, counter).getPeca() != quemJoga){
                    break;
                }
                counter++;
            }
            if (counter == 3) return true;
        }
        return false;
    }
    
    private static void selecionaPeca(int i, int j, int k){
        Espaco escolhido;
        escolhido = tabuleiro.getEspaco(i, j, k);
        
        if (escolhido.getPeca() != quemJoga){
            FrameManager.escreveMensagem("Selecione suas pecas!");
            return;
        }
        
        if (escolhido.getPeca() == Player.NONE){
            FrameManager.escreveMensagem("Selecione uma de suas pecas!");
            return;
        }
        
        try {
            escolhido.seleciona();
            Game.jaSelecionado = true;
            
            Game.espacoSelec[0] = i;
            Game.espacoSelec[1] = j;
            Game.espacoSelec[2] = k;
            
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void deslocaPeca(int i, int j, int k){
        Espaco selecAgora;
        Espaco selecAntes;
        selecAgora = tabuleiro.getEspaco(i, j, k);
        selecAntes = tabuleiro.getEspaco(espacoSelec[0],espacoSelec[1], espacoSelec[2]);
        if (i == espacoSelec[0] && j == espacoSelec[1] && k == espacoSelec[2]){
            try {
                selecAgora.desseleciona();
                jaSelecionado = false;
                return;
            } catch (Exception ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (selecAgora.getPeca() != Player.NONE){
            FrameManager.escreveMensagem("Selecione uma posicao valida!");
            return;
        }
        
        if (i != espacoSelec[0] && j == espacoSelec[1] && k == espacoSelec[2]){
            if(j == 1 || k == 1){
                if (i - espacoSelec[0] == 1 || i - espacoSelec[0] == -1){
                    try {
                        selecAgora.setPeca(quemJoga);
                        selecAntes.removePeca();
                        jaSelecionado = false;
                        if(verificaMoinho(i,j,k)){
                            remocao();
                        }else{
                            passaVez();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
        
        if (i == espacoSelec[0] && j != espacoSelec[1] && k == espacoSelec[2]){
            if (j - espacoSelec[1] == 1 || j - espacoSelec[1] == -1){
                try {
                    selecAgora.setPeca(quemJoga);
                    selecAntes.removePeca();
                    jaSelecionado = false;
                    if(verificaMoinho(i,j,k)){
                        remocao();
                    }else{
                        passaVez();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (i == espacoSelec[0] && j == espacoSelec[1] && k != espacoSelec[2]){
            if (k - espacoSelec[2] == 1 || k - espacoSelec[2] == -1){
                try {
                    selecAgora.setPeca(quemJoga);
                    selecAntes.removePeca();
                    jaSelecionado = false;
                    if(verificaMoinho(i,j,k)){
                        remocao();
                    }else{
                        passaVez();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
    }
}
