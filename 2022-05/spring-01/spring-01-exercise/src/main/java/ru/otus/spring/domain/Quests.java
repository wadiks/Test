package ru.otus.spring.domain;

import lombok.Data;


@Data
public class Quests {
    public String id;
    public String quest;
    public String response;




    public Quests(String id, String quest, String response) {
        this.id=id;
        this.quest=quest;
        this.response = response;
    }

    public Quests() {

    }
}
