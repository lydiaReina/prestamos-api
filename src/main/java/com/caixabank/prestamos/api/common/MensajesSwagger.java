package com.caixabank.prestamos.api.common;

import lombok.NoArgsConstructor;

/**
 * Clase con los mensajes para las anotaciones swagger
 */
@NoArgsConstructor
public class MensajesSwagger {

	public static final String IDENTIFICADOR_DESC = "Identificador de la solicitud";
	public static final String IDENTIFICADOR_EJEMPLO = "1";
	
	public static final String NOMBRE_SOLICITANTE_DESC = "Nombre del Solicitante";
	public static final String NOMBRE_SOLICITANTE_EJEMPLO = "Lydia Reina";
	
	public static final String IMPORTE_SOLICITADO_DESC = "Importe Solicitado";
	public static final String IMPORTE_SOLICITADO_EJEMPLO = "1500.5";
	
	public static final String DIVISA_DESC = "Divisa";
	public static final String DIVISA_EJEMPLO = "EUR";
	
	public static final String DOCUMENTO_IDENTIDAD_DESC = "Documento de Identidad";
	public static final String DOCUMENTO_IDENTIDAD_EJEMPLO = "99999999L";
	
	public static final String FECHA_CREACION_SOLICITUD_DESC = "Fecha Creación Solicitud";
	public static final String FECHA_CREACION_SOLICITUD_EJEMPLO = "2026-01-2";
	
	public static final String ESTADO_SOLICITUD_DESC = "Estado de la Solicitud";
	public static final String ESTADO_SOLICITUD_EJEMPLO = "PENDIENTE";
	
	public static final String PRESTAMOS_API_TAG = "Solicitudes de Préstamos";
	public static final String PRESTAMOS_API_DESC = "Endpoint para la gestión de solicitudes de préstamos personales";
	
	public static final String CREAR_SOLICITUD_DESC = "Crear una solicitud de préstamos personal";
	public static final String CREAR_SOLICITUD_OP_ID = "crearSolicitudPrestamo";
	public static final String CREAR_SOLICITUD_201 = "Solicitud creada correctamente";
	public static final String CREAR_SOLICITUD_400 = "Datos de entrada incorrectos";
	public static final String SOLICITUD_500 = "Error interno del servidor";
	
	public static final String GET_ID_SOLICITUD_DESC = "Obtener una solicitud de préstamos personal";
	public static final String GET_ID_SOLICITUD_OP_ID = "getSolicitudPretamoPorId";
	public static final String GET_ID_SOLICITUD_200 = "Solicitud encontrada";
	public static final String GET_ID_SOLICITUD_404 = "Solicitud no encontrada";
	
	public static final String GET_ALL_SOLICITUD_DESC = "Obtener todas las solicitudes de préstamos personal";
	public static final String GET_ALL_SOLICITUD_OP_ID = "getAllSolicitudPretamo";
	public static final String GET_ALL_SOLICITUD_200 = "Listado de solicitudes";
	
	public static final String UPDATE_ESTADO_SOLICITUD_DESC = "Modificar el estado de una solicitud de préstamos personal";
	public static final String UPDATE_ESTADO_SOLICITUD_OP_ID = "modificarEstadoSolicitud";
	public static final String UPDATE_ESTADO_SOLICITUD_200 = "Estado actuaizado correctamente";
	public static final String UPDATE_ESTADO_SOLICITUD_400 = "Estado incorrecto";
	public static final String UPDATE_ESTADO_SOLICITUD_404 = "Solicitud no encontrada";
}
