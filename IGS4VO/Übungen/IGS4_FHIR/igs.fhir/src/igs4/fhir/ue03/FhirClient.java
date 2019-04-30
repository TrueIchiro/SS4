package igs4.fhir.ue03;

import java.util.List;

import com.sun.security.ntlm.Client;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IIdType;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.TokenParam;

public class FhirClient {
	
	/*
	 * Der Context beinhaltet eine Reihe an Fabrikmethoden f�r das Verarbeiten von FHIR Nachrichten
	 */
	FhirContext ctx = FhirContext.forDstu3();
	
	/**
	 * Die Parser für die Empfangenen Dateien
	 */
	IParser jsonParser = ctx.newJsonParser();
	IParser xmlParser = ctx.newXmlParser();

	/**
	 * Patienten Ressource zum Testen
	 * @return testpatient
	 */
	private Patient createPatientFromString() {
		String msgString = "<Patient xmlns=\"http://hl7.org/fhir\">"
				  + "<text><status value=\"generated\" /><div xmlns=\"http://www.w3.org/1999/xhtml\">John Cardinal</div></text>"
				  + "<identifier><system value=\"http://orionhealth.com/mrn\" /><value value=\"PRP1660\" /></identifier>"
				  + "<name><use value=\"official\" /><family value=\"Cardinal\" /><given value=\"John\" /></name>"
				  + "<gender><coding><system value=\"http://hl7.org/fhir/v3/AdministrativeGender\" /><code value=\"M\" /></coding></gender>"
				  + "<address><use value=\"home\" /><line value=\"2222 Home Street\" /></address><active value=\"true\" />"
				  + "</Patient>";
		
		return xmlParser.parseResource(Patient.class, msgString);
	}
	
    String SERVER_URL = "https://fhirtest.uhn.ca/baseDstu3";
	
	/**
	 * Tests the search patient by identifier method
	 * @param system system id to find 
	 * @param id id of patient to find
	 */
	public void findPatientByIdentifier(String system, String id) {
		// TODO: Implement me

		ClientInterface client =
				ctx.newRestfulClient(ClientInterface.class, SERVER_URL);

		TokenParam param = new TokenParam();
		param.setSystem(system);
		param.setValue(id);

		List<Patient> patients = client.findPatientsByIdentifier(param);

		for (Patient p : patients) {
			String patientStr = xmlParser.encodeResourceToString(p);
			System.out.println(patientStr);
		}
	}
	
	/**
	 * Tests the search patient by gender method
	 */
	public void findPatientsByGender() {
		// TODO: Implement me

		ClientInterface client = ctx.newRestfulClient(ClientInterface.class, SERVER_URL);

		List<Patient> patients =
				client.findPatientsByGender(AdministrativeGender.MALE.toCode());

		System.out.println("Number of patients: " + patients.size());

		for (Patient p : patients) {
			String patientStr = jsonParser.encodeResourceToString(p);
			System.out.println(patientStr);
		}
	}
	
	/**
	 * Create Update Delete
	 */
	public void crudPatient() {
		// TODO: Implement me
		Patient p = createPatientFromString();

		ClientInterface client = ctx.newRestfulClient(ClientInterface.class, SERVER_URL);

		//saving
		MethodOutcome outcomeCreate = client.createNewPatient(p);
		IIdType id = outcomeCreate.getId();

		//checking if patient was created
		Patient stored = client.findPatientById(id);

		System.out.println(xmlParser.encodeResourceToString(stored));

		//update
		MethodOutcome outcomeUpdate = client.updatePatient(id, p);
		System.out.println("Updated resource: " + outcomeUpdate.getId());

		//delete
		client.deletePatient(outcomeUpdate.getId());
		Patient deleted = client.findPatientById(id);

		if (deleted != null) {
			System.out.println("Patient still exists?");
			System.out.println(xmlParser.encodeResourceToString(deleted));
		}
	}
	
	public static void main(String[] args) {
		// 1) Create a client initially
		FhirClient client = new FhirClient();
		
		// 2) search by identifier
		//client.findPatientByIdentifier("" , "12345");
		
		// 3) search by gender
		//client.findPatientsByGender();
		
		// 4) CRUD 
		//client.crudPatient();
	}

}
