package com.company;

import java.util.ArrayList;

public class RecursiveWorker extends Thread {
    private final int starting_i, limit, space, sections;
    private int result;
    private final ArrayList<RecursiveWorker> children = new ArrayList<>();

    RecursiveWorker(int starting_i, int limit, int sections) {
        super();
        this.starting_i = starting_i;
        this.limit = Math.min(limit, Datas.length());
        space = limit - starting_i;
        this.sections = sections;
    }

    public void run() {
        if (space > sections) {

            int section = space/sections + 1;
            int end = starting_i + section;
            int start = starting_i;

            for (int i = 0; i < ; i++) {
                children.add(new RecursiveWorker(start, (Math.min(end, limit)), sections));
                children.get(i).start();
                start = end;
                end += section;
            }

            return;
        }

        for (int i = starting_i; i < limit; i++) result += Datas.get(i);
    }

    public int getResult() {
        for (RecursiveWorker t : children) {
            try{
                t.join();
            }catch (Exception e){}
            result += t.getResult();
        }
        return result;
    }
}
