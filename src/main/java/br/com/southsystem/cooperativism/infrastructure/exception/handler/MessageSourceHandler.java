package br.com.southsystem.cooperativism.infrastructure.exception.handler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Classe CustomExceptionHandler
 *
 */
@Component
public class MessageSourceHandler {

    private MessageSource messageSource;

    /**
     * Recupera a mensagem associada ao erro HTTP.
     *
     * @param status http status
     * @return message
     */
    public String getMessage(HttpStatus status) {
        return this.getMessage(status, (Object[]) null);
    }

    /**
     * Recupera a mensagem associada ao erro HTTP.
     *
     * @param status http status
     * @param args argumentos
     * @return message
     */
    public String getMessage(HttpStatus status, Object... args) {
    	return this.getMessage(String.format("%s.%d", HttpStatus.class.getSimpleName(), status.value()), args);
    }

    /**
     * Recupera a mensagem formatada a partir do codigo de erro de
     * negocio informado.
     *
     * @param code
     * @param args
     * @return message
     */
    public String getMessage(String code, Object... args) {
        return this.messageSource.getMessage(code, args, Locale.getDefault());
    }

    /**
     * Recupera a mensagem formatada a partir do codigo de erro de
     * negocio informado em {@link MessageSourceResolvable}.
     *
     * @param resolvable
     * @return message
     */
    public String getMessage(MessageSourceResolvable resolvable) {
        return this.messageSource.getMessage(resolvable, Locale.getDefault());
    }

    /**
	 * @param messageSource the messageSource to set
	 */
    @Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
