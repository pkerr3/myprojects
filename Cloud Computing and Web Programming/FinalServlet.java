import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class FinalServlet
 */
@WebServlet("/FinalServlet")
@MultipartConfig
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 final PrintWriter out = response.getWriter();
	        
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String location = request.getParameter("location");
	        String gender = request.getParameter("gender");
	        String experience = request.getParameter("experience");
	        String fileName = request.getParameter("fileName");
	        
	        /*	
	         * request.getPart is to get the uploaded file handler. 
	         * You can use filePart.getInputStream() to read the streaming data from client, for example:
	         * InputStream filecontent = filePart.getInputStream();
	        */
	        Part filePart = request.getPart("file");
	        InputStream filecontent = filePart.getInputStream();
	        byte buffer[] = new byte[1000];
		    int nRead = 0;
		    /*
		     * fileout is for you to save the uploaded picture in your local disk. 
		     * */
		    FileOutputStream fileout = new FileOutputStream("C:\\Users\\Administrator\\workspace1\\RealFinalProject\\" + fileName);
		    while ((nRead = filecontent.read(buffer)) != -1)
		    	fileout.write(buffer);
		    fileout.close();
		    	    
		    /*
		     * Write your code here
		     * Step 1: check whether the client's inputs are complete or not; if anything is missing, return a web page that contains a link to go back to the registration page (e.g., UserRegistration.html)
		     * Step 2: save the uploaded picture under your project WebContent directory, for example, mine is "F:\workspace\UserRegistrationProject\WebContent". 
		     * Step 3: send back the client's registration information to the client, remember, the client should be able to see all the information, including the profile picture. 
		     * */
		    if (name.isEmpty() || email.isEmpty() || gender == null || experience.isEmpty() || fileName.isEmpty() || filecontent == null){
				String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
				out.println(docType +
						"<html>\n"+
						"<head></head>\n"+
						"<body>\n"+
						"<p>\n"+
						/**note: the path to static is defined in the apache server in the workspace;
						if anything involving static does not compile properly, make sure the following code
						is in the Apache "server" folder: <Context docBase="_path to the project_" path="/static" />*/
						"Your input information is incomplete. <a href = http://localhost:8080/static/The%20Student's%20Servlet%20project.html>Go back?</a>\n"+
						"</p>\n"+
						"</body></html>");
		    }
		    else {
		    filecontent.close();
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
			out.println(docType +
					"<html>\n"+
					"<head><title>User registration</title></head>\n"+
					"<body>\n"+
					"<h1 align=\"center\">Welcome " + name +"</h1>"+
					"<ul>\n"+
					"<li><b>Your name</b>: " + name + "\n"+
					"<li><b>Your email</b>: " + email + "\n"+
					"<li><b>Your location</b>: " + location + "\n"+
					"<li><b>Your gender</b>: " + gender + "\n"+
					"<li><b>Your experience</b>: " + experience + "\n"+
					"<li><b>Your profile picture</b> " + fileName + "<b> has been uploaded successfully:\n</b><br>"+
					"<img src = http://localhost:8080/static/" + fileName + "></img>"+
					"</ul>\n"+
					"</body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
