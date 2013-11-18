package com.wisbook.model.cms;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="text_entity_value")
public class TextEntityValue extends EntityValue{

	private String content;
	
	@Override
	@Column(name="content",length=255)
	public String getContent() {
		return this.content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

}
