package threadlearning;

/**
 * @Description 线程休眠，休眠可以自动实现线程的唤醒，以继续后续的处理
 * @Param
 * @Return
 * @Author bx.Zhao
 * @Date 2019/1/15
 * @Time 9:40
 */
public class ThreadSleep {
    public static void main(String[] args) {
//单个线程休眠
//        new Thread(()->{
//            for (int i = 0; i <10 ; i++) {
//                System.out.println(Thread.currentThread().getName() +"i="+i);
//                try {
//                    /**
//                     * 线程休眠1000毫秒
//                     */
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"线程对象").start();

//多线程休眠，由于线程之间的执行差别较小，看上去像一起执行一起休眠，实际上是有具体的先手顺序的
        Runnable run = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "i=" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ;
        };
        for (int i = 0; i < 5; i++) {
            new Thread(run, "线程对象" + i).start();
        }

    }
}
