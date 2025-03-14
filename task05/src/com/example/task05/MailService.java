package com.example.task05;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MailService<T> implements Consumer<Mail<T>> {
    private final Map<String, List<T>> mailBox = new HashMap<>();

    @Override
    public void accept(Mail<T> mail) {
        mailBox.computeIfAbsent(mail.getTo(), k -> new ArrayList<>()).add(mail.getContent());
    }

    public Map<String, List<T>> getMailBox() {
        return mailBox.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Collections.unmodifiableList(e.getValue())
                ));
    }
}