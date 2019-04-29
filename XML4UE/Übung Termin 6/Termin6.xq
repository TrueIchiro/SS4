xquery version "3.1";

declare namespace ext="http://www.altova.com/xslt-extensions";

(:
for $doc in doc( "genes.xml" )
let $i := $doc/genes/gene
for $j in $i
order by $j/@gi
return $j/symbol
:)

(:
<reviewedGenes> {
for $i in //genes/gene
where $i[@refSeqStatus = "reviewed"]
return $i/symbol
}
</reviewedGenes>
:)

(:
<root>
{
for $i in //genes/gene
return <geneSummary>{$i/summary}</geneSummary>
}
</root>
:)
