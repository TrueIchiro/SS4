xquery version "3.1";

declare namespace ext="http://www.altova.com/xslt-extensions";
declare option output:item-separator "&#xa;";

(: 5.1.1 :)
(:
for $doc in doc( "desc2015_reduced.xml" )/DescriptorRecordSet/DescriptorRecord
where $doc/DateCreated/Year = 1974
return $doc/DescriptorName
:)

(: 5.1.2 :)
(:
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
order by $i/DateCreated/Year
return $i/DescriptorName
:)

(: 5.1.3 :)
(:
<Symptoms> {
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
where $i/ConceptList/Concept/SemanticTypeList/SemanticType/SemanticTypeName = 'Sign or Symptom'
order by $i/DateCreated/Year
return 
$i/DescriptorName
}
</Symptoms>
:)

(: 5.1.4 :)
(:
<Symptoms> {
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
where $i/ConceptList/Concept/SemanticTypeList/SemanticType/SemanticTypeName = 'Sign or Symptom'
order by $i/DateCreated/Year
return 
<Symptom> {
data($i/DescriptorName)
}
</Symptom>
}
</Symptoms>
:)

(: 5.1.5 :)
(:
<Tree> {
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
for $j in $i/TreeNumberList/TreeNumber
order by $j
return 
<TreeNode nr="{$i/TreeNumberList/TreeNumber}"> {
data($i/DescriptorName)
}</TreeNode>
} </Tree>
:)

(: 5.1.6 :)
(:
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
return
element {$i/DescriptorUI} {
attribute {"activeSince"} {concat($i/DateCreated/Year, '-', $i/DateCreated/Month, '-', $i/DateCreated/Day)},
attribute {fn:node-name($i/@DescriptorClass)} {$i/@DescriptorClass},
data($i/DescriptorName)
}
:)

(: 5.1.7 :)
(:
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
return
element {$i/DescriptorUI} {
attribute {fn:node-name($i/@DescriptorClass)} {$i/@DescriptorClass},
$i/*except $i/DescriptorUI
}
:)

(: 5.1.8 :)
(:
for $i in doc("desc2015_reduced.xml")/DescriptorRecordSet/DescriptorRecord
return
element {"Descriptor"} {
attribute {"pre2K"} {
if ($i/DateCreated/Year < 2000) then
'Y'
else
'N'
},
data($i/DescriptorName)
}
:)