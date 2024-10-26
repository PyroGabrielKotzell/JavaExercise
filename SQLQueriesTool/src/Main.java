public class Main {
    private static String database, tabella;
    private static SQLTalker sql;
    private static String[] values;

    public static void main(String[] args) {
        database = "eserciziojdbc";
        tabella = "tabella";
        sql = new SQLTalker(database, tabella);
        // ID slot is a key and an auto incrementing one
        sql.addAutoIncKey(1);
        values = sql.getFormattedValues();
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
                case "2" -> {
                    doModify();
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
        if (values.length == 0) {
            System.out.println("Cannot insert row, no fields found");
            return;
        }

        String insertedVars = getInputForVars("Unidentified input, exiting insert");
        if (insertedVars.equals("")) return;

        sql.insert(insertedVars);
    }

    public static void doModify() {
        if (values.length == 0) {
            System.out.println("Cannot modify row, no fields found");
            return;
        }

        String input = System.console().readLine("Specify row number: ");
        Object n = getInputType(input);
        if (!n.getClass().equals(Integer.class)) {
            System.out.println("Cannot identify row, exiting modify");
            return;
        }
        Integer row = (Integer) n;

        String insertedVars = getInputForVars("Unidentified input, exiting modify");
        if (insertedVars.equals("")) return;

        sql.update(row, insertedVars);
    }

    private static String getInputForVars(String exiting_message) {
        String insertedVars = "";

        for (String string : values) {
            String input = System.console().readLine("Specify '" + string + "': ");
            Object n = getInputType(input);
            if (n == null) {
                System.out.println(exiting_message);
                return "";
            }
            insertedVars += n + ",";
        }
        insertedVars = insertedVars.substring(0, insertedVars.length() - 1);

        return insertedVars;
    }

    private static Object getInputType(String input) {
        if (input.contains(",")) {
            System.out.println("Please do not use ','");
            return null;
        }
        String inputCopy = input + "";
        if (inputCopy.replaceAll("[a-zA-Z]", "A").contains("A")) {
            return "'" + input + "'";
        }
        if (input.contains(".")) {
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
