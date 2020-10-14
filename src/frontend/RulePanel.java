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
public class RulePanel extends JPanel {
	private static final int MAX_OPERATORS = 25;
	
	static final GridBagConstraints relativeGBC = new GridBagConstraints();
	static {
		relativeGBC.gridx = 0;
		relativeGBC.gridy = GridBagConstraints.RELATIVE;
		relativeGBC.anchor = GridBagConstraints.NORTH;
		relativeGBC.weighty = 0.0;
	}
	
	JButton buttonAddNewOperator;
	List<RuleOperatorPanel> operators;
	
	JPanel fillerComp;
	GridBagConstraints gbc_fillerComp;
	
	// Panel enabled only if this value is 0, see `requestEnabled` for details
	int enabledFlag; 
	
	public RulePanel() {
		super();
		
		this.operators = new ArrayList<>(MAX_OPERATORS);
		
		fillerComp = new JPanel();
		gbc_fillerComp = new GridBagConstraints();
		gbc_fillerComp.gridx = 0;
		gbc_fillerComp.gridy = GridBagConstraints.RELATIVE;
		gbc_fillerComp.anchor = GridBagConstraints.NORTH;
		gbc_fillerComp.weighty = 1.0;
		gbc_fillerComp.fill = GridBagConstraints.BOTH;
		
		this.enabledFlag = 0;
		
		this.setLayout(new GridBagLayout());
		
		buttonAddNewOperator = new JButton("Add new Operator");
		buttonAddNewOperator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand() == "Add new Operator") {
					addNewOperator();
				}
			}
		});
		
		this.addNewOperator();
		this.resyncOperators();
	}
	
	public void addNewOperator() {
		RuleOperatorPanel newRule = new RuleOperatorPanel();
		
		this.operators.add(newRule);
		
		this.resyncOperators();
		
		this.operators.get(this.operators.size() - 1).comboboxSelectRuleType.requestFocus();
	}
	
	// Re-add all the operator objects in order
	private void resyncOperators() {
		this.removeAll();
		
		for (RuleOperatorPanel operatorPanel : this.operators) {
			this.add(operatorPanel, relativeGBC);
		}
		if (this.operators.size() < 25) {
			this.add(this.buttonAddNewOperator, relativeGBC);
		}
		
		this.add(this.fillerComp, this.gbc_fillerComp);
		
		this.revalidate();
		this.repaint();
	}

	public void removeRuleOperator(RuleOperatorPanel operatorPanel) {
		if (this.operators.remove(operatorPanel)) {
			this.resyncOperators();
			this.buttonAddNewOperator.requestFocus();
		}
	}
	
	/**
	 * Handles when multiple sources request for this panel to be disabled.
	 * The panel will only be enabled if all sources request for it to be enabled
	 * 
	 * Current source ids (power of twos):
	 * 1:  Stream selector combo box
	 * 2:  Streaming status 
	 * @param idFlag Bit flag, should be power of two and unique per source to ensure independence
	 * @param enabled What the source is requesting the enabled status to be.
	 */
	public void requestSetEnabled(int idFlag, boolean enabled) {
		if (enabled) {
			// Clear bits represented by `idFlag`
			this.enabledFlag &= ~idFlag;
		} else {
			// Set bits represented by `idFlag`
			this.enabledFlag |= idFlag;
		}
		
		this.setEnabled(this.enabledFlag == 0);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		for (Component comp : this.getComponents()) {
			comp.setEnabled(enabled);
		}
	}
	
	// Create one single query object.
	public String buildRule() throws IllegalStateException {
		StringBuilder builder = new StringBuilder();
		
		for (RuleOperatorPanel operator : this.operators) {
			builder.append(operator.toRuleComponent());
		}
		
		if (builder.length() > 512) {
			throw new IllegalStateException("Rule too big:  Delete some operators");
		}
		
		return builder.toString();
	}
	
	// Highlight first invalid rule operator
	public void focusInvalidOperator() {
		for (RuleOperatorPanel operator : this.operators) {
			if (!operator.filteredTextField.isFieldValid()) {
				operator.filteredTextField.validateField();
				operator.filteredTextField.requestFocus();
				return;
			}
		}
	}
}
