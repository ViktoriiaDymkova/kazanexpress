package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Range{
	private Integer min;
	private Integer max;

	@JsonProperty("__typename")
	private String typename;
}