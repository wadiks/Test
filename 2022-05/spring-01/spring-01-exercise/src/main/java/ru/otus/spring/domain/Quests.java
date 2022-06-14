package ru.otus.spring.domain;

import lombok.Data;


@Data
public class Quests {
    public String id;
    public String quest;
    public String otvet;

    public Quests(String id, String quest, String otvet) {
        this.id=id;
        this.quest=quest;
        this.otvet=otvet;
    }

    public Quests() {

    }
}
