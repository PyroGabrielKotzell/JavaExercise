public class Main {

    public static void main(String[] args) {
        GestoreListe<String> gestore = new GestoreListe<>("a");
        System.out.println(gestore);
        System.out.println("--------------------");
        gestore.add("1");
        System.out.println("--------------------");
        gestore.add("b");
        System.out.println(gestore);
        System.out.println("--------------------");
        System.out.println(gestore.getLenght());
        System.out.println("--------------------");
        gestore.changeVal("10", 0);
        System.out.println(gestore);
        System.out.println("--------------------");
        gestore.add("c");
        gestore.add("d");
        System.out.println(gestore);
        System.out.println("--------------------");
        System.out.println(gestore.getNumNodeVal("c"));
        System.out.println("--------------------");
        System.out.println(gestore.getValNode(0));
        System.out.println("--------------------");
        gestore.add("z");
        System.out.println(gestore);
        System.out.println("--------------------");
        gestore.add("a");
        System.out.println(gestore);
        System.out.println("--------------------");
        gestore.removeNum(0);
        gestore.removeVal("d");
        System.out.println(gestore);
        System.out.println("--------------------");
    }
}