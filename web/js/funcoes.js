obj=null;
ob2=null;
posicao=0;
msgErro=document.getElementById("div_erroBusca");

function Embaralhar()
{   //event.preventDefault(); // evita refresh da tela
    var divSelect=document.getElementById("div_formSelect");
    var resultEmb=document.getElementById("resultEmb");
    var relat=document.getElementById("div_relatorio");
    var resultBusca=document.getElementById("resultBusca");
    var btnNext=document.getElementById("buttonNext");
    var msgErroInicio=document.getElementById("div_erro");
    posicao=0;
    
    btnNext.style.display="none";
    divSelect.style.display = 'none';
    resultBusca.innerHTML="";
    relat.innerHTML="";
    
    
    const URL_TO_FETCH = 'TelaPuzzle';
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('formEstFinal'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('acao', 'embaralhar');
    fetch(URL_TO_FETCH, { method: 'post', body: data
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        console.log(retorno)
        if(retorno=="null"){
            msgErroInicio.innerHTML="Erro na entrada";
            resultEmb.innerHTML="";
        }
        else{
            msgErroInicio.innerHTML="";
            obj=JSON.parse(retorno);
            resultEmb.innerHTML="<div class='div_puzzle'>"+
                    "<div class='div_linha'>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[0][0]+"</p></div>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[0][1]+"</p></div>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[0][2]+"</p></div>"+
                    "</div>"+
                    "<div class='div_linha'>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[1][0]+"</p></div>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[1][1]+"</p></div>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[1][2]+"</p></div>"+
                    "</div>"+
                    "<div class='div_linha'>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[2][0]+"</p></div>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[2][1]+"</p></div>"+
                        "<div class='div_coluna'><p>"+obj.puzzle[2][2]+"<p></div>"+
                    "</div>"+
                "</div>";
                divSelect.style.display = 'block';
                window.scrollTo(0,document.body.scrollHeight);
        }

    }).catch(function (error) {
        console.error(error);
    });
    
    
}

async function Buscar()
{   //event.preventDefault(); // evita refresh da tela
    var selectTp=document.getElementById("tipoBusca");
    var relat=document.getElementById("div_relatorio");
    var resultBusca=document.getElementById("resultBusca");
    var btnNext=document.getElementById("buttonNext");
    posicao=0;
    var tpBusca=selectTp.value;
    resultBusca.innerHTML="";
    relat.innerHTML="";
    btnNext.disabled = false;
    btnNext.style.display='none';
    const URL_TO_FETCH = 'TelaPuzzle';
    console.log(obj);
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('formEstFinal'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('2elem1',obj.puzzle[0][0]);
    data.append('2elem2',obj.puzzle[0][1]);
    data.append('2elem3',obj.puzzle[0][2]);
    data.append('2elem4',obj.puzzle[1][0]);
    data.append('2elem5',obj.puzzle[1][1]);
    data.append('2elem6',obj.puzzle[1][2]);
    data.append('2elem7',obj.puzzle[2][0]);
    data.append('2elem8',obj.puzzle[2][1]);
    data.append('2elem9',obj.puzzle[2][2]);
    
    if(tpBusca=='A')
        data.append('acao', 'buscaa');
    else
        data.append('acao', 'buscarbestfirst');
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        console.log(retorno)
        if(retorno=="null")
            msgErro.innerHTML="Erro na entrada";
        else{
            obj2=JSON.parse(retorno);
            console.log(obj2);
            relat.innerHTML="<p>Número de passos: "+(obj2.caminhos[0].caminho.length-1)+"</p>";
            relat.innerHTML+="<p>Número de nós visitados: "+obj2.visitados+"</p>";
            relat.innerHTML+="<p>Tempo (em milissegundos):"+obj2.tempo+"</p>"
            resultBusca.innerHTML="";
            resultBusca.innerHTML+="<div class='div_puzzle'>"+
                    "<div class='div_linha'>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[0][0]+"</p></div>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[0][1]+"</p></div>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[0][2]+"</p></div>"+
                     "</div>"+
                     "<div class='div_linha'>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[1][0]+"</p></div>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[1][1]+"</p></div>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[1][2]+"</p></div>"+
                     "</div>"+
                     "<div class='div_linha'>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[2][0]+"</p></div>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[2][1]+"</p></div>"+
                         "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[2][2]+"<p></div>"+
                     "</div>"+
                 "</div>";
            posicao=posicao+1;
            btnNext.style.display="block";
            window.scrollTo(0,document.body.scrollHeight);
        }
            
    }).catch(function (error) {
        console.error(error);
    });
    
}

function exibirPassos(){
    var resultBusca=document.getElementById("resultBusca");
    var btnNext=document.getElementById("buttonNext");
    if(posicao<obj2.caminhos[0].caminho.length){
        resultBusca.innerHTML+="<div class='div_puzzle'>"+
                       "<div class='div_linha'>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[0][0]+"</p></div>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[0][1]+"</p></div>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[0][2]+"</p></div>"+
                        "</div>"+
                        "<div class='div_linha'>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[1][0]+"</p></div>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[1][1]+"</p></div>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[1][2]+"</p></div>"+
                        "</div>"+
                        "<div class='div_linha'>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[2][0]+"</p></div>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[2][1]+"</p></div>"+
                            "<div class='div_coluna'><p>"+obj2.caminhos[0].caminho[posicao].puzzle[2][2]+"<p></div>"+
                        "</div>"+
                    "</div>";
        posicao=posicao+1;
        window.scrollTo(0,document.body.scrollHeight);
        if(posicao==obj2.caminhos[0].caminho.length){
            btnNext.disabled = true;
            btnNext.style.display="none";
        }
    }
}