// CalcController.java - Controller
//    Handles user interaction with listeners.
//    Calls View and Model as needed.
// Fred Swartz -- December 2004

import java.awt.event.*;

public class Controller {
    //... The Controller needs to interact with both the Model and View.
    private Model m_model;
    private View  m_view;
    
    //========================================================== constructor
    /** Constructor */
    public Controller(Model model, View view) {
        m_model = model;
        m_view  = view;
        
        //... Add listeners to the view.
        view.add_adunare(new adunare_Listener());
        view.add_scadere(new scadere_Listener());
        view.add_inmultire(new inmultire_Listener());
        view.add_impartire(new impartire_Listener());
        view.add_derivare(new derivare_Listener());
        view.add_integrare(new integrare_Listener());
        view.add_clear(new clear_Listener());
    }
    
    
    ////////////////////////////////////////// inner class MultiplyListener
    /** When a mulitplication is requested.
     *  1. Get the user input number from the View.
     *  2. Call the model to mulitply by this number.
     *  3. Get the result from the Model.
     *  4. Tell the View to display the result.
     * If there was an error, tell the View to display it.
     */
    class adunare_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String Input1 = "";
            
            String Input2 = "";
           
               Input1 = m_view.getUserInput1();
               Input2 = m_view.getUserInput2();
               Polinom p=m_model.convertire(Input1);
               Polinom p1=m_model.convertire(Input2);
               p.reducere_termeni_asemenea();
             m_view.setTotal(p.suma(p1).afis());
             
           
        }
    }
    class scadere_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String Input1 = "";
            
            String Input2 = "";
            
               Input1 = m_view.getUserInput1();
               Input2 = m_view.getUserInput2();
              Polinom p=m_model.convertire(Input1);
              Polinom p1=m_model.convertire(Input2);
                  m_view.setTotal(p.diferenta(p1).afis());
                
            
        }
    }
    class inmultire_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String Input1 = "";
            
            String Input2 = "";
            try {
               Input1 = m_view.getUserInput1();
               Input2 = m_view.getUserInput2();
              Polinom p=m_model.convertire(Input1);
              Polinom p1=m_model.convertire(Input2);
                m_view.setTotal(p.produs(p1).afis());
                
            } catch (NumberFormatException nfex) {
                m_view.showError("Bad input: '" + Input1 + "'");
            }
        }
    }
    class impartire_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String Input1 = "";
            String Input2 = "";
            try {
               Input1 = m_view.getUserInput1();
               Input2 = m_view.getUserInput2();
              Polinom p=m_model.convertire(Input1);
              Polinom p1=m_model.convertire(Input2);
              
              if(p.impartire(p1).get(1).getMonom().get(0).getCoef()==0)
              {
            	  m_view.setTotal("CAt:    "+p.impartire(p1).get(0).afis());
              }
              else
              {
            	  m_view.setTotal("CAt:    "+p.impartire(p1).get(0).afis()+"  Rest :       " + p.impartire(p1).get(1).afis());
              }
            } catch (ExImposibil nfex) {
              //  m_view.showError("Bad input: '" + Input1 + "'");
            }
        }
    }
    class derivare_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String Input1 = "";
            
            try {
               Input1 = m_view.getUserInput1();
              
              Polinom p=m_model.convertire(Input1);
             
                m_view.setTotal(p.derivare().afis());
                
            } catch (NumberFormatException nfex) {
                m_view.showError("Bad input: '" + Input1 + "'");
            }
        }
    }
    class integrare_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String Input1 = "";
            
            try {
               Input1 = m_view.getUserInput1();
              
              Polinom p=m_model.convertire(Input1);
             
                m_view.setTotal(p.integrare().afis());
                
            } catch (NumberFormatException nfex) {
                m_view.showError("Bad input: '" + Input1 + "'");
            }
        }
    }
    
    
    //end inner class MultiplyListener
    
    
    //////////////////////////////////////////// inner class ClearListener
    /**  1. Reset model.
     *   2. Reset View.
     */    
    class clear_Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
       
            m_view.reset();
        }
    }// end inner class ClearListener
}
