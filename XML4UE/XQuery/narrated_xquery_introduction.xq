xquery version "3.1";

declare namespace ext="http://www.altova.com/xslt-extensions";


(:
for $i in 1 to 5
return $i
:)

(:
for $i in 1 to 5
let $j := $i + 1
return $j
:)

(:
for $i in 1 to 5
let $j := $i + 1
where $j mod 2 = 0
return $j
:)

(:
for $i in 1 to 5
order by $i descending
return $i
:)

(:
for $i in 1 to 5
order by $i descending
return if ($i mod 2 = 0) then
		"even"
	else
		"odd"
:)

(:
<numbers>{
for $i in 1 to 5
return <number>{$i}</number>
}</numbers>
:)

(:
for $i in 1 to 5
return <numbers max="{$i}">{
	for $j in 1 to $i
	return <number>{$j}</number>
}</numbers>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return $e
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la>{$e}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry/*
return <la>{$e}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la>{$e/*}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la>{$e/@id}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la>{$e/@id, $e/*[1]}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la>{$e/@id, data($e/*[1])}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la attr1 = "{$e/*[1]}">{$e/@id, data($e/*[1])}</la>
:)

(:
let $doc1 := doc("doc1.xml")
for $e in $doc1//entry
return <la>{$e/(* except value2)}</la>
:)

(:
let $doc1 := doc("doc1.xml")
let $doc2 := doc("doc2.xml")
for $e1 in $doc1//entry
for $e2 in $doc2//entry
where $e1/@id = $e2/@id
return <unionEntry>
{
	$e1, $e2
}
</unionEntry>
:)

(:
let $doc2 := doc("doc2.xml")
for $id in distinct-values($doc2//entry/@id)
let $entries := $doc2//entry[@id = $id]
return <group id="{$id}">
{
	$entries
}
</group>
:)

(:
let $doc2 := doc("doc2.xml")
for $e in $doc2//entry
let $id := $e/@id
group by $id
return <group id="{$id}">
{
	$e
}
</group>
:)

(:
for $i in 1 to 5
return element {concat("a", $i)} {
attribute {concat("attr", $i)} {"sdf"},
$i
}
:)
