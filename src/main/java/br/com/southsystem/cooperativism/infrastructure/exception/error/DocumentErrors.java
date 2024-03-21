package br.com.southsystem.cooperativism.infrastructure.exception.error;

public class DocumentErrors {

	private DocumentErrors() {
		throw new IllegalStateException("Utility class");
	}

	/* Erro relacionado ao padrao esperado para um campo */
	public static final String ERRO_400001 = "400.001";
	
	/* Erro relacionado ao padrao esperado. Os dois campos file e name não podem ambos serem vazios. */
	public static final String ERRO_400002 = "400.002";
	
	/* Erro relacionado ao padrao esperado para um campo - @Pattern */
	public static final String ERRO_400003 = "400.003";
		
	/* Erros Precondition Failed */
	public static final String ERRO_412001 = "412.001";

	/* Não foi localizada a Pauta informada. */
	public static final String ERRO_422001 = "422.001";
	
	/* Não foi localizada nenhuma sessao pela pauta informada. */
	public static final String ERRO_422002 = "422.002";
	
	/* Não foi localizada o associado informado. */
	public static final String ERRO_422003 = "422.003";
	
	/* Não foi localizada a sessão informada. */
	public static final String ERRO_422004 = "422.004";
	
	/* Esta sessão se encontra expirada. Abra outra sessão. */
	public static final String ERRO_422005 = "422.005";
	
	/* O associado informado já votou nessa Pauta. */
	public static final String ERRO_422006 = "422.006";
}
