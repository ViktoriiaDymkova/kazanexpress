package tests.api.models.request;

import lombok.Data;

@Data
public class Pagination{
	private Integer offset;
	private Integer limit;
}