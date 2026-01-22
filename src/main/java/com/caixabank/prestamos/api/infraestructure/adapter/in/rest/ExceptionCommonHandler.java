package com.caixabank.prestamos.api.infraestructure.adapter.in.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caixabank.prestamos.api.common.MensajesCommon;
import com.caixabank.prestamos.api.infraestructure.config.i18n.InternationalizationHelper;

/**
 * Clase para el manejo de excepciones propias
 */
@ControllerAdvice
public class ExceptionCommonHandler {

	private static final String TIME_STAMP_KEY = "timestamp";
	private static final String MENSAJE_KEY = "mensaje";
	private static final String ERRORES_KEY = "errores";

	/**
	 * Control de excepcion producida en la solicitud
	 * 
	 * @param ex {@link IllegalStateException}
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler({ IllegalStateException.class })
	public ResponseEntity<Object> handleEstadoException(IllegalStateException ex) {
		var body = createMapError(ex.getMessage(), null);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Control de excepcion objetos no encontrados
	 * 
	 * @param ex {@link RuntimeException}
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
		var body = createMapError(ex.getMessage(), null);
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Control de excepcion producida en los dato de la solicitud
	 * 
	 * @param ex {@link IllegalArgumentException}
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		var body = createMapError(ex.getMessage(), null);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 *  Control de excepcion producida en la validación de los campos de la solicitud
	 *  
	 * @param ex {@link MethodArgumentNotValidException}
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleValidationtException(MethodArgumentNotValidException ex) {
		Map<String, String> mapErroresValidacion = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> mapErroresValidacion.put(error.getField(), error.getDefaultMessage()));

		var body = createMapError(
				InternationalizationHelper.getMessage(MensajesCommon.ERROR_SOLICITUD_VALIDACIONES, new Object[] {}),
				mapErroresValidacion);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Crear mapa con la información del error
	 */
	private Map<String, Object> createMapError(String message, Map<String, String> errores) {

		Map<String, Object> body = new HashMap<>();
		body.put(TIME_STAMP_KEY, LocalDateTime.now());
		body.put(MENSAJE_KEY, message);

		if (errores != null) {
			body.put(ERRORES_KEY, errores);
		}

		return body;
	}

}
