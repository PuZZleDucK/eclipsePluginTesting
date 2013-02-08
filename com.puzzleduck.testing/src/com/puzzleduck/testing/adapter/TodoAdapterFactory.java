package com.puzzleduck.testing.adapter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import com.puzzleduck.testing.model.TodoModel;

public class TodoAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		// TODO Auto-generated method stub
		if ( adapterType == IPropertySource.class && adaptableObject instanceof TodoModel) {
			return new TodoPropertySource( (TodoModel)adaptableObject );
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		// TODO Auto-generated method stub
		return new Class[] { IPropertySource.class };
	}





}
