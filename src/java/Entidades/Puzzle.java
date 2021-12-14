package Entidades;

import java.util.ArrayList;
import java.util.Random;

public class Puzzle {
    private int[][] puzzle;
    private int avaliacao;
    public Puzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle() {
        this.puzzle=new int[3][3];
    }
    
    public int getPuzzle(int lin,int colum) {
        return puzzle[lin][colum];
    }

    public void setPuzzle(int lin,int colum,int valor) {
        this.puzzle[lin][colum]=valor;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvalizacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
    
    public void moverPeca(){
        boolean flag=false;
        int i=0,j=0,lin=0,col=0,op=0;
        
        while(lin<3 && !flag)// loop para encontrar o espaço que está vazio
        {
            col=0;
            while(col<3 && !flag){
                if(puzzle[lin][col]==0) // o lin e col é a posição do espaço
                    flag=!flag;
                else
                    col++;
            }
            if(!flag)
                lin++;
        }
        ArrayList<Integer> opcoes=new ArrayList(); // a lista de movimentos que pode fazer
        int qtdeP=0;
        if(lin-1>=0){ opcoes.add(0);qtdeP++;} // se tem peça acima movimento(0)
        if(lin+1<3) {opcoes.add(1);;qtdeP++;} // se tem peça abaixo movimento(1)
        if(col-1>=0) {opcoes.add(2);;qtdeP++;} // se tem peça a esquerda movimento(2)
        if(col+1<3) {opcoes.add(3);;qtdeP++;} // se tem peça a direita movimento(3)
        Random r = new Random();
        int pos = r.nextInt(opcoes.size()); // sorteio uma movimento possivel
        op=opcoes.get(pos); //qual o número do movimento permitido
        switch(op){ // faço a troca de valores no puzzle
            case 0:
                puzzle[lin][col]=puzzle[lin-1][col];
                puzzle[lin-1][col]=0;
                break;
            case 1:
                
                puzzle[lin][col]=puzzle[lin+1][col];
                puzzle[lin+1][col]=0;
                break;
            case 2:
                
                puzzle[lin][col]=puzzle[lin][col-1];
                puzzle[lin][col-1]=0;
                break;
            case 3:
                
                puzzle[lin][col]=puzzle[lin][col+1];
                puzzle[lin][col+1]=0;
                break;
        }
    }
    public int pecasFora(Puzzle estFinal){
        int qtde=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(puzzle[i][j]!=estFinal.getPuzzle(i, j))
                    qtde++;
                
            }
        }
        return qtde;
    }
    
    public void exibirPuzzle(){
        for(int lin=0;lin<3;lin++){
            for(int col=0;col<3;col++){
                System.out.print(" "+puzzle[lin][col]);
            }
            System.out.println("");
        }
        System.out.println("*********************************");
    }
    public void copia(Puzzle p){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.puzzle[i][j]=p.getPuzzle(i, j);
            }
        }
        //System.out.println("Copia os puzzles");
    }
    public boolean validarEntrada(){
        boolean valido=true,achei=false;
        int k=0,i=0,j=0;
        System.out.println("Vou conferir a entrada");
        while(k<9 && valido){ // o k é o número que tem que estar em alguma posição do puzzle
            i=0;
            achei=false;
            while(i<3 && valido && !(achei)){
                j=0;
                while(j<3 && valido && !(achei)){
                   if(k==puzzle[i][j])
                       achei=true;
                   j++; 
                }
                i++;
            }
            if(!achei) //não encontrei o número no puzzle logo não é válido
                valido=!valido;
            k++;
        }
        System.out.println("Valido a entrada= "+valido);
        return valido;
    }
}
