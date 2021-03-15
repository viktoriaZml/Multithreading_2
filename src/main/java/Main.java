import java.util.*;
import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) throws Exception {
    // Создаем пул потоков
    final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    final MyCallable myCallable = new MyCallable();
    // submit
    final Future<Integer> task1 = threadPool.submit(myCallable);
    final Future<Integer> task2 = threadPool.submit(myCallable);
    final Future<Integer> task3 = threadPool.submit(myCallable);
    final Future<Integer> task4 = threadPool.submit(myCallable);
    Integer result = task1.get() + task2.get() + task3.get() + task4.get();
    System.out.println("Итого сообщений: " + result);

    List<Callable<Integer>> list = new ArrayList<>();
    list.add(myCallable);
    list.add(myCallable);
    list.add(myCallable);
    list.add(myCallable);
    // invokeAll
    List<Future<Integer>> futList = threadPool.invokeAll(list);
    result = 0;
    for (Future<Integer> i : futList) {
      result += i.get();
    }
    System.out.println("Итого сообщений: " + result);
    // invokeAny
    result = threadPool.invokeAny(list);
    System.out.println("Минимум сообщений: " + result);

    // Завершаем работу пула потоков
    threadPool.shutdown();
  }
}

