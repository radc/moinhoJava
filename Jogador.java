/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moinhomultiplayer;

/**
 *
 * @author Ruhan Conceição
 */
public class Jogador {
    private final boolean isHumano;
    private final Player AB;
    private final String nome;
    private int numeroPecas;

    public Jogador(boolean isHumano, Player AB, String nome) {
        this.isHumano = isHumano;
        this.AB = AB;
        this.nome = nome;
        this.numeroPecas = 0;
    }

    public Jogador(boolean isHumano, Player AB) {
        this.isHumano = isHumano;
        this.AB = AB;
        if (isHumano){
            if(AB == Player.PLAYER_A){
                this.nome = "Jogador A";
            }else{
                this.nome = "Jogador B";
            }
        }else{
            this.nome = "Computador";
        }
    }

    public int getNumeroPecas() {
        return numeroPecas;
    }
    
    public void incNumeroPecas() throws Exception{
        if (this.numeroPecas == 9){
            throw new Exception("Impossivel jogador ter mais de 9 peças");
        }else{
            this.numeroPecas++;
        }
    }
    
    public void decNumeroPecas() throws Exception{
        if (this.numeroPecas == 0){
            throw new Exception("Impossivel jogador ter menos de 0 peças");
        }else{
            this.numeroPecas--;
        }
    }

    
    
}
