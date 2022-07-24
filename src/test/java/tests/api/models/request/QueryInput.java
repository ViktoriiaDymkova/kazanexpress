package tests.api.models.request;

import java.util.List;
import lombok.Data;

@Data
public class QueryInput{
	private Pagination pagination;
	private String showAdultContent;
	private String text;
	private List<Object> filters;
	private String sort;
}