/*SMS Sender
 * @author: Akash Shende
 * @JDK 1.6
 * @NOTE: Require cellphone connected to USB, USB drivers 
 * works on Samsung,Nokia cell phones only.(AT command supported phones)*/   
  
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SMSServer.*;

public class SMSSender extends JFrame implements ActionListener
{
	JButton snd,no_btn;
	JTextField mobi_no;
	JTextArea jt;
	int flag;
	
	SMSSender()
	{
		super("SMS Sender");
		setLayout(new BorderLayout(10,10));
		setSize(300,300);

		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		setResizable(false);
		//getContentPane().add(new MobiPane(),BorderLayout.NORTH);
		//getContentPane().add(new MSGPane(),BorderLayout.CENTER);
		
		//
		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(0,2));
		jp.add(new JLabel("Mobile Number:",null,JLabel.RIGHT));
		mobi_no=new JTextField();
		jp.add(mobi_no);
		jt=new JTextArea();
		
		add(jt,BorderLayout.CENTER);
		getContentPane().add(jp,BorderLayout.NORTH);
		
		JPanel buttons=new JPanel();
		buttons.setLayout(new GridLayout(1,2));
	
		snd=new JButton("Send");
		
		no_btn=new JButton("Contact >>");
		//set flag
		flag=0;
		
		buttons.add(snd);
		buttons.add(no_btn);
		
		getContentPane().add(buttons,BorderLayout.SOUTH);
		
		//set actions
		snd.addActionListener(this);
		no_btn.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==snd)
		{
			SMSClient sms=new SMSClient();
			String no="";
			String sms_msg;
		
			no=mobi_no.getText().trim();
			sms_msg=jt.getText().trim();
			System.out.println("No.="+no + "Msg="+ sms_msg);
			sms.sendMessage(no,sms_msg);
		}
		
		if(ae.getSource()==no_btn)
		{
			flag=1^flag;
			if(flag==0)
			{
				no_btn.setText("Contact >>");
				setSize(300,300);
			}
			else
			{
				no_btn.setText("<< Contact");
				setSize(500,300);
				getContentPane().add(new Contact_pane(),BorderLayout.EAST);
			}
				
			
			
		}
		
		
		
	}
	
	public class Contact_pane extends JPanel
	{
		JList jl;
		Contact_pane()
		{
			setLayout(new BorderLayout());
			setSize(200,200);
			jl=new JList();

	
			
			add(jl,BorderLayout.CENTER);
			
		}
	}
	
/*	public Insets getInsets()
	{
		return new Insets(40,10,10,10);
	}*/
	
	public static void main(String args[])
	{
		SMSSender s=new SMSSender();
		s.setVisible(true);
	}
}

