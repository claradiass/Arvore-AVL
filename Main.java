public class Main {
    public static void main(String[] args) {
        ArvoreAVL a = new ArvoreAVL();

        a.inserirNo(40);
        a.inserirNo(30);
        a.inserirNo(10);
        a.inserirNo(70);
        a.inserirNo(90);
        a.inserirNo(80);
        a.inserirNo(60);
        a.inserirNo(50);
        a.inserirNo(45);


        System.out.println(a.getRaiz().getDado());
        System.out.println(a.getRaiz().getDireita().getDado());
        System.out.println(a.getRaiz().getDireita().getEsquerda().getDado());
        System.out.println(a.getRaiz().getDireita().getEsquerda().getEsquerda().getDado());

    }
}
