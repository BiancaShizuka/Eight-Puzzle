
package Entidades;

import java.util.ArrayList;

public class CaminhoA {
    private ArrayList<PuzzleA> caminho;
    private int avaliacao;
    private int custo;
    public CaminhoA(ArrayList<PuzzleA> caminho, int avaliacao) {
        this.caminho = caminho;
        this.avaliacao = avaliacao;
        this.custo = caminho.size();
    }
    public CaminhoA(ArrayList<PuzzleA> copia) {
        caminho=new ArrayList();
        for(int i=0;i<copia.size();i++){
            inserirPuzzle(copia.get(i));
        }
        this.avaliacao=caminho.get(caminho.size()-1).getAvaliacao();
        this.custo=caminho.size();
        //System.out.println("Copiri o caminhos dos puzzles");
    }
    public CaminhoA(PuzzleA inicio) {
        this.caminho = new ArrayList();
        this.caminho.add(inicio);
        this.avaliacao=inicio.getAvaliacao();
        this.custo=0;
        //System.out.println("Avaliacao do puzzle inicio= "+this.avaliacao);
    }
    public boolean inserirPuzzle(PuzzleA novo){
        boolean flag=true;
        int i=0,qtde=0;
        
        if(caminho.size()>0){
            while(qtde<caminho.size() && flag){
                if(noIgual(caminho.get(qtde),novo)){
                    flag=false;
                }
                qtde++;
            }
        }
        if(flag){
            caminho.add(novo);
            this.avaliacao=novo.getAvaliacao();
            this.custo=this.custo+1;
        }
        return flag;
    } 
    public boolean noIgual(PuzzleA p1,PuzzleA p2){
        boolean igual=true;
        int i=0,j=0;
        while(i<3 && igual){
            j=0;
            while(j<3 && igual){
                if(p1.getPuzzle(i, j)!=p2.getPuzzle(i, j))
                    igual=false;
                j++;
            }
            i++;
        }
        return igual;
    }
    
    public ArrayList<PuzzleA> getCaminho() {
        return caminho;
    }

    public void setCaminho(ArrayList<PuzzleA> caminho) {
        this.caminho = caminho;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvalizacao(int avalizacao) {
        this.avaliacao = avalizacao;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getTotal() {
        return this.avaliacao+this.custo;
    }
    
    
}
