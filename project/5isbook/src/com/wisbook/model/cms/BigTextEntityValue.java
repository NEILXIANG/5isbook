package com.wisbook.model.cms;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="big_text_entity_value")
public class BigTextEntityValue extends EntityValue{

	private String content;
	
	@Override
	@Column(name="content",columnDefinition="text")
	public String getContent() {
		return this.content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

}
