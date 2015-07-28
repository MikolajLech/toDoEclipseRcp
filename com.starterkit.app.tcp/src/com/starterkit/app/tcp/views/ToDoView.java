package com.starterkit.app.tcp.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class ToDoView extends ViewPart {
	private Text text;
	private List list;
	private Button btnDeleteItem;
	private Button btnAddItem;
	private Combo combo;
	private Table table;

	public ToDoView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		
		btnAddItem = new Button(parent, SWT.NONE);
		FormData fd_btnAddItem = new FormData();
		fd_btnAddItem.bottom = new FormAttachment(0, 35);
		fd_btnAddItem.right = new FormAttachment(0, 92);
		fd_btnAddItem.top = new FormAttachment(0, 5);
		fd_btnAddItem.left = new FormAttachment(0, 5);
		btnAddItem.setLayoutData(fd_btnAddItem);
		btnAddItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!("").equals(text.getText())) {
					list.add(text.getText());
					TableItem item = new TableItem (table, SWT.NONE);
					item.setText(0, "" + table.getItemCount());
					item.setText(1, text.getText());
				}
			}
		});
		btnAddItem.setText("Add Item");
		
		text = new Text(parent, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(0, 377);
		fd_text.top = new FormAttachment(0, 9);
		fd_text.left = new FormAttachment(0, 97);
		text.setLayoutData(fd_text);
		
		btnDeleteItem = new Button(parent, SWT.NONE);
		FormData fd_btnDeleteItem = new FormData();
		fd_btnDeleteItem.bottom = new FormAttachment(0, 68);
		fd_btnDeleteItem.right = new FormAttachment(0, 92);
		fd_btnDeleteItem.top = new FormAttachment(0, 40);
		fd_btnDeleteItem.left = new FormAttachment(0, 5);
		btnDeleteItem.setLayoutData(fd_btnDeleteItem);
		btnDeleteItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(list.getFocusIndex());
				if(table.getSelectionIndex() != -1) {
					table.remove(table.getSelectionIndex());
				}
			}
		});		
		btnDeleteItem.setText("Delete Item");		
		
		list = new List(parent, SWT.BORDER);
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(text, 159, SWT.BOTTOM);
		fd_list.top = new FormAttachment(btnDeleteItem, 3, SWT.TOP);
		fd_list.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_list.left = new FormAttachment(text, 0, SWT.LEFT);
		list.setLayoutData(fd_list);
		
		combo = new Combo(parent, SWT.DROP_DOWN);
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(list, 16);
		fd_combo.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_combo.left = new FormAttachment(text, 0, SWT.LEFT);
		combo.setLayoutData(fd_combo);
		combo.setItems(new String [] {"Bardzo ważne", "Średnio ważne", "Mało ważne"});
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.getSelection()[0].setText(2, combo.getText());
				combo.deselectAll();
			}
		});
		
		
		table = new Table(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(combo, 211, SWT.BOTTOM);
		fd_table.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_table.top = new FormAttachment(combo, 18);
		fd_table.left = new FormAttachment(text, 0, SWT.LEFT);
		table.setLayoutData(fd_table);
		String[] titles = {"num", "name", "priority"};
		for(int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}
		int count = 3;
		for (int i=0; i<count; i++) {
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText (0, "" + table.getItemCount());
			item.setText (1, "nowe zadanie");
			item.setText (2, "mało ważne");
		}
		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}	
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
