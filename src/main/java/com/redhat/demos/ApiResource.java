package com.redhat.demos;

import com.redhat.demos.domain.Todo;
import com.redhat.demos.domain.TodoJson;
import com.redhat.demos.domain.TodoRepository;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/api")
public class ApiResource {

    private static final Logger LOGGER = getLogger(ApiResource.class);

    @Inject
    TodoRepository todoRepository;

    @POST
    @Transactional
    public Response addTodo(TodoJson todoJson) {

        Todo todo = Todo.createFromJson(todoJson);
        todoRepository.persist(todo);
        return Response.created(URI.create("/" + todo.id)).entity(todo).build();
    }

    @GET
    public Response allTodos() {

        List<Todo> allTodos = todoRepository.listAll();
        return Response.ok().entity(allTodos).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateTodo(@PathParam("id") final Long todoId, TodoJson todoJson) {

        LOGGER.info("updating Todo with id {} from {}", todoId, todoJson);
        Todo todo = todoRepository.findById(todoId);
        Todo updatedTodo = todo.updateFromJson(todo, todoJson);
        todoRepository.persist(updatedTodo);
        return Response.ok().entity(updatedTodo).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTodo(@PathParam("id") final Long id) {

        todoRepository.deleteById(id);
        return Response.ok().build();
    }

}
