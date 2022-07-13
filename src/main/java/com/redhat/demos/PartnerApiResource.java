package com.redhat.demos;

import com.redhat.demos.domain.TodoJson;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/partner")
public class PartnerApiResource {

    private static final Logger LOGGER = getLogger(PartnerApiResource.class);

    @Channel("add")
    Emitter<TodoJson> addTodoEmitter;

    @POST
    public Response addTodo(TodoJson todoJson) {

        LOGGER.info("adding todo from todoJson: {}", todoJson);
        addTodoEmitter.send(todoJson);
        return Response.accepted().build();
    }

}
