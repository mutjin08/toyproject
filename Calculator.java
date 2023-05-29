import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame {
   private JButton [] btn = new JButton[16];         // 버튼
   private JTextField firstTextfield = new JTextField(10);      // 첫 번째 텍스트 필드
   private JTextField secondTextfield = new JTextField(10);   // 두 번째 텍스트 필드
   private int [] changeOperand = new int[10];         // 제곱 함수를 이용해서 두번째 피연산자를 처리하기 위한 배열
   private int firstOperand = 0;         // 첫 번째 피연산자
   private int secondOperand = 0;         // 두 번째 피연산자
   private char operator;            // 연산자
   
   public Calculator() {
      this.setTitle("간단한 계산기");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      
      Container c = getContentPane();
      
      c.setLayout(new BorderLayout(3, 3));
      
      c.add(new FirstPanel(), BorderLayout.NORTH);
      c.add(new SecondPanel());
      
      this.setSize(300, 300);
      this.setVisible(true);

   }
   
   private class FirstPanel extends JPanel {      // 첫 번째 패널 생성
      public FirstPanel() {
         setBackground(Color.GRAY);
         setLayout(new FlowLayout());
         
         add(new JLabel("수식"));
         
         add(firstTextfield);
         firstTextfield.setEditable(false);      // 사용자가 키보드로 직접 입력할 수 없도록 처리
         
         add(new JLabel("결과"));
         
         add(secondTextfield);
         secondTextfield.setEditable(false);      // 사용자가 키보드로 직접 입력할 수 없도록 처리
      }
   }
   
   private class SecondPanel extends JPanel {      // 두 번째 패널 생성
      public SecondPanel() {
         setLayout(new GridLayout(4, 4, 5, 5));
         
         for(int i = 0; i < 10; i++) {
            btn[i] = new JButton(Integer.toString(i));
            btn[i].addActionListener(new MyActionListener());
            add(btn[i]);
         }
         
         JButton btn_ce = new JButton("CE");
         JButton btn_calc = new JButton("계산");
         JButton btn_add = new JButton("+");
         JButton btn_sub = new JButton("-");
         JButton btn_mul = new JButton("x");
         JButton btn_div = new JButton("/");
         
         btn_ce.addActionListener(new MyActionListener());
         btn_calc.addActionListener(new MyActionListener());
         btn_add.addActionListener(new MyActionListener());
         btn_sub.addActionListener(new MyActionListener());
         btn_mul.addActionListener(new MyActionListener());
         btn_div.addActionListener(new MyActionListener());
         
         btn_add.setBackground(Color.CYAN);
         btn_sub.setBackground(Color.CYAN);
         btn_mul.setBackground(Color.CYAN);
         btn_div.setBackground(Color.CYAN);
         
         add(btn_ce);
         add(btn_calc);
         add(btn_add);
         add(btn_sub);
         add(btn_mul);
         add(btn_div);
      }
   }
   
   private class MyActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         JButton btn = (JButton)e.getSource();
         
         int index = 0;
         // changeOperand 배열의 순서를 확인하기 위해 사용할 변수
         
         if(btn.getText().equals("CE")) {      // 계산과 관련된 모든 변수 초기화
            firstTextfield.setText("");
            secondTextfield.setText("");
            firstOperand = 0;
            secondOperand = 0;
         }
         else if(btn.getText().equals("계산")) {
            String str = firstTextfield.getText();
            for(int i = firstTextfield.getText().length()-1; i >= 0; i--) {
               char c = str.charAt(i);
               // 첫 번째 텍스트 필드에 저장되어 있는 문자를 뒤에서 부터 하나씩 읽어들임(두 번째 피연산자를 찾기 위함)
               
               if(c == '+' | c == '-' | c == 'x' | c == '/') { 
                  operator = c;
                  break;
               }
               // 연산자를 기준으로 오른쪽 텍스트가 피연산자이기 때문에 연산자를 찾으면 더 이상 루프를 돌 필요가 없음
               
               else {
                  changeOperand[index] = Integer.parseInt(Character.toString(c));
                  secondOperand += changeOperand[index]*(Math.pow(10, index));
                  // 제곱 함수를 사용해서 두 번째 피연산자 처리
                  
                  index++;
               }
            }
            
            switch(operator) {
            case '+':
               secondTextfield.setText(Float.toString(firstOperand + secondOperand));
               break;
            case '-':
               secondTextfield.setText(Float.toString(firstOperand - secondOperand));
               break;
            case 'x':
               secondTextfield.setText(Float.toString(firstOperand * secondOperand));
               break;
            case '/':
               secondTextfield.setText(Float.toString(firstOperand / secondOperand));
               break;
            }
         }
         
         else if(btn.getText().equals("+") | btn.getText().equals("-") | btn.getText().equals("x") | btn.getText().equals("/")) {
         // 텍스트 필드에 연산자가 입력되면 이전에 입력되었던 텍스트들을 첫 번째 피연산자로 firstOperand 변수에 저장

            firstOperand = Integer.parseInt(firstTextfield.getText());
      
            switch(btn.getText()) {
            case "+":
               firstTextfield.setText(firstTextfield.getText() + "+");
               break;
            case "-":
               firstTextfield.setText(firstTextfield.getText() + "-");
               break;
            case "x":
               firstTextfield.setText(firstTextfield.getText() + "x");
               break;
            case "/":
               firstTextfield.setText(firstTextfield.getText() + "/");
               break;
            }
         }
         
         else {
            for(int i = 0; i < 10; i++) {
               if(btn.getText().equals(Integer.toString(i))) {
                  firstTextfield.setText(firstTextfield.getText() + Integer.toString(i));
               }
            }
         }
      }
   }
   
   public static void main(String args[]) {
      new Calculator();
   }
}
