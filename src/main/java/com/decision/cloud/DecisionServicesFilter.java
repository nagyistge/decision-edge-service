package com.decision.cloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

// We filter Decision Services and maps them,
// by convention, to the proper Service ID by Eureka
// The convention is zuulserver:zuulport/decisions/${CONCLUSION}/${VIEW}/${VERSION}
// Would be mapped to a Decision Service with ID ${CONCLUSION}${VIEW}${VERSION}

@Component
public class DecisionServicesFilter extends ZuulFilter {

    private static final String URL_PREFIX = "/decisions";
    private static final String SERVICE_ID_KEY = "serviceId";

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = ctx.getRequest().getRequestURI();
        if (requestURI!=null && requestURI.startsWith(URL_PREFIX)){
            ctx.set(SERVICE_ID_KEY,findMicroServiceName(requestURI));
        }
        return false;
    }

    private String findMicroServiceName(String requestURI) {
        String microServiceName =  requestURI.replace(URL_PREFIX,"");
        microServiceName = microServiceName.replace("/","");
        microServiceName = microServiceName.replace("\\\\\\\\","");
        return microServiceName;
    }

    @Override
    public Object run() {
        return null;
    }
}
