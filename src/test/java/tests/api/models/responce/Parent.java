package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Parent{
	@JsonProperty("__typename")
	private String typename;

	private Integer id;
}