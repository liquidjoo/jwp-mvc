package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {

    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private static final String DEFAULT_EXTENSION_POSTFIX = ".jsp";

    private String viewName;

    public JspView(String viewName) {
        if (viewName.matches(".*.jsp")) {
            viewName = viewName.substring(0, viewName.length() - 4);
        }
        this.viewName = viewName;
    }

    @Override
    public void render(final Map<String, ?> model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewName + DEFAULT_EXTENSION_POSTFIX);
        dispatcher.forward(request, response);
    }
}
