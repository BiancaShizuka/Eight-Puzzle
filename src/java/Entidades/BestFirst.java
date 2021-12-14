package Entidades;

import java.util.ArrayList;

public class BestFirst {
    ArrayList<CaminhoB> caminhos;
    private Puzzle estFinal;
    private int visitados;
    private int tempo;
    public BestFirst(Puzzle inicio,Puzzle estFinal) { //incio o estado que ficou depois de embaralhar e o estFinal é o estado que deve ser alcançado
        this.caminhos = new ArrayList();
        this.estFinal=estFinal;
        this.visitados=0;
        long tempoInicial = System.currentTimeMillis();
        System.out.println("Milissegundos inicio"+tempoInicial);
        
        inicio.setAvalizacao(inicio.pecasFora(estFinal));
        caminhos.add(new CaminhoB(inicio));
        
        encontrarSolucao();
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Milissegundos inicio"+tempoFinal);
        this.tempo=(int)(tempoFinal-tempoInicial);
        
        System.out.println("Terminei a busca bestFirst");
    }
    
    public void encontrarSolucao(){
        boolean achou=false;
        int b=0;
        CaminhoB caminho;
        while(!achou){
            caminho=encontrarCaminhoNoMenor();
            //System.out.println("Avaliacao do menor="+no.getAvaliacao());
            if(caminho.getAvaliacao()==0){ //já encontrei o estFinal
                System.out.println("b="+b);
                caminhos.removeAll(caminhos); //excluir todos os caminhos que haviam sido gerados
                caminhos.add(caminho); // adiciona o caminho que foi encontrado
                achou=!achou;
                System.out.println("Exclui todos e coloquei apenas o caminho");
            }
            else{ // ainda não estou no estado final
                encontrarPossibilidades(caminho); //encontrar todas as possiveis movimentações para gerar novos caminhos
            }
            b++;
        }
    }
    
    public CaminhoB encontrarCaminhoNoMenor(){
        CaminhoB Cmenor=null,Caux=null;
        int i=0,menor=0;
        if(caminhos.size()==1){
            //System.out.println("Entrei pela primeira vez");
            Cmenor=caminhos.remove(0);
        }
        else{
            while(i<caminhos.size()){ //para encontrar qual o caminho com menor avaliação
                /*System.out.println("Caux.getAvalização="+Caux.getAvaliacao());
                Caux.getCaminho().get(Caux.getCaminho().size()-1).exibirPuzzle();*/
                if(caminhos.get(i).getAvaliacao()<caminhos.get(menor).getAvaliacao()){
                    menor=i;
                }
                i++;
            }
            //System.out.println("*-------------");
            
            Cmenor=caminhos.remove(menor); // retiro dos caminhos, aquele que possuia a menor avaliação
            System.out.println("Peguei pela busca pelo menor="+Cmenor.getAvaliacao());
        }
        this.visitados+=1;
        return Cmenor;
    }
    
    public void encontrarPossibilidades(CaminhoB no){
        int lin=0,col=0;
        boolean flag=false;
        CaminhoB camB;
        Puzzle pAux=no.getCaminho().get(no.getCaminho().size()-1); // pAux é o ultimo estado do caminho
        Puzzle pNovo;
        
        while(lin<3 && !flag) //lin e col vão mostrar a posição que se encontra o número 0
        {
            col=0;
            while(col<3 && !flag){
                if(pAux.getPuzzle(lin, col)==0)
                    flag=!flag;
                else
                    col++;
            }
            if(!flag)
                lin++;
        }
        
        if(lin-1>=0){ // posso movimentar para cima
            //System.out.println("Adicionaer um novo vaminho na lista lin-1");
            pNovo=new Puzzle();
            pNovo.copia(pAux); //copio a ultima estado
            pNovo.setPuzzle(lin, col, pAux.getPuzzle(lin-1, col)); //copio no número 0 o outro valor
            pNovo.setPuzzle(lin-1, col, 0); //No outra posição eu coloco o número 0
            pNovo.setAvalizacao(pNovo.pecasFora(estFinal)); //calcula a avaliação do novo estado após a movimentação
            
            camB=new CaminhoB(no.getCaminho());
            if(camB.inserirPuzzle(pNovo)) // vejo se no caminho já havia um estado igual
                caminhos.add(camB); // se não havia eu insiro o novo caminho nos caminhos possiveis
            
        }
        if(lin+1<3){ //posso movimentar para baixo
            //System.out.println("Adicionaer um novo caminho na lista lin+1");
            pNovo=new Puzzle();
            pNovo.copia(pAux);
            pNovo.setPuzzle(lin, col,pAux.getPuzzle(lin+1, col));
            pNovo.setPuzzle(lin+1, col, 0);
            pNovo.setAvalizacao(pNovo.pecasFora(estFinal));
            
            camB=new CaminhoB(no.getCaminho());
            if(camB.inserirPuzzle(pNovo))
            caminhos.add(camB);
            
        }
        if(col-1>=0){ //posso movimentar oara a esquerda
            //System.out.println("Adicionaer um novo caminho na lista col-1");
            pNovo=new Puzzle();
            pNovo.copia(pAux);
            pNovo.setPuzzle(lin, col, pAux.getPuzzle(lin, col-1));
            pNovo.setPuzzle(lin, col-1, 0);
            pNovo.setAvalizacao(pNovo.pecasFora(estFinal));
            
            camB=new CaminhoB(no.getCaminho());
            if(camB.inserirPuzzle(pNovo))
            caminhos.add(camB);
            
        }
        if(col+1<3){ //posso movimentar para a direita
            //System.out.println("Adicionaer um novo caminho na lista col+1");
            pNovo=new Puzzle();
            pNovo.copia(pAux);
            pNovo.setPuzzle(lin, col, pAux.getPuzzle(lin, col+1));
            pNovo.setPuzzle(lin, col+1, 0);
            pNovo.setAvalizacao(pNovo.pecasFora(estFinal));
            camB=new CaminhoB(no.getCaminho());
            
            if(camB.inserirPuzzle(pNovo))
            caminhos.add(camB);
        }
    }
}
