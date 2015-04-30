/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moinhomultiplayer;

import java.awt.Color;

/**
 *
 * @author Ruhan Conceição
 */
public abstract class FrameManager {
    public static FrameMoinho mainFrame;
    
    public static void startFrame(){
        FrameManager.mainFrame = new FrameMoinho();
        FrameManager.mainFrame.setVisible(true);
        
    }
    
    public static void bloqueiaSelecaoModo(){
        mainFrame.getjButton3HvsH().setEnabled(false);
        mainFrame.getjButtonHvsPC().setEnabled(false);
        mainFrame.getjButtonPCvsPC().setEnabled(false);
    }
    
    public static void desbloqueiaSelecaoModo(){
        mainFrame.getjButton3HvsH().setEnabled(true);
        mainFrame.getjButtonHvsPC().setEnabled(true);
        mainFrame.getjButtonPCvsPC().setEnabled(true);
    }
    
    public static void escreveMensagem(String s){
        
        mainFrame.getMensagensLabel().setText(s);
        
        
    }
    
    public static void escreveQuemJoga (Player player){
        if (player == Player.PLAYER_A){
            mainFrame.getQuemJoga().setText("A");
            mainFrame.getQuemJoga().setForeground(Color.red);
        }else if (player == Player.PLAYER_B){
            mainFrame.getQuemJoga().setText("B");
            mainFrame.getQuemJoga().setForeground(Color.cyan);
            
        }else{
            mainFrame.getQuemJoga().setText("");
            mainFrame.getQuemJoga().setForeground(Color.white);
        }
    }
}
