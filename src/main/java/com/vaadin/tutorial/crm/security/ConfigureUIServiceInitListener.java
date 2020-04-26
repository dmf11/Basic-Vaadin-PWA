package com.vaadin.tutorial.crm.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.tutorial.crm.ui.view.login.LoginView;
import org.springframework.stereotype.Component;

/*
 Spring Security restricts access to content based on paths.
 Vaadin applications are single page applications, they do not trigger a full browser refresh when you navigate between
 views, even though the path does change.
 To secure a Vaadin application we need to wire Spring Security to the Vaadin navigation system.
*/

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticationNavigation);
        });
    }

    private void authenticationNavigation(BeforeEnterEvent event) {
        if(!LoginView.class.equals(event.getNavigationTarget())
            && !SecurityUtils.isUserLoggedIn()) {
                event.rerouteTo(LoginView.class);
        }
    }

}