package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Category{
	private Parent parent;

	@JsonProperty("__typename")
	private String typename;

	private Object icon;
	private Integer id;
	private Seo seo;
	private String title;
	private Boolean adult;
}