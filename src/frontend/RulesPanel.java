package frontend; 

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
	}
	
	JButton buttonAddNewRule;
	List<RulePanel> rules;
	
	public RulesPanel() {
		super();
		
		this.rules = new ArrayList<>(MAX_RULES);
		
		GridBagLayout gbl_panelRules = new GridBagLayout();
		gbl_panelRules.columnWidths = new int[]{0};
		gbl_panelRules.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25};
		gbl_panelRules.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelRules.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panelRules);
		
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
		
		this.revalidate();
		this.repaint();
	}

	public void removeRule(RulePanel rulePanel) {
		if (this.rules.remove(rulePanel)) {
			this.resyncRules();
		}
	}
}
