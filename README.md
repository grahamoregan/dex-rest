h1. RestTest

This library provides a very simple wrapper around JSON content. Provide it with a java.util.Reader and it will
convert the content to an object graph, store the results in memory and allow you to perform XPath queries against it.

It's primary purpose is for testing or monitoring values from JSON API endpoints but it could be used instead of 
unmarshalling the data into domain-specific objects.

The library uses Jackson to create an object graph from JSON content and JXPath to evaluate the XPath expressions.

e.g. given the following JSON;

{ "list":["a","b","c"] }

The following test passes;

assertEquals(Double.valueOf(3), model.count("/list"));

h1.

# Support XML too 
