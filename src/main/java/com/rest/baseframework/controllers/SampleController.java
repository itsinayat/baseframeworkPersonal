package com.rest.baseframework.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.rest.baseframework.entity.Employee;

@Component
@Path("/")
public class SampleController {

	 private static SessionFactory factory; 
	
	@Path("hello")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		Employee e=new Employee();
		      try {
		         factory = new Configuration().configure().buildSessionFactory();
		      } catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         throw new ExceptionInInitializerError(ex); 
		      }
		      
		Session session=      factory.openSession();
	                    	session.beginTransaction();
	                    	session.save(e);
	                    	session.getTransaction().commit();
	                    	session.close();
	                    	factory.close();
		      
		
		return Response.status(200).entity(e).build();
		
	}

	

}
