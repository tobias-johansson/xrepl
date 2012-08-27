/*******************************************************************************
 * Copyright (c) 2011 Sebastian Benz and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sebastian Benz - initial API and implementation
 *******************************************************************************/
package org.xtext.scripting.interpreter;

import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.interpreter.IEvaluationContext;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;
import org.xtext.scripting.scripting.Import;
import org.xtext.scripting.scripting.Script;

public class ScriptingInterpreter extends XbaseInterpreter {
	
	
//	public Object _evaluateEPackageImport(XEPackageImport packageUse, IEvaluationContext context, CancelIndicator cancelIndicator) {
//		Object ePackage = EPackage.Registry.INSTANCE.get(packageUse.getNsUri());
//		if(ePackage == null){
//			throw new RuntimeException("Could not resolve EPackage with uri '" + packageUse.getNsUri() + "'");
//		}else{
//			return ePackage;
//		}
//	}
//	

	public Object _evaluateScript(Script literal, IEvaluationContext context, CancelIndicator cancelIndicator) {
		Object result = null;
		for (EObject element : literal.eContents()) {
			if (element instanceof XExpression) {
				XExpression expression = (XExpression) element;
				result = internalEvaluate(expression, context, cancelIndicator);
			} else if (element instanceof Import) {
				Import xImport = (Import) element;
				result = xImport;
			}
		}
		return result;
	}
	
	@Override
	protected Object _evaluateVariableDeclaration(
			XVariableDeclaration variableDecl, IEvaluationContext context,
			CancelIndicator indicator) {
		super._evaluateVariableDeclaration(variableDecl, context, indicator);
		return context.getValue(QualifiedName.create(variableDecl.getName()));
	}
	
//	public Object _evaluateXNewEObject(XNewEObject newEObject, IEvaluationContext context, CancelIndicator cancelIndicator) {
//		EClass type = newEObject.getType();
//		if(type == null || type.eIsProxy()){
//			return null;
//		}
//		return type.getEPackage().getEFactoryInstance().create(type);
//	}
//	
//	public Object _evaluateXImport(XImport xImport, IEvaluationContext context, CancelIndicator cancelIndicator) {
//		return xImport.getImportedNamespace();
//	}

}
