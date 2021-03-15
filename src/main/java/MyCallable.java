import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable  implements Callable<Integer> {
  private int maxCountMessage = 10;
  @Override
  public Integer call() throws Exception {
    int cnt = new Random().nextInt(maxCountMessage);
    for (int i = 1; i <= cnt; i++) {
      Thread.sleep(1000);
      System.out.println("Я поток " + Thread.currentThread().getName() + ". Сообщение №" + i);
    }
    return cnt;
  }
}
