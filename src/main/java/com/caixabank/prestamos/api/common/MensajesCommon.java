package com.caixabank.prestamos.api.common;

import lombok.NoArgsConstructor;

/**
 * Clase con los códigos de los mensajes
 */
@NoArgsConstructor
public class MensajesCommon {
	
	/**
	 * Mensaje de error producido al transicionar de estado incorrecto la solicitud
	 */
	public static final String ERROR_TRANSICION_ESTADO_SOLICITUD = "error.transicion.estado.solicitud";
	
	/**
	 *  Mensaje de error producido al introducir un importe invalido
	 */
	public static final String ERROR_IMPORTE_INVALIDO = "error.importe.invalido";
	
	/**
	 *  Mensaje de error producido al crear una solicitud ya existente
	 */
	public static final String ERROR_SOLICITUD_EXISTENTE = "error.solicitud.existente";
	
	/**
	 *  Mensaje de error producido al obtener una solicitud que no existente
	 */
	public static final String ERROR_SOLICITUD_NO_EXISTENTE = "error.solicitud.no.existente";
	
	/**
	 *  Mensaje de error producido al validar los campos de la solicitud
	 */
	public static final String ERROR_SOLICITUD_VALIDACIONES = "error.solicitud.validaciones";

}
