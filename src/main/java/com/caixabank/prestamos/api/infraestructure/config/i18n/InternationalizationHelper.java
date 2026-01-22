package com.caixabank.prestamos.api.infraestructure.config.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

/**
 * Clase para la internacionalización 
 */
@RequiredArgsConstructor
@Component
public class InternationalizationHelper {
	
	private final MessageSource messageSource;
	private static MessageSource staticMessageSource;
	
	
	/**
	 * Inicia gestor mensaje
	 */
	@PostConstruct
    public void init() {
        staticMessageSource = this.messageSource;
    }

	/**
	 * Obtiene mensaje
	 * 
	 * @param key {@link String}
	 * @param args {@link Object[]}
	 * @return  {@link String}
	 */
    public static String getMessage(String key, Object[] args) {
        return staticMessageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

}
