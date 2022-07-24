package tests.api.models.responce;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FacetsItem{
	private Filter filter;
	private Object buckets;

	@JsonProperty("__typename")
	private String typename;

	private Range range;
}