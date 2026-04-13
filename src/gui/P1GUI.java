package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import filter.FilterAcademyAwards;
import io.InvalidTokenException;


public class P1GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private static P1GUI guiInstance;
	
	private Collection<String> aNames;

	private JComboBox<String> cBoxActors = new JComboBox<>();
	
	private JTextField  yearField = new JTextField("1975");
	
	private JTextArea ta1 = new JTextArea(15,50);
	private JTextArea ta2 = new JTextArea(15,50);
	private JTextArea ta3 = new JTextArea(15,50);

	FilterAcademyAwards filter = new FilterAcademyAwards();
	
	public static final P1GUI getInstance() throws InvalidTokenException{
		return (guiInstance != null) ?
				guiInstance : (guiInstance = new P1GUI());
	}
	
	private P1GUI() throws InvalidTokenException{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.filter.init();
		this.aNames = filter.getActorNames();
		this.createComponents();		
		this.pack();
	}
	
	private void createComponents(){
		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		fillP1(p1);
		TitledBorder titledborder1 = BorderFactory.createTitledBorder("Actors: Nominations and Awards");
		titledborder1.setTitleColor(Color.BLUE);
		p1.setBorder(titledborder1);
		c.add(p1);
		
		JPanel p2 = new JPanel();
		fillP2(p2);
		TitledBorder titledborder2 = BorderFactory.createTitledBorder("Actors born in year");
		titledborder2.setTitleColor(Color.blue);
		p2.setBorder(titledborder2);
		c.add(p2);
		
		JPanel p3 = new JPanel();
		fillP3(p3);
		TitledBorder titledborder3 = BorderFactory.createTitledBorder("TOP 5 Nomination Counts");
		titledborder3.setTitleColor(Color.blue);
		p3.setBorder(titledborder3);
		c.add(p3);
		
	}
	
	private void getAwardsAndNominations(){
		String name = (String) P1GUI.this.cBoxActors.getSelectedItem();
		String awards = filter.getAwardsActor(name);
		this.ta1.setText(awards);
		this.ta1.setCaretPosition(0);
	}
	
	private void fillP1(JPanel p){
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p1.add(new JLabel("Actress/Actor: "));
		
		if ((aNames != null) && (aNames.size() > 0)){
			this.fillComboBox(this.cBoxActors,this.aNames);
			int k = (aNames.size() < 8) ? 0 : 7; // Wenn moeglich, Element am Index 7 nehmen
			cBoxActors.setSelectedIndex(k);
			getAwardsAndNominations();
		}
		//JButton submit = new JButton("submit");
		cBoxActors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getAwardsAndNominations();
			}
		});

		p1.add(cBoxActors);
		//p1.setMaximumSize(new Dimension(400,30));
		
		JPanel ergPanel = new JPanel();
		
		this.ta1.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane scroller = new JScrollPane(this.ta1);
		ergPanel.add(scroller);	
		ergPanel.setPreferredSize(new Dimension(450,140));
		scroller.setPreferredSize(new Dimension(450,140));
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(ergPanel);
		//p.setPreferredSize(new Dimension(420,180));

	}
	
	private void getTopNomCount(){
		String top3 = filter.getTopThreeNomCount();
		this.ta2.setText(top3);
		this.ta2.setCaretPosition(0);
	}
	
	private void fillP3(JPanel p){
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
//		p1.add(new JLabel("Year: "));
//		
//		if ((years != null) && (years.size() > 0)){
//			this.fillComboBox(this.cBoxYears,this.years);
//			cBoxYears.setSelectedIndex(0);
//			getTopNomCount();
//		}
//		
//		p1.add(cBoxYears);
		
		JButton submit = new JButton("Get Counts");

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTopNomCount();
			}
		});
		p1.add(submit);

		JPanel ergPanel = new JPanel();
		
		this.ta2.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane scroller = new JScrollPane(this.ta2);
		ergPanel.add(scroller);	
		ergPanel.setPreferredSize(new Dimension(450,140));
		scroller.setPreferredSize(new Dimension(450,140));
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(ergPanel);
		//p.setPreferredSize(new Dimension(420,180));

	}
	
	private void fillP2(JPanel p){
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		
	
		//JButton submit = new JButton("submit");

		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int year = -1;
				if (yearField.getText().trim().length() == 0){
					year = 0;
				}
					
				try {

					year = Integer.parseInt(yearField.getText());
					if ((year < 1850) || (year > 2000)){
						throw new Exception ("Value " + year +  " is not valid.");
					} else {
						String result = filter.actorsBornInYear(year);
						if (result.length() > 0){
							ta3.setText(result);
						} else {
							ta3.setText("no match found");
						}
					}
				} catch (Exception ex){
					JOptionPane.showMessageDialog(P1GUI.this, ex.getMessage());
				}
			}
		});

		p1.add(new JLabel(" Year: "));
		p1.add(yearField);
		p1.add(submit);
		
		//p1.setMaximumSize(new Dimension(400,30));
		
		JPanel ergPanel = new JPanel();
		
		this.ta3.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane scroller = new JScrollPane(this.ta3);
		ergPanel.add(scroller);	
		ergPanel.setPreferredSize(new Dimension(450,140));
		scroller.setPreferredSize(new Dimension(450,140));
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(ergPanel);
		//p.setPreferredSize(new Dimension(420,180));

	}
	
	private <T> void fillComboBox(JComboBox<T> cb, Collection<T> elemente){
		Vector<T> items = new Vector<>(elemente);
		if (items != null){
			cb.removeAllItems();
			items
			.stream()
			.forEach(item -> cb.addItem(item));
			if (cb.getItemCount() > 0){
				cb.setSelectedIndex(0);
			}
		}
	}
	
	

	
}
