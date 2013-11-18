package com.wisbook.util.exceluitl;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author willing
 */
public class NotEnoughColumnsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Set<String> missingColumnNameSet;

	public NotEnoughColumnsException(Set<String> missingColumnNameSet) {
		this.missingColumnNameSet =
				Collections.unmodifiableSet(missingColumnNameSet);
	}

	public Set<String> getMissingColumnNameSet() {
		return missingColumnNameSet;
	}

	@Override
	public String getMessage() {
		return "缺少列 " + missingColumnNameSet;
	}
}
