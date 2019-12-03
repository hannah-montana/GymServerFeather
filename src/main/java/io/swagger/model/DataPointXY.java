package io.swagger.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Validated
@Document(collection = "DataPoint")
public class DataPointXY {

}
