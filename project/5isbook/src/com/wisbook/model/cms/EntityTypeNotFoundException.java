package com.wisbook.model.cms;
/**
 * 无法找到entity下面的某个entitytype
 * @author volador
 *
 */
public class EntityTypeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EntityTypeNotFoundException(String message) {
		super(message);
	}
}
