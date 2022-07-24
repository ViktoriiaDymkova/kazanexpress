package tests.api.models.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryTreeItem{
	private Integer total;

	@JsonProperty("__typename")
	private String typename;
	private Category category;
}