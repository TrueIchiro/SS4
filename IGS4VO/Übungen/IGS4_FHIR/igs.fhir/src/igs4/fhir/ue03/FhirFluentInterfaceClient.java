package igs4.fhir.ue03;

import ca.uhn.fhir.rest.annotation.Operation;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.hl7.fhir.instance.model.api.IIdType;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class FhirFluentInterfaceClient {
	/*
	 * Der Context beinhaltet eine Reihe an Fabrikmethoden für das Verarbeiten von
	 * FHIR Nachrichten
	 */
	static FhirContext ctx = FhirContext.forDstu3();

	/**
	 * Die Parser für die Empfangenen Dateien
	 */
	static IParser jsonParser = ctx.newJsonParser();
	static IParser xmlParser = ctx.newXmlParser();

	/**
	 * Patienten Ressource zum Testen
	 * 
	 * @return testpatient
	 */
	private static Patient createPatientFromString() {
		String msgString = "<Patient xmlns=\"http://hl7.org/fhir\"> <id value=\"example\"/> <text> <status value=\"generated\"/> <div xmlns=\"http://www.w3.org/1999/xhtml\"> <table> <tbody> <tr> <td> Name</td> <td> Peter James <b> Chalmers</b> (&quot;Jim&quot;) </td> </tr> <tr> <td> Address</td> <td> 534 Erewhon, Pleasantville, Vic, 3999</td> </tr> <tr> <td> Contacts</td> <td> Home: unknown. Work: (03) 5555 6473</td> </tr> <tr> <td> Id</td> <td> MRN: 12345 (Acme Healthcare)</td> </tr> </tbody> </table> </div> </text> <!-- MRN assigned by ACME healthcare on 6-May 2001 --> <identifier> <use value=\"usual\"/> <type> <coding> <system value=\"http://hl7.org/fhir/v2/0203\"/> <code value=\"MR\"/> </coding> </type> <system value=\"urn:oid:1.2.36.146.595.217.0.1\"/> <value value=\"12345\"/> <period> <start value=\"2001-05-06\"/> </period> <assigner> <display value=\"Acme Healthcare\"/> </assigner> </identifier> <active value=\"true\"/> <!-- Peter James Chalmers, but called \"Jim\" --> <name> <use value=\"official\"/> <family value=\"Chalmers\"/> <given value=\"Peter\"/> <given value=\"James\"/> </name> <name> <use value=\"usual\"/> <given value=\"Jim\"/> </name> <name> <!-- Maiden names apply for anyone whose name changes as a result of marriage - irrespective of gender --> <use value=\"maiden\"/> <family value=\"Windsor\"/> <given value=\"Peter\"/> <given value=\"James\"/> <period> <end value=\"2002\"/> </period> </name> <telecom> <use value=\"home\"/> <!-- home communication details aren't known --> </telecom> <telecom> <system value=\"phone\"/> <value value=\"(03) 5555 6473\"/> <use value=\"work\"/> <rank value=\"1\"/> </telecom> <telecom> <system value=\"phone\"/> <value value=\"(03) 3410 5613\"/> <use value=\"mobile\"/> <rank value=\"2\"/> </telecom> <telecom> <system value=\"phone\"/> <value value=\"(03) 5555 8834\"/> <use value=\"old\"/> <period> <end value=\"2014\"/> </period> </telecom> <!-- use FHIR code system for male / female --> <gender value=\"male\"/> <birthDate value=\"1974-12-25\"> <extension url=\"http://hl7.org/fhir/StructureDefinition/patient-birthTime\"> <valueDateTime value=\"1974-12-25T14:35:45-05:00\"/> </extension> </birthDate> <deceasedBoolean value=\"false\"/> <address> <use value=\"home\"/> <type value=\"both\"/> <text value=\"534 Erewhon St PeasantVille, Rainbow, Vic 3999\"/> <line value=\"534 Erewhon St\"/> <city value=\"PleasantVille\"/> <district value=\"Rainbow\"/> <state value=\"Vic\"/> <postalCode value=\"3999\"/> <period> <start value=\"1974-12-25\"/> </period> </address> <contact> <relationship> <coding> <system value=\"http://hl7.org/fhir/v2/0131\"/> <code value=\"N\"/> </coding> </relationship> <name> <family value=\"du March�\"> <!-- the \"du\" part is a family name prefix (VV in iso 21090) --> <extension url=\"http://hl7.org/fhir/StructureDefinition/humanname-own-prefix\"> <valueString value=\"VV\"/> </extension> </family> <given value=\"B�n�dicte\"/> </name> <telecom> <system value=\"phone\"/> <value value=\"+33 (237) 998327\"/> </telecom> <address> <use value=\"home\"/> <type value=\"both\"/> <line value=\"534 Erewhon St\"/> <city value=\"PleasantVille\"/> <district value=\"Rainbow\"/> <state value=\"Vic\"/> <postalCode value=\"3999\"/> <period> <start value=\"1974-12-25\"/> </period> </address> <gender value=\"female\"/> <period> <!-- The contact relationship started in 2012 --> <start value=\"2012\"/> </period> </contact> </Patient>";
		return xmlParser.parseResource(Patient.class, msgString);
	}

	static String SERVER_URL = "https://fhirtest.uhn.ca/baseDstu3";

	public static void main(String[] args) {
		// 1. create a client
		IGenericClient client = ctx.newRestfulGenericClient(SERVER_URL);

		// 2. create and execute a query
		Bundle response =
				client.search().forResource(Patient.class)
						.where(Patient.IDENTIFIER.exactly()
						.systemAndCode("http://orionhealth.com/mrn", "PRP1660"))
						.returnBundle(Bundle.class).execute();

		String bundleString = xmlParser.encodeResourceToString(response);
		System.out.println(bundleString);

		// 3. search by multiple attributes
		response = client.search().forResource(Patient.class)
				.where(Patient.GENDER.exactly().code("female"))
				.and(Patient.ANIMAL_BREED.isMissing(true))
				.and(Patient.BIRTHDATE.before().now())
				.returnBundle(Bundle.class).execute();

		String bundleString2 = xmlParser.encodeResourceToString(response);
		System.out.println(bundleString2);

		// 4. CRUD Create, Update, Delete
		Patient p = createPatientFromString();
		MethodOutcome outcome =
				client.create().resource(p).
				prettyPrint().encodedJson().execute();

		IIdType id = outcome.getId();
		System.out.println("Our new patient has id: " + id.getValue());

		//update the fuck out of it
		p.setId(id); //crucial for update patient has to have id set
		MethodOutcome updateOutcome = client.update()
				.resource(p).execute();

		id = updateOutcome.getId();
		System.out.println("Our updated patient: " + id.getValue());

		//delete
		IBaseOperationOutcome resp =
				client.delete().resourceById(id).execute();

		//Warning: resp of deleted may be null
		if (resp != null) {
			OperationOutcome deleteOutcome = (OperationOutcome) resp;
			System.out.println(deleteOutcome.getIssueFirstRep().getCode() +
					" " + deleteOutcome.getIssueFirstRep().getDiagnostics());
		} else {
			System.out.println("He's dead, Jim");
		}

	}

}
