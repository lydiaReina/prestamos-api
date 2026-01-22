package com.caixabank.prestamos.api.application.port.out;

import java.util.List;
import java.util.Optional;

import com.caixabank.prestamos.api.domain.model.SolicitudPrestamo;

/**
 * Interfaz contrato para la gestión de solicitudes de préstamos personales
 */
public interface PrestamoRepositoryPort {
	
	/**
	 * Crear una solicitud de prestamo
	 * 
	 * @param solicitudPrestamo {@link SolicitudPrestamo}
	 * 
	 * @return {@link SolicitudPrestamo}
	 */
	public SolicitudPrestamo crearSolicitud(SolicitudPrestamo solicitudPrestamo);
	
	/**
	 * Obtener una solicitud de prestamos por identificador
	 * 
	 * @param id {@link Long}
	 * 
	 * @return {@link Optional} de {@link SolicitudPrestamo}
	 */
	public Optional<SolicitudPrestamo> getSolicitudPretamoPorId(Long id);
	
	/**
	 * Obtener todas las solicitudes de prestamo
	 * 
	 * @return {@link List} de {@link SolicitudPrestamo}
	 */
	public List<SolicitudPrestamo> getAllSolicitudPretamo();
	
}
