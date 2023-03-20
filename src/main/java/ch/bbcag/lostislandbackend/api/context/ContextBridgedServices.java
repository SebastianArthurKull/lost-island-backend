package ch.bbcag.lostislandbackend.api.context;

import ch.bbcag.lostislandbackend.api.service.UserService;

public interface ContextBridgedServices {
    UserService getUserService();
}
