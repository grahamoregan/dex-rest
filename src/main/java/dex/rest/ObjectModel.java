package dex.rest;

import java.util.List;
import java.util.Map;

import org.apache.commons.jxpath.JXPathContext;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * This class executes XPath against a Map<String,Object> using JXPath to
 * evaluate and execute the query. It should be instantiated using the create()
 * static factory method and would normally be called by the {@link ObjectModelConvertor} class
 * which could create the Map from a JSON response.
 * 
 * TODO add the functions from
 * http://www.w3schools.com/xpath/xpath_functions.asp
 * 
 * This should probably be an interface so it could be subclassed for XML and
 * JSON. The XPath queries should still work, they'd just need to be converted
 * to request types differently.
 */
public class ObjectModel {

	private Map<String, Object> map = Maps.newHashMap();
	private List<Object> list = Lists.newArrayList();

	private JXPathContext context;

	public static final ObjectModel create(Map<String, Object> map) {

		ObjectModel model = new ObjectModel();
		model.map = map;
		model.context = JXPathContext.newContext(model.map);

		return model;

	}

	public static final ObjectModel create(List<Object> list) {

		ObjectModel model = new ObjectModel();
		model.list = list;
		model.context = JXPathContext.newContext(model.list);

		return model;

	}

	public Map<String, Object> getMap() {
		return map;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String expr) {
		return (T) context.getValue(expr);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String operator, String operand1, String operand2) {
		return (T) get(operand1 + " " + operator + " " + operand2);
	}

	public Double count(String expr) {
		return get("count(" + expr + ")");
	}

	public Double add(String operand1, String operand2) {
		return get("+", operand1, operand2);
	}

	public Double subtract(String operand1, String operand2) {
		return get("-", operand1, operand2);
	}

	public Double multiply(String operand1, String operand2) {
		return get("*", operand1, operand2);
	}

	public Double divide(String operand1, String operand2) {
		return get("div", operand1, operand2);
	}

	public Boolean equals(String operand1, String operand2) {
		return get("=", operand1, operand2);
	}

	public Boolean notEquals(String operand1, String operand2) {
		return get("!=", operand1, operand2);
	}

	public Boolean lessThan(String operand1, String operand2) {
		return get("<", operand1, operand2);
	}

	public Boolean greaterThan(String operand1, String operand2) {
		return get(">", operand1, operand2);
	}

	public Boolean lessThanOrEqualTo(String operand1, String operand2) {
		return get("<=", operand1, operand2);
	}

	public Boolean greaterThanOrEqualTo(String operand1, String operand2) {
		return get(">=", operand1, operand2);
	}

	public Boolean or(String expr, Object value1, Object value2) {
		return get(expr + "=" + value1.toString() + " or " + expr + "=" + value1.toString());
	}

	public Boolean and(String expr, Object value1, Object value2) {
		return get(expr + "=" + value1.toString() + " and " + expr + "=" + value2.toString());
	}

	public Double mod(String operand1, String operand2) {
		return get("mod", operand1, operand2);
	}

}
