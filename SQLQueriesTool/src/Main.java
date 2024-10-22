public class Main {
    private static String database, tabella;
    private static SQLTalker sql;

    public static void main(String[] args) {
        database = "eserciziojdbc";
        tabella = "tabella";
        sql = new SQLTalker(database, tabella);
        interfaccia();
    }

    private static void interfaccia() {
        String liner = "GUDB[" + database + "<" + tabella + ">]: ";
        boolean leave = false;
        while (!leave) {
            System.out.println();
            System.out.println("""
                        1 - inserimento di una tupla
                        2 - modifica di una tupla
                        3 - cancellazione di una tupla
                        4 - visulizza tutto l'elenco
                        99- esci dal programma
                    """);
            String command = System.console().readLine(liner);
            switch (command) {
                case "1" -> {
                    doInsert();
                }
                case "4" -> {
                    sql.select("*", "1");
                }
                case "99" -> {
                    leave = true;
                }
            }
        }
    }

    private static void doInsert() {
        String tabVar = sql.getValues();
        String[] values = tabVar.substring(1, tabVar.length() - 1).split(",");
        String insertedVars = "";
        if (values.length == 0) {
            System.out.println("Can't put in values");
            return;
        }

        for (String string : values) {
            String input = System.console().readLine("Specify '" + string + "': ");
            Integer n = isInputInt(input);
            insertedVars += (n != null ? n : "'" + input + "'") + ", ";
        }
        insertedVars = insertedVars.substring(0, insertedVars.length() - 2);

        sql.insert(insertedVars);
    }

    private static Integer isInputInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
