package threadlearning;

/**
 * @Description 多线程实现售票
 * @Param
 * @Return
 * @Author bx.Zhao
 * @Date 2019/1/8
 * @Time 17:05
 */
public class SellTicket {
    public static void main(String[] args) {
        TicketThread tt = new TicketThread();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();

    }
}

class TicketThread implements Runnable {
    private int ticket = 5;
    int num = 100;

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            if (ticket > 0) {
                System.out.println("卖票，ticket" + this.ticket--);
            }
        }
    }
}
