/**
 * 
 */
package com.puzzleduck.testing.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author bminerds
 *
 */
public class AddMarkerHandler extends AbstractHandler {


	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveSite(event).getSelectionProvider().getSelection();
		if ( selection != null ) {
			Object firstSelection = selection.getFirstElement();
			if ( firstSelection instanceof IJavaProject) {
				IJavaProject selectedProject = (IJavaProject) firstSelection;
				writeMarkers(selectedProject);
			}
		} else {
			return null;
		}

		
		return null;
	}  //exec

	private void writeMarkers(IJavaProject selectedProject) {
		// TODO Auto-generated method stub
		try {
			IResource res = selectedProject.getUnderlyingResource();
			IMarker mark = res.createMarker ( IMarker.TASK );
			mark.setAttribute(IMarker.MESSAGE, "Project marker!");
			mark.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			mark.setAttribute(IMarker.LOCATION, "here");
			//mark.setAttribute(IMarker.TEXT, "free text");
			//mark.setAttribute(IMarker.LINE_NUMBER, 7);
			//mark.setAttribute(IMarker.PROBLEM, "i have a problem");
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
