package com.puzzleduck.testing.views;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;

import com.puzzleduck.testing.model.TodoModel;

public class TodoView extends ViewPart {
	private static final String ID = "com.puzzleduck.testing.views.TodoView";
	private TableViewer tViewer;

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		tViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL );
		tViewer.setContentProvider( new ArrayContentProvider() );
		tViewer.setLabelProvider( new ViewLabelProvider() );   // MAY NEED CUSTOM LABEL???
		getSite().setSelectionProvider(tViewer);
		tViewer.setInput( getElements() );
	}

	private TodoModel[] getElements() {
		// TODO Auto-generated method stub
		TodoModel[] todoList = new TodoModel[2];
		todoList[0] = new TodoModel();
		todoList[1] = new TodoModel();
		todoList[0].setDescription("Description 1");
		todoList[1].setDescription("Description 2");
		todoList[0].setSummary("Summary 1");
		todoList[1].setSummary("Summary 2");
		
		return todoList;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		tViewer.getControl().setFocus();
	}




}
