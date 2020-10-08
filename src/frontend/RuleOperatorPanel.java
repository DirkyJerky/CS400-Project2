package frontend;

import javax.swing.JPanel;

import frontend.JFilteredTextField.FilterType;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// unchecked, rawtype suppression because my GUI builder doesn't like when I (properly) use the generic version of JComboBox
@SuppressWarnings({ "serial", "unchecked", "rawtypes" }) 
public class RuleOperatorPanel extends JPanel {
	
	static final String CLOSE_CHARACTER = "×";
	
	JButton buttonRemoveRule;
	JComboBox comboboxSelectRuleType;
	JFilteredTextField filteredTextField;
	
	public RuleOperatorPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 85, 100, 0};
		gridBagLayout.rowHeights = new int[]{20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.buttonRemoveRule = new JButton();
		buttonRemoveRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == CLOSE_CHARACTER) {
					Component parent = buttonRemoveRule.getParent().getParent();
					if (parent instanceof RulePanel) {
						((RulePanel) parent).removeRuleOperator((RuleOperatorPanel) buttonRemoveRule.getParent()); 
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
		
		this.comboboxSelectRuleType = new JComboBox(OperatorType.values());
		comboboxSelectRuleType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == "comboBoxChanged" && filteredTextField != null) {
					OperatorType selectedType = (OperatorType) comboboxSelectRuleType.getSelectedItem();
					filteredTextField.updateFilterType(selectedType.filterType);
				}
			}
		});
		GridBagConstraints gbc_comboboxSelectRuleType = new GridBagConstraints();
		gbc_comboboxSelectRuleType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboboxSelectRuleType.gridx = 1;
		gbc_comboboxSelectRuleType.gridy = 0;
		add(comboboxSelectRuleType, gbc_comboboxSelectRuleType);
		
		filteredTextField = new JFilteredTextField(FilterType.NORMAL);
		filteredTextField.setPreferredSize(new Dimension(100, 25));
		GridBagConstraints gbc_filteredTextField = new GridBagConstraints();
		gbc_filteredTextField.insets = new Insets(0, 5, 0, 0);
		gbc_filteredTextField.gridx = 2;
		gbc_filteredTextField.gridy = 0;
		this.add(filteredTextField, gbc_filteredTextField);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		for (Component comp : this.getComponents()) {
			comp.setEnabled(enabled);
		}
	}
	
	public void addOperatorToBuilder(StringBuilder builder) throws IllegalStateException {
		if (this.isFieldValid()) {
			builder.append(((OperatorType) this.comboboxSelectRuleType.getSelectedItem()).label);
			builder.append(':');
			builder.append(this.filteredTextField.getText());
			builder.append(' ');
		} else {
			throw new IllegalStateException("Invalid operator");
		}
	}

	public boolean isFieldValid() {
		return this.filteredTextField.isFieldValid();
	}
}
