package com.redhat.demos;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Todo extends PanacheEntity {

    String title;

    @Column(name = "ordering")
    int order;

    boolean completed;

    public Todo() {
    }

    public Todo(String title, int order, boolean completed) {
        this.title = title;
        this.order = order;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", order=" + order +
                ", completed=" + completed +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (order != todo.order) return false;
        if (completed != todo.completed) return false;
        return title != null ? title.equals(todo.title) : todo.title == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + order;
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
