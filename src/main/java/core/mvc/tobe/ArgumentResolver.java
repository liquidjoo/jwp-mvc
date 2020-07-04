package core.mvc.tobe;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public interface ArgumentResolver {
    boolean equalsTo(Class parameterType, Method method);

    Object getParameterValue(final HttpServletRequest request, final HttpServletResponse response, final Class parameterType, String parameterName, Method method) throws Exception;
}
