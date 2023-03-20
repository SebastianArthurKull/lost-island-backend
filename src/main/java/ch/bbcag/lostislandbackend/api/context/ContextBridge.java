package ch.bbcag.lostislandbackend.api.context;

import ch.bbcag.lostislandbackend.api.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextBridge implements ContextBridgedServices, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private final UserService userService;

    @Autowired
    public ContextBridge(UserService userService) {
        this.userService = userService;
    }

    public static ContextBridgedServices services() {
        return applicationContext.getBean(ContextBridgedServices.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextBridge.applicationContext = applicationContext;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
