package threadlearning;

/**
 * @Description 多线程编程经典案例2：生产电脑和搬走电脑，要求生产一台 搬走一台，如果没有新电脑生产，则先生产，如果生产的电脑没搬走，则先搬走后再生产
 * @Author bx.Zhao
 * @Date 2019/1/21
 * @Time 15:41
 */
public class ThreadCase002 {
    public static void main(String[] args) {
        ComputerResource computerResource = new ComputerResource();
        new Thread(new ComputerProducer(computerResource)).start();
        new Thread(new ComputerConsumer(computerResource)).start();
    }
}

/**
 * @Description 电脑信息类
 * @Author bx.Zhao
 * @Date 2019/1/21
 * @Time 15:52
 */
class Computer {
    private static int count = 0;
    private String name;
    private double price;

    public Computer(String name, double price) {
        this.name = name;
        this.price = price;
        count++;
    }

    @Override
    public String toString() {
        return "第" + count + "台电脑 " +
                "电脑名字：'" + this.name + '\'' +
                ", 电脑价格" + this.price +
                '}';
    }
}

class ComputerResource {
    private Computer computer;

    public synchronized void make() {
        if (this.computer != null) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.computer = new Computer("戴尔电脑", 6666.88);
        System.out.println("【生产电脑】"+this.computer);
        super.notifyAll();

    }

    public synchronized void get() {
        if (this.computer == null) {
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
        System.out.println("【取走电脑】"+this.computer);
        //取走了
        this.computer = null;
        super.notifyAll();

    }
}

class ComputerProducer implements Runnable {
    private ComputerResource computerResource;

    public ComputerProducer(ComputerResource computerResource) {
        this.computerResource = computerResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            this.computerResource.make();
        }
    }
}

class ComputerConsumer implements Runnable {
    private ComputerResource computerResource;

    public ComputerConsumer(ComputerResource computerResource) {
        this.computerResource = computerResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            this.computerResource.get();
        }
    }
}
