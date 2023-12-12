package org.example.User;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "psy")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    private String chatId;
    private String emotion;
    private String intensity;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String accordance;
    private String effectivness;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String question6) {
        this.question6 = question6;
    }

    public String getAccordance() {
        return accordance;
    }

    public void setAccordance(String accordance) {
        this.accordance = accordance;
    }

    public String getEffectivness() {
        return effectivness;
    }

    public void setEffectivness(String effectivness) {
        this.effectivness = effectivness;
    }
}