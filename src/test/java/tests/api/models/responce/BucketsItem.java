package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BucketsItem{
	private Integer total;
	private FilterValue filterValue;

	@JsonProperty("__typename")
	private String typename;
}