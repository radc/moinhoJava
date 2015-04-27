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
public class Tabuleiro {
    private final Espaco tabuleiro[][][];

    public Tabuleiro() {
        tabuleiro = new Espaco[3][3][3];
        
        tabuleiro[0][0][0] = new Espaco(true,FrameManager.mainFrame.getjButton_7A());
        tabuleiro[0][0][1] = new Espaco(true,FrameManager.mainFrame.getjButton_7D());
        tabuleiro[0][0][2] = new Espaco(true,FrameManager.mainFrame.getjButton_7G());
        
        tabuleiro[0][1][0] = new Espaco(true,FrameManager.mainFrame.getjButton_4A());
        tabuleiro[0][1][1] = new Espaco(false,null);
        tabuleiro[0][1][2] = new Espaco(true,FrameManager.mainFrame.getjButton_4G());
        
        tabuleiro[0][2][0] = new Espaco(true,FrameManager.mainFrame.getjButton_1A());
        tabuleiro[0][2][1] = new Espaco(true,FrameManager.mainFrame.getjButton_1D());
        tabuleiro[0][2][2] = new Espaco(true,FrameManager.mainFrame.getjButton_1G());
        
        
        tabuleiro[1][0][0] = new Espaco(true,FrameManager.mainFrame.getjButton_6B());
        tabuleiro[1][0][1] = new Espaco(true,FrameManager.mainFrame.getjButton_6D());
        tabuleiro[1][0][2] = new Espaco(true,FrameManager.mainFrame.getjButton_6F());
        
        tabuleiro[1][1][0] = new Espaco(true,FrameManager.mainFrame.getjButton_4B());
        tabuleiro[1][1][1] = new Espaco(false,null);
        tabuleiro[1][1][2] = new Espaco(true,FrameManager.mainFrame.getjButton_4F());
        
        tabuleiro[1][2][0] = new Espaco(true,FrameManager.mainFrame.getjButton_2B());
        tabuleiro[1][2][1] = new Espaco(true,FrameManager.mainFrame.getjButton_2D());
        tabuleiro[1][2][2] = new Espaco(true,FrameManager.mainFrame.getjButton_2F());
        
        
        tabuleiro[2][0][0] = new Espaco(true,FrameManager.mainFrame.getjButton_5C());
        tabuleiro[2][0][1] = new Espaco(true,FrameManager.mainFrame.getjButton_5D());
        tabuleiro[2][0][2] = new Espaco(true,FrameManager.mainFrame.getjButton_5E());
        
        tabuleiro[2][1][0] = new Espaco(true,FrameManager.mainFrame.getjButton_4C());
        tabuleiro[2][1][1] = new Espaco(false,null);
        tabuleiro[2][1][2] = new Espaco(true,FrameManager.mainFrame.getjButton_4E());
        
        tabuleiro[2][2][0] = new Espaco(true,FrameManager.mainFrame.getjButton_3C());
        tabuleiro[2][2][1] = new Espaco(true,FrameManager.mainFrame.getjButton_3D());
        tabuleiro[2][2][2] = new Espaco(true,FrameManager.mainFrame.getjButton_3E());
    }
    
    public void apagaTabuleiro(){
       for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++){
                for (int k = 0; k < 3; k++){
                    if (j == 1 && k == 1){
                        //
                    }else{
                        try {
                            this.tabuleiro[i][j][k].removePeca();
                        } catch (Exception ex) {
                            
                        }
                    }
                }
            }
        } 
    }
    
    public Espaco getEspaco(int i, int j, int k){
        if (j == 1 && k == 1){
            System.out.println("Tentativa de obter espaço inválido");
            return null;
        }else{
            return this.tabuleiro[i][j][k];
        }
        
    }
    
        
}
