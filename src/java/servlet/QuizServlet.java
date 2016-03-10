/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import app.Quiz;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bimal
 */
@WebServlet(name = "QuizServlet", urlPatterns = {"/"})
public class QuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException
  {         
        Quiz quiz=null;// create a quiz object

    if(request.getSession().getAttribute("quiz")==null){        // check if the session exists or not
       quiz=new Quiz();                                     // instanciate quiz object
       request.getSession().setAttribute("quiz", quiz);      //create a session with quiz value and attribute quiz
       genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(), false,"");  //generate quiz page as per the parameters             
     }    
   }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException
  {     
       Quiz quiz=(Quiz)request.getSession().getAttribute("quiz");
       boolean correct=false; 
       String answer=request.getParameter("txtAnswer"); 
       
       if(quiz.getIndex()==quiz.getNumQuestions()-1){
         genQuizOverPage(response.getWriter());  
       }
       else {
        if(request.getParameter("btnNext")!=null){

           if(answer!=null&&quiz.isCorrect(answer)){
               quiz.scoreAnswer();
               correct=true;
           }
           else{
               correct=false; 
           }
        
        }
        else{   
            quiz.setIndex(0);     
        } 
        
        boolean ans_flag=quiz.isCorrect(answer);
        if(correct){
               genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(), quiz.isCorrect(answer),answer);
        }
        else{  
               genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(), !ans_flag,answer);    
        }   
       }
  }
  
  private void genQuizOverPage(PrintWriter out) {
        out.print("<html> ");
	out.print("<head >");
	out.print("<title>NumberQuiz is over</title> ");
	out.print("</head> ");
	out.print("<body> ");
	out.print("<p style='color:red'>The number quiz is over!</p>	</body> ");
        out.print("</html> ");
  }
  
   private void genQuizPage(Quiz sessQuiz, PrintWriter out, String currQuest, boolean error, String answer) {
        
        out.print("<html>");
	out.print("<head>");
	out.print("<title>NumberQuiz</title>");
	out.print("</head>");
	out.print("<body>");
	out.print("<form method='post'>");
	out.print("<h3>Have fun with NumberQuiz!</h3>");
        out.print("<p>Your current score is: ");
        out.print(sessQuiz.getNumCorrect() + "</br></br>");
        out.print("<p>Guess the next number in the sequence! ");
        out.print(currQuest + "</p>");

        out.print("<p>Your answer:<input type='text' name='txtAnswer' value='' /></p> ");

        /* if incorrect, then print out error message */
        if (error && (answer != null)) {  //REFACTOR?-- assumes answer null only when first open page
            out.print("<p style='color:red'>Your last answer was not correct! Please try again</p> ");
        }
        out.print("<p><input type='submit' name='btnNext' value='Next' /></p> ");
        out.print("</form>");
        out.print("</body></html>");
    }
}


