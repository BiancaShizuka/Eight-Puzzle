package Entidades;

import java.util.ArrayList;

public class BuscaA {
    private ArrayList<CaminhoA> caminhos;
    private PuzzleA estFinal;
    private int visitados;
    private int tempo;
    public BuscaA(PuzzleA inicio,PuzzleA estFinal) {
        this.caminhos=new ArrayList();
        this.estFinal=estFinal;
        this.visitados=0;
        inicio.setAvaliacao(inicio.distancia(estFinal));
        caminhos.add(new CaminhoA(inicio));
        
        long tempoInicial = System.currentTimeMillis();
        System.out.println("Milissegundos inicio"+tempoInicial);
        encontrarSolucao();
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Milissegundos inicio"+tempoFinal);
        this.tempo=(int)(tempoFinal-tempoInicial);
        System.out.println("Terminei a busca A*");
    }
    
    public BuscaA(){
        
    }
    
    public void encontrarSolucao(){
        boolean achou=false;
        CaminhoA camA;
        int b=0;
        while(!achou){
            camA=encontrarMenorCaminho();
            if(camA.getAvaliacao()==0){
                achou=!achou;
                caminhos.removeAll(caminhos);
                caminhos.add(camA);
                System.out.println("Removi todos e coloquei apenas o caminho certo");
            }else{
                //System.out.println("Vou ver os possiveis caminhos");
                encontrarPossibilidades(camA);
            }
            b++;
        }
    }
    public CaminhoA encontrarMenorCaminho(){
        int i=0,menor=0;
        CaminhoA Cmenor;
        //System.out.println("Qauntidade de caminhos="+caminhos.size());
        if(caminhos.size()==1){
            Cmenor=caminhos.remove(0);
        }else{
            while(i<caminhos.size()){
                if(caminhos.get(i).getTotal()<caminhos.get(menor).getTotal()){
                    menor=i;
                }
                i++;
            }
            
            Cmenor=caminhos.remove(menor);
            //System.out.println("O menor = "+Cmenor.getTotal());
        }
        this.visitados+=1;
        return Cmenor;
    }
    
    public void encontrarPossibilidades(CaminhoA no){
        int lin=0,col=0;
        boolean flag=false;
        CaminhoA camA;
        PuzzleA pAux=no.getCaminho().get(no.getCaminho().size()-1);
        PuzzleA pNovo;
        
        
        while(lin<3 && !flag)
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
        
        if(lin-1>=0){
            //System.out.println("Adicionaer um novo vaminho na lista lin-1");
            pNovo=new PuzzleA();
            pNovo.copia(pAux); 
            
            
            pNovo.setPuzzle(lin, col, pAux.getPuzzle(lin-1, col));
            pNovo.setPuzzle(lin-1, col, 0);
            pNovo.setAvaliacao(pNovo.distancia(estFinal));
            
            camA=new CaminhoA(no.getCaminho());
            //System.out.println("Vou conferir se já existe um no assim");
            if(camA.inserirPuzzle(pNovo)){
                caminhos.add(camA);
                //System.out.println("inserir um caminho novo lin-1");
            }
            
        }
        if(lin+1<3){
            //System.out.println("Adicionaer um novo caminho na lista lin+1");
            pNovo=new PuzzleA();
            pNovo.copia(pAux);
            
            
            pNovo.setPuzzle(lin, col,pAux.getPuzzle(lin+1, col));
            pNovo.setPuzzle(lin+1, col, 0);
            pNovo.setAvaliacao(pNovo.distancia(estFinal));
            
            camA=new CaminhoA(no.getCaminho());
            //System.out.println("Vou conferir se já existe um no assim");
            if(camA.inserirPuzzle(pNovo)){
                caminhos.add(camA);
                //System.out.println("inserir um caminho novo lin+1");
            }
            
        }
        if(col-1>=0){
            //System.out.println("Adicionaer um novo caminho na lista col-1");
            pNovo=new PuzzleA();
            pNovo.copia(pAux);
            
            
            pNovo.setPuzzle(lin, col, pAux.getPuzzle(lin, col-1));
            pNovo.setPuzzle(lin, col-1, 0);
            pNovo.setAvaliacao(pNovo.distancia(estFinal));
            
            camA=new CaminhoA(no.getCaminho());
            //System.out.println("Vou conferir se já existe um no assim");
            if(camA.inserirPuzzle(pNovo)){
                caminhos.add(camA);
                //System.out.println("inserir um caminho novo col-1");
            }
            
        }
        if(col+1<3){
            //System.out.println("Adicionaer um novo caminho na lista col+1");
            pNovo=new PuzzleA();
            pNovo.copia(pAux);
            pNovo.setPuzzle(lin, col, pAux.getPuzzle(lin, col+1));
            pNovo.setPuzzle(lin, col+1, 0);
            pNovo.setAvaliacao(pNovo.distancia(estFinal));
            camA=new CaminhoA(no.getCaminho());
            //System.out.println("Vou conferir se já existe um no assim");
            if(camA.inserirPuzzle(pNovo)){
                caminhos.add(camA);
                //System.out.println("inserir um caminho novo col+1");
            }
        }
    }
    
    public ArrayList<CaminhoA> getCaminhos() {
        return caminhos;
    }

    public void setCaminhos(ArrayList<CaminhoA> caminhos) {
        this.caminhos = caminhos;
    }
}
