package com.wisdge.web.springframework;

import com.wisdge.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
public class MessagesLocaleResolver implements LocaleResolver {
    @Nullable
    private Locale defaultLocale;

    public void setDefaultLocale(@Nullable Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    @Nullable
    public Locale getDefaultLocale() {
        return this.defaultLocale;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale defaultLocale = this.getDefaultLocale();
        if(defaultLocale != null && request.getHeader("Accept-Language") == null) {
            return defaultLocale;
        } else {
            Locale requestLocale = request.getLocale();
            String localeFlag = request.getParameter("lang");
            if (! StringUtils.isEmpty(localeFlag)) {
                String[] split = localeFlag.split("_");
                requestLocale = new Locale(split[0], split[1]);
            }
            return requestLocale;
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        this.defaultLocale = locale;
    }
}
