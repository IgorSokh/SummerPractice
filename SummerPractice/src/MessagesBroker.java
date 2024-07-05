import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class MessagesBroker {
    public static void main(String[] args) {
        MessageBroker messageBroker = new MessageBroker();
        MessageProducer producer = new MessageProducer(messageBroker);
        MessageConsumer consumer = new MessageConsumer(messageBroker);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Введите команду (start/stop/exit):");
            String command = scanner.nextLine();

            switch (command) {
                case "start":
                    if (!producerThread.isAlive()) {
                        producerThread = new Thread(producer);
                        producerThread.start();
                    }
                    if (!consumerThread.isAlive()) {
                        consumerThread = new Thread(consumer);
                        consumerThread.start();
                    }
                    System.out.println("Потоки запущены.");
                    break;
                case "stop":
                    if (producerThread.isAlive()) {
                        producer.stop();
                        producerThread.interrupt();
                    }
                    if (consumerThread.isAlive()) {
                        consumer.stop();
                        consumerThread.interrupt();
                    }
                    System.out.println("Потоки остановлены.");
                    break;
                case "exit":
                    if (producerThread.isAlive()) {
                        producer.stop();
                        producerThread.interrupt();
                    }
                    if (consumerThread.isAlive()) {
                        consumer.stop();
                        consumerThread.interrupt();
                    }
                    running = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неизвестная команда. Попробуйте снова.");
                    break;
            }
        }

        scanner.close();
    }
}

class Message {
    private String content;
    private boolean read;
    private LocalDateTime createdAt;

    public Message(String content, LocalDateTime createdAt) {
        this.content = content;
        this.read = false;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return read;
    }

    public void markAsRead() {
        this.read = true;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

class MessageBroker {
    private Queue<Message> messageQueue = new LinkedList<>();

    public synchronized void addMessage(Message message) {
        messageQueue.add(message);
        notifyAll();
    }

    public synchronized Message getUnreadMessage() throws InterruptedException {
        while (messageQueue.isEmpty() || messageQueue.peek().isRead()) {
            wait();
        }
        Message message = messageQueue.poll();
        return message;
    }
}

class MessageProducer implements Runnable {
    private MessageBroker messageBroker;
    private volatile boolean running = true;

    public MessageProducer(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(2000);
                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Message message = new Message("Сообщение сгенерированное в " + timestamp, now);
                messageBroker.addMessage(message);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

class MessageConsumer implements Runnable {
    private MessageBroker messageBroker;
    private Random random = new Random();
    private volatile boolean running = true;

    public MessageConsumer(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                int sleepTime = random.nextInt(5000) + 1000;
                Thread.sleep(sleepTime);
                Message message = messageBroker.getUnreadMessage();
                if (message != null) {
                    LocalDateTime now = LocalDateTime.now();
                    String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    System.out.println(message.getContent() + " сообщение прочтено в " + timestamp);
                    message.markAsRead();
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}