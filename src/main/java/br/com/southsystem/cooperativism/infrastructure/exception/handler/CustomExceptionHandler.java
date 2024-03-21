package br.com.southsystem.cooperativism.infrastructure.exception.handler;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.southsystem.cooperativism.application.response.dto.ErrorDTO;
import br.com.southsystem.cooperativism.common.exception.CooperativismBusinessException;
import br.com.southsystem.cooperativism.infrastructure.exception.error.DocumentErrors;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Autowired
	private MessageSourceHandler messageHandler;

	private final Map<Class<?>, String> errorCodeMap = new HashMap<>();

	/**
     * Setup inicial.
     */
    @jakarta.annotation.PostConstruct
    public void setup() {
    	this.errorCodeMap.put(Pattern.class, DocumentErrors.ERRO_400003);
        this.errorCodeMap.put(Min.class, DocumentErrors.ERRO_400001);
        this.errorCodeMap.put(Max.class, DocumentErrors.ERRO_400001);
        this.errorCodeMap.put(NotNull.class, DocumentErrors.ERRO_400001);
        this.errorCodeMap.put(NotBlank.class, DocumentErrors.ERRO_400001);
        this.errorCodeMap.put(Digits.class, DocumentErrors.ERRO_400001);
        this.errorCodeMap.put(Size.class, DocumentErrors.ERRO_400001);
        this.errorCodeMap.put(NotEmpty.class, DocumentErrors.ERRO_400001);
    }

	/**
	 * Retorna uma instancia de {@link ErrorDTO} com a mensagem preenchida a partir
	 * do código HTTP.
	 *
	 * @param httpStatus
	 * @return {@link ErrorDTO}
	 */
	private ErrorDTO getError(HttpStatus httpStatus) {
		ErrorDTO error = new ErrorDTO();
		error.setCode(Integer.toString(httpStatus.value()));
		error.setDescription(this.messageHandler.getMessage(httpStatus));
		return error;
	}

	/**
	 * {@code Handler} para direcionar quando ocorrer um erro o qual não exista um
	 * gerenciador de erros relacionado. Neste caso o erro retornado será
	 * <b>500</b>.
	 *
	 * @param nrfe
	 * @see NoHandlerFoundException
	 * @return {@link ErrorDTO}
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO noHandlerFound(NoHandlerFoundException nrfe) {
		LOGGER.error("NoHandlerFoundException", nrfe);
		return this.getError(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * <b>500</b></br>
	 * {@code Handler} para retornar o resultado de erros para serviços não
	 * encontrados. {@link Exception}.
	 *
	 * @param e
	 * @see Exception
	 * @return {@link ResponseEntity} Not Found
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handleException(Exception e) {
		LOGGER.error("Exception ", e);
		return this.getError(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * <b>400</b></br>
	 * {@code Handler} para retornar o erros de BAD_REQUEST. {@link Exception}.
	 *
	 * @param e
	 * @see Exception
	 * @return {@link ResponseEntity} Not Found
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleException(HttpMessageNotReadableException e) {

		JsonMappingException jme = (JsonMappingException) e.getCause();
		Field campoErro = this.getField(jme.getPath().get(0).getFrom(), jme.getPath().get(0).getFieldName());

		String description = this.messageHandler.getMessage("invalidFormat", campoErro.getName(),
				campoErro.getType().getSimpleName());

		return ErrorDTO.of(DocumentErrors.ERRO_400001, description);
	}

	private Field getField(Object ob, String obName) {

		List<Field> fieldList = Arrays.asList(ob.getClass().getDeclaredFields());

		return fieldList.stream().filter(e -> obName.equals(e.getName())).findFirst().orElse(null);
	}

	/**
	 * <b>400</b></br>
	 * {@code Handler} para retornar o resultado de erros para informações do
	 * {@code header} que são obrigatorias.
	 *
	 * @param ex
	 * @see MissingRequestHeaderException
	 * @return {@link ResponseEntity} Unauthorized
	 */
	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorDTO> handleException(MissingRequestHeaderException mrhe) {
		String description = this.messageHandler.getMessage("MissingHeader", mrhe.getHeaderName());
		return Arrays.asList(ErrorDTO.of(DocumentErrors.ERRO_400001, description));
	}

	/**
	 * <b>400</b></br>
	 * {@code Handler} para preparar o retorno para requisições que possuam
	 * preenchimento incorreto.
	 *
	 * @param matme
	 * @see MethodArgumentTypeMismatchException
	 * @return {@link ErrorDTO}
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorDTO> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException matme) {
		String dMessage = String.join(".", matme.getErrorCode(), matme.getName());
		String description = this.messageHandler.getMessage(dMessage, matme.getValue());
		return Arrays.asList(ErrorDTO.of(DocumentErrors.ERRO_400001, description));
	}
	
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorDTO> handleMethodArgumentTypeMismatch(NumberFormatException ex) {
		String dMessage = String.join(ex.getMessage());
		String description = this.messageHandler.getMessage(dMessage, ex);
		return Arrays.asList(ErrorDTO.of(DocumentErrors.ERRO_400001, description));
	}

	/**
	 * <b>400</b></br>
	 * {@code Handler} para preparar o retorno para requisições que possuam
	 * preenchimento incorreto.
	 *
	 * @param e
	 * @see BindException
	 * @return {@link ErrorDTO}
	 */
	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorDTO> handleBindException(BindException be) {
		return this.getError(be.getFieldErrors());
	}

	/**
	 * Recupera a lista de erros a partir de um {@link FieldError}.
	 *
	 * @param erros
	 * @return {@link ErrorDTO}
	 */
	private List<ErrorDTO> getError(List<? extends ObjectError> errors) {
		if (CollectionUtils.isEmpty(errors)) {
			return Collections.emptyList();
		}

		return errors.stream().map(t -> ErrorDTO.of(DocumentErrors.ERRO_400001,this.messageHandler.getMessage(t)
				.equals(t.getDefaultMessage())
				? t.getDefaultMessage()
				: this.messageHandler.getMessage(t) + " - " +
				t.getDefaultMessage())).collect(Collectors.toList());
	}

	/**
	 * <b>400</b></br>
	 * {@code Handler} para preparar o retorno para requisições que possuam
	 * preenchimento incorreto.
	 *
	 * @param cve
	 * @see ConstraintViolationException
	 * @return {@link ErrorDTO}
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorDTO> handleConstraintViolationException(ConstraintViolationException cve) {
		Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();

		if (CollectionUtils.isEmpty(violations)) {
			return Collections.emptyList();
		}

		return violations.stream().map(t -> ErrorDTO.of(this.getErrorCode(t), t.getMessage()))
				.collect(Collectors.toList());
	}

	/**
	 * Retorna o código do erro a partir de um {@link ConstraintViolation}.
	 *
	 * @param violation
	 * @return errorCode
	 */
	private String getErrorCode(ConstraintViolation<?> violation) {
		return this.errorCodeMap.get(violation.getConstraintDescriptor().getAnnotation().annotationType());
	}
	
	/**
	 * <b>405</b></br>
	 * {@code Handler} para preparar o retorno quando há a tentativa de acessar um
	 * recurso não permitido.
	 *
	 * @param ex
	 * @see HttpRequestMethodNotSupportedException
	 * @return {@link ResponseEntity} Method Not Allowed
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorDTO handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		return this.getError(HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * <b>400</b></br>
	 * {@code Handler} para preparar o retorno para requisições que não tenham
	 * atendido as pré condições de processamento.
	 *
	 * @param manve
	 * @see MethodArgumentNotValidException
	 * @return {@link ErrorDTO}
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException manve) {
		BindingResult resultado = manve.getBindingResult();
		return this.getError(resultado.getAllErrors());
	}

	/**
	 * <b>404</b></br>
	 * {@code Handler} para preparar o retorno para requisições que não tenham
	 * atendido as pré condições de processamento.
	 *
	 * @param mbe
	 * @see CooperativismBusinessException
	 * @return {@link ErrorDTO}
	 */
	@ExceptionHandler(CooperativismBusinessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO documentNotFoundException(CooperativismBusinessException mbe) {
		return ErrorDTO.of(mbe.getErrorCode(), this.messageHandler.getMessage(mbe.getErrorCode()));
	}
		
    /**
     * <b>400</b></br>
     * {@code Handler} para preparar o retorno para requisições que
     * não tenham atendido as pré condições de processamento.
     *
     * @param msrpe
     * @see MethodArgumentNotValidException
     * @return {@link ErrorDTO}
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentEmpty(MissingServletRequestParameterException msrpe) {
    	LOGGER.error("Parâmetro obrigatório da URL não informado: " + msrpe.getParameterName(), msrpe);
    	return ErrorDTO.of(DocumentErrors.ERRO_400001, "Parâmetro obrigatório da URL não informado: " + msrpe.getParameterName());
    }
}
