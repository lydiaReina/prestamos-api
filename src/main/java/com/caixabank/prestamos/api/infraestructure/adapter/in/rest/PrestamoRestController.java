package com.caixabank.prestamos.api.infraestructure.adapter.in.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caixabank.prestamos.api.application.service.PrestamoService;
import com.caixabank.prestamos.api.common.MensajesCommon;
import com.caixabank.prestamos.api.common.MensajesSwagger;
import com.caixabank.prestamos.api.domain.model.EstadoPrestamoEnum;
import com.caixabank.prestamos.api.domain.model.SolicitudPrestamo;
import com.caixabank.prestamos.api.infraestructure.config.i18n.InternationalizationHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * API para la gestión de préstamos personales
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/prestamos", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = MensajesSwagger.PRESTAMOS_API_TAG, description = MensajesSwagger.PRESTAMOS_API_DESC)
public class PrestamoRestController {

	private final PrestamoService service;

	/**
	 * Crear Solicitud de préstamo personal
	 * 
	 * @param solicitud {@link SolicitudPrestamo}
	 * @return {@link ResponseEntity} de {@link SolicitudPrestamo}
	 */
	@Operation(summary = MensajesSwagger.CREAR_SOLICITUD_DESC, operationId = MensajesSwagger.CREAR_SOLICITUD_OP_ID)
	@Tag(name = MensajesSwagger.PRESTAMOS_API_TAG)
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "201", description = MensajesSwagger.CREAR_SOLICITUD_201), //
			@ApiResponse(responseCode = "400", description = MensajesSwagger.CREAR_SOLICITUD_400), //
			@ApiResponse(responseCode = "500", description = MensajesSwagger.SOLICITUD_500) }) //
	@PostMapping()
	public ResponseEntity<SolicitudPrestamo> crearSolicitudPrestamo(@Valid @RequestBody SolicitudPrestamo solicitud) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.crearSolicitudPrestamo(solicitud));
	}

	/**
	 * Obtener Solicitud de préstamos personal por identificador
	 * 
	 * @param id {@link Long}
	 * @return {@link ResponseEntity} de {@link SolicitudPrestamo}
	 */
	@Operation(summary = MensajesSwagger.GET_ID_SOLICITUD_DESC, operationId = MensajesSwagger.GET_ID_SOLICITUD_OP_ID)
	@Tag(name = MensajesSwagger.PRESTAMOS_API_TAG)
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = MensajesSwagger.GET_ID_SOLICITUD_200), //
			@ApiResponse(responseCode = "404", description = MensajesSwagger.GET_ID_SOLICITUD_404), //
			@ApiResponse(responseCode = "500", description = MensajesSwagger.SOLICITUD_500) }) //
	@GetMapping(value = "/{id}")
	public ResponseEntity<SolicitudPrestamo> getSolicitudPretamoPorId(@PathVariable Long id) {
		return Optional.ofNullable(service.getSolicitudPretamoPorId(id)).map(ResponseEntity::ok)
				.orElseThrow(() -> new RuntimeException(InternationalizationHelper
						.getMessage(MensajesCommon.ERROR_SOLICITUD_NO_EXISTENTE, new Object[] { id })));
	}

	/**
	 * Obtiene todas las solicitudes de préstamos personal almacenadas
	 * 
	 * @return {@link ResponseEntity} de {@link List} de {@link SolicitudPrestamo}
	 */
	@Operation(summary = MensajesSwagger.GET_ALL_SOLICITUD_DESC, operationId = MensajesSwagger.GET_ALL_SOLICITUD_OP_ID)
	@Tag(name = MensajesSwagger.PRESTAMOS_API_TAG)
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = MensajesSwagger.GET_ALL_SOLICITUD_200), //
			@ApiResponse(responseCode = "500", description = MensajesSwagger.SOLICITUD_500) }) //
	@GetMapping()
	public ResponseEntity<List<SolicitudPrestamo>> getAllSolicitudPretamo() {
		return ResponseEntity.ok(service.getAllSolicitudPretamo());
	}

	/**
	 * Modificar Estado Solicitud de préstamos personal
	 * 
	 * @param id {@link Long}
	 * @param nuevoEstado {@link EstadoPrestamoEnum} 
	 * @return {@link ResponseEntity} de {@link SolicitudPrestamo}
	 */
	@Operation(summary = MensajesSwagger.UPDATE_ESTADO_SOLICITUD_DESC, operationId = MensajesSwagger.UPDATE_ESTADO_SOLICITUD_OP_ID)
	@Tag(name = MensajesSwagger.PRESTAMOS_API_TAG)
	@ApiResponses(value = { //
			@ApiResponse(responseCode = "200", description = MensajesSwagger.UPDATE_ESTADO_SOLICITUD_200), //
			@ApiResponse(responseCode = "400", description = MensajesSwagger.UPDATE_ESTADO_SOLICITUD_400), //
			@ApiResponse(responseCode = "404", description = MensajesSwagger.UPDATE_ESTADO_SOLICITUD_404), //
			@ApiResponse(responseCode = "500", description =  MensajesSwagger.SOLICITUD_500) }) //
	@PatchMapping(value = "/{id}/estado")
	public ResponseEntity<SolicitudPrestamo> modificarEstadoSolicitud(@PathVariable Long id,
			@RequestParam EstadoPrestamoEnum nuevoEstado) {
		return ResponseEntity.ok(service.modificarEstadoSolicitud(id, nuevoEstado));
	}

}
