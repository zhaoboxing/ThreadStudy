package threadlearning;
/**
*@Description 线程中断，Thread提供中断方法：判断线程是否被中断isInterrupted（） 中断线程执行interrupt（）.所有的线程都是可以被中断的
*中断线程必须处理异常
*@Author bx.Zhao
*@Date 2019/1/15
*@Time 10:04
*/
public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread thread=new Thread(()->{
            System.out.println("好瞌睡，我要睡十秒");
            try {
                Thread.sleep(10000);
                System.out.println("睡够了，起来继续嗨");
            } catch (InterruptedException e) {
                System.out.println("谁叫醒我了，我要弄死他");
            }

        });
        thread.start();//线程启动,开始睡觉
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断线程是否被中断,该方法返回false
        if (!thread.isInterrupted()){
            System.out.println("只能让他 睡一秒，我要叫醒他");
            //中断线程
            thread.interrupt();
            if(thread.isInterrupted()){
                System.out.println("成功叫醒了他");
            }
        }
    }


}
