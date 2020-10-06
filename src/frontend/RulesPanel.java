package frontend; 

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RulesPanel extends JPanel {
	private static final int MAX_RULES = 25;
	
	static final GridBagConstraints relativeGBC = new GridBagConstraints();
	static {
		relativeGBC.gridx = 0;
		relativeGBC.gridy = GridBagConstraints.RELATIVE;
		relativeGBC.anchor = GridBagConstraints.NORTH;
		relativeGBC.weighty = 0.0;
	}
	
	JButton buttonAddNewRule;
	List<RulePanel> rules;
	
	JPanel fillerComp;
	GridBagConstraints gbc_fillerComp;
	
	public RulesPanel() {
		super();
		
		this.rules = new ArrayList<>(MAX_RULES);
		
		fillerComp = new JPanel();
		gbc_fillerComp = new GridBagConstraints();
		gbc_fillerComp.gridx = 0;
		gbc_fillerComp.gridy = GridBagConstraints.RELATIVE;
		gbc_fillerComp.anchor = GridBagConstraints.NORTH;
		gbc_fillerComp.weighty = 1.0;
		gbc_fillerComp.fill = GridBagConstraints.BOTH;
		
		this.setLayout(new GridBagLayout());
		
		buttonAddNewRule = new JButton("Add new Rule");
		buttonAddNewRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == "Add new Rule") {
					addNewRule();
				}
			}
		});
		
		this.addNewRule();
		this.resyncRules();
	}
	
	public void addNewRule() {
		RulePanel newRule = new RulePanel();
		
		this.rules.add(newRule);
		
		this.resyncRules();
	}
	
	// Re-add all the rules objects in order
	private void resyncRules() {
		this.removeAll();
		
		for (RulePanel rulePanel : this.rules) {
			this.add(rulePanel, relativeGBC);
		}
		if (this.rules.size() < 25) {
			this.add(this.buttonAddNewRule, relativeGBC);
		}
		
		this.add(this.fillerComp, this.gbc_fillerComp);
		
		this.revalidate();
		this.repaint();
	}

	public void removeRule(RulePanel rulePanel) {
		if (this.rules.remove(rulePanel)) {
			this.resyncRules();
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		for (Component comp : this.getComponents()) {
			comp.setEnabled(enabled);
		}
	}
}
