package threadlearning;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
*@Description 第三种实现多线程启动的方法，实现Callable接口，这一种方法在线程执行完毕后会有返回值
*@Param 
*@Return 
*@Author bx.Zhao
*@Date 2019/1/8
*@Time 17:30
*/
public class ThirdThread {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask=new FutureTask<>(new MyThirdThread("线程一"));
        new Thread(futureTask).start();
        FutureTask<String> futureTask2=new FutureTask<>(new MyThirdThread("线程二"));
        new Thread(futureTask2).start();
        System.out.println("线程执行完毕后返回的结果："+futureTask.get());

    }
}
class MyThirdThread implements Callable<String> {
    private String title;
    public MyThirdThread(String title){
        this.title=title;
    }
    int num=10;
    @Override
    public String call() throws Exception {
        for (int i = 0; i <num ; i++) {
            System.out.println(this.title+"线程启动 i="+i);
        }
        return "线程启动完毕"+title;
    }
}
