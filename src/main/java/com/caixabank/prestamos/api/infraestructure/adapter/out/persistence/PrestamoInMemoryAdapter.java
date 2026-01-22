package com.caixabank.prestamos.api.infraestructure.adapter.out.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.caixabank.prestamos.api.application.port.out.PrestamoRepositoryPort;
import com.caixabank.prestamos.api.domain.model.SolicitudPrestamo;

/**
 * Clase repositorio temporal de solicitudes de préstamos personales
 */
@Repository
public class PrestamoInMemoryAdapter implements PrestamoRepositoryPort {
	
	private final Map<Long, SolicitudPrestamo> datosAlmacenados = new HashMap<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SolicitudPrestamo crearSolicitud(SolicitudPrestamo solicitudPrestamo) {
		datosAlmacenados.put(solicitudPrestamo.getId(), solicitudPrestamo);
		return solicitudPrestamo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<SolicitudPrestamo> getSolicitudPretamoPorId(Long id) {
		return Optional.ofNullable(datosAlmacenados.get(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SolicitudPrestamo> getAllSolicitudPretamo() {
		return new ArrayList<>(datosAlmacenados.values());
	}
	
}
