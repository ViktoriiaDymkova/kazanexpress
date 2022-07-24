package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Seo{
	private String metaTag;

	@JsonProperty("__typename")
	private String typename;
	private String header;
}