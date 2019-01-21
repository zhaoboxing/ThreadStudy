package threadlearning;
/**
*@Description 线程的礼让：先将资源让出去，让别的线程先执行，线程礼让可以使用Thread中提供的yeild()方法
*每次执行线程礼让都要调用一次yield()方法，且只会礼让当前资源
*@Author bx.Zhao
*@Date 2019/1/15
*@Time 11:03
*/
public class ThreadYield {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                if(i%3==0){
                    //线程礼让
                    Thread.yield();
                    System.out.println("子线程进行线程礼让");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"执行+i="+i);
            }
        },"子线程");
        thread.start();
        for (int i = 0; i <10 ; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("主线程执行，x="+i);

        }
    }


}
