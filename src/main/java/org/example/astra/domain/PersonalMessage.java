package org.example.astra.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "personal_messages")
public class PersonalMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Сперва введите сообщение:")
    @Length(max = 2048, message = "Сообщение слишком длинное")
    private String text;


    @ManyToOne(fetch = FetchType.EAGER) //здесь мы указываем, что многие сообщения могут соответствовать одному автору.
    // получая сообщение, сразу подтягиваем информацию об авторе
    @JoinColumn(name = "user_id")
    private User author;

    private String dialogname;

    private String filename;

    public PersonalMessage() {
    }

    public PersonalMessage(String text, User author) {
        this.author = author;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDialogname() {
        return dialogname;
    }

    public void setDialogname(String dialogname) {
        this.dialogname = dialogname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
