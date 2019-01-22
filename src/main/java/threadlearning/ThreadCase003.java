package threadlearning;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description 竞争抢答：设置三个抢答者，同时发出抢答指令，抢答成功者给出成功提示，未抢答成功者给出失败提示
 * @Author bx.Zhao
 * @Date 2019/1/22
 * @Time 15:22
 */
public class ThreadCase003 {
    public static void main(String[] args) throws Exception {
        Compete compete1 = new Compete();
        FutureTask<String> task2 = new FutureTask<String>(compete1);
        FutureTask<String> task = new FutureTask<String>(compete1);
        FutureTask<String> task3 = new FutureTask<String>(compete1);
        new Thread(task, "贾莹").start();
        new Thread(task2, "贾牛牛").start();
        new Thread(task3, "赵小猫").start();
        System.out.println(task.get());
        System.out.println(task2.get());
        System.out.println(task3.get());

    }
}

/**
 * 选择实现Callable接口，因为callable接口中有一个call（）方法，具有返回值，可以通过返回值确定成功还是失败
 */
class Compete implements Callable<String> {
    /**
     * 抢答处理
     */
    private boolean flag = false;

    @Override
    public synchronized String call() throws Exception {
        if (this.flag == false) {
            this.flag = true;
            return Thread.currentThread().getName() + "抢答成功";
        } else {
            return Thread.currentThread().getName() + "抢答失败";
        }
    }

}