package br.com.southsystem.cooperativism.application.response.dto;

import java.io.Serializable;

/**
 * Classe ErrorDTO.
 *
 */
public final class ErrorDTO implements Serializable {

	private static final long serialVersionUID = -990745646226030815L;
	private String code;
	private String description;

	/**
	 * Construtor padrão.
	 */
	public ErrorDTO() {
	}

	/**
	 * Construtor padrão.
	 */
	public ErrorDTO(String code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * Construtor padrão.
	 *
	 * @param code
	 * @param description
	 * @return {@link ErrorDTO}
	 */
	public static ErrorDTO of(String code, String description) {
		return new ErrorDTO(code, description);
	}

	/**
	 * Error code.
	 *
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Error full description.
	 *
	 * @return description
	 **/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ErrorDTO other = (ErrorDTO) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorDTO [code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
