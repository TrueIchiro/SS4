package igs4.fhir.ue03;
import java.util.List;

import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IIdType;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IRestfulClient;
import ca.uhn.fhir.rest.param.TokenParam;


public interface ClientInterface extends IRestfulClient {
	
	@Create
	public MethodOutcome createNewPatient(@ResourceParam Patient thePatient);
	
	@Update
	public MethodOutcome updatePatient(@IdParam IIdType theId, @ResourceParam Patient thePatient);

	@Delete(type = Patient.class)
	public void deletePatient(@IdParam IIdType id);
	
	@Read
	public Patient findPatientById(@IdParam IIdType id);
	
	@Search
	public List<Patient> findPatientsByGender(@RequiredParam(name=Patient.SP_GENDER) String gender);
	
	@Search
	public List<Patient> findPatientsByIdentifier(@RequiredParam(name="identifier") TokenParam identifier);
	
}
