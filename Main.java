//public class Main {
////    public static void main(String[] args) {
////        ArvoreAVL a = new ArvoreAVL();
////
////        a.inserirNo(40);
////        a.inserirNo(70);
////        a.inserirNo(90);
////        a.inserirNo(80);
////        a.inserirNo(60);
////        a.inserirNo(50);
////        a.inserirNo(45);
////
////
////
////
////        System.out.println(a.getRaiz().getDado());
////        System.out.println(a.getRaiz().getDireita().getDado());
////        System.out.println(a.getRaiz().getDireita().getEsquerda().getDado());
////        System.out.println(a.getRaiz().getDireita().getEsquerda().getEsquerda().getDado());
////
////    }
//public static void main(String[] args) {
//    ArvoreAVL avl = new ArvoreAVL();
//    avl.inserirNo(10);
//    avl.inserirNo(30);
//    avl.inserirNo(20); // Vai causar rotação dupla à esquerda
//
//    avl.imprimirArvore(avl.getRaiz(), "", true);
//}
//}
//
//
//


public class Main {
    public static void main(String[] args) {
        ArvoreAVL avl = new ArvoreAVL();


        avl.inserirNo(30);
        avl.inserirNo(40);
        avl.inserirNo(10);
        avl.inserirNo(9);
        avl.inserirNo(18);
        avl.inserirNo(12);

        // A árvore resultante deve ser:
        //     20
        //    /  \
        //  10    30

        imprimirArvore(avl.getRaiz());

        System.out.println("__________________________________");

        avl.removerNo(33);
        imprimirArvore(avl.getRaiz());
    }

    public static void imprimirArvore(No no) {
        if (no != null) {
            System.out.println(no.getDado() + " ");
            imprimirArvore(no.getEsquerda());
            imprimirArvore(no.getDireita());
        }
    }
}

