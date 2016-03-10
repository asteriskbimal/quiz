/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author 985013
 */
public class Quiz {
    
    private final String[] questions = {
        "3, 1, 4, 1, 5, ?", 
        "1, 1, 2, 3, 5, ?", 
        "1, 4, 9, 16, 25, ?", 
        "1, 3, 5, 7, 9, ?", 
        "1, 2, 4, 8, 16, ?"
    };
    
    private final String[] answers = {"9", "8", "36", "11", "32"};
    
    private int index = 0;
    
    private int correct = 0;

    public boolean isCorrect(String string) {
        return string.equals(answers[index]);
    }
    
    public void setIndex(int i){
    this.index=i;
    }

    public int getNumQuestions() {
        return answers.length;
    }

    public int getNumCorrect() {
        return correct;
    }

    public void scoreAnswer() {
        ++correct;
        ++index;
    }

    public int getCurrentQuestionIndex() {
        return index;
    }

    public String getCurrentQuestion() {
        return questions[index];
    }

    public int getIndex() {
         return index;
    }
        
}
