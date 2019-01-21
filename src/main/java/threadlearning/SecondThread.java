package threadlearning;

/**
 * 第二种实现多线程的方法：实现runnable接口,重写run()方法,通过Thread调用start()方法启动多线程
 * 这种方法去除了由于继承Thread类所带来的单继承问题
 *
 * 在以后的开发之中对于多线程的实现，优先考虑的就是Runnable接口实现，并且永恒都是通过Thread类对象的start()方法启动的
 *  @author bxZhao
 *  @date 2019/1/7
 */
public class SecondThread {
    public static void main(String[] args) {
        Thread thread1=new Thread(new MySecondThread("线程A"));
        thread1.start();
        Thread thread2=new Thread(new MySecondThread("线程B"));
        thread2.start();
        Thread thread3=new Thread(new MySecondThread("线程C"));
        thread3.start();

        //使用lambda表达式执行
        int n=3,k=10;
        for (int i = 0; i <n ; i++) {
            String title="线程对象"+i;
            new Thread(()->{
                for (int j = 0; j <k ; j++) {
                    System.out.println(title+"运行,j="+j);
                }
            }).start();
        }
    }
}
class  MySecondThread implements Runnable{
    private String title;

    public MySecondThread(String title) {
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