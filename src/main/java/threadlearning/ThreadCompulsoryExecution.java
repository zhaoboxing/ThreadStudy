package threadlearning;

/**
 * @Description 线程的强制执行   mian()方法也属于一个线程，main()方法中的子方法则属于子线程
 * @Author bx.Zhao
 * @Date 2019/1/15
 * @Time 10:32
 */
public class ThreadCompulsoryExecution {
    public static void main(String[] args) {
        //获得主线程
        //在进行线程强制执行的时候一定要获取强制执行线程对象之后才可以执行join()调用
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    try {
                        //主线程要先于子线程强制执行
                        mainThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行，i=" + i);
            }
        }, "子线程");
        thread.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程运行,第"+i+"次");
        }
    }
}
