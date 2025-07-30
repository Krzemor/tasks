package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GlobalHttpErrorHandlerTest {

    @Test
    void handleTaskNotFoundException() {
        //Given
        GlobalHttpErrorHandler handler = new GlobalHttpErrorHandler();
        TaskNotFoundException exception = new TaskNotFoundException();

        //When
        ResponseEntity<Object> response = handler.handleTaskNotFoundException(exception);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Task with given id does not exist");
    }

}