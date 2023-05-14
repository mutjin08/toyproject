import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

class Word{
    private String english;
    private String korean;

    public Word(String english, String korean){
        this.english=english;
        this.korean=korean;
    }
    
    public String getEnglish() {return english;}
    public String getKorean() {return korean;}
}

public class WordQuiz{
    private Vector<Word> v;
    public WordQuiz(){
        v = new Vector<Word>();
        v.add(new Word("love", "사랑"));
        v.add(new Word("animal", "동물"));
        v.add(new Word("emotion", "감정"));
        v.add(new Word("human", "인간"));
        v.add(new Word("stock", "주식"));
    }
    private int makeExample(int ex[], int answerIndex){
        int n[]={-1, -1, -1, -1};
        int index;
        
        for(int i=0; i<n.length; i++) {
            do {
                index = (int)(Math.random()*v.size());
            } while(index == answerIndex || exists(n, index)); 
            n[i] = index;
        }
        
        for(int i=0; i<n.length; i++) ex[i] = n[i];
        return (int)(Math.random()*n.length); 
    }
    
    private boolean exists(int n[], int index) {
        for(int i=0; i<n.length; i++) {
            if(n[i] == index)
                return true;
        }
        return false;
    }
    
    private boolean exist(int n[], int index){
        for(int i=0;i<n.length;i++){
            if(n[i]==index)
                return true;
        }
        return false;
    }
    public void run(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("영어 어휘 테스트를 시작합니다. 1~4 외 다른 입력시 종료합니다");
        while(true){
            int answerIndex=(int)(Math.random()*v.size());
            String eng=v.get(answerIndex).getEnglish();
            int example[]=new int[4];
            int answerLoc = makeExample(example, answerIndex); 
            example[answerLoc]=answerIndex;
        
        
            System.out.print(":>"); 
            try {
                int in = scanner.nextInt(); //사용자의 정답 입력
                if(in<1||in>4) {
                System.out.println("프로그램을 종료합니다...");
                return;
                }
         
                        
                in--;
                        
                if(in == answerLoc)
                    System.out.println("Great~!! ");
                else
                    System.out.println("No~!!");
            }
            catch(InputMismatchException e) {
                scanner.next(); 
                System.out.println("프로그램을 종료합니다...");
                break;
            }
        } //end of while
        scanner.close();
    }
    
    public void main(String[] args){
        WordQuiz quiz=new WordQuiz();
        quiz.run();
    }
}


            

