<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fhhgb="http://www.fh-hagenberg.at/contacts">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
<!-- Lisa Niedermayr -->
<!-- S1710458025 -->
<!-- finally finished on the 4.4.2019 -->
<!-- task 5.2 -->
<!-- please use it on 'ue04_contacts.xml' -->
<!-- I absolutely despise JSON now :) -->

<!-- template root-->
	<xsl:template match="/">
		<xsl:text>
		{
		</xsl:text>
		<xsl:apply-templates/>
		<xsl:text>
		}</xsl:text>
	</xsl:template>

<!-- template countries -->
	<xsl:template match="fhhgb:countries">
		<xsl:text>"countries": [
		</xsl:text>
		<xsl:apply-templates select="fhhgb:country"/>
		<xsl:text>
		],
		</xsl:text>
	</xsl:template>
	
<!-- template country -->
	<xsl:template match="fhhgb:country">
		<xsl:text>{
		</xsl:text>
	<!-- code -->
		<xsl:text>"code": </xsl:text>
		<xsl:value-of select="concat('&quot;', @code, '&quot;', ',')"/>
	<!-- name -->
		<xsl:text>
		"name": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:name, '&quot;', ',')"/>
	<!-- telephoneCode -->
		<xsl:text>
		"telephoneCode": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:telephoneCode, '&quot;')"/>
		<xsl:text>
		},
		</xsl:text>
	</xsl:template>
	
<!-- template country -->
	<xsl:template match="fhhgb:country[last()]">
		<xsl:text>{
		</xsl:text>
	<!-- code -->
		<xsl:text>"code": </xsl:text>
		<xsl:value-of select="concat('&quot;', @code, '&quot;', ',')"/>
	<!-- name -->
		<xsl:text>
		"name": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:name, '&quot;', ',')"/>
	<!-- telephoneCode -->
		<xsl:text>
		"telephoneCode": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:telephoneCode, '&quot;')"/>
		<xsl:text>
		}
		</xsl:text>
	</xsl:template>
	
<!-- template contacts -->
	<xsl:template match="fhhgb:contacts">
		<xsl:text>"contacts": [
		</xsl:text>
		<xsl:apply-templates select="fhhgb:contactEntry"/>
		<xsl:text>
		]
		</xsl:text>
	</xsl:template>
	
<!-- template contactEntry -->
	<xsl:template match="fhhgb:contactEntry">
	<xsl:text>{
	</xsl:text>
		<xsl:text>"id": </xsl:text>
		<xsl:value-of select="concat('&quot;', @id, '&quot;', ',')"/>
		<xsl:apply-templates select="fhhgb:contact"/>
		<xsl:apply-templates select="fhhgb:internet"/>
		<xsl:apply-templates select="fhhgb:phone"/>
		<xsl:apply-templates select="fhhgb:address"/>
	<xsl:text>},
	</xsl:text>
	</xsl:template>
	
<!-- template contactEntry but for last element -->
	<xsl:template match="fhhgb:contactEntry[last()]">
	<xsl:text>{
	</xsl:text>
		<xsl:text>"id": </xsl:text>
		<xsl:value-of select="concat('&quot;', @id, '&quot;', ',')"/>
		<xsl:apply-templates select="fhhgb:contact"/>
		<xsl:apply-templates select="fhhgb:internet"/>
		<xsl:apply-templates select="fhhgb:phone"/>
		<xsl:apply-templates select="fhhgb:address"/>
	<xsl:text>}
	</xsl:text>
	</xsl:template>

<!-- template contact -->
	<xsl:template match="fhhgb:contact">
		<xsl:text>
		"contact": {
		</xsl:text>
		<xsl:apply-templates select="fhhgb:person"/>
		<xsl:text>"company": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:company, '&quot;')"/>
		<xsl:text>
		},
		</xsl:text>
	</xsl:template>
	
<!-- template person -->
	<xsl:template match="fhhgb:person">
	<xsl:text>"person": {</xsl:text>
	<!-- salutation -->
		<xsl:text>
		"salutation": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:salutation, '&quot;', ',')"/>
	<!-- firstName -->
		<xsl:text>
		"firstName": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:firstName, '&quot;', ',')"/>
	<!-- lastName -->
		<xsl:text>
		"lastName": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:lastName, '&quot;')"/>
	<xsl:text>
	},
	</xsl:text>
	</xsl:template>
	
<!-- template internet -->
	<xsl:template match="fhhgb:internet">
		<xsl:text>"internet": {
		</xsl:text>
		<xsl:apply-templates select="fhhgb:eMail"/>
		<xsl:apply-templates select="fhhgb:website"/>
		<xsl:text>
		},
		</xsl:text>
	</xsl:template>
	
<!-- template eMail -->
	<xsl:template match="fhhgb:eMail">
		<xsl:text>"eMailAdresses": [
		</xsl:text>
		<!-- eMailAdresses go here -->
		<xsl:value-of select="concat('&quot;', ., '&quot;')"/>
		<xsl:text>
		],
		</xsl:text>
	</xsl:template>
	
<!-- template website -->
	<xsl:template match="fhhgb:website">
		<xsl:text>"website": </xsl:text>
		<xsl:value-of select="concat('&quot;', ., '&quot;')"/>
	</xsl:template>
	
<!-- template phone -->
	<xsl:template match="fhhgb:phone">
		<xsl:text>"phones": [
		</xsl:text>
		<xsl:text>{
		</xsl:text>
		<!-- type -->
		<xsl:text>"type": </xsl:text>
		<xsl:value-of select="concat('&quot;', @type, '&quot;', ',')"/>
		<!-- phoneNumber -->
		<xsl:text>
		"phoneNumber": </xsl:text>
		<xsl:value-of select="concat('&quot;', ., '&quot;')"/>
		<xsl:text>
		}</xsl:text>
		<xsl:text>
		],
		</xsl:text>
	</xsl:template>
	
<!-- template address -->
	<xsl:template match="fhhgb:address">
		<xsl:text>"address": {
		</xsl:text>
		<!-- type -->
		<xsl:text>"type": </xsl:text>
		<xsl:value-of select="concat('&quot;', @type, '&quot;', ',')"/>
		<!-- mailingAddress -->
		<xsl:text>
		"mailingAddress": </xsl:text>
		<xsl:value-of select="concat('&quot;', @mailingAddress, '&quot;', ',')"/>
		<!-- street -->
		<xsl:text>
		"street": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:street, '&quot;', ',')"/>
		<!-- place -->
		<xsl:text>
		"place": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:place, '&quot;', ',')"/>
		<!-- state -->
		<xsl:text>
		"state": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:state, '&quot;', ',')"/>
		<!-- zipCode -->
		<xsl:text>
		"zipCode": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:zipCode, '&quot;', ',')"/>
		<!-- countryCode -->
		<xsl:text>
		"countryCode": </xsl:text>
		<xsl:value-of select="concat('&quot;', fhhgb:country/@code, '&quot;')"/>
		<xsl:text>
		}
		</xsl:text>
	</xsl:template>
	
<!-- template for text() -->
	<xsl:template match="text()">
	</xsl:template>
	
</xsl:stylesheet>
