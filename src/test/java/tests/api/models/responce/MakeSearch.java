package tests.api.models.responce;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MakeSearch{
	private Integer total;
	private Boolean mayHaveAdultContent;

	@JsonProperty("__typename")
	private String typename;

	private String queryText;
	private String id;
	private List<CategoryTreeItem> categoryTree;
	private Object category;
	private List<Object> items;
	private String queryId;
	private List<FacetsItem> facets;
}