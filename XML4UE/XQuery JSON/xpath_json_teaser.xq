xquery version "3.1";

declare namespace ext="http://www.altova.com/xslt-extensions";
declare namespace xf="http://www.w3.org/2005/xpath-functions";


(: 
###########################################################################################
## XML meets JSON                                                                        ##
## a short teaser on using XML technologies for working with JSON                        ##
## see http://www.w3.org/TR/xpath-functions-31/#json-functions                           ##
## JSON can be represented in XDM using maps and arrays                                  ##
###########################################################################################
:)


(: reading JSON
parse-json: parses the supplied JSON string and returns it in the form of a map or array
json-doc: read external JSON resource
json-to-xml: parse supplied JSON string and return it in form of a XML document node
:)

(: parse-json and json-doc :)

fn:parse-json('{"numbers": [1, 2, 3], "letters": ["A", "B", "C"]}')("numbers")(2)


(:
fn:json-doc("test.json")("contacts")(1)("contact")("person")("firstName")
:)

(: use lookup operator >?< to conveniently navigate JSON :)
(:
fn:json-doc("test.json")?contacts?*?contact?person?firstName
:)

(: json-to-xml :)
(:
fn:json-to-xml('{"numbers": [1, 2, 3], "letters": ["A", "B", "C"]}')
:)

(:
fn:json-to-xml(fn:unparsed-text("test.json"))
:)

(:
for $x in fn:json-to-xml(fn:unparsed-text("test.json"))
return data($x//xf:array[@key="contacts"]/xf:map/xf:map[@key = "contact"]/xf:map[@key = "person"]/xf:string[@key = "firstName"])
:)