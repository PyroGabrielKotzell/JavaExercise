package com.company;

import java.lang.Thread;
import java.util.Scanner;

public class UserThread extends Thread{
    public String userIn = "";
    private boolean isEx = false;

    UserThread() {
    }

    public void run() {
        isEx = true;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("> ");
            userIn = sc.nextLine();
            isEx = false;
        }catch (Exception e){e.printStackTrace();}
    }

    public boolean isExecuting(){
        return isEx;
    }

    public void terminate() {
        interrupt();
    }
}
