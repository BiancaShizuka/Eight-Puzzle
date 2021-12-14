
package servlets;

import Entidades.BestFirst;
import Entidades.BuscaA;
import Entidades.Puzzle;
import Entidades.PuzzleA;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TelaPuzzle", urlPatterns = {"/TelaPuzzle"})
public class TelaPuzzle extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            int elem12,elem22,elem32,elem42,elem52,elem62,elem72,elem82,elem92;
            String erro = "";
            Gson gson = new Gson();
            String acao = request.getParameter("acao");
            int qtdeNulo=0;
            int elem1,elem2,elem3,elem4,elem5,elem6,elem7,elem8,elem9;
            
            if(request.getParameter("elem1").length()==0){elem1=0;qtdeNulo++;}
            else {elem1=Integer.parseInt(request.getParameter("elem1"));}

            if(request.getParameter("elem2").length()==0){elem2=0;qtdeNulo++;}
            else{elem2=Integer.parseInt(request.getParameter("elem2"));}

            if(request.getParameter("elem3").length()==0){elem3=0;qtdeNulo++;}
            else{elem3=Integer.parseInt(request.getParameter("elem3"));}

            if(request.getParameter("elem4").length()==0){elem4=0;qtdeNulo++;}
            else{elem4=Integer.parseInt(request.getParameter("elem4"));}

            if(request.getParameter("elem5").length()==0){elem5=0;qtdeNulo++;}
            else{elem5=Integer.parseInt(request.getParameter("elem5"));}

            if(request.getParameter("elem6").length()==0){elem6=0;qtdeNulo++;}
            else{elem6=Integer.parseInt(request.getParameter("elem6"));}

            if(request.getParameter("elem7").length()==0){elem7=0;qtdeNulo++;}
            else{elem7=Integer.parseInt(request.getParameter("elem7"));}

            if(request.getParameter("elem8").length()==0){elem8=0;qtdeNulo++;}
            else{elem8=Integer.parseInt(request.getParameter("elem8"));}

            if(request.getParameter("elem9").length()==0){elem9=0;qtdeNulo++;}
            else{elem9=Integer.parseInt(request.getParameter("elem9"));}
            
            int [][] pFinal={{elem1,elem2,elem3},{elem4,elem5,elem6},{elem7,elem8,elem9}};
            
            switch (acao.toLowerCase()) 
            {
                case "embaralhar":   
                    Puzzle puzzle;
                    if(qtdeNulo==1){
                        int [][] p={{elem1,elem2,elem3},{elem4,elem5,elem6},{elem7,elem8,elem9}};
                        puzzle=new Puzzle(p);
                        if(puzzle.validarEntrada()){
                            int lin,col;
                            boolean achou=false;
                            for(int i=0;i<20;i++){
                                puzzle.moverPeca();
                            }
                        }else{
                            puzzle=null;
                        }

                    }else puzzle=null;
                    response.getWriter().print(gson.toJson(puzzle));
                break;
                
                case "buscarbestfirst":
                    System.out.println("Entrei no best first");
                    elem12=Integer.parseInt(request.getParameter("2elem1"));
                    elem22=Integer.parseInt(request.getParameter("2elem2"));
                    elem32=Integer.parseInt(request.getParameter("2elem3"));
                    elem42=Integer.parseInt(request.getParameter("2elem4"));
                    elem52=Integer.parseInt(request.getParameter("2elem5"));
                    elem62=Integer.parseInt(request.getParameter("2elem6"));
                    elem72=Integer.parseInt(request.getParameter("2elem7"));
                    elem82=Integer.parseInt(request.getParameter("2elem8"));
                    elem92=Integer.parseInt(request.getParameter("2elem9"));
                    int [][] p={{elem12,elem22,elem32},{elem42,elem52,elem62},{elem72,elem82,elem92}};
                    puzzle=new Puzzle(p);
                    Puzzle pFim=new Puzzle(pFinal);
                    BestFirst best=new BestFirst(puzzle,pFim);
                    response.getWriter().print(gson.toJson(best));
                    break;
                case "buscaa":
                    System.out.println("Entrei na busca A*");
                    elem12=Integer.parseInt(request.getParameter("2elem1"));
                    elem22=Integer.parseInt(request.getParameter("2elem2"));
                    elem32=Integer.parseInt(request.getParameter("2elem3"));
                    elem42=Integer.parseInt(request.getParameter("2elem4"));
                    elem52=Integer.parseInt(request.getParameter("2elem5"));
                    elem62=Integer.parseInt(request.getParameter("2elem6"));
                    elem72=Integer.parseInt(request.getParameter("2elem7"));
                    elem82=Integer.parseInt(request.getParameter("2elem8"));
                    elem92=Integer.parseInt(request.getParameter("2elem9"));
                    
                    int [][] pA={{elem12,elem22,elem32},{elem42,elem52,elem62},{elem72,elem82,elem92}};
                    PuzzleA puzzle2=new PuzzleA(pA);
                    PuzzleA puzzleF=new PuzzleA(pFinal);
                    BuscaA busca=new BuscaA(puzzle2,puzzleF);
                    response.getWriter().print(gson.toJson(busca));
                    break;
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
