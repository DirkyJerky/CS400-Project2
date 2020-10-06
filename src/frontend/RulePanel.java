package frontend;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// unchecked, rawtype suppression because my GUI builder doesn't like when I (properly) use the generic version of JComboBox
@SuppressWarnings({ "serial", "unchecked", "rawtypes" }) 
public class RulePanel extends JPanel {
	
	static final String CLOSE_CHARACTER = "×";
	
	JButton buttonRemoveRule;
	JComboBox comboboxSelectRuleType;
	
	public RulePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 85, 100, 0};
		gridBagLayout.rowHeights = new int[]{20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.buttonRemoveRule = new JButton();
		buttonRemoveRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == CLOSE_CHARACTER) {
					Component parent = buttonRemoveRule.getParent().getParent();
					if (parent instanceof RulesPanel) {
						((RulesPanel) parent).removeRule((RulePanel) buttonRemoveRule.getParent()); 
					}
				}
			}
		});
		buttonRemoveRule.setText(CLOSE_CHARACTER);
		buttonRemoveRule.setMargin(new Insets(0,0,0,0));
		GridBagConstraints gbc_buttonRemoveRule = new GridBagConstraints();
		gbc_buttonRemoveRule.insets = new Insets(0, 0, 0, 0);
		gbc_buttonRemoveRule.anchor = GridBagConstraints.WEST;
		gbc_buttonRemoveRule.gridx = 0;
		gbc_buttonRemoveRule.gridy = 0;
		this.add(buttonRemoveRule, gbc_buttonRemoveRule);
		
		this.comboboxSelectRuleType = new JComboBox();
		for (RuleType type : RuleType.values()) {
			this.comboboxSelectRuleType.addItem(type);
		}
		GridBagConstraints gbc_comboboxSelectRuleType = new GridBagConstraints();
		gbc_comboboxSelectRuleType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboboxSelectRuleType.gridx = 1;
		gbc_comboboxSelectRuleType.gridy = 0;
		add(comboboxSelectRuleType, gbc_comboboxSelectRuleType);
		
		JPanel auxComponentSpace = new JPanel();
		GridBagConstraints gbc_auxComponentSpace = new GridBagConstraints();
		gbc_auxComponentSpace.insets = new Insets(0, 5, 0, 0);
		gbc_auxComponentSpace.gridx = 2;
		gbc_auxComponentSpace.gridy = 0;
		this.add(auxComponentSpace, gbc_auxComponentSpace);
		
		JLabel lblNewLabel = new JLabel("Select rule type");
		auxComponentSpace.add(lblNewLabel);
	}
	
	

}
