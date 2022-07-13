package com.redhat.demos.infrastructure;

import com.redhat.demos.domain.Todo;
import com.redhat.demos.domain.TodoJson;
import com.redhat.demos.domain.TodoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    @Transactional
    public Todo createTodo(TodoJson todoJson) {

        Todo todo = Todo.createFromJson(todoJson);
        todoRepository.persist(todo);
        return todo;
    }

    @Transactional
    public List<Todo> listAll() {
        return todoRepository.listAll();
    }

    @Transactional
    public Todo updateTodo(Long id, TodoJson todoJson) {

        Todo todo = todoRepository.findById(id);
        Todo updatedTodo = todo.updateFromJson(todo, todoJson);
        todoRepository.persist(updatedTodo);
        return updatedTodo;
    }

    @Transactional
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
