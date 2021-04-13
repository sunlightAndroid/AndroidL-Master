package com.eric.jetpack.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {
    public static void main(String[] args) {
        TicketSell sell = new TicketSell();
        sell.createTickets();
        sell.sell() ;
    }

    public static class TicketSell {
        private List<String> tickets = new ArrayList<>();
        public void createTickets() {
            for (int i = 0; i < 5; i++) {
                tickets.add("电影票 " + i);
            }
        }

        public void sell() {
            for (int i = 0; i < 5; i++) {
                new Thread(this::printThreadName).start();
            }
        }

        private synchronized void printThreadName(){
            String name = Thread.currentThread().getName();
            System.out.println("买票人：" + name + "准备买票了，排队中。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String ticket = tickets.remove(0);
            System.out.println("买票人：" + name + "买到的票是：" + ticket);
        }
    }
}
