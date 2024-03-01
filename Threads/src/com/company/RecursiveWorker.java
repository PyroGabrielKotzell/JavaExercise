package com.company;

public class RecursiveWorker extends Thread {
    private final int limit, starting_i;
    private int result;

    RecursiveWorker(int starting_i, int limit) {
        super();
        this.starting_i = starting_i;
        this.limit = Math.min(limit, Datas.length());
    }

    public void run() {
        if (limit - starting_i == 1) result = Datas.get(starting_i) + Datas.get(limit);
        else {
            RecursiveWorker rw1 = new RecursiveWorker(starting_i, limit/2);
            rw1.start();
            RecursiveWorker rw2 = new RecursiveWorker(limit/2, limit);
            rw2.start();
            try{
                rw1.join();
                rw2.join();
                result += rw1.getResult() + rw2.getResult();
            }catch (Exception e){}
        }
    }

    public int getResult(){
        return result;
    }
}
