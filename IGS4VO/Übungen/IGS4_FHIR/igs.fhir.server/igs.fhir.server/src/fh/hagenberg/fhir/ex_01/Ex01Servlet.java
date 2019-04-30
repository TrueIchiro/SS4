package fh.hagenberg.fhir.ex_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.springframework.web.cors.CorsConfiguration;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.CorsInterceptor;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import fh.hagenberg.fhir.ex_01.provider.PatientResourceProvider;

public class Ex01Servlet extends RestfulServer {
 
    private static final long serialVersionUID = 1L;
 
    public Ex01Servlet() {
        // Der RestfulServer unterstützt DSTU3
        super(FhirContext.forDstu3()); 
      }

   @Override
   protected void initialize() throws ServletException {

      addResourceProviders();

      CorsConfiguration config = new CorsConfiguration();
      CorsInterceptor corsInterceptor = new CorsInterceptor(config);
      config.addAllowedHeader("Accept");
      config.addAllowedHeader("Content-Type");
      config.addAllowedOrigin("*");
      config.addExposedHeader("Location");
      config.addExposedHeader("Content-Location");
      config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
      registerInterceptor(corsInterceptor);

      /*
       * Der ResponseHighlighterInterceptor ermöglicht bei Zugriffen 
       * auf das Rest-Interface via Browser, dass die Resultate
       * im Browserfenster farblich hervorgehoben gerendert werden.
       */
      registerInterceptor(new ResponseHighlighterInterceptor());

      /*
       * Die Resultate eines FHIR-Request werden sauber formatiert 
       */
      setDefaultPrettyPrint(true);
   }

   private void addResourceProviders(){
      List<IResourceProvider> resourceProviders = new ArrayList<IResourceProvider>();
      resourceProviders.add(new PatientResourceProvider());
      setResourceProviders(resourceProviders);
   }
   
   public static void main( String[] args ) throws Exception
   {
       Server server = new Server(9000);
       ServletHandler handler = new ServletHandler();
       server.setHandler(handler);
       handler.addServletWithMapping(Ex01Servlet.class, "/ex01/*");
       server.start();
       server.join();
   }
     
}