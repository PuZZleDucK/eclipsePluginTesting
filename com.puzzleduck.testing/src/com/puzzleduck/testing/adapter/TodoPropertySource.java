package com.puzzleduck.testing.adapter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.eclipse.jface.dialogs.MessageDialog;

import com.puzzleduck.testing.model.TodoModel;

public class TodoPropertySource implements IPropertySource {
	private final TodoModel todo;

	
	public TodoPropertySource(TodoModel adaptableObject) {
		// TODOne Auto-generated constructor stub
		this.todo = adaptableObject;
	}

	@Override
	public Object getEditableValue() {
		// TODOne Auto-generated method stub
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODOne Auto-generated method stub
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor("summary","Summary"),
				new TextPropertyDescriptor("description","Description")};
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if (id.equals("summary")) {
			return todo.getSummary();
		}
		if (id.equals("description")) {
			return todo.getDescription();
			
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODOne... null. Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODOne... empty. Auto-generated method stub
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		String valueString = (String) value;
		if (id.equals("summary")) {
			todo.setSummary(valueString);
		}
		if (id.equals("description")) {
			todo.setDescription(valueString);
		}
	}




}
