import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import Agents.*;

public class Main{
    
    public static void main(String[] args){
        creaAgents(1);
    }
    
    public static void creaAgents(int teamId){
        File agents = new File("Agents");
        String[] classes = agents.list();
        assert classes != null;
        String path = "Agents\\";
        for (String aClass : classes) {
            try {
                Class<?> t = Class.forName(path + aClass.substring(0, aClass.indexOf(".")));
                Constructor<?> con = t.getConstructor(int.class);
                con.newInstance(teamId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}