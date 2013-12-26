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
		
		label = new JLabel("学生信息管理系统", JLabel.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("欢迎图片.jpg")));
		label.setFont(new Font("黑体", Font.BOLD, 40));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		menuSystem = new JMenu("系统管理");		
		itemWel = new JMenuItem("欢迎界面");
		login = new JMenuItem("登录");
		itemExit = new JMenuItem("退出");
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
		pCenter.add("欢迎界面",label);
		pCenter.add("登录",logon);
		pCenter.add("管理员",ap);
		pCenter.add("学生",sp);
		pCenter.add("教师",tp);
		
		add(pCenter,BorderLayout.CENTER);
		setBounds(100,100,900,600);
		
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
	
		if(e.getSource() == itemWel){
			int ok = JOptionPane.showConfirmDialog(this, "确认返回吗？", "确认对话框",
					JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION)
					card.show(pCenter,"欢迎界面");
		}
		//退出
		else if (e.getSource() == itemExit) {
			int n = JOptionPane.showConfirmDialog(this, "确认退出吗？", "确认对话框",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
				System.exit(0);			
		} 		
		//录入
		else if (e.getSource() == login) {
	
			card.show(pCenter, "登录");			
		} 
		//修改
		
		if(e.getSource()==logon.button1)
		{
			String role=null;
			try{
				role=logon.logon();
				
			}catch(SQLException ex)
			{		
				ex.printStackTrace();
			}	
			if(role.equals("学生"))
			{
				card.show(pCenter, "学生");
			}
			else if(role.equals("教师"))
			{
				card.show(pCenter, "教师");
			}	
			else if(role.equals("管理员"))
			{
				
				card.show(pCenter, "管理员");
			}
		}
		else if(e.getSource()==logon.button2)
		{
			logon.reset();
		}	
	}
}
