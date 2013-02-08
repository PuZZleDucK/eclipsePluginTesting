package com.puzzleduck.testing.views;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.puzzleduck.testing.model.TodoModel;

public class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

	public Image getImage ( Object element ) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODOne Auto-generated method stub
		return getImage(element);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TodoModel thisTodo = (TodoModel) element;
		return thisTodo.getSummary();
	}


}
