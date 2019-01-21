package threadlearning;

/**
 * @Description 多线程编程经典案例：生产者与消费者   生产者负责生产信息，消费者负责消费信息，生产者生产一条消息，等待消费者消费之后再生产，不能产生重复数据
 * @Author bx.Zhao
 * @Date 2019/1/17
 * @Time 10:17
 */
public class ProducerAndCustomer {
    public static void main(String[] args) {
        Message message =new Message();
        new Thread(new Producer(message)).start();
        new Thread(new Customer(message)).start();
    }
}

class Message {
    private String titlle;
    private String content;
    /**
     * 表示生产或者消费的状态
     * flag=true 表示：允许生产，但不允许消费，此时消费者需要等待
     * flag=false 表示：允许消费，但不允许生产，此时生产者需要等待
     */
    private boolean flag = true;

    public synchronized void set(String titlle, String content) {
        //此时无法进行生产，应该等待
        if (flag == false) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            this.titlle = titlle;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.content = content;
            //生产完成之后状态变为false,允许消费单不允许生产
            this.flag = false;
            //唤醒等待的线程，有则唤醒
            super.notify();

    }

    public synchronized String get() {
        //还未生产需要等待
        if (this.flag == true) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            return this.titlle + " - " + this.content;
        } finally {
            //继续生产
            this.flag = true;
            //唤醒等待线程
            super.notify();
        }

    }
}

class Producer implements Runnable {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                this.message.set("赵博兴", "宇宙无敌大帅哥");
            } else {
                this.message.set("贾牛牛", "超级超级大美女");
            }
        }
    }
}

class Customer implements Runnable {
    private Message message;

    public Customer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.message.get());
        }
    }
}