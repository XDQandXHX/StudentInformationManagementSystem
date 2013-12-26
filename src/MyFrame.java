import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame implements ActionListener
{
	JPanel pCenter;
	CardLayout card=null;
	Logon logon;
	
	JLabel label;
	JMenu menuSystem;
	JMenuItem itemWel,login,itemExit;
	JMenuBar bar;
	
	AdmPanel ap;
	TeaPanel tp;
	StuPanel sp;
	MyFrame(String s)
	{
		super(s);
		
		
		card=new CardLayout();
		pCenter=new JPanel();
		logon=new Logon();
		ap=new AdmPanel();
		tp=new TeaPanel(this);
		sp=new StuPanel(this);
		
		label = new JLabel("ѧ����Ϣ����ϵͳ", JLabel.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("��ӭͼƬ.jpg")));
		label.setFont(new Font("����", Font.BOLD, 40));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		menuSystem = new JMenu("ϵͳ����");		
		itemWel = new JMenuItem("��ӭ����");
		login = new JMenuItem("��¼");
		itemExit = new JMenuItem("�˳�");
		menuSystem.add(itemWel);
		menuSystem.add(login);
		menuSystem.add(itemExit);
		bar = new JMenuBar();
		bar.add(menuSystem);
		this.setJMenuBar(bar);
		itemWel.addActionListener(this);
		login.addActionListener(this);
		itemExit.addActionListener(this);
		
		logon.button1.addActionListener(this);
		logon.button2.addActionListener(this);
		
		pCenter.setLayout(card);
		pCenter.add("��ӭ����",label);
		pCenter.add("��¼",logon);
		pCenter.add("����Ա",ap);
		pCenter.add("ѧ��",sp);
		pCenter.add("��ʦ",tp);
		
		add(pCenter,BorderLayout.CENTER);
		setBounds(100,100,900,600);
		
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
	
		if(e.getSource() == itemWel){
			int ok = JOptionPane.showConfirmDialog(this, "ȷ�Ϸ�����", "ȷ�϶Ի���",
					JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION)
					card.show(pCenter,"��ӭ����");
		}
		//�˳�
		else if (e.getSource() == itemExit) {
			int n = JOptionPane.showConfirmDialog(this, "ȷ���˳���", "ȷ�϶Ի���",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
				System.exit(0);			
		} 		
		//¼��
		else if (e.getSource() == login) {
	
			card.show(pCenter, "��¼");			
		} 
		//�޸�
		
		if(e.getSource()==logon.button1)
		{
			String role=null;
			try{
				role=logon.logon();
				
			}catch(SQLException ex)
			{		
				ex.printStackTrace();
			}	
			if(role.equals("ѧ��"))
			{
				card.show(pCenter, "ѧ��");
			}
			else if(role.equals("��ʦ"))
			{
				card.show(pCenter, "��ʦ");
			}	
			else if(role.equals("����Ա"))
			{
				
				card.show(pCenter, "����Ա");
			}
		}
		else if(e.getSource()==logon.button2)
		{
			logon.reset();
		}	
	}
}
