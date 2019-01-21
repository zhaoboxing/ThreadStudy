package threadlearning;

/**
 * @Description 线程同步   关键字synchronized：1.同步代码块 2.同步方法    一般情况使用同步方法，同步方法性能优于同步代码块，同步会降低系统运行效率
 * 当没有使用同步的时候，多线程任务访问同一资源，在有延迟的情况下，会产生ticke=-1，这是因为在延迟的情况下，多个线程均有可能同时越过判断条件，故此时需要让线程同步
 *  @Author bx.Zhao
 * @Date 2019/1/16
 * @Time 10:41
 */

/**
*@Description   死锁：在进行多线程同步的过程中可能会出现的问题。所谓的死锁指的是若干个线程彼此等待的过程。
 * 造成死锁的主要原因：彼此都在互相等待，等待对方先让出资源。
 * 若干个线程访问同一资源的时候一定要进行同步处理，而过多的同步会造成死锁问题
*@Author bx.Zhao
*@Date 2019/1/16
*@Time 11:37
*/
public class ThreadSynchronized implements Runnable {
    private int ticket = 10;
    public static void main(String[] args) {
        ThreadSynchronized mt = new ThreadSynchronized();
        new Thread(mt, "售票机A").start();
        new Thread(mt, "售票机B").start();
        new Thread(mt, "售票机C").start();

    }
    /**
     * 同步代码块
     *
     * synchronized (this) {
     *             while (true) {
     *                 if (this.ticket > 0) {
     *                     try {
     *                         //模拟网络延迟
     *                         Thread.sleep(1000);
     *                     } catch (InterruptedException e) {
     *                         e.printStackTrace();
     *                     }
     *                     System.out.println(Thread.currentThread().getName() + "卖票，ticket=" + this.ticket--);
     *                 } else {
     *                     System.out.println("*******票卖完了********");
     *                     break;
     *                 }
     *             }
     *         }
     */


    /**
     * 同步方法
     */
    public synchronized  boolean sale(){
        if (this.ticket > 0) {
            try {
                //模拟网络延迟
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖票，ticket=" + this.ticket--);
            return  true;
        } else {
            System.out.println("*******票卖完了********");
            return false;
        }
    }
    @Override
    public void run() {
        synchronized (this) {
            while (this.sale()) {

            }
        }
    }


}
