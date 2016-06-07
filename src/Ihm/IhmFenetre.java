/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ihm;

import Jeu.Monopoly;
import main.Main;
import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author albertar
 */
public class IhmFenetre extends JPanel {
        
    public IhmFenetre() {        
       JFrame frame = new JFrame();
       frame.setTitle("Monopoly");
       frame.setSize(900, 800);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new Interface ());
       frame.setVisible(true);
    }
    
}
