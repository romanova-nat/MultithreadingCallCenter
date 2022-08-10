import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {

    private static ConcurrentLinkedQueue<Call> queue = new ConcurrentLinkedQueue<>(); // очередь для звонков
    final int TIME_FOR_PROCESSING = 3000; // спим во время обработки звонков оператором
    final int TIME_FOR_ATS_WORK = 1000; // спим между вбросами звонком АТС

    Random random = new Random();
    final int ALL_CALL_LIMIT = 60; // лимит всех звонков выбираем рандомно
    final int CALL_LIMIT = 10; //лимит вбросов звонков

    // Добавляем звонки в очередь
    public void workToATS() throws InterruptedException {

        while (queue.size() < ALL_CALL_LIMIT) { // пока очередь не равна лимиту, каждую секунду вбрасываем по пачке звонков
            Thread.sleep(TIME_FOR_ATS_WORK);
            for (int i = 1; i < CALL_LIMIT+1; i++) {
                queue.add(new Call(i));
            }
            for (Call s : queue) {
                System.out.println(s.toString());
            }
            System.out.println("Очередь звонков составляет: " + queue.size()); // после каждого вброса партии звонков
        }
    }


    //Обрабатываем звонки из очереди
    public void workToOperators() throws InterruptedException {
        while (true) {
            Thread.sleep(TIME_FOR_PROCESSING);
            Call number = queue.remove();
            if (number.equals(null)) {
                System.out.println("Еще никто не звонил");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " обработал " + number);
        }
    }
}
