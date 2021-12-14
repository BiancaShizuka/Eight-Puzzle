package Entidades;

import java.util.ArrayList;

public class CaminhoB {
    private ArrayList<Puzzle> caminho;
    private int avaliacao;
    public CaminhoB(ArrayList<Puzzle> caminho, int avaliacao) {
        this.caminho = caminho;
        this.avaliacao = avaliacao;
    }
    public CaminhoB(ArrayList<Puzzle> copia) {
        caminho=new ArrayList();
        for(int i=0;i<copia.size();i++){
            inserirPuzzle(copia.get(i));
        }
        this.avaliacao=caminho.get(caminho.size()-1).getAvaliacao();
    }
    public CaminhoB(Puzzle inicio) {
        this.caminho = new ArrayList();
        this.caminho.add(inicio);
        this.avaliacao=inicio.getAvaliacao();
        //System.out.println("Avaliacao do puzzle inicio= "+this.avaliacao);
    }

    public CaminhoB() {
    }
    
    public boolean inserirPuzzle(Puzzle novo){
        boolean flag=true;
        int i=0,qtde=0;
        
        //System.out.println("Vou ver se são iguais");
        if(caminho.size()>0){// se o caminho já havia outros estados
            while(qtde<caminho.size() && flag){ //vejo todos os estados desse caminho
                if(noIgual(caminho.get(qtde),novo)){ //vejo se o novo estado é igual a algum outro do caminho
                    flag=false;
                    //System.out.println("os nos são iguais");
                }else{/*
                    System.out.println("#################################");
                    System.out.println("os nós são diferentes"); 
                    System.out.println("Novo");
                    novo.exibirPuzzle();
                    System.out.println("Fila");
                    caminho.get(qtde).exibirPuzzle();
                    System.out.println("#################################");*/
                }
                qtde++;
            }
        }
        if(flag){
            caminho.add(novo);
            this.avaliacao=novo.getAvaliacao(); //a avaliação do caminho muda para o do novo estado
            //System.out.println("Premira os nós são diferentes"); 
            /*
            
            System.out.println("caminhoBcaminhoBcaminhoB");
            System.out.println("Avaliacao al inserir um novo: "+this.avaliacao);
            novo.exibirPuzzle();
            System.out.println("caminhoBcaminhoBcaminhoB");*/
            
        }
        return flag;
    } 
    public boolean noIgual(Puzzle p1,Puzzle p2){ //vejo se os dois estados são iguais
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
    public ArrayList<Puzzle> getCaminho() {
        return caminho;
    }

    public void setCaminho(ArrayList<Puzzle> caminho) {
        this.caminho = caminho;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
}
