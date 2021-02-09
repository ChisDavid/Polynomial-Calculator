// CalcView.java - View component
//    Presentation only.  No user actions.
// Fred Swartz -- December 2004

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class View  {
    //... Components
	private JFrame main_frame= new JFrame("calculator de polinome");
    private JTextField input1 = new JTextField(5);
    private JTextField input2 = new JTextField(5);
    public JTextField out    = new JTextField(20);
    private JButton    inmultire = new JButton("Inmultire");
    private JButton    adunare = new JButton("Adunare");
    private JButton    scadere = new JButton("Scadere");
    private JButton    impartire    = new JButton("Impartire");
    private JButton    derivare = new JButton("Derivare");
    private JButton    integrare = new JButton("Integrare");
    private JButton    clear = new JButton("Clear");
    
    private Model m_model;
  

  public  View(Model model) {
        //... Set up the logic
        m_model = model;
        m_model.setValue(new Polinom(new ArrayList<monom>()));
        
        //... Initialize components
       out.setText(" ");
        out.setEditable(false);
        
        //... Layout the components.      
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(new JLabel("Input"));
        JLabel in1 = new JLabel("Input1");
        JLabel in2 = new JLabel("Input2");
        in1.setBounds(10, 50, 50, 30);

        input1.setBounds(80, 50, 170, 25);
        
        
        
        
        in2.setBounds(10, 80, 50, 30);
        
        input2.setBounds(80, 80, 170, 25);
        JLabel REZULTAT = new JLabel("Output :");
        REZULTAT.setBounds(20, 250, 70, 30);
        panel.add(REZULTAT);
        out.setBounds(100, 250, 270, 40);
        adunare.setBounds(300, 50, 120, 20);
        scadere.setBounds(300, 80, 120, 20);
        inmultire.setBounds(300, 110, 120, 20);
       impartire.setBounds(300, 140, 120, 20);
        derivare.setBounds(300, 170, 120, 20);
        integrare.setBounds(300, 200, 120, 20);
        
        
        clear.setBounds(90, 350, 250, 20);
        panel.add(clear);
        panel.add(in1);
        panel.add(in2);
        panel.add(input1);
        panel.add(input2);
        panel.add(inmultire);
        panel.add(new JLabel("Total"));
       
        panel.add(out);
        
        panel.add(adunare);
        panel.add(scadere);
        panel.add(inmultire);
        panel.add(impartire);
        panel.add(derivare);
        panel.add(integrare);
        //... finalize layout
        main_frame.setContentPane(panel);
        main_frame.setSize(500, 500);
       main_frame.setVisible(true);
        // The window closing event should probably be passed to the 
        // Controller in a real program, but this is a short example.
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void reset() {
    	
       out.setText("");
    }
    
    String getUserInput1() {
        return input1.getText();
    }
    String getUserInput2() {
        return input2.getText();
    }
    
    void setTotal(String newTotal) {
       out.setText(newTotal);
    }
    
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(main_frame, errMessage);
    }
    
    void add_adunare(ActionListener mal) {
        adunare.addActionListener(mal);
    }
    void add_scadere(ActionListener mal) {
        scadere.addActionListener(mal);
    }
    void add_inmultire(ActionListener mal) {
        inmultire.addActionListener(mal);
    }
    void add_impartire(ActionListener mal) {
        impartire.addActionListener(mal);
    }
    void add_derivare(ActionListener mal) {
        derivare.addActionListener(mal);
    }
    void add_integrare(ActionListener mal) {
        integrare.addActionListener(mal);
    }
    
    void add_clear(ActionListener cal) {
       clear.addActionListener(cal);
    }
}
