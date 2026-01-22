package com.caixabank.prestamos.api.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.caixabank.prestamos.api.application.port.out.PrestamoRepositoryPort;
import com.caixabank.prestamos.api.common.MensajesCommon;
import com.caixabank.prestamos.api.domain.model.EstadoPrestamoEnum;
import com.caixabank.prestamos.api.domain.model.SolicitudPrestamo;
import com.caixabank.prestamos.api.infraestructure.config.i18n.InternationalizationHelper;

import lombok.RequiredArgsConstructor;

/**
 * Clase de servicio con la lógica de gestión de solicitudes de préstamos personales
 */
@Service
@RequiredArgsConstructor
public class PrestamoService {

	private final PrestamoRepositoryPort repository;

	/**
	 * Crear una nueva solicitus de prestamo
	 * 
	 * @param solicitudPrestamoNueva {@link SolicitudPrestamo}
	 * @return {@link SolicitudPrestamo}
	 */
	public SolicitudPrestamo crearSolicitudPrestamo(SolicitudPrestamo solicitudPrestamoNueva) {

		// Comprobar si existe la solicitud
		repository.getSolicitudPretamoPorId(solicitudPrestamoNueva.getId()).ifPresent(solicitud -> {
			throw new IllegalArgumentException(InternationalizationHelper.getMessage(
					MensajesCommon.ERROR_SOLICITUD_EXISTENTE, new Object[] { solicitudPrestamoNueva.getId() }));
		});

		// Inicializar solicitud
		solicitudPrestamoNueva.inicializarSolicitudPrestamo();

		// Guardar solicitud
		return repository.crearSolicitud(solicitudPrestamoNueva);
	}

	/**
	 * Obtener una solicitud de prestamos por identificador
	 * 
	 * @param id {@link id}
	 * @return {@link SolicitudPrestamo}
	 */
	public SolicitudPrestamo getSolicitudPretamoPorId(Long id) {
		return repository.getSolicitudPretamoPorId(id)
				.orElseThrow(() -> new RuntimeException(InternationalizationHelper.getMessage(
						MensajesCommon.ERROR_SOLICITUD_NO_EXISTENTE, new Object[] { id })));
	}

	/**
	 * Obtener todas las solicitudes de prestamo
	 * 
	 * @return {@link List} de {@link SolicitudPrestamo}
	 */
	public List<SolicitudPrestamo> getAllSolicitudPretamo() {
		return repository.getAllSolicitudPretamo();
	}
	
	/**
	 * Modificación del estado de la solicitud
	 * 
	 * @param id {@link Long}
	 * @param estadoNuevo {@link EstadoPrestamoEnum}
	 *  
	 * @return{@link SolicitudPrestamo}
	 */
	public SolicitudPrestamo modificarEstadoSolicitud(Long id, EstadoPrestamoEnum estadoNuevo) {
		// Obtener solicitud
		var solicitudPrestamo = getSolicitudPretamoPorId(id);
		
		// Cambiar estado
		solicitudPrestamo.cambioEstado(estadoNuevo);
		
		// Guardar el cambio
		return repository.crearSolicitud(solicitudPrestamo);
	}

}
