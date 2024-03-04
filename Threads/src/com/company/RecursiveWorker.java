package com.company;

import java.util.ArrayList;

public class RecursiveWorker extends Thread {
    private final int limit, starting_i, sections;
    private int result;

    RecursiveWorker(int starting_i, int limit, int sections) {
        super();
        this.starting_i = starting_i;
        this.limit = Math.min(limit, Datas.length());
        this.sections = sections;
    }

    public void run() {
        if (limit - starting_i <= sections) {
            for (int i = starting_i; i < limit; i++) result += Datas.get(i);
            return;
        }

        ArrayList<RecursiveWorker> childs = new ArrayList<>();

        int section = (limit - starting_i)/sections + 1;
        int end = starting_i + section;
        int start = starting_i;

        for (int i = 0; i < sections; i++) {
            childs.add(new RecursiveWorker(start, (Math.min(end, limit)), sections));
            childs.get(i).start();
            start = end;
            end += section;
        }

        for (RecursiveWorker t : childs) {
            try{
                t.join();
            }catch (Exception e){}
            result += t.getResult();
        }
    }

    public int getResult() {
        return result;
    }
}
