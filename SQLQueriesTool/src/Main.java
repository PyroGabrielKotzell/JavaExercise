import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static String database, tabella;
    private static SQLTalker sql;
    private static String[] values;
    private static ArrayList<String> selectedFields;

    public static void main(String[] args) {
        database = "eserciziojdbc";
        tabella = "tabella";
        sql = new SQLTalker(database, tabella);
        // ID slot is a key and an auto incrementing one
        sql.addAutoIncKey(1);
        sql.addKey(1);
        values = sql.getFormattedValues();
        selectedFields = new ArrayList<>();
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
                        5 - visualizza con condizione
                        6 - prendi da file
                        ###############################
                        7 - stesso cognome
                        8 - nome che inizia per
                        9 - età media
                        10 - età minore
                        11 - età maggiore
                        99- esci dal programma
                    """);
            System.out.println("Table values: " + Arrays.toString(values));
            String command = System.console().readLine(liner);
            System.out.println();
            System.out.println("[][][][][]");
            switch (command) {
                case "1" -> {
                    doInsert();
                }
                case "2" -> {
                    doModify();
                }
                case "3" -> {
                    doDelete();
                }
                case "4" -> {
                    sql.select("*", "1");
                }
                case "5" -> {
                    doSelect();
                }
                case "6" -> {
                    gatherFromFile();
                }
                case "7" -> {
                    doEs1();
                }
                case "8" -> {
                    doEs2();
                }
                case "9" -> {
                    doEs3();
                }
                case "10" -> {
                    doEs4();
                }
                case "11" -> {
                    doEs5();
                }
                case "99" -> {
                    leave = true;
                }
            }
            System.out.println("[][][][][]");
            selectedFields.clear();
        }
    }

    private static void doInsert() {
        if (values.length == 0) {
            System.out.println("Cannot insert row, no fields found");
            return;
        }

        String insertedVars = getInputForVars("Unidentified input, exiting insert");
        if (insertedVars.equals(""))
            return;

        sql.insert(insertedVars);
    }

    public static void doModify() {
        if (values.length == 0) {
            System.out.println("Cannot modify rows, no fields found");
            return;
        }

        String whereClause = getWhereClause();
        if (whereClause == null)
            return;

        System.out.println("Select the fields to edit");
        if (!selectVars() || selectedFields.isEmpty())
            return;

        String insertedVars = getInputForSelVars("Unidentified input, exiting modify");
        if (insertedVars.equals(""))
            return;

        String[] insertedVarsArr = insertedVars.split(",");
        String formattedVars = "";
        for (int i = 0; i < selectedFields.size(); i++) {
            formattedVars += selectedFields.get(i) + " = " + insertedVarsArr[i] + ",";
        }
        formattedVars = formattedVars.substring(0, formattedVars.length() - 1);

        sql.update(whereClause, formattedVars);
    }

    private static void doDelete() {
        if (values.length == 0) {
            System.out.println("Cannot delete rows, no fields found");
            return;
        }

        String whereClause = getWhereClause();
        if (whereClause == null)
            return;

        sql.delete(whereClause);
    }

    private static void doSelect() {
        if (values.length == 0) {
            System.out.println("Cannot select rows, no fields found");
            return;
        }

        System.out.println("Select the fields to project, none for *");
        String fields = "";
        if (!selectVars())
            return;
        if (selectedFields.isEmpty())
            fields = "*";
        else {
            fields = selectedFields.toString();
            fields = fields.substring(1, fields.length() - 1);
        }

        String whereClause = getWhereClause();
        if (whereClause == null)
            return;

        sql.select(fields, whereClause);
    }

    private static void gatherFromFile() {

    }

    private static void doEs1() {

    }

    private static void doEs2() {

    }

    private static void doEs3() {

    }

    private static void doEs4() {

    }

    private static void doEs5() {

    }
    
    /**
     * Gets the values for all the possible fields.
     * 
     * @param exiting_message The exiting message after an unidentified input
     * @return The inserted vars from the user
     */
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

    /**
     * Selector of the fields from the user.
     * 
     * @return if it had success
     */
    private static boolean selectVars() {
        selectedFields.clear();
        for (String string : values) {
            String input = System.console().readLine("Do you want to select '" + string + "'? [y/n]: ");
            Object n = getInputType(input);
            if (n == null || !n.equals("'y'") && !n.equals("'n'")) {
                System.out.println("Not a valid input");
                return false;
            }
            if (n.equals("'y'"))
                selectedFields.add(string);
        }
        return true;
    }

    /**
     * Gets the values to put into the selected columns beforehand
     * 
     * @param exiting_message The exiting message after an unidentified input
     * @return The inserted vars from the user
     */
    private static String getInputForSelVars(String exiting_message) {
        if (selectedFields.isEmpty())
            return "";
        String insertedVars = "";

        for (String string : selectedFields) {
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

    /**
     * Tries to gather the where clause from the user.
     * 
     * @return The where clause
     */
    private static String getWhereClause() {
        String input = System.console().readLine("Specify the where clause: ");
        Object n = getInputType(input);
        if (n == null) {
            System.out.println("Where clause invalid");
            return null;
        }
        String whereClause = String.valueOf(n);
        if (n.getClass().equals(String.class))
            whereClause = whereClause.substring(1, whereClause.length() - 1);
        return whereClause;
    }

    /**
     * Determines the input type.
     * 
     * @param input The input string
     * @return The formatted input
     */
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
