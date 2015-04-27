/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moinhomultiplayer;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Ruhan Conceição
 */
public class Espaco {
    private boolean ocupado;
    private Player peca;
    private boolean selecionado;
    private final boolean espacoValido;
    private final javax.swing.JButton jButtonAssociado;
    
    public Espaco(boolean valido, javax.swing.JButton jButton) {
        ocupado = false;
        peca = Player.NONE;
        selecionado = false;
        this.espacoValido = valido;
        //this.jButtonAssociado.setBackground(Color.white);
        //this.jButtonAssociado.setForeground(Color.white);
        this.jButtonAssociado = jButton;
    }

    public void setPeca(Player peca) throws Exception {
        if (this.espacoValido == false){
            throw new Exception("Impossivel colocar pecas em espacos invalidos"); 
        }
        
        //System.out.println("Teste de insercao");
        
        if (this.ocupado == true){
            throw new Exception("Espaco ja ocupado"); 
        }else{
            this.peca = peca;
            this.ocupado = true;
            if (this.peca == Player.PLAYER_A){
                this.jButtonAssociado.setBackground(Color.red);
                this.jButtonAssociado.setForeground(Color.red);
            }else{
                this.jButtonAssociado.setBackground(Color.blue);
                this.jButtonAssociado.setForeground(Color.blue);
            }
        }
    }
    
    public void seleciona() throws Exception{
        if (this.espacoValido == false){
            throw new Exception("Impossivel selecionar espaco invalido"); 
        }
        
        if (this.ocupado == false){
            throw new Exception("Impossivel selecionar espaco em branco");
        }else{
            this.selecionado = true;
        }
        
        if(this.peca == Player.PLAYER_A){
            this.jButtonAssociado.setBackground(Color.pink);
            this.jButtonAssociado.setForeground(Color.pink);
        }else{
            this.jButtonAssociado.setBackground(Color.cyan);
            this.jButtonAssociado.setForeground(Color.cyan);
        }
    }
    
    public void desseleciona() throws Exception{
        if (this.espacoValido == false){
            throw new Exception("Impossivel selecionar espaco invalido"); 
        }
        
        if (this.ocupado == false){
            throw new Exception("Impossivel selecionar espaco em branco");
        }else{
            this.selecionado = true;
        }
        
        if(this.peca == Player.PLAYER_A){
            this.jButtonAssociado.setBackground(Color.red);
            this.jButtonAssociado.setForeground(Color.red);
        }else{
            this.jButtonAssociado.setBackground(Color.blue);
            this.jButtonAssociado.setForeground(Color.blue);
        }
    }
    
    public void removePeca() throws Exception {
        if (this.ocupado == false){
            throw new Exception("Impossivel selecionar espaco em branco");
        }else{
            //System.out.println("Teste de remocao!");
            this.ocupado = false;
            this.peca = Player.NONE;
            this.selecionado = false;
            this.jButtonAssociado.setBackground(Color.white);
            this.jButtonAssociado.setForeground(Color.white);
        }
    }
    
    public void deslocaPeca() throws Exception {
        if (this.ocupado == false || this.selecionado == false){
            throw new Exception("Erro no deslocamento");
        }else{
            this.ocupado = false;
            this.peca = Player.NONE;
            this.selecionado = false;
        }
    }

    public Player getPeca() {
        return peca;
    }

    public JButton getjButtonAssociado() {
        return jButtonAssociado;
    }
    
    
    
    
}
