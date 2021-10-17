//Joey Paschke
//Homework 8 CS 326
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ColorSampler extends JFrame implements ActionListener
{
	int count = 0;
	int r = 255;
	int g = 255;
	int b = 255;
	int reset;
	static int R[] = new int[11];
	static int G[] = new int[11];
	static int B[] = new int[11];
	static String color[] = new String[11];
	private JLabel label_R;
	private JLabel label_G;
	private JLabel label_B;
	
	private JFrame frame;
	
	private JPanel panel;
	
	private JTextField red_text_field;
	private JTextField green_text_field;
	private JTextField blue_text_field;
	
	private JList<String> color_list;
	
	private DrawingTester color_drawing;
	
	private JButton buttonRPlus;
	private JButton buttonRMinus;
	private JButton buttonGPlus;
	private JButton buttonGMinus;
	private JButton buttonBPlus;
	private JButton buttonBMinus;
	private JButton buttonSave;
	private JButton buttonReset;
	
    public ColorSampler()
    {
    	//Button Setup
    	buttonRPlus = new JButton("+");
    	buttonRMinus = new JButton("-");
    	buttonGPlus = new JButton("+");
    	buttonGMinus = new JButton("-");
    	buttonBPlus = new JButton("+");
    	buttonBMinus = new JButton("-");
    	buttonSave = new JButton("Save");
    	buttonReset = new JButton("Reset");
    	buttonRPlus.addActionListener(this);
    	buttonRMinus.addActionListener(this);
    	buttonGPlus.addActionListener(this);
    	buttonGMinus.addActionListener(this);
    	buttonBPlus.addActionListener(this);
    	buttonBMinus.addActionListener(this);
    	buttonSave.addActionListener(this);
    	buttonReset.addActionListener(this);
    	
    	//Label Setup
    	label_R = new JLabel("Red:");
    	label_G = new JLabel("Green:");
    	label_B = new JLabel("Blue:");
    	
    	//Text Field Setup
    	red_text_field = new JTextField("255");
    	green_text_field = new JTextField("255");
    	blue_text_field = new JTextField("255");
    	
    	//Drawing
    	color_drawing = new DrawingTester();
    	
    	//List Setup
    	color_list = new JList();
    	color_list.setListData(color);
    	color_list.addListSelectionListener(new ListHandler());

        //Panel Setup
        panel = new JPanel();
        panel.setBounds(100, 100, 300, 300);
        panel.setLayout(null);
        panel.add(buttonRPlus);
        panel.add(buttonRMinus);
        panel.add(buttonGPlus);
        panel.add(buttonGMinus);
        panel.add(buttonBPlus);
        panel.add(buttonBMinus);
        panel.add(buttonReset);
        panel.add(buttonSave);
        panel.add(label_R);
        panel.add(label_G);
        panel.add(label_B);
        panel.add(red_text_field);
        panel.add(green_text_field);
        panel.add(blue_text_field);
        panel.add(color_list);
        panel.add(color_drawing);
        
        //Bounds Setup
        label_R.setBounds(40, 220, 40, 30);
        label_G.setBounds(40, 220, 40, 120);
        label_B.setBounds(40, 220, 40, 225);
        
        red_text_field.setBounds(80, 220, 50, 30);
        green_text_field.setBounds(80, 270, 50, 30);
        blue_text_field.setBounds(80, 320, 50, 30);
        
        color_list.setBounds(300, 0, 200, 300);
        
        color_drawing.setBounds(10,10,270,200);
        
        buttonRPlus.setBounds(210, 220, 50, 30);
        buttonRMinus.setBounds(150, 220, 50, 30);
        buttonGPlus.setBounds(210, 270, 50, 30);
        buttonGMinus.setBounds(150, 270, 50, 30);
        buttonBPlus.setBounds(210, 320, 50, 30);
        buttonBMinus.setBounds(150, 320, 50, 30);
        buttonSave.setBounds(60,375,80,25);
        buttonReset.setBounds(160,375,80,25);
        
        //Frame Setup
        frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Color Sampler");
        frame.setSize(400, 500);
        frame.setVisible(true);  
    }
    
	///////////////////////////////////////////////////////////
	///////////////////////Main Function///////////////////////
	///////////////////////////////////////////////////////////
    public static void main(String[] args) throws IOException
    {
        new ColorSampler();
        
        FileInputStream istream = new FileInputStream("colors.txt");  
		InputStreamReader reader = new InputStreamReader(istream); 
		StreamTokenizer tokens = new StreamTokenizer(reader); 
		
		int i = 0;
		while (tokens.nextToken() != tokens.TT_EOF)
		{  
				color[i] = (String) tokens.sval;
				tokens.nextToken();
				R[i] = (int) tokens.nval;
				tokens.nextToken();
				G[i] = (int) tokens.nval;
				tokens.nextToken();
				B[i] = (int) tokens.nval;
				i++;
		}
		istream.close();
	}

    
	//This is called every time the button is pressed.
    public void actionPerformed(ActionEvent action) 
	{
		///////////////////////////////////////////////////////////
		///////////////RED BUTTON ACTION LOGIC/////////////////////
		///////////////////////////////////////////////////////////
		if(action.getSource() == buttonRPlus)
		{
			if (Integer.parseInt(red_text_field.getText()) == 255)
			{
				return;
			}
			else
			{
				r = Integer.parseInt(red_text_field.getText());
				r += 5;
				red_text_field.setText(String.valueOf(r));
				color_drawing.repaint();
			}
			frame.setTitle("Color Sampler" + "*");
		}
		else if(action.getSource() == buttonRMinus)
		{
			if (Integer.parseInt(red_text_field.getText()) == 0)
			{
				return;
			}
			else
			{
				r = Integer.parseInt(red_text_field.getText());
				r -= 5;
				red_text_field.setText(String.valueOf(r));
				color_drawing.repaint();
			}
			frame.setTitle("Color Sampler" + "*");
		}
		
		///////////////////////////////////////////////////////////
		///////////////GREEN BUTTON ACTION LOGIC///////////////////
		///////////////////////////////////////////////////////////
		if(action.getSource() == buttonGPlus)
		{
			if (Integer.parseInt(green_text_field.getText()) == 255)
			{
				return;
			}
			else
			{
				g = Integer.parseInt(green_text_field.getText());
				g += 5;
				green_text_field.setText(String.valueOf(g));
				color_drawing.repaint();
			}
			frame.setTitle("Color Sampler" + "*");
		}
		else if(action.getSource() == buttonGMinus)
		{
			if (Integer.parseInt(green_text_field.getText()) == 0)
			{
				return;
			}
			else
			{
				g = Integer.parseInt(green_text_field.getText());
				g -= 5;
				green_text_field.setText(String.valueOf(g));
				color_drawing.repaint();
			}
			frame.setTitle("Color Sampler" + "*");
		}
		
		///////////////////////////////////////////////////////////
		///////////////BLUE BUTTON ACTION LOGIC////////////////////
		///////////////////////////////////////////////////////////
		if(action.getSource() == buttonBPlus)
		{
			if (Integer.parseInt(blue_text_field.getText()) == 255)
			{
				return;
			}
			else
			{
				b = Integer.parseInt(blue_text_field.getText());
				b += 5;
				blue_text_field.setText(String.valueOf(b));
				color_drawing.repaint();
			}
			frame.setTitle("Color Sampler" + "*");
			
		}
		else if(action.getSource() == buttonBMinus)
		{
			if (Integer.parseInt(blue_text_field.getText()) == 0)
			{
				return;
			}
			else
			{
				b = Integer.parseInt(blue_text_field.getText());
				b -= 5;
				blue_text_field.setText(String.valueOf(b));
				color_drawing.repaint();
				
			}
			frame.setTitle("Color Sampler" + "*");
			
		}
		
		///////////////////////////////////////////////////////////
		///////////////SAVE BUTTON ACTION LOGIC////////////////////
		///////////////////////////////////////////////////////////
		if(action.getSource() == buttonSave)
		{
			frame.setTitle("Color Sampler");
			BufferedWriter writeTable = null;
			R[reset] = r;
	        G[reset] = g;
	        B[reset] = b;
	        
		    try
		    {
		        writeTable = new BufferedWriter(new FileWriter("colors.txt"));
		        for (int i = 0; i < 11; i++)
		        {
		            writeTable.write(color[i] + " " + R[i] + " " + G[i] +  " " + B[i]);
		            writeTable.newLine();
		        }
		        writeTable.close();
		    }
		    catch(IOException e)
		    {
		        System.out.println(e);
		    }
		}
		///////////////////////////////////////////////////////////
		///////////////RESET BUTTON ACTION LOGIC/////////////////////
		///////////////////////////////////////////////////////////
		if(action.getSource() == buttonReset)
		{
			r = R[reset];
			g = G[reset];
			b = B[reset];
			color_drawing.repaint();
			red_text_field.setText(String.valueOf(R[reset]));
			green_text_field.setText(String.valueOf(G[reset]));
			blue_text_field.setText(String.valueOf(B[reset]));
		}
	}
	
///////////////////////////////////////////////////////////
/////////////////////List Handler//////////////////////////
///////////////////////////////////////////////////////////                              
private class ListHandler implements ListSelectionListener
{
	public void valueChanged(ListSelectionEvent e)
	{ 
		if (e.getSource() == color_list)
		{
			if (!e.getValueIsAdjusting())
			{
				int i = color_list.getSelectedIndex();
				reset = i;
				String s = (String) color_list.getSelectedValue();
				r = R[i];
				g = G[i];
				b = B[i];
				red_text_field.setText(String.valueOf(R[i]));
				green_text_field.setText(String.valueOf(G[i]));
				blue_text_field.setText(String.valueOf(B[i]));
				color_drawing.repaint();
				
			}
	   }
    }
}

///////////////////////////////////////////////////////////
/////////////////////Drawing Tester////////////////////////
///////////////////////////////////////////////////////////
class DrawingTester extends JComponent
	{
		public void paint(Graphics graph)
		{
			Dimension d = getSize();
			graph.setColor(new Color(r,g,b));
			graph.fillRect(1,1,d.width - 2, d.height - 2);
		}
	}
}
