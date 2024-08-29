public class ArvoreAVL {
    private No raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public void removerNo(int dado){
        No noEncontrado = buscaRecursiva(getRaiz(), dado);

        if( noEncontrado != null){
            if(noEncontrado.getDireita() == null && noEncontrado.getEsquerda() == null){
                remocaoNoFolha(noEncontrado); // nó sem filhos

            } else if (noEncontrado.getDireita() == null || noEncontrado.getEsquerda() == null){
                remocaoNoUmFilho(noEncontrado); // nó com apenas um filho

            } else { // nó com dois filhos (procura o seu sucessor)

                No sucessor = encontrarSucessor(noEncontrado);
                noEncontrado.setDado(sucessor.getDado()); // substitue pelo dado do sucessor

                // remover o sucessor, pois seu dado subu para o No que queria remover
                if(sucessor.getDireita() != null){
                    remocaoNoUmFilho(sucessor); // Se o sucessor tem filho à direita, remove o nó com um filho
                } else {
                    remocaoNoFolha(sucessor); // Se o sucessor é folha, remove o nó folha
                }
            }
            ajustarArvore(getRaiz());
            return;
        }
        System.out.println("No não encontrado");
    }


    public No encontrarSucessor(No no){
        No atual = no.getDireita();
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public void remocaoNoFolha(No no){
        // Verifica se o nó folha é filho esquerdo do pai, caso não for significa que ele é filho a direita
        if (no == no.getPai().getEsquerda()) {
            no.getPai().setEsquerda(null);
        } else {
            no.getPai().setDireita(null);
        }
    }

    public void remocaoNoUmFilho(No no){
        // verifica qual lado que o filho está
        No filho = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();

        // se o nó for a raiz, o filho dele se tornará raiz
        if (no == raiz) {
            raiz = filho;

        } else {
            // Verifica se o nó folha é filho esquerdo do pai, caso não for significa que ele é filho a direita
            if (no == no.getPai().getEsquerda()) {
                no.getPai().setEsquerda(filho);
            } else {
                no.getPai().setDireita(filho);
            }
        }
        // Ajusta a referência ao pai
        if (filho != null) {
            filho.setPai(no.getPai());
        }
    }

    public No buscaRecursiva(No no, int valor){ //Criar método de busca que recebe um valor e diz se o valor está ou não na árvore.
        if(no == null){
            return null;
        }
        if(no.getDado() == valor){
            return no;
        }
        else if(valor < no.getDado()){
            return buscaRecursiva(no.getEsquerda(), valor);
        } else {
            return buscaRecursiva(no.getDireita(), valor);
        }
    }

    public void inserirNo(int dado){
        No novoNo = new No(dado);

        if(raiz == null){
            raiz = novoNo;
        } else {
            int primeiro = raiz.getDado();
            No descida = raiz;

            while (descida != null) {
                if(primeiro > dado){
                    if(descida.getEsquerda() != null){
                        descida = descida.getEsquerda();
                        primeiro = descida.getDado();
                    } else {
                        novoNo.setPai(descida);
                        descida.setEsquerda(novoNo);
                        descida = null;
                    }
                } else {
                    if(descida.getDireita() != null){
                        descida = descida.getDireita();
                        primeiro = descida.getDado();
                    } else {
                        novoNo.setPai(descida);
                        descida.setDireita(novoNo);
                        descida = null;
                    }
                }
            }
        }
        ajustarArvore(getRaiz());
    }

    public void ajustarArvore(No no){
        if(no == null){
            return;
        }

        if(fatorBalanciamento(no) > 1 || fatorBalanciamento(no) < -1 ){
            if(fatorBalanciamento(no) > 1 && fatorBalanciamento(no.getEsquerda()) >= 0){
                rotacaoDireita(no);
                System.out.println("Rotação direita");
            } else if (fatorBalanciamento(no) < -1 && fatorBalanciamento(no.getDireita()) <= 0){
                rotacaoEsquerda(no);
                System.out.println("Rotação esquerda");
            } else if (fatorBalanciamento(no) > 1 && fatorBalanciamento(no.getEsquerda()) < 0){
                rotacaoEsquerda(no);
                rotacaoDireita(no);
                System.out.println("Rotação esquerda-direita");
            } else {
                rotacaoDireita(no);
                rotacaoEsquerda(no);
                System.out.println("Rotação direita-esquerda");
            }
        }

        if(no.getEsquerda() != null){
            ajustarArvore(no.getEsquerda());
        } else if (no.getDireita() != null){
            ajustarArvore(no.getDireita());
        }
    }

    public int altura(No no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return 0;
        } else if (no.getEsquerda() == null){
            return altura(no.getDireita())+1;

        } else if (no.getDireita() == null){
            return altura(no.getEsquerda())+1;
        }

        return Math.max(altura(no.getDireita()), altura(no.getEsquerda())) + 1;

    }

    public int fatorBalanciamento(No no){
        if(no.getEsquerda() == null && no.getDireita() == null){
            return 0;
        } else if (no.getEsquerda() == null){
            return  (-1) - altura(no.getDireita());
        } else if (no.getDireita() == null){
            return altura(no.getEsquerda()) - (-1) ;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    public void rotacaoDireita(No avo){

        if(avo.getEsquerda() == null) {
            return; // Não há nada para rotacionar
        }

        avo.getEsquerda().setPai(avo.getPai());
        if(avo.getPai() != null){
            avo.getPai().setEsquerda(avo.getEsquerda());
        } else {
            raiz = avo.getEsquerda();
        }
        avo.setPai(avo.getEsquerda());
        avo.setEsquerda( avo.getEsquerda().getDireita());

        if(avo.getEsquerda() != null){
            avo.getEsquerda().setPai(avo);
        }
        avo.getPai().setDireita(avo);
    }

    public void rotacaoEsquerda(No avo){
        if(avo.getDireita() == null) {
            return; // Não há nada para rotacionar
        }
        avo.getDireita().setPai(avo.getPai());
        if(avo.getPai() != null){
            avo.getPai().setDireita(avo.getDireita());
        } else {
            raiz = avo.getDireita();
        }
        avo.setPai(avo.getDireita());
        avo.setDireita( avo.getDireita().getEsquerda());

        if(avo.getDireita() != null){
            avo.getDireita().setPai(avo);
        }
        avo.getPai().setEsquerda(avo);
    }

    public No getRaiz() {
        return raiz;
    }
}
