package igs4.fhir.ue02;

import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.HumanName.NameUse;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.StringType;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;



public class Serialization {

	public static void main(String[] args) {
		// TODO: Implement me

		FhirContext ctx = FhirContext.forDstu3();

		Patient patient = new Patient();

		//add MRN (a patientIdentifier)
		Identifier identifier = patient.addIdentifier();
		identifier.setSystem("http://example.com/fictitious-mrns");
		identifier.setValue("MRN001");

		//add a name
		HumanName name = patient.addName();
		name.setUse(NameUse.OFFICIAL);
		name.setFamily("Doe");
		name.addGiven("John");
		name.addGiven("T.");

		//add a gender
		patient.setGender(AdministrativeGender.MALE);

		//use a parser to encode the resource
		IParser xmlParser = ctx.newXmlParser();
		String xml = xmlParser.encodeResourceToString(patient);
		System.out.println(xml);

		IParser jsonParser = ctx.newJsonParser();
		String json = jsonParser.encodeResourceToString(patient);
		System.out.println(json);

		//let us check if our serialization is valid
		FhirValidator validator = ctx.newValidator();

		//create an invalid observation resource
		Observation observation = new Observation();
		observation.getCode().addCoding().setSystem("http://loinc.org").setCode("1245-6");
		observation.setValue(new StringType("sample value"));

		//validate the resources
		ValidationResult result = validator.validateWithResult(observation);

		//doe we have any errors?
		System.out.println("Successfull: " + result.isSuccessful());

		//show me issues
		for (SingleValidationMessage next : result.getMessages()) {
			System.out.println("Next issue " + next.getSeverity()
					+ " - " + next.getLocationString()
					+ " - " + next.getMessage());
		}
	}

}
