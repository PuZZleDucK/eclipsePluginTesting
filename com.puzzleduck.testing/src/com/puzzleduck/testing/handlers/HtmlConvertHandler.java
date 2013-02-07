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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
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
public class HtmlConvertHandler extends AbstractHandler {

	private QualifiedName path = new QualifiedName("html", "path");

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		Shell thisShell = HandlerUtil.getActiveShell(event);
		ISelection thisSelection = HandlerUtil.getActiveMenuSelection(event);
		IStructuredSelection thisStrucSelection = (IStructuredSelection)thisSelection;
		
		Object firstSelectedElement = thisStrucSelection.getFirstElement();
		if ( firstSelectedElement instanceof ICompilationUnit) {
			createHtmlOutput(thisShell, (ICompilationUnit)firstSelectedElement);
		} else {
			MessageDialog.openInformation(thisShell, "Information", "please select "
					+ "a java file to convert to HTML.");
		}
		
		return null;
	}  //exec

	private void createHtmlOutput ( Shell shell,
			ICompilationUnit compUnit ) {
		// TODO Auto-generated method stub
		String directory;
		IResource res = compUnit.getResource ();
		boolean newDirectory = true;
		directory = getPersistantProperty ( res, path );
		
		if ( directory != null && directory.length() > 0 ) {
			newDirectory = !( MessageDialog.openQuestion ( shell, "Export directory", 
					"Do you wish to use the same directory as last time ("
			        +directory+")?") );
		}
		if ( newDirectory ) {
			DirectoryDialog dirDialog = new DirectoryDialog ( shell ); 
			directory = dirDialog.open();
		}
		if ( directory != null && directory.length() > 0 ) {
			setPersistantProperty(res, path, directory);
			writeOutput(directory, compUnit);
		}
		
	} //create


	private String getPersistantProperty(IResource res, QualifiedName path) {
		// TODO Auto-generated method stub
		try {
			return res.getPersistentProperty ( path );
		} catch ( CoreException e ) {
			return "";
		}
	}

	private void setPersistantProperty(IResource res, QualifiedName path,
			String directory) {
		// TODO Auto-generated method stub
		try {
			res.setPersistentProperty ( path, directory );
		} catch ( CoreException e ) {
			e.printStackTrace();
		}
	}

	private void writeOutput(String directory, ICompilationUnit compUnit) {
		// TODO Auto-generated method stub
		try {
			compUnit.getCorrespondingResource().getName();
			String unitName = compUnit.getCorrespondingResource().getName();
			String[] name = unitName.split("\\.");
			String targetFile = directory + "/" + name[0] + ".html";
			FileWriter fWriter = new FileWriter(targetFile);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write("<HTML> <HEAD> </HEAD> <BODY>");
			IType thisType = compUnit.findPrimaryType();
			bWriter.write("<P> IType: " + thisType.getElementName() +  
				       "("+ thisType.getElementType() + ") </P>");
			IType[] allTypes = compUnit.getAllTypes();
//			bWriter.write("<P> Types: " + allTypes.length + "</P>");

			IImportDeclaration[] impDec = compUnit.getImports();
			bWriter.write("<P> Imports: " + impDec.length + "</P>");
			for ( IImportDeclaration i : impDec ) {
				bWriter.write(" --" + i.getElementName() + "<BR />");
			}			
			
			IJavaElement jElement = compUnit.getPrimaryElement();
			bWriter.write("<P> Primary Element: " 
				       + jElement.getElementName() +  
				       "("+ jElement.getElementType() + ") </P>");
			
			
			

			bWriter.write("<HR /> <PRE>");
			bWriter.write(compUnit.getSource());
			bWriter.write("</PRE> </BODY> </HTML>");
			bWriter.flush();
			bWriter.close();
			
			
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
