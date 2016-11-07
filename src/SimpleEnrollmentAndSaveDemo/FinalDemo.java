package SimpleEnrollmentAndSaveDemo;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
/*
 * AbsoluteLayoutDemo.java requires no other files.
 */

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FinalDemo extends JPanel implements ActionListener{
		
	JButton connectButton, browseButton, enrollButton, saveTemplateButton;
    JLabel selectPortLabel, targetDirectory, employeeNameLabel;
    JTextField employeeName;
    JFileChooser fc;
    JComboBox portSelectionCombo;
	DemoFlow communicator;
    public FinalDemo (Container pane) {    	
        pane.setLayout(null);

        connectButton = new JButton("Connect");
        selectPortLabel = new JLabel("Select port") ;
        browseButton = new JButton("Browse");
        enrollButton = new JButton("Enroll");
        saveTemplateButton = new JButton("Save Template");
        employeeName = new JTextField();
        employeeNameLabel = new JLabel("Enter Name");
        targetDirectory = new JLabel("...") ;
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        portSelectionCombo = new JComboBox();
        portSelectionCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COM1", "COM2", "COM3",
				"COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10"}));
        portSelectionCombo.setToolTipText("Port Selection");
        portSelectionCombo.setSelectedIndex(0);
        /*portSelectionCombo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//portSelectionActionPerformed(evt);
			}
		});*/
        
        //portSelectionCombo.addActionListener(this);
        connectButton.addActionListener(this);
        browseButton.addActionListener(this);
        enrollButton.addActionListener(this);
        saveTemplateButton.addActionListener(this);
        employeeName.addActionListener(this);

		pane.add(selectPortLabel);
        pane.add(portSelectionCombo);
        
        pane.add(connectButton);
        pane.add(browseButton);
        pane.add(targetDirectory);
        pane.add(employeeNameLabel);
        pane.add(employeeName);
        pane.add(enrollButton);
        pane.add(saveTemplateButton);

        Insets insets = pane.getInsets();
        Dimension size = selectPortLabel.getPreferredSize();
        selectPortLabel.setBounds(25 + insets.left, 5 + insets.top,
                     size.width, size.height);
        size = connectButton.getPreferredSize();
        portSelectionCombo.setBounds(120 + insets.left, 5 + insets.top,
                     70, size.height);
        connectButton.setBounds(25 + insets.left, 45 + insets.top,
                size.width, size.height);
        
        browseButton.setBounds(25 + insets.left, 85 + insets.top,
                size.width, size.height);
        targetDirectory.setBounds(120 + insets.left, 85 + insets.top,
                size.width+150, size.height);
        
        employeeNameLabel.setBounds(25 + insets.left, 125 + insets.top,
                size.width, size.height);
        employeeName.setBounds(120 + insets.left, 125 + insets.top,
                size.width, size.height);
        
        enrollButton.setBounds(25 + insets.left, 165 + insets.top,
                size.width, size.height);
        saveTemplateButton.setBounds(120 + insets.left, 165 + insets.top,
                size.width+50, size.height);
        /*size = b3.getPreferredSize();
        b3.setBounds(150 + insets.left, 15 + insets.top,
                     size.width + 50, size.height + 20)*/
        
    }
    
    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == browseButton) {
            int returnVal = fc.showOpenDialog(FinalDemo.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getCurrentDirectory();//getSelectedFile();
                
                targetDirectory.setText(fc.getSelectedFile().getAbsolutePath());
            } else {
                //log.append("Open command cancelled by user." + newline);
            }
            //log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        }
        else if (e.getSource() == connectButton) {
        	System.out.println("led on off " + portSelectionCombo.getSelectedItem().toString());
        	try {
    			communicator = new DemoFlow(true, portSelectionCombo.getSelectedItem().toString());
    		} catch (IOException ee) {
    			// TODO Auto-generated catch block
    			System.out.println("DemoFlow initiation failed");
    			ee.printStackTrace();
    		}
        	communicator.LedOnOff();
        }
        
        else if (e.getSource() == enrollButton) {
        	System.out.println("start enrolling"); 
        	if(communicator == null){
        		try {
        			communicator = new DemoFlow(true, portSelectionCombo.getSelectedItem().toString());
        		} catch (IOException ee) {
        			// TODO Auto-generated catch block
        			System.out.println("DemoFlow initiation failed");
        			ee.printStackTrace();
        		}
        	}
        	communicator.EnrollFromFrontEnd();
        }
        
        else if (e.getSource() == saveTemplateButton) {
        	System.out.println("save template"); 
        	File file = fc.getCurrentDirectory();
        	if(communicator == null){
        		try {
        			communicator = new DemoFlow(true, portSelectionCombo.getSelectedItem().toString());
        		} catch (IOException ee) {
        			// TODO Auto-generated catch block
        			System.out.println("DemoFlow initiation failed");
        			ee.printStackTrace();
        		}
        	}
        	
        	System.out.println(fc.getSelectedFile().getAbsolutePath() + "\\" +  employeeName.getText() + ".fpt"  );
        	communicator.SaveInFile(fc.getSelectedFile().getAbsolutePath() + "\\" +  employeeName.getText() + ".fpt" );
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Inovace Enroll");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        frame.add(new FinalDemo(frame.getContentPane()));
        //addComponentsToPane(frame.getContentPane());

        //Size and display the window.
        Insets insets = frame.getInsets();
        frame.setSize(400 + insets.left + insets.right,
                      250 + insets.top + insets.bottom);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
