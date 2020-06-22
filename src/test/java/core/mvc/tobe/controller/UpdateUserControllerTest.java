package core.mvc.tobe.controller;

import core.mvc.tobe.AnnotationHandlerMapping;
import core.mvc.tobe.HandlerExecution;
import next.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UpdateUserControllerTest {

    private AnnotationHandlerMapping handlerMapping;

    @BeforeEach
    public void setup() {
        handlerMapping = new AnnotationHandlerMapping("core.mvc.tobe");
        handlerMapping.initialize();
    }

    @Test
    void updateUser() throws Exception {
        User user = new User("pobi", "password", "포비", "pobi@nextstep.camp");
        createUser(user);
        MockHttpServletRequest request = new MockHttpServletRequest("PATCH", "/users/update");
        request.setParameter("userId", "seongju");
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerExecution execution = handlerMapping.getHandler(request);
        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(() -> execution.handle(request, response));

    }

    private void createUser(User user) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/users/create");
        request.setParameter("userId", user.getUserId());
        request.setParameter("password", user.getPassword());
        request.setParameter("name", user.getName());
        request.setParameter("email", user.getEmail());
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerExecution execution = handlerMapping.getHandler(request);
        execution.handle(request, response);
    }
}
