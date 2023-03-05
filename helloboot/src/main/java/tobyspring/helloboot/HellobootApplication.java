package tobyspring.helloboot;

import java.io.IOException;
import java.net.http.HttpHeaders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class HellobootApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		
		WebServer webServer = serverFactory.getWebServer(servletContext ->{
			HelloController helloController = new HelloController();
			
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
					//auth, secure, incoding, common ...
					
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name()) ) {
						String name = req.getParameter("name");
						
						String ret = helloController.hello(name);// binding -> web 요청을 java 형식으로 바꿔준다.
						
						resp.setStatus(HttpStatus.OK.value());//default = ok
						resp.setHeader("Content-Type", "text/plain");
						resp.getWriter().println(ret);
					
					} 
					else if (req.getRequestURI().equals("/user")) {
						
					} 
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
					
				}
			}).addMapping("/*");
			
		});
		
		webServer.start();
	}

}
 