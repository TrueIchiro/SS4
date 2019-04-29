xquery version "3.1";

declare namespace ext="http://www.altova.com/xslt-extensions";

(: 5.2.1 :)
(:
let $i := fn:json-doc("contacts.json")
return $i?contactList?contacts?*?id
:)

(: 5.2.2 :)
for $i in fn:json-doc("contacts.json")?contactList?contacts?*
return 
<xs:string> {
(concat($i?contact?person?salutation, '. ', $i?contact?person?firstName, ' ', $i?contact?person?lastName, ';'))
}
</xs:string>