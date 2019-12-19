package by.epam.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SessionRequestContent {
    private static final Logger logger = LogManager.getLogger(SessionRequestContent.class);
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private boolean isInvalidate;

    public SessionRequestContent() {
        this.requestAttributes = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {
        isInvalidate = false;
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = request.getAttribute(attributeName);
            requestAttributes.put(attributeName, attribute);
        }
        requestParameters.putAll(request.getParameterMap());

        Enumeration<String> sessionAttributeNames = request.getSession().getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            String attributeName = sessionAttributeNames.nextElement();
            Object attribute = request.getSession().getAttribute(attributeName);
            sessionAttributes.put(attributeName, attribute);
        }
    }

    public void insertAttributes(HttpServletRequest request) {
        Set<String> keySet = requestAttributes.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String attributeName = iterator.next();
            Object attribute = requestAttributes.get(attributeName);
            request.setAttribute(attributeName, attribute);
        }

        keySet = sessionAttributes.keySet();
        iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String attributeName = iterator.next();
            Object attribute = sessionAttributes.get(attributeName);
            request.getSession().setAttribute(attributeName, attribute);
        }
        if (isInvalidate) {
            request.getSession().invalidate();
            isInvalidate = false;
        }
        logger.info(requestAttributes);
        logger.info(requestParameters);
        logger.info(sessionAttributes);
    }

    public Object getAttribute(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public String getParameter(String parameterName) {
        if (requestParameters.containsKey(parameterName)) {
            return requestParameters.get(parameterName)[0];
        }
        return null;
    }

    public Object getSessionAttribute(String attributeName) {
        if (sessionAttributes.containsKey(attributeName)) {
            return sessionAttributes.get(attributeName);
        }
        return null;
    }

    public void setAttribute(String attributeName, Object attribute) {
        requestAttributes.put(attributeName, attribute);
    }

    public void setAttribute(Map<String, ? extends Object> attributeMap) {
        requestAttributes.putAll(attributeMap);
    }

    public void setSessionAttribute(String attributeName, Object attribute) {
        sessionAttributes.put(attributeName, attribute);
    }

    public boolean isInvalidate() {
        return isInvalidate;
    }

    public void setInvalidate(boolean invalidate) {
        isInvalidate = invalidate;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }
}
