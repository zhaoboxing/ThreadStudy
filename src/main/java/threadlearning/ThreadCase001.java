package threadlearning;
/**
*@Description 多线程编程经典案例1：四个线程，两个加法线程，两个减法线程，加减法线程交替执行
*@Author bx.Zhao
*@Date 2019/1/21
*@Time 15:40
*/
public class ThreadCase001 {
    public static void main(String[] args) {
        Resource resource = new Resource();
        addThread at = new addThread(resource);
        subThread st = new subThread(resource);
        new Thread(at, "加法线程-A").start();
        new Thread(at, "加法线程-B").start();
        new Thread(st, "加法线程-C").start();
        new Thread(st, "加法线程-D").start();
    }
}

/**
 * @Description 定义一个操作的资源
 * @Author bx.Zhao
 * @Date 2019/1/18
 * @Time 11:18
 */
class Resource {
    /**
     * 这是要进行加减操作的数据
     */
    private int num = 0;
    /**
     * 加减操作的切换
     * flag=true 表示进行加法操作，但无法进行减法操作
     * flag=false表示进行减法操作，但无法进行加法操作
     */
    private boolean flag = true;

    /**
     * 加法操作
     * 如果用if(this.flag==false){
     *             try {
     *                 super.wait();
     *             } catch (InterruptedException e) {
     *                 e.printStackTrace();
     *             }
     *         }
     * 这种方法判断去执行加法还是减法，会出现无法交替执行的情况，
     * wait表示在获取到该对象锁之后，主动释放该对象锁，同时本线程休眠。wait()会释放同步锁，让其它线程进来
     * if（）经过单次判断后不会再进行判断，所以用if就有可能出现无法交替执行（AddThread或SubThread多次执行）
     * while（）会不断执行判断，所以最好用while
     */

    /**
     * 出现无法交替执行情况的原因：
     * 当flag为ture的时候，实际上有两个减法在休眠，最后执行完加法后同时有两个减法被唤醒。 同理若是默认flag为false，会出现两次加法
     */
    public synchronized void add() {
        //如果flag=false则加法线程进入等待，执行减法操作
        while (this.flag==false){
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
        this.num++;
        System.out.println("【加法操作" + Thread.currentThread().getName() + "】num=" + this.num);
        //加法执行完毕，进行减法操作
        this.flag=false;
        //唤醒所有等待程
        super.notifyAll();
    }

    /**
     * 减法操作
     */
    public synchronized void sub() {
        //如果flag=true则减法线程进入等待，执行加法操作
        while(flag==true){
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
        this.num--;
        System.out.println("【减法操作" + Thread.currentThread().getName() + "】num=" + this.num);
        //减法进行完毕，进行减法操作
        this.flag=true;
        //唤醒所有等待线程
        super.notifyAll();
    }
}

/**
 * 加法线程
 */
class addThread implements Runnable {
    private Resource resource;

    public addThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            this.resource.add();
        }
    }
}

/**
 * 减法线程
 */
class subThread implements Runnable {
    private Resource resource;

    public subThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            this.resource.sub();
        }
    }
}