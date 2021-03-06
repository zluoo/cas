package org.apereo.cas.logout;

import org.apereo.cas.logout.slo.SingleLogoutServiceMessageHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is {@link DefaultLogoutExecutionPlan}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@Slf4j
public class DefaultLogoutExecutionPlan implements LogoutExecutionPlan {

    private final List<LogoutPostProcessor> handlers = new ArrayList<>();
    private final List<SingleLogoutServiceMessageHandler> singleLogoutServiceMessageHandlers = new ArrayList<>();

    @Override
    public void registerLogoutPostProcessor(final LogoutPostProcessor handler) {
        LOGGER.debug("Registering logout handler [{}]", handler.getName());
        handlers.add(handler);
    }

    @Override
    public Collection<LogoutPostProcessor> getLogoutPostProcessor() {
        OrderComparator.sort(this.handlers);
        return this.handlers;
    }

    @Override
    public void registerSingleLogoutServiceMessageHandler(final SingleLogoutServiceMessageHandler handler) {
        LOGGER.trace("Registering single logout service message handler [{}]", handler.getName());
        singleLogoutServiceMessageHandlers.add(handler);
    }

    @Override
    public Collection<SingleLogoutServiceMessageHandler> getSingleLogoutServiceMessageHandlers() {
        OrderComparator.sort(this.singleLogoutServiceMessageHandlers);
        return this.singleLogoutServiceMessageHandlers;
    }
}
