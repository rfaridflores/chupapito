package com.farid.ws.restws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import com.farid.ws.restws.model.Passenger;

@Service
public class PassengerServiceimpl implements PassengerService {
	// in memory db
	List<Passenger> passengers = new ArrayList<>();
	int currenId = 123;

	// start and size son parametros para la paginacion y no devoler todos los
	// pasajeros. que pasaraia si son 1 millon?
	@Override
	public List<Passenger> getPassenger(int start, int size) {
		System.out.println(start);
		System.out.println(size);
		return passengers;
	}

	/*
	 * @Override public Passenger addPassenger(Passenger passenger) {
	 * passenger.setId(currenId++); passengers.add(passenger); return passenger; }
	 */
	@Override
	public void addPassenger(String fistName, String lastName, String agent, HttpHeaders headers) {
		System.out.println(fistName);
		System.out.println(lastName);
		System.out.println(agent);

		MultivaluedMap<String, String> allHeaders = headers.getRequestHeaders();
		Set<String> headerKeys = allHeaders.keySet();
		for (String key : headerKeys) {
			System.out.println(key);
			System.out.println(headers.getHeaderString(key));
		}
		// headers already have the cookies
		Map<String, Cookie> cookies = headers.getCookies();
		Set<String> cookieKeys = cookies.keySet();
		for (String eachCookieKey : cookieKeys) {
			System.out.println("--------------------Cookies------------------------");
			System.out.println(eachCookieKey);
			System.out.println(cookies.get(eachCookieKey).getValue());
		}
	}
}
