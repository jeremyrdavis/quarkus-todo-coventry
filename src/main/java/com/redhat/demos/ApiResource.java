package com.redhat.demos;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api")
public class ApiResource {

    @POST
    @Transactional
    public Response addTodo(final Todo todoToAdd) {

        Todo todo = new Todo();
        todo.setCompleted(todoToAdd.isCompleted());
        todo.setOrder(todoToAdd.getOrder());
        todo.setTitle(todoToAdd.getTitle());
        todo.persist();
        return Response.created(URI.create("/" + todo.id)).entity(todo).build();
    }

    @GET
    public Response allTodos() {

        List<Todo> allTodos = Todo.listAll();
        return Response.ok().entity(allTodos).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateTodo(@PathParam("id") final Long todoId, final Todo updatedTodo) {

        Todo todo = Todo.findById(todoId);
        todo.setTitle(updatedTodo.getTitle());
        todo.setOrder(updatedTodo.getOrder());
        todo.setCompleted(updatedTodo.isCompleted());
        todo.persist();
        return Response.ok().entity(todo).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTodo(@PathParam("id") final Long id) {

        Todo todo = Todo.findById(id);
        todo.delete();
        return Response.ok().build();
    }

}
