package tobyspring.helloboot;

import java.io.IOException;
import java.net.http.HttpHeaders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpStatus;

public class HellobootApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		
		WebServer webServer = serverFactory.getWebServer(servletContext ->{
			servletContext.addServlet("hello", new HttpServlet() {
				
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
					String name = req.getParameter("name");
					
					resp.setStatus(HttpStatus.OK.value());
					resp.setHeader("Content-Type", "text/plain");
					resp.getWriter().println("hello servlet: " + name);
				}
			}).addMapping("/hello");
			
		});
		
		webServer.start();
	}

}
 