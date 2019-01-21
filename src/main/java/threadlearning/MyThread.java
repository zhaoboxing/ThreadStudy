package threadlearning;

/**
 * 实现多线程的第一只种方法，通过集成Thread类，重写其中的run()方法，最后调用start()方法启动多线程
 * 注意：多线程的启动必须是调用Thread类中的start()方法进行启动，这是标准的多线程启动
 * 缺点：因为继承了Thread类所以受制于单继承的局限性
 */
class MyFirstThread extends Thread {
    private String title;

    public MyFirstThread(String title) {
        this.title = title;
    }

    /**
     * 线程的主体方法
     */
    int num = 10;

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            System.out.println(this.title + "运行，i=" + i);
        }
    }
}
/**
 * 多线程学习Demo class
 *
 * @author bxZhao
 * @date 2019/1/7
 */
public class MyThread {
    public static void main(String[] args) {
        /**
         *         new MyFirstThread("线程A").run();
         *         new MyFirstThread("线程B").run();
         *         new MyFirstThread("线程C").run();
         *         当直接调用run()方法的时候，线程会依次按顺序执行。多线程的执行必须调用start()方法，才能真正执行多线程
         */
        new MyFirstThread("线程A").start();
        new MyFirstThread("线程B").start();
        new MyFirstThread("线程C").start();
        /**
         *  MyFirstThread mt=new MyFirstThread("线程A");
         *         mt.start();
         *         mt.start();
         *         重复启动线程的时候会抛出一个IllegalThreadStateException异常，属于运行时异常
         */



    }
}
