package com.redhat.demos.infrastructure;

import com.redhat.demos.domain.TodoJson;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class KafkaResource {

    private static final Logger LOGGER = getLogger(KafkaResource.class);

    @Inject
    TodoService todoService;

    @Incoming("add") @Blocking
    public void addTodo(TodoJson todoJson) {

        LOGGER.info("adding Todo from json: {}", todoJson);
        todoService.createTodo(todoJson);
    }
}
