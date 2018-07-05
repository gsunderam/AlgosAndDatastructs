package com.gs.home.webservices;

import static com.gs.home.log.Logger.stdout;
import net.webservicex.*; //step 2

/**
 * 3 simple steps to call webservice
 * 
 * 1) Generate stubs using wsimport via the WSDL one gets from the service provider. 
 *    wsimport -keep -s <src folder to keep java files> http://www.webservicex.net/geoipservice.asmx?WSDL
 * 2) Import the java sources into your project
 * 3) Look at the WSDL element : wsdl:service and create an instance of the service using "new" op
 * 4) From that get the Port, which is the stub to begin calling remote methods
 * 5) Call the method, as if it were local, using the stub and use the results
 * 6) Using codes, check if there is any error handling to be done in real world
 * 
 * @author chandrashekar
 */
public class WebServiceClient {
	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			System.exit(0);
		}
		
		//Now input is 1, so call the webservice
		callGeoLocationSvc(args[0]);
	}

	private static void callGeoLocationSvc(String ip) {
		stdout("Calling the service with IP: " + ip);
		long start = System.nanoTime();
		
		//call the external service
		GeoIPService ipSvc= new GeoIPService(); //step 3
		GeoIPServiceSoap geoIPServiceStub = ipSvc.getGeoIPServiceSoap(); //step 4
		GeoIP geoIP = geoIPServiceStub.getGeoIP(ip); //step 5
		stdout("Country is " + geoIP.getCountryName());
		stdout("Return code "+ geoIP.getReturnCodeDetails()); //step 6
		stdout(geoIP.getIP());
		//Ends
		
		long end = System.nanoTime();
		stdout("Service call completed, Time taken: " + (end - start));
	}

	private static void usage() {
		stdout("Usage: java WebServiceClient <IP>");
		stdout("Please provide ipaddress as input to the webservice");
	}
}
