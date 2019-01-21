package threadlearning;
/**
*@Description 线程优先级 线程优先级越高，越有可能先执行（越有可能先抢占到资源）
*主线程的优先级属于中等优先级，优先级为5.而默认创建的线程也是中等优先级，线程优先级高的只是有可能先执行，而不是绝对优先执行
*@Author bx.Zhao
*@Date 2019/1/15
*@Time 11:51
*/
public class ThreadPriority {
    public static void main(String[] args) {
        Runnable run=()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行");
            }
        };
        Thread threadA=new Thread(run,"线程A");
        Thread threadB=new Thread(run,"线程B");
        Thread threadC=new Thread(run,"线程C");
        threadA.setPriority(1);
        threadB.setPriority(1);
        threadC.setPriority(10);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
