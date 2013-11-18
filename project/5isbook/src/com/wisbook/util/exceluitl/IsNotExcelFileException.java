package com.wisbook.util.exceluitl;

/**
 *
 * @author willing
 */
public class IsNotExcelFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IsNotExcelFileException() {
	}

	public IsNotExcelFileException(String message) {
		super(message);
	}

	public IsNotExcelFileException(Throwable cause) {
		super(cause);
	}

	public IsNotExcelFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
