package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FilterValue{
	private Object image;

	@JsonProperty("__typename")
	private String typename;

	private String name;
	private Object description;
	private Integer id;
}