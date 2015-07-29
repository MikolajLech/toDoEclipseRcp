package com.starterkit.app.tcp.views;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class ToDoView extends ViewPart {
	private Text text;
	private Button btnDeleteItem;
	private Button btnAddItem;
	private Combo combo;
	private Table table;

	public ToDoView() {
		// TODO Auto-generated constructor stub
	}
	
	private void addItem() {
		if(!("").equals(text.getText())) {
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText(0, "" + table.getItemCount());
			item.setText(1, text.getText());
			if(!combo.getText().equals("")) {
				item.setText(2, combo.getText());
			}
			text.setText("");
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		
		btnAddItem = new Button(parent, SWT.NONE);
		btnAddItem.setText("Add");
		FormData fd_btnAddItem = new FormData();
		fd_btnAddItem.bottom = new FormAttachment(0, 35);
		fd_btnAddItem.right = new FormAttachment(0, 92);
		fd_btnAddItem.top = new FormAttachment(0, 5);
		fd_btnAddItem.left = new FormAttachment(0, 5);
		btnAddItem.setLayoutData(fd_btnAddItem);
		btnAddItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addItem();
			}
		});
		
		
		text = new Text(parent, SWT.BORDER);
		text.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.character == SWT.CR) {
					addItem();
				}
				
			}
		});
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(btnAddItem, 294, SWT.RIGHT);
		fd_text.left = new FormAttachment(btnAddItem, 14);
		fd_text.bottom = new FormAttachment(btnAddItem, 26);
		fd_text.top = new FormAttachment(btnAddItem, 5, SWT.TOP);
		text.setLayoutData(fd_text);
		
		btnDeleteItem = new Button(parent, SWT.NONE);
		btnDeleteItem.setText("Delete");		
		FormData fd_btnDeleteItem = new FormData();
		fd_btnDeleteItem.bottom = new FormAttachment(btnAddItem, 35, SWT.BOTTOM);
		fd_btnDeleteItem.top = new FormAttachment(btnAddItem, 5);
		fd_btnDeleteItem.left = new FormAttachment(0, 5);
		fd_btnDeleteItem.right = new FormAttachment(0, 92);
		btnDeleteItem.setLayoutData(fd_btnDeleteItem);
		btnDeleteItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int itemsNumToDelete = table.getSelectionCount();
				for(int i = 0; i < itemsNumToDelete; i++) {
					if(table.getSelectionIndex() != -1) {
						table.remove(table.getSelectionIndex());
					}
				}
			}
		});
		
		combo = new Combo(parent, SWT.DROP_DOWN);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(0, 386);
		fd_combo.left = new FormAttachment(0, 106);
		combo.setLayoutData(fd_combo);
		combo.setItems(new String [] {"", "Bardzo ważne", "Średnio ważne", "Mało ważne"});
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.getSelection()[0].setText(2, combo.getText());
				combo.deselectAll();
			}
		});
		
		
		final List<Integer> tabItemsToDelete = new ArrayList<Integer>();
		
		table = new Table(parent, SWT.CHECK | SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		fd_combo.top = new FormAttachment(table, 17);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(btnDeleteItem, 356, SWT.RIGHT);
		fd_table.bottom = new FormAttachment(100, -214);
		fd_table.top = new FormAttachment(text, 13);
		fd_table.left = new FormAttachment(btnDeleteItem, 14);
		table.setLayoutData(fd_table);
		String[] titles = {"checkbox", "num", "name", "priority", "finished"};
		int colNum = titles.length;
		for(int i = 0; i < colNum; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(50);
			column.setText(titles[i]);
		}
		int itemNum = 3;
		for (int i=0; i<itemNum; i++) {
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText (1, "" + table.getItemCount());
			item.setText (2, "nowe zadanie");
			item.setText (3, "mało ważne");
		}
		for (int i=0; i<titles.length; i++) {
			table.getColumn(i).pack();
			table.getColumn(i).setMoveable(true);
		}	
//		TableItem[] items = table.getItems();
//		for (int i = 0; i < items.length; i++) {
//		  TableEditor editor = new TableEditor(table);
//		  Button button = new Button(table, SWT.CHECK);
//		  button.pack();
//		  editor.minimumWidth = button.getSize().x;
//		  editor.horizontalAlignment = SWT.CENTER;
//		  editor.setEditor(button, items[i], 4);
//		}
		table.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				String string = event.detail == SWT.CHECK ? "Checked" : "Selected";
				System.out.println(string);
				if(event.detail == SWT.CHECK) {
//					tabItemsToDelete.add(event.index);
//					System.out.println(event.widget.);
				}
			}
		});
		
//		CheckboxTableViewer checkboxTableViewer = new CheckboxTableViewer(table);
//		checkboxTableViewer.setContentProvider(new ArrayContentProvider());
//		
//		checkboxTableViewer.addCheckStateListener(new ICheckStateListener() {
//			
//			@Override
//			public void checkStateChanged(CheckStateChangedEvent event) {
//				System.out.println("qbc");
//				
//			}
//		});
		
		Button btnArchive = new Button(parent, SWT.NONE);
		FormData fd_btnArchive = new FormData();
		fd_btnArchive.bottom = new FormAttachment(btnDeleteItem, 36, SWT.BOTTOM);
		fd_btnArchive.top = new FormAttachment(btnDeleteItem, 6);
		fd_btnArchive.left = new FormAttachment(btnAddItem, 0, SWT.LEFT);
		fd_btnArchive.right = new FormAttachment(btnAddItem, 0, SWT.RIGHT);
		btnArchive.setLayoutData(fd_btnArchive);
		btnArchive.setText("Archive");
		btnArchive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int itemsNumToDelete = table.getSelectionCount();
				for(int i = 0; i < itemsNumToDelete; i++) {
					if(table.getSelectionIndex() != -1) {
						table.remove(table.getSelectionIndex());
					}
				}
//				int itemNum = tabItemsToDelete.size();
//				for(int i = 0; i < itemNum; i++) {
//					table.remove(tabItemsToDelete.get(i));
//				}
//				for(int i = 0; i < table.getItemCount(); i++) {
//					table.getItem(i).setChecked(false);
//				}
//				tabItemsToDelete.clear();
			}
		});
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
