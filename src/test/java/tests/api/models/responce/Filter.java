package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Filter{
	@JsonProperty("__typename")
	private String typename;

	private Object description;
	private Integer id;
	private String title;
	private String type;
	private String measurementUnit;
}