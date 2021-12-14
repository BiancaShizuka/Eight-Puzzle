package Entidades;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;


public class PuzzleA {
    private int[][] puzzle;
    private int avaliacao;
    public PuzzleA(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public PuzzleA() {
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

    public void setAvaliacao(int avalizacao) {
        this.avaliacao = avalizacao;
    }
    
    public void moverPeca(){
        boolean flag=false;
        int i=0,j=0,lin=0,col=0,op=0;
        ArrayList<Integer> opcoes=new ArrayList();
        while(lin<3 && !flag)
        {
            col=0;
            while(col<3 && !flag){
                if(puzzle[lin][col]==0)
                    flag=!flag;
                else
                    col++;
            }
            if(!flag)
                lin++;
        }
        int qtdeP=0;
        if(lin-1>=0){ opcoes.add(0);qtdeP++;}
        if(lin+1<3) {opcoes.add(1);;qtdeP++;}
        if(col-1>=0) {opcoes.add(2);;qtdeP++;}
        if(col+1<3) {opcoes.add(3);;qtdeP++;}
        Random r = new Random();
        int pos = r.nextInt(opcoes.size());
        op=opcoes.get(pos);
        switch(op){
            case 0:
                puzzle[lin][col]=puzzle[lin-1][col];
                puzzle[lin-1][col]=0;
                //System.out.println("Entrei no lin-1");
                break;
            case 1:
                
                puzzle[lin][col]=puzzle[lin+1][col];
                puzzle[lin+1][col]=0;
                //System.out.println("Entrei no lin+1");
                break;
            case 2:
                
                puzzle[lin][col]=puzzle[lin][col-1];
                puzzle[lin][col-1]=0;
                //System.out.println("Entrei no col-1");
                break;
            case 3:
                
                puzzle[lin][col]=puzzle[lin][col+1];
                puzzle[lin][col+1]=0;
                //System.out.println("Entrei no col+1");
                break;
        }
    }
    public int distancia(PuzzleA estFinal){
        int soma=0;
        for (int lin=0;lin<3;lin++){
            for(int col=0;col<3;col++){
                soma+=calculaDistancia(puzzle[lin][col],lin,col,estFinal);
            }
        }
        //System.out.println("Soma="+soma);
        return soma;
    }
    public int calculaDistancia(int num,int lin2,int col2,PuzzleA estFinal){
        int dist=0;
        int lin=0,col=0;
        boolean achou=false;
        
        while(lin<3 && !achou){
            col=0;
            while(col<3 && !achou){
                if(estFinal.getPuzzle(lin, col)==num)
                    achou=!achou;
                else
                    col++;
            }
            if(!achou)
                lin++;
        }
        //System.out.println("lin2="+lin2+"  col2="+col2+ "    lin="+lin+ "   col="+col);
        dist=abs(lin2-lin)+abs(col2-col);
        //System.out.println("dist="+dist);
        return dist;
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
    public void copia(PuzzleA p){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                this.puzzle[i][j]=p.getPuzzle(i, j);
            }
        }
        //System.out.println("Copia os puzzles");
    }
}
