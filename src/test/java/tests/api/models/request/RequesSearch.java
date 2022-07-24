package tests.api.models.request;

import lombok.Data;

@Data
public class RequesSearch{
	private Variables variables;
	private String query;
	private String operationName;
}