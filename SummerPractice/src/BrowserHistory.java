import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BrowserHistory {
    public static void main(String[] args) {
        List<LocalDateTime> dateTimes = IntStream.range(0, 10)
                .mapToObj(i -> LocalDateTime.now().plusMinutes(new Random().nextInt(1000)))
                .collect(Collectors.toList());

        List<String> addresses = List.of(
                "https://www.youtube.com/", "https://www.google.com/", "https://vk.ru/",
                "https://stackoverflow.com/", "https://metanit.com/", "https://habr.com/",
                "https://mail.ru/", "http://ok.ru/", "http:/https://ru.wikipedia.org/",
                "https://https://github.com/"
        );

        List<Session> sessions = dateTimes.stream()
                .map(dateTime -> new Session(addresses.get(new Random().nextInt(addresses.size())), dateTime))
                .collect(Collectors.toList());

        List<Session> latestSessions = sessions.stream()
                .sorted(Collections.reverseOrder(java.util.Comparator.comparing(session -> session.visitTime)))
                .limit(5)
                .collect(Collectors.toList());

        latestSessions.forEach(session -> System.out.println(session.address + " - " + session.visitTime));
    }
}

class Session {
    String address;
    LocalDateTime visitTime;

    public Session(String address, LocalDateTime visitTime) {
        this.address = address;
        this.visitTime = visitTime;
    }
}