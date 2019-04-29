<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	
<!-- root element -->
<xsl:template match="/">
	<xsl:element name="Einträge">
		<xsl:apply-templates/>
	</xsl:element>
</xsl:template>	

<!-- template Schlagwort -->
<xsl:template match="DescriptorRecord">
	<xsl:element name="Schlagwort">
		<xsl:attribute name="ID" select="DescriptorUI"/>
		<xsl:attribute name="Erstellungsdatum" select="fn:concat(DateCreated/Year, '-', DateCreated/Month, '-', DateCreated/Day)"/>
		<xsl:attribute name="Überarbeitungsdatum" select="fn:concat(DateRevised/Year, '-', DateRevised/Month, '-', DateRevised/Day)"/>
		<xsl:apply-templates select="DescriptorName"/>
		<xsl:apply-templates select="AllowableQualifiersList"/>
		<xsl:apply-templates select="TreeNumberList"/>
		<xsl:apply-templates select="ConceptList"/>
	</xsl:element>
</xsl:template>
	
<!-- template Name -->
<xsl:template match="DescriptorName">
	<xsl:element name="Name">
		<xsl:value-of select="."/>
	</xsl:element>
</xsl:template>	

<!-- template Einschränkung -->
<xsl:template match="AllowableQualifiersList">
	<xsl:element name="NähereBestimmung">
		<xsl:for-each select="AllowableQualifier">
			<xsl:sort select="Abbreviation"/>
				<xsl:element name="Einschränkung">
					<xsl:value-of select="QualifierReferredTo/QualifierName"/>
				<!-- I don't know why the Einschränkung-tag looks to ugly in the output :( -->
				</xsl:element>
		</xsl:for-each>
	</xsl:element>
</xsl:template>

<!-- template Hierachie -->
<xsl:template match="TreeNumberList">
	<xsl:element name="Hierachie">
		<xsl:element name="Position">
			<xsl:value-of select="TreeNumber"/>
		</xsl:element>
	</xsl:element>
</xsl:template>

<!-- template Konzepte -->
<xsl:template match="ConceptList">
	<xsl:choose>
		<xsl:when test="Concept[@PreferredConceptYN = 'Y']">
			<xsl:element name="BevorzugtesKonzept">
				<xsl:attribute name="ID" select="Concept/ConceptUI"/>
				<xsl:call-template name="ConceptName"/>
			</xsl:element>
		</xsl:when>
		<xsl:otherwise>
			<xsl:element name="Konzept">
				<xsl:attribute name="ID" select="Concept/ConceptUI"/>
				<xsl:call-template name="ConceptName"/>
			</xsl:element>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- template Konzepte/Name -->
<xsl:template name="ConceptName">
	<xsl:element name="Name">
		<xsl:value-of select="./ConceptName"/>
	</xsl:element>
</xsl:template>
	
</xsl:stylesheet>
