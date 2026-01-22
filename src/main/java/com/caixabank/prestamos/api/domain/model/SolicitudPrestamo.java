package com.caixabank.prestamos.api.domain.model;

import java.time.LocalDate;

import com.caixabank.prestamos.api.common.MensajesCommon;
import com.caixabank.prestamos.api.common.MensajesSwagger;
import com.caixabank.prestamos.api.infraestructure.config.i18n.InternationalizationHelper;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de dominio que representa una solicitud de préstamo personal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudPrestamo {

	/**
	 * Identificador de la solicitud
	 */
	@Schema(description = MensajesSwagger.IDENTIFICADOR_DESC, example = MensajesSwagger.IDENTIFICADOR_EJEMPLO)
	@NotNull(message = "{error.id.nulo}")
	@JsonProperty("id")
	private Long id;

	/**
	 * Nombre del Solucitante
	 */
	@Schema(description = MensajesSwagger.NOMBRE_SOLICITANTE_DESC, example = MensajesSwagger.NOMBRE_SOLICITANTE_EJEMPLO)
	@NotBlank(message = "{error.nombre.vacio}")
	@Size(min = 1, max = 30, message = "{error.nombre.size}")
	@JsonProperty("nombreSolicitante")
	private String nombreSolicitante;

	/**
	 * Importe Solicitado
	 */
	@Schema(description = MensajesSwagger.IMPORTE_SOLICITADO_DESC, example = MensajesSwagger.IMPORTE_SOLICITADO_EJEMPLO)
	@NotNull(message = "{error.importe.nulo}")
	@Positive(message = "{error.importe.positivo}")
	@JsonProperty("importeSolicitado")
	private Double importeSolicitado;

	/**
	 * Divisa
	 */
	@Schema(description = MensajesSwagger.DIVISA_DESC, example = MensajesSwagger.DIVISA_EJEMPLO)
	@NotBlank(message = "{error.divisa.vacio}")
	@Pattern(regexp = "^[A-Z]{3}$", message = "{error.divisa.formato}")
	@JsonProperty("divisa")
	private String divisa;

	/**
	 * Documento de Identidad
	 */
	@Schema(description = MensajesSwagger.DOCUMENTO_IDENTIDAD_DESC, example = MensajesSwagger.DOCUMENTO_IDENTIDAD_EJEMPLO)
	@NotBlank(message = "{error.documento.identificativo.vacio}")
	@Size(min = 1, max = 9, message = "{error.documento.identificativo.size}")
	@JsonProperty("documentoIdentificativo")
	private String documentoIdentificativo;

	/**
	 * Fecha de Creación
	 */
	@Schema(description = MensajesSwagger.FECHA_CREACION_SOLICITUD_DESC, example = MensajesSwagger.FECHA_CREACION_SOLICITUD_EJEMPLO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate fechaCreacion;

	/**
	 * Estado de la Solicitud
	 */
	@Schema(description = MensajesSwagger.ESTADO_SOLICITUD_DESC, example = MensajesSwagger.ESTADO_SOLICITUD_EJEMPLO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private EstadoPrestamoEnum estadoSolicitud;
	
	/**
	 * Validar Crear solicitud prestamos
	 */
	public void inicializarSolicitudPrestamo() {				
		estadoSolicitud = EstadoPrestamoEnum.PENDIENTE;
		fechaCreacion = LocalDate.now();
	}

	/**
	 * Validar el cambio de estado en la solicitud
	 */
	public void cambioEstado(EstadoPrestamoEnum estadoNuevo) {
		
		var esValido = switch (estadoSolicitud){
			case APROBADA -> estadoNuevo == EstadoPrestamoEnum.CANCELADA;
			case PENDIENTE -> estadoNuevo == EstadoPrestamoEnum.APROBADA || estadoNuevo == EstadoPrestamoEnum.RECHAZADA;
			default -> false;	
		};
		
		if (!esValido) {
			throw new IllegalStateException(InternationalizationHelper.getMessage(
			        MensajesCommon.ERROR_TRANSICION_ESTADO_SOLICITUD, 
			        new Object[] { estadoSolicitud, estadoNuevo }
			    ));
		}
		
		estadoSolicitud = estadoNuevo;		
	}

}
